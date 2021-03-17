package com.cjhme.system.impl.dao.fileupload.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cjhme.common.model.fileupload.FileDataBean;
import com.cjhme.system.impl.base.dao.BaseDao;
import com.cjhme.system.impl.dao.fileupload.FileDataDao;

/**
 * 
 * <p>  
 * Title: FileDataDaoImpl.java 
 * </p>  
 * Description: 文件数据
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
@Repository("system.fileDataDao")
public class FileDataDaoImpl extends BaseDao implements FileDataDao {

	/**
	 * 添加文件数据
	 * @param parameter
	 * @return
	 */
	public  int saveFileData(Map<String, Object> parameter){
		return this.getSqlSessionTemplate().insert("com.cjhme.system.core.dao.fileupload.FileDataDao.saveFileData",parameter);
	}
	
	/**
	 * 根据业务标识与业务id查询文件数据
	 * @param parameter
	 * @return
	 */
	public List<FileDataBean> queryFileDataByBMarkAndBId(Map<String, Object> parameter){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.core.dao.fileupload.FileDataDao.queryFileDataByBMarkAndBId", parameter);
	}
	
	/**
	 * 根据id修改文件数据
	 * @param parameter
	 * @return
	 */
	public int updateFileDataByFileId(Map<String, Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.core.dao.fileupload.FileDataDao.updateFileDataByFileId",parameter);
	}
	
}
