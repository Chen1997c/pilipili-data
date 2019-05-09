package com.pilipili.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pilipili.common.exception.ResolveDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * 描述： 类型转换工具类
 *
 * @author ChenJianChuan
 * @date 2019/3/6　14:42
 */
@Slf4j
public class ObjectUtil {

    private final static ObjectMapper mapper = new ObjectMapper();

    /**
     * Object 转 实体类
     *
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T objectConvertToClass(Object data, Class<T> clazz) {
        try {
            return mapper.convertValue(data, clazz);
        } catch (Exception e) {
            log.error("解析数据异常", e);
        }
        return null;
    }

    /**
     * 随机获取list中n个值
     *
     * @param list
     * @param generateNum
     * @param <T>
     * @return
     */
    public static <T> List<T> generateRandomDataNoRepeat(List<T> list, Integer generateNum) {
        Map map = new HashMap(16);
        List<T> listNew = new ArrayList<>();
        if (list.size() <= generateNum || CollectionUtils.isEmpty(list)) {
            return list;
        } else {
            while (map.size() < generateNum) {
                int random = (int) (Math.random() * list.size());
                if (!map.containsKey(random)) {
                    map.put(random, "");
                    listNew.add(list.get(random));
                }
            }
        }
        return listNew;
    }

    /**
     * 将List<Object[]> 转换为 List<实体>
     *
     * @param objects
     * @param target
     * @param <T>
     * @return
     * @throws ClassNotFoundException
     */
    public static <T> List<T> transArrayToClass(List<Object[]> objects, Class<T> target) {
        if (CollectionUtils.isEmpty(objects)) {
            return null;
        }
        Field[] declaredFields = target.getDeclaredFields();
        if (CollectionUtils.isEmpty(Arrays.asList(declaredFields))) {
            return null;
        }
        if (declaredFields.length < objects.get(0).length) {
            throw new ResolveDataException("指定实体类字段数量小于数据长度,无法转换");
        }
        List<T> list = new ArrayList<>();
        objects.forEach(object -> {
            T t ;
            try {
                t =  target.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new ResolveDataException("创建实体实例失败",e);
            }
            for (int i = 0; i < object.length; i++) {
                declaredFields[i].setAccessible(true);
                if (object[i] != null) {
                    try {
                        if (BigInteger.class.getTypeName().contains(object[i].getClass().getName())) {
                            if (Integer.class.getTypeName().contains(declaredFields[i].getType().getName())) {
                                declaredFields[i].set(t, ((BigInteger) object[i]).intValue());
                            } else {
                                declaredFields[i].set(t, ((BigInteger) object[i]).longValue());
                            }
                        } else if(BigDecimal.class.getTypeName().contains(object[i].getClass().getName())) {
                            declaredFields[i].set(t, ((BigDecimal) object[i]).doubleValue());
                        }
                        else {
                            declaredFields[i].set(t, object[i]);
                        }
                    }catch (IllegalAccessException e) {
                        throw new ResolveDataException("实体属性赋值失败",e);
                    }
                }
            }
            list.add(t);
        });
        return list;
    }

}
