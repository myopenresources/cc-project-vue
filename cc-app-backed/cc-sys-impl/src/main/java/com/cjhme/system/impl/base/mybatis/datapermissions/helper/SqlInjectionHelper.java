package com.cjhme.system.impl.base.mybatis.datapermissions.helper;

/**
 * 
 * <p>
 * Title: SqlInjectionHelper.java
 * </p>
 * Description: sql注入帮助
 * <p>
 * Modify histoty:
 * 
 * @author
 * @version 1.0
 */
public class SqlInjectionHelper {

	/**
	 * 检查
	 * @param str
	 * @return
	 */
	public static boolean check(String str) {
		String injectionStr = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,|--";
		String[] injectionStrArr = injectionStr.split("\\|");

		for (int i = 0; i < injectionStrArr.length; i++) {
			if (str.indexOf(injectionStrArr[i]) !=-1) {
				return true;
			}
		}
		return false;
	}

}
