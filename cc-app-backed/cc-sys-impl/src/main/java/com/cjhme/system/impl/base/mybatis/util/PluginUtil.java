package com.cjhme.system.impl.base.mybatis.util;


import java.lang.reflect.Proxy;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.reflection.MetaObject;

public final  class PluginUtil {

    private PluginUtil() {}

    /**
     * 获得真正的处理对象,可能多层代理.
     */
    @SuppressWarnings("unchecked")
    public static <T> T realTarget(Object target) {
        if (Proxy.isProxyClass(target.getClass())) {
            MetaObject metaObject = MetaObject.forObject(target);
            return realTarget(metaObject.getValue("h.target"));
        }
        return (T) target;
    }

    /**
     * 根据 key 获取 Properties 的值
     */
    public static String getProperty(Properties properties, String key) {
        String value = properties.getProperty(key);
        return StringUtils.isBlank(value) ? null : value;
    }
}
