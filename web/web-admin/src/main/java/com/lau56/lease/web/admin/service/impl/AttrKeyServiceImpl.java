package com.lau56.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lau56.lease.model.entity.AttrKey;
import com.lau56.lease.web.admin.mapper.AttrKeyMapper;
import com.lau56.lease.web.admin.service.AttrKeyService;
import com.lau56.lease.web.admin.vo.attr.AttrKeyVo;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【attr_key(房间基本属性表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
@AllArgsConstructor
public class AttrKeyServiceImpl extends ServiceImpl<AttrKeyMapper, AttrKey> implements AttrKeyService{

    @Resource
    private final AttrKeyMapper attrKeyMapper;

    @Override
    public List<AttrKeyVo> listAttrInfo() {
        return attrKeyMapper.listAttrInfo();
    }
}




