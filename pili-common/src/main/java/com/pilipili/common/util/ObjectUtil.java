package com.pilipili.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

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
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T objectConvertToClass(Object data, Class<T> clazz) {
        try {
            return mapper.convertValue(data, clazz);
        }catch (Exception e) {
            log.error("解析数据异常",e);
        }
        return null;
    }

    /**
     * 随机获取list中n个值
     * @param list
     * @param generateNum
     * @param <T>
     * @return
     */
    public static <T> List<T> generateRandomDataNoRepeat(List<T> list,Integer generateNum){
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

}
