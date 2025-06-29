package com.lau56.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lau56.lease.model.entity.FeeKey;
import com.lau56.lease.web.admin.mapper.FeeKeyMapper;
import com.lau56.lease.web.admin.service.FeeKeyService;
import com.lau56.lease.web.admin.vo.fee.FeeKeyVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【fee_key(杂项费用名称表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class FeeKeyServiceImpl extends ServiceImpl<FeeKeyMapper, FeeKey>
    implements FeeKeyService{

    @Resource
    private FeeKeyMapper feeKeyMapper;
    @Override
    public List<FeeKeyVo> feeInfoList() {
        return feeKeyMapper.feeInfoList();
    }
}




