package com.cjhme.system.impl.service.fileupload;

import java.util.List;
import java.util.Map;

import com.cjhme.common.model.fileupload.FileDataBean;

/**
 * 
 * <p>  
 * Title: FileDataService.java 
 * </p>  
 * Description: 文件数据
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
public interface FileDataService {

	/**
	 * 根据业务标识与业务id查询文件数据
	 * @param parameter
	 * @return
	 */
	public List<FileDataBean> queryFileDataByBMarkAndBId(Map<String, Object> parameter);
	
	/**
	 * 根据业务标识与业务id查询一条文件数据
	 * @param parameter
	 * @return
	 */
	public FileDataBean queryOneFileDataByBMarkAndBId(Map<String, Object> parameter);
}
