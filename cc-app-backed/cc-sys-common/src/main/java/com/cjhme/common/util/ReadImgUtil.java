package com.cjhme.common.util;

import java.io.File;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 图片读取
 * <p>
 * Title: ReadImgUtil.java
 * </p>
 * Description:
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public class ReadImgUtil {
	static Log LOGGER = LogFactory.getLog(ReadImgUtil.class);
	

	/**
	 * 图片读取
	 * @param response
	 * @param filePath
	 * @param fileName
	 * @param fileSuffix
	 */
	public static void readImgToResponst(HttpServletResponse response,String filePath,String fileName,String fileSuffix){
		File file=new File(filePath);
		try (
		   FileImageInputStream  fs = new FileImageInputStream (file);	
		) {
			int streamLength = (int)fs.length();
			byte[] image = new byte[streamLength];
			fs.read(image,0,streamLength );
			response.setHeader("Content-Type","application/octet-stream");
			response.setHeader("Content-Disposition","attachment;filename="+fileName+fileSuffix);
			response.getOutputStream().write(image);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("图片读取失败,错误——>"+e);
	    }
		
	}

}
