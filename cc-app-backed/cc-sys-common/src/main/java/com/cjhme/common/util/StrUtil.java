package com.cjhme.common.util;

/**
 * 
 * <p>
 * Title: StrUtil.java
 * </p>
 * Description: 字符串处理类
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public class StrUtil {

	/**
	 * 如果超过19位，返回19位的子串
	 * 
	 * @param str
	 * @return
	 */
	public static String subStrNineteenth(String str) {
		if (null != str && !str.equals("") && str.length() > 19) {
			return str.substring(0, 19);
		}
		return str;
	}

	/**
	 * 如果超过19位，返回10位的子串
	 * 
	 * @param str
	 * @return
	 */
	public static String subStrTen(String str) {
		if (null != str && !str.equals("") && str.length() > 10) {
			return str.substring(0, 10);
		}
		return str;
	}

	/**
	 * 如果是null，返回"0"
	 * 
	 * @param str
	 * @return
	 */
	public static String nullToNum(String str) {
		if (null == str || str.equals("")) {
			return "0";
		}
		return str;

	}

	/**
	 * 如果是null，返回指定的字符
	 * 
	 * @param str
	 * @param toStr
	 * @return
	 */
	public static String nulToStr(String str, String toStr) {
		if (null == str || "".equals(str) || "null".equals(str)) {
			return toStr;
		}
		return str;
	}

	/**
	 * 如果是null，返回""
	 * 
	 * @param str
	 * @param toStr
	 * @return
	 */
	public static String nulToStr(String str) {
		return nulToStr(str, "");
	}
}
