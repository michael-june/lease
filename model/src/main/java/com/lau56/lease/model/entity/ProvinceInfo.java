package com.lau56.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "省份信息表")
@TableName(value = "province_info")
@Data
public class ProvinceInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "省份名称")
    @TableField(value = "name")
    private String name;

}