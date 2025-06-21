package com.lau56.lease.common.exception;

import com.lau56.lease.common.result.ResultCodeEnum;
import lombok.Data;

/**
 * @author Michael
 * @date 2025/6/22 00:35
 * @description 自定义Lease通用异常
 */

@Data
public class LeaseException extends RuntimeException{
    private Integer code;
    public LeaseException(Integer code,String message){
        super(message);
        this.code=code;
    }
    public LeaseException(ResultCodeEnum codeEnum){
        super(codeEnum.getMessage());
        this.code=codeEnum.getCode();
    }
}
