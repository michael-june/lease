package com.lau56.lease.web.admin.controller.lease;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lau56.lease.common.result.Result;
import com.lau56.lease.model.entity.LeaseAgreement;
import com.lau56.lease.model.enums.LeaseStatus;
import com.lau56.lease.web.admin.vo.agreement.AgreementQueryVo;
import com.lau56.lease.web.admin.vo.agreement.AgreementVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


@Tag(name = "租约管理")
@RestController
@RequestMapping("/admin/agreement")
public class LeaseAgreementController {

    @Operation(summary = "保存或修改租约信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody LeaseAgreement leaseAgreement) {
        return Result.ok();
    }

    @Operation(summary = "根据条件分页查询租约列表")
    @GetMapping("page")
    public Result<IPage<AgreementVo>> page(@RequestParam long current, @RequestParam long size, AgreementQueryVo queryVo) {
        return Result.ok();
    }

    @Operation(summary = "根据id查询租约信息")
    @GetMapping(name = "getById")
    public Result<AgreementVo> getById(@RequestParam Long id) {
        return Result.ok();
    }

    @Operation(summary = "根据id删除租约信息")
    @DeleteMapping("removeById")
    public Result removeById(@RequestParam Long id) {
        return Result.ok();
    }

    @Operation(summary = "根据id更新租约状态")
    @PostMapping("updateStatusById")
    public Result updateStatusById(@RequestParam Long id, @RequestParam LeaseStatus status) {
        return Result.ok();
    }

}

