package com.cjhme.common.util;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cjhme.common.constant.CommonConstants;
import com.cjhme.common.model.base.ResultData;
import com.cjhme.common.model.fileupload.FileDataBean;

/**
 * 
 * <p>  
 * Title: UploadImgUtil.java 
 * </p>  
 * Description: 图片上传
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
public class UploadImgUtil {
	
	static Log LOGGER = LogFactory.getLog(UploadImgUtil.class);
	

	/**
	 * 文件上传
	 * @param request request请求
	 * @param formFileName 表单中的file对象名
	 * @param dir   存在目录
	 * @return
	 */
	public static ResultData upload(HttpServletRequest request,String formFileName,String dir,boolean isNewFileName){
		ResultData resultData=new ResultData();
		//图片存放的url
		String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+dir;
		
		
		//获得存储的目录
		
		String dirPath = new File(request.getSession().getServletContext().getRealPath("")).getParent()+dir;
		
		//转换为MultipartHttpRequest对象
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
	
		//实例化File对象
		File mdFile = new File(dirPath);
		
		//如果目录不存在，创建一个目录
		if(!mdFile.exists()){
			mdFile.mkdirs();
		}
		
		//页面控件的文件流
		MultipartFile multipartFile = multipartRequest.getFile(formFileName);
		
		//判断上传的文件是否为空
		if(!multipartFile.isEmpty()){
		
			//判断是否需要重新生成文件名
			String fileName="";
			if(isNewFileName){
				String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
				fileName =  UUID.randomUUID().toString()+ suffix;
			}else{
				fileName=multipartFile.getOriginalFilename();
			}
			
			//拼接文件路径
			String filePath = dirPath +File.separator + fileName;
			
			//实例化File对象
			File savaFile  = new File(filePath);
			
		    try {
				//写入文件
				multipartFile.transferTo(savaFile);
				
				url+=fileName;
				
				
				FileDataBean fileData = new FileDataBean();
				fileData.setFileName(fileName);
				fileData.setFileUrl(url);
				
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultMsg("文件上传成功！");
				resultData.setResultData(fileData);
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
				
				resultData.setResultType(CommonConstants.RESULT_ERROR);
				resultData.setResultMsg("文件上传失败！");
				
				LOGGER.error("文件上传失败,错误——>"+e);
			}
			
		}
		
		return resultData;
	}
	
	
	/**
	 * 图片上传压缩处理
	 * @param request request请求
	 * @param formFileName 表单中的file对象名
	 * @param dir   存在目录
	 * @param sign 是否压缩 0正常，1压缩
	 * @param width 压缩指定宽度，无压缩时传0
	 * @param height 压缩指定高度，无压缩时传0
	 * @return
	 */
	public static ResultData uploadImgCompression(HttpServletRequest request,String formFileName,String dir,String sign,int width,int height,String isNewFileName){
		ResultData resultData=new ResultData();
		
		//图片存放的url
		String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+dir;
		
		//获得存储的目录
		String dirPath = new File(request.getSession().getServletContext().getRealPath("")).getParent()+dir;
		
		//转换为MultipartHttpRequest对象
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
	
		//实例化File对象
		File mdFile = new File(dirPath);
		
		//如果目录不存在，创建一个目录
		if(!mdFile.exists()){
			mdFile.mkdirs();
		}
		
		//页面控件的文件流
		MultipartFile multipartFile = multipartRequest.getFile(formFileName);
		
		//判断上传的文件是否为空
		if(!multipartFile.isEmpty()){
		
			//判断是否需要重新生成文件名
			String fileName="";
			if(isNewFileName.equals("Y")){
				String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
				fileName =  UUID.randomUUID().toString()+ suffix;
			}else{
				fileName=multipartFile.getOriginalFilename();
			}
				
			//拼接文件路径,以UUID生成文件名
			String filePath = dirPath +File.separator + fileName;
			
			
			
		    try {
		    	
		    	//判断是否压缩
		    	if(sign.equals("Y")){
		    		PhotoCompression.photoAllCompression(multipartFile.getInputStream(), filePath, width,height);
		    	}else{
		    		//实例化File对象
					File savaFile  = new File(filePath);
					//写入文件
					multipartFile.transferTo(savaFile);
		    	}
				
				url+=fileName;
				
				FileDataBean fileData = new FileDataBean();
				fileData.setFileName(fileName);
				fileData.setFileUrl(url);
				
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultMsg("图片上传压缩处理成功！");
				resultData.setResultData(fileData);
				
				
			} catch (Exception e) {
				e.printStackTrace();
				resultData.setResultType(CommonConstants.RESULT_ERROR);
				resultData.setResultMsg("图片上传压缩处理失败！");
				LOGGER.error("图片上传压缩处理失败,错误——>"+e);
			}
			
		}
		
		 
		return resultData;
	}
	
}
