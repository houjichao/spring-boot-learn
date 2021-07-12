package com.hjc.learn.convert;


import com.alibaba.fastjson.JSON;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

/**
 *
 * @author houjichao
 */
public class JsonConfigConvert<T> extends BidirectionalConverter<T, String> {
    @Override
    public String convertTo(T source, Type<String> destinationType, MappingContext mappingContext) {
        return JSON.toJSONString(source);
    }
    @Override
    public T convertFrom(String source, Type<T> destinationType, MappingContext mappingContext) {
        return JSON.parseObject(source, destinationType.getRawType());
    }
}
