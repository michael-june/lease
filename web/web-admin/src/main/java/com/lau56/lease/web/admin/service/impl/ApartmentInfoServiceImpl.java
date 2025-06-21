package com.lau56.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lau56.lease.common.exception.LeaseException;
import com.lau56.lease.common.result.ResultCodeEnum;
import com.lau56.lease.model.entity.*;
import com.lau56.lease.model.enums.ItemType;
import com.lau56.lease.web.admin.mapper.*;
import com.lau56.lease.web.admin.service.*;
import com.lau56.lease.web.admin.vo.apartment.ApartmentDetailVo;
import com.lau56.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.lau56.lease.web.admin.vo.apartment.ApartmentQueryVo;
import com.lau56.lease.web.admin.vo.apartment.ApartmentSubmitVo;
import com.lau56.lease.web.admin.vo.fee.FeeValueVo;
import com.lau56.lease.web.admin.vo.graph.GraphVo;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liubo
 * @description 针对表【apartment_info(公寓信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class ApartmentInfoServiceImpl extends ServiceImpl<ApartmentInfoMapper, ApartmentInfo>
        implements ApartmentInfoService {
    @Resource
    private GraphInfoService graphInfoService;
    @Resource
    private ApartmentFacilityService apartmentFacilityService;
    @Resource
    private ApartmentLabelService apartmentLabelService;
    @Resource
    private ApartmentFeeValueService feeValueService;
    @Resource
    private ApartmentInfoMapper apartmentInfoMapper;
    @Resource
    private GraphInfoMapper graphInfoMapper;
    @Resource
    private LabelInfoMapper labelInfoMapper;
    @Resource
    private FacilityInfoMapper facilityInfoMapper;
    @Resource
    private FeeValueMapper feeValueMapper;
    @Resource
    private RoomInfoMapper roomInfoMapper;

    @Override
    public void saveOrUpdateApartment(ApartmentSubmitVo apartmentSubmitVo) {
        boolean isUpdate = apartmentSubmitVo.getId() != null;
        super.saveOrUpdate(apartmentSubmitVo);
        if (isUpdate) {
//            1. 删除图片列表
            LambdaQueryWrapper<GraphInfo> graphQw = new LambdaQueryWrapper<>();
            graphQw.eq(GraphInfo::getItemType, ItemType.APARTMENT)
                    .eq(GraphInfo::getItemId, apartmentSubmitVo.getId());
            graphInfoService.remove(graphQw);
//            2. 删除配套列表
            LambdaQueryWrapper<ApartmentFacility> facilityQw = new LambdaQueryWrapper<>();
            facilityQw.eq(ApartmentFacility::getApartmentId, apartmentSubmitVo.getId());
            apartmentFacilityService.remove(facilityQw);
//            3. 删除标签列表
            LambdaQueryWrapper<ApartmentLabel> labelQw = new LambdaQueryWrapper<>();
            labelQw.eq(ApartmentLabel::getApartmentId, apartmentSubmitVo.getId());
            apartmentLabelService.remove(labelQw);
//            4. 删除杂费列表
            LambdaQueryWrapper<ApartmentFeeValue> feeValueQw = new LambdaQueryWrapper<>();
            feeValueQw.eq(ApartmentFeeValue::getApartmentId, apartmentSubmitVo.getId());
            feeValueService.remove(feeValueQw);
        }

//            1. 插入图片列表
        List<GraphVo> graphVoList = apartmentSubmitVo.getGraphVoList();
        if (!CollectionUtils.isEmpty(graphVoList)) {
            ArrayList<GraphInfo> graphInfoList = new ArrayList<>();
            for (GraphVo graphVo : graphVoList) {
                GraphInfo graphInfo = GraphInfo.builder()
                        .itemType(ItemType.APARTMENT)
                        .itemId(apartmentSubmitVo.getId())
                        .name(graphVo.getName())
                        .url(graphVo.getUrl())
                        .build();
                graphInfoList.add(graphInfo);
            }
            graphInfoService.saveBatch(graphInfoList);
        }
//            2. 插入配套列表
        List<Long> facilityInfoIdList = apartmentSubmitVo.getFacilityInfoIds();
        if (!CollectionUtils.isEmpty(facilityInfoIdList)) {
            ArrayList<ApartmentFacility> facilityList = new ArrayList<>();
            for (Long facilityId : facilityInfoIdList) {
                ApartmentFacility apartmentFacility = ApartmentFacility.builder()
                        .apartmentId(apartmentSubmitVo.getId())
                        .facilityId(facilityId)
                        .build();
                facilityList.add(apartmentFacility);
            }
            apartmentFacilityService.saveBatch(facilityList);
        }
//            3. 插入标签列表
        List<Long> labelIds = apartmentSubmitVo.getLabelIds();
        if (!CollectionUtils.isEmpty(labelIds)) {
            List<ApartmentLabel> apartmentLabelList = new ArrayList<>();
            for (Long labelId : labelIds) {
                ApartmentLabel apartmentLabel = ApartmentLabel.builder()
                        .apartmentId(apartmentSubmitVo.getId())
                        .labelId(labelId)
                        .build();
                apartmentLabelList.add(apartmentLabel);
            }
            apartmentLabelService.saveBatch(apartmentLabelList);
        }
//            4. 插入杂费列表
        List<Long> feeValueIds = apartmentSubmitVo.getFeeValueIds();
        if (!CollectionUtils.isEmpty(feeValueIds)) {
            ArrayList<ApartmentFeeValue> apartmentFeeValueList = new ArrayList<>();
            for (Long feeValueId : feeValueIds) {
                ApartmentFeeValue apartmentFeeValue = ApartmentFeeValue.builder()
                        .apartmentId(apartmentSubmitVo.getId())
                        .feeValueId(feeValueId)
                        .build();
                apartmentFeeValueList.add(apartmentFeeValue);
            }
            feeValueService.saveBatch(apartmentFeeValueList);
        }
    }

    @Override
    public IPage<ApartmentItemVo> pageItem(Page<ApartmentItemVo> page, ApartmentQueryVo queryVo) {
        return apartmentInfoMapper.pageItem(page, queryVo);
    }

    @Override
    public ApartmentDetailVo getDetailById(Long id) {
//        1. 查询公寓信息
        ApartmentInfo apartmentInfo = apartmentInfoMapper.selectById(id);
//        2. 查询图片列表
        List<GraphVo> graphVoList = graphInfoMapper.selectListByItemTypeAndId(ItemType.APARTMENT, id);
//        3. 查询标签列表
        List<LabelInfo> labelInfoList=labelInfoMapper.selectListByApartmentId(id);
//        4. 查询配套列表
        List<FacilityInfo> facilityInfoList = facilityInfoMapper.selectListByApartmentId(id);
//        5. 查询杂费列表
        List<FeeValueVo> feeValueVoList= feeValueMapper.selectListByApartmentId(id);
//        6. 组装结果
        ApartmentDetailVo apartmentDetailVo = new ApartmentDetailVo();
        BeanUtils.copyProperties(apartmentInfo, apartmentDetailVo);
        apartmentDetailVo.setGraphVoList(graphVoList);
        apartmentDetailVo.setLabelInfoList(labelInfoList);
        apartmentDetailVo.setFacilityInfoList(facilityInfoList);
        apartmentDetailVo.setFeeValueVoList(feeValueVoList);
        return apartmentDetailVo;
    }

    @Override
    public void removeApartmentById(Long id) {
//        查询公寓下房间数量
        LambdaQueryWrapper<RoomInfo> rQw = new LambdaQueryWrapper<>();
        rQw.eq(RoomInfo::getApartmentId,id);
        Long count = roomInfoMapper.selectCount(rQw);
        if(count>0){
//            停止删除,给出响应
            throw new LeaseException(ResultCodeEnum.ADMIN_APARTMENT_DELETE_ERROR);
        }

//        删除apartment_info表数据
        super.removeById(id);
//        1. 删除图片列表
        LambdaQueryWrapper<GraphInfo> graphQw = new LambdaQueryWrapper<>();
        graphQw.eq(GraphInfo::getItemType, ItemType.APARTMENT)
                .eq(GraphInfo::getItemId, id);
        graphInfoService.remove(graphQw);
//            2. 删除配套列表
        LambdaQueryWrapper<ApartmentFacility> facilityQw = new LambdaQueryWrapper<>();
        facilityQw.eq(ApartmentFacility::getApartmentId, id);
        apartmentFacilityService.remove(facilityQw);
//            3. 删除标签列表
        LambdaQueryWrapper<ApartmentLabel> labelQw = new LambdaQueryWrapper<>();
        labelQw.eq(ApartmentLabel::getApartmentId, id);
        apartmentLabelService.remove(labelQw);
//            4. 删除杂费列表
        LambdaQueryWrapper<ApartmentFeeValue> feeValueQw = new LambdaQueryWrapper<>();
        feeValueQw.eq(ApartmentFeeValue::getApartmentId, id);
        feeValueService.remove(feeValueQw);

    }
}




