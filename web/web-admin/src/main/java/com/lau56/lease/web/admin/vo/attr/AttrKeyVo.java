package com.lau56.lease.web.admin.vo.attr;

import com.lau56.lease.model.entity.AttrKey;
import com.lau56.lease.model.entity.AttrValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;


@Data
public class AttrKeyVo extends AttrKey {

    @Schema(description = "属性value列表")
    private List<AttrValue> attrValueList;
}
