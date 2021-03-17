package com.cjhme.common.util;

import java.util.UUID;

/**
 *
 * <p>
 * Title: UUIDUtil.java
 * </p>
 * Description: UUID工具类
 * <p>
 * Modify histoty:
 * @author  cjh
 * @version 1.0
 */
public class UUIDUtil {

    /**
     * 创建32位UUID
     * @return
     */
    public static String createUUID32(){
        return  UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }


}
