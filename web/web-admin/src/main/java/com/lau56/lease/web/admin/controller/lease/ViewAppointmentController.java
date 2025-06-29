package com.lau56.lease.web.admin.controller.lease;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lau56.lease.common.result.Result;
import com.lau56.lease.model.enums.AppointmentStatus;
import com.lau56.lease.web.admin.vo.appointment.AppointmentQueryVo;
import com.lau56.lease.web.admin.vo.appointment.AppointmentVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


@Tag(name = "预约看房管理")
@RequestMapping("/admin/appointment")
@RestController
public class ViewAppointmentController {

    @Operation(summary = "分页查询预约信息")
    @GetMapping("page")
    public Result<IPage<AppointmentVo>> page(@RequestParam long current, @RequestParam long size, AppointmentQueryVo queryVo) {
        return Result.ok();
    }

    @Operation(summary = "根据id更新预约状态")
    @PostMapping("updateStatusById")
    public Result updateStatusById(@RequestParam Long id, @RequestParam AppointmentStatus status) {
        return Result.ok();
    }

}
