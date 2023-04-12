package com.jeesite.modules.wms.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @Author christina
 * @Date 2022/12/19 10:49
 */
@Slf4j
public class ValidUtil {
    @SneakyThrows
    public static<T> String valid(T entity){
        StringBuilder builder = new StringBuilder();
        Class<?> clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            //非空校验
            if (field.isAnnotationPresent(NotNull.class)){
                Type type = field.getGenericType();
                NotNull annotation = field.getAnnotation(NotNull.class);
                field.setAccessible(true);
                Object value = field.get(entity);
                Class<?> fieldType = Class.forName(type.getTypeName());
                //值为null则拼接异常信息
                if (value == null){
                    builder.append("," + annotation.message());
                    continue;
                }
                //string类型判断空字符串
                if (fieldType.isAssignableFrom(String.class)) {
                    if (StringUtils.isBlank(String.valueOf(value))){
                        builder.append("," + annotation.message());
                    }
                }
                //list判断空
                else if (fieldType.isAssignableFrom(List.class)){
                    if (((List)value).isEmpty()){
                        builder.append("," + annotation.message());
                    }
                }
            }
        }
        return builder.toString().replaceFirst(",", "");
    }
}
