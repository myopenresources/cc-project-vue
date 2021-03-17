package com.cjhme.system.impl.dao.accessurl.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cjhme.common.model.accessurl.AccessUrlBean;
import com.cjhme.common.model.base.DataPaging;
import com.cjhme.system.impl.base.dao.BaseDao;
import com.cjhme.system.impl.dao.accessurl.AccessUrlDao;

/**
 * 
 * <p>  
 * Title: AccessUrlDaoImpl.java 
 * </p>  
 * Description: 访问地址
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
@Repository("system.accessUrlDao")
public class AccessUrlDaoImpl extends BaseDao implements AccessUrlDao {

	/**
	 * 根据条件分页查询访问地址
	 * @param pageParameter
	 * @return
	 */
	public DataPaging<Object> queryAccessUrlByConditionPaging(DataPaging<Object> pageParameter){
		return this.selectPaging("com.cjhme.system.core.dao.accessurl.AccessUrlDao.queryAccessUrlByConditionPaging", pageParameter);
	}
	
	/**
	 * 判断访问地址是否存在
	 * @param parameter
	 * @return
	 */
	public int queryAccessUrlIsExists(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.core.dao.accessurl.AccessUrlDao.queryAccessUrlIsExists",parameter);
	}
	
	/**
	 * 添加访问地址
	 * @param parameter
	 * @return
	 */
	public int saveAccessUrl(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().insert("com.cjhme.system.core.dao.accessurl.AccessUrlDao.saveAccessUrl",parameter);
	}
	
	/**
	 * 根据id查询访问地址
	 * @param parameter
	 * @return
	 */
	public AccessUrlBean queryAccessUrlById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.core.dao.accessurl.AccessUrlDao.queryAccessUrlById",parameter);
	}
	
	/**
	 *  根据id修改访问地址
	 * @param parameter
	 * @return
	 */
	public int updateAccessUrlById(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.core.dao.accessurl.AccessUrlDao.updateAccessUrlById",parameter);
	}
	
	/**
	 * 根据ids删除访问地址
	 */
	public int deleteAccessUrlByIds(Map<String,Object> parameter){
		return this.getSqlSessionTemplate().update("com.cjhme.system.core.dao.accessurl.AccessUrlDao.deleteAccessUrlByIds",parameter);
	}
	
	/**
	 * 查询所有访问地址列表
	 * @return
	 */
	public List<AccessUrlBean> queryAllAccessUrlList(){
		return this.getSqlSessionTemplate().selectList("com.cjhme.system.core.dao.accessurl.AccessUrlDao.queryAllAccessUrlList");
	}
	
	/**
	 * 根据访问地址ids删除访问地址角色映射
	 * @param parameter
	 * @return
	 */
	public int deleteAccessUrlRoleMappingByIds(Map<String, Object> parameter){
		return this.getSqlSessionTemplate().delete("com.cjhme.system.core.dao.accessurl.AccessUrlDao.deleteAccessUrlRoleMappingByIds", parameter);
	}
}
