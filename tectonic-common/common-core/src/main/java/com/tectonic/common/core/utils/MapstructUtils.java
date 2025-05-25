package com.tectonic.common.core.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import io.github.linpeilie.Converter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Mapstruct 工具类
 * <p>参考文档：<a href="https://mapstruct.plus/introduction/quick-start.html">mapstruct-plus</a></p>
 *
 * @author xiaoyan.song
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MapstructUtils {

    private final static Converter CONVERTER = SpringUtils.getBean(Converter.class);

    /**
     * 将 T 类型对象，转换为 target 类型的对象并返回
     *
     * @param source 数据来源实体
     * @param target 转换后的对象
     * @return target
     */
    public static <T, V> V convert(T source, Class<V> target) {
        if (ObjectUtil.isNull(source)) {
            return null;
        }
        if (ObjectUtil.isNull(target)) {
            return null;
        }
        return CONVERTER.convert(source, target);
    }

    /**
     * 将 T 类型对象，按照配置的映射字段规则，给 target 类型的对象赋值并返回 target 对象
     *
     * @param source 数据来源实体
     * @param target 转换后的对象
     * @return target
     */
    public static <T, V> V convert(T source, V target) {
        if (ObjectUtil.isNull(source)) {
            return null;
        }
        if (ObjectUtil.isNull(target)) {
            return null;
        }
        return CONVERTER.convert(source, target);
    }

    /**
     * 将 T 类型的集合，转换为 target 类型的集合并返回
     *
     * @param sourceList 数据来源实体列表
     * @param target     描述对象 转换后的对象
     * @return target
     */
    public static <T, V> List<V> convert(List<T> sourceList, Class<V> target) {
        if (ObjectUtil.isNull(sourceList)) {
            return null;
        }
        if (CollUtil.isEmpty(sourceList)) {
            return CollUtil.newArrayList();
        }
        return CONVERTER.convert(sourceList, target);
    }

    /**
     * 将 Map 转换为 beanClass 类型的集合并返回
     *
     * @param map       数据来源
     * @param beanClass bean类
     * @return bean对象
     */
    public static <T> T convert(Map<String, Object> map, Class<T> beanClass) {
        if (MapUtil.isEmpty(map)) {
            return null;
        }
        if (ObjectUtil.isNull(beanClass)) {
            return null;
        }
        return CONVERTER.convert(map, beanClass);
    }

}
