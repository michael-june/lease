package com.lau56.lease.web.admin.controller.apartment;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lau56.lease.common.result.Result;
import com.lau56.lease.model.entity.CityInfo;
import com.lau56.lease.model.entity.DistrictInfo;
import com.lau56.lease.model.entity.ProvinceInfo;
import com.lau56.lease.web.admin.service.CityInfoService;
import com.lau56.lease.web.admin.service.DistrictInfoService;
import com.lau56.lease.web.admin.service.ProvinceInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "地区信息管理")
@RestController
@RequestMapping("/admin/region")
public class RegionInfoController {
    @Resource
    private ProvinceInfoService provinceService;
    @Resource
    private CityInfoService cityService;
    @Resource
    private DistrictInfoService districtService;

    @Operation(summary = "查询省份信息列表")
    @GetMapping("province/list")
    public Result<List<ProvinceInfo>> listProvince() {
        List<ProvinceInfo> list = provinceService.list();
        return Result.ok(list);
    }

    @Operation(summary = "根据省份id查询城市信息列表")
    @GetMapping("city/listByProvinceId")
    public Result<List<CityInfo>> listCityInfoByProvinceId(@RequestParam Long id) {
        LambdaQueryWrapper<CityInfo> qw = new LambdaQueryWrapper<>();
        qw.eq(CityInfo::getProvinceId,id);
        List<CityInfo> list = cityService.list(qw);
        return Result.ok(list);
    }

    @GetMapping("district/listByCityId")
    @Operation(summary = "根据城市id查询区县信息")
    public Result<List<DistrictInfo>> listDistrictInfoByCityId(@RequestParam Long id)  {
        LambdaQueryWrapper<DistrictInfo> qw = new LambdaQueryWrapper<>();
        qw.eq(DistrictInfo::getCityId, id);
        List<DistrictInfo> list = districtService.list(qw);
        return Result.ok(list);
    }

}
