package com.lau56.lease.web.admin.custom.converter;

import com.lau56.lease.model.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * @author Michael
 * @date 2025/6/7 11:46
 * @description String-BaseEnum类型转换器
 */

@Component
public class StringToBaseEnumConverterFactory implements ConverterFactory<String, BaseEnum> {

    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new Converter<String, T>() {
            @Override
            public T convert(String code) {
                T[] enumConstants = targetType.getEnumConstants();
                for (T enumConstant : enumConstants) {
                    if (enumConstant.getCode().toString().equals(code)) {
                        return enumConstant;
                    }
                }
                throw new IllegalArgumentException("code:" + code + "非法,无法转换为" + targetType.getSimpleName());
            }
        };
    }

}
