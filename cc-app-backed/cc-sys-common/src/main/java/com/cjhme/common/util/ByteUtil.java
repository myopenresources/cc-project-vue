package com.cjhme.common.util;

/**
 * 
 * <p>
 * Title: ByteUtil.java
 * </p>
 * Description: 字节工具类
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public class ByteUtil {

	/**
	 * 字节转换
	 * 
	 * @param size
	 *            字节大小
	 * @return 转换后值
	 */
	public static final String convertSizeToStr(long size) {
		long kb = 1024;
		long mb = kb * 1024;
		long gb = mb * 1024;
		if (size >= gb) {
			return String.format("%.1f GB", (float) size / gb);
		} else if (size >= mb) {
			float f = (float) size / mb;
			return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
		} else if (size >= kb) {
			float f = (float) size / kb;
			return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
		} else {
			return String.format("%d B", size);
		}
	}
}
