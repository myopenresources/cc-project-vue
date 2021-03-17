package com.cjhme.common.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * <p>  
 * Title: PrintData.java 
 * </p>  
 * Description: 输出参数
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
public class PrintData {

	/**
	 * 输入数据
	 * @param response response对象
	 * @param contentType 内容类型
	 * @param encode 编码
	 * @param data 数据
	 */
	public static void printData(HttpServletResponse response, String contentType, String encode, Object data) {
		response.setCharacterEncoding(encode);
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType(contentType);
		PrintWriter writer = null;
		try {
			writer=response.getWriter();
			writer.print(data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
		
	}
	
	
	/**
	 * 输入数据
	 * @param response
	 * @param data
	 */
	public static void printData(HttpServletResponse response,String contentType,Object data) {
		PrintData.printData(response, contentType, "utf-8", data);
	}

}
