package com.lau56.lease.web.admin.controller.apartment;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lau56.lease.common.result.Result;
import com.lau56.lease.model.entity.AttrKey;
import com.lau56.lease.model.entity.AttrValue;
import com.lau56.lease.web.admin.service.AttrKeyService;
import com.lau56.lease.web.admin.service.AttrValueService;
import com.lau56.lease.web.admin.vo.attr.AttrKeyVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@Tag(name = "房间属性管理")
@RestController
@RequestMapping("/admin/attr")
@Transactional
public class AttrController {

    @Autowired
    private AttrKeyService attrKeyService;
    @Autowired
    private AttrValueService attrValueService;

    @Operation(summary = "新增或更新属性名称")
    @PostMapping("key/saveOrUpdate")
    public Result saveOrUpdateAttrKey(@RequestBody AttrKey attrKey) {
        attrKeyService.saveOrUpdate(attrKey);
        return Result.ok();

    }

    @Operation(summary = "新增或更新属性值")
    @PostMapping("value/saveOrUpdate")
    public Result saveOrUpdateAttrValue(@RequestBody AttrValue attrValue) {
        attrValueService.saveOrUpdate(attrValue);
        return Result.ok();
    }


    @Operation(summary = "查询全部属性名称和属性值列表")
    @GetMapping("list")
    public Result<List<AttrKeyVo>> listAttrInfo() {
        List<AttrKeyVo> list = attrKeyService.listAttrInfo();
        return Result.ok(list);
    }

    @Operation(summary = "根据id删除属性名称")
    @DeleteMapping("key/deleteById")
    public Result removeAttrKeyById(@RequestParam Long attrKeyId) {
//        删除属性名称
        attrKeyService.removeById(attrKeyId);
//        删除属性值
        attrValueService.update(
                new UpdateWrapper<AttrValue>().lambda()
                        .eq(AttrValue::getAttrKeyId, attrKeyId)
                        .set(AttrValue::getUpdateTime, new Date()) // 逻辑删除
                        .set(AttrValue::getIsDeleted, 1) // 逻辑删除
        );
        return Result.ok();
    }

    @Operation(summary = "根据id删除属性值")
    @DeleteMapping("value/deleteById")
    public Result removeAttrValueById(@RequestParam Long id) {
        attrValueService.removeById(id);
        return Result.ok();
    }

}
