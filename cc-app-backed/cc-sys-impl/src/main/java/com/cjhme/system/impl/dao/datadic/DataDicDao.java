package com.cjhme.system.impl.dao.datadic;

import java.util.List;
import java.util.Map;

import com.cjhme.common.model.base.DataPaging;
import com.cjhme.common.model.base.TreeNodeBean;
import com.cjhme.common.model.datadic.DataDicItemBean;
import com.cjhme.common.model.datadic.DataDicItemRoleMappingBean;
import com.cjhme.common.model.datadic.DataDicTypeBean;

/**
 * 
 * <p>  
 * Title: DataDicDao.java 
 * </p>  
 * Description: 数据字典
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
public interface DataDicDao {
	
	/**
	 * 初始化时查询数据字典分类列表
	 * @return
	 */
	public Map<String,Object> queryInitDataDicType();

	/**
	 * 查询数据字典分类列表
	 * @return
	 */
	public List<DataDicTypeBean> queryDataDicTypeList(Map<String, Object> parameter);
	
	/**
	 * 判断数据字典分类是否存在
	 * @param parameter
	 * @return
	 */
	public int queryDataDicTypeIsExists(Map<String,Object> parameter);
	
	/**
	 * 添加数据字典分类
	 * @param dataDicTypeBean
	 * @return
	 */
	public int saveDataDicType(Map<String,Object> parameter);
	
	/**
	 * 根据id查询数据字典分类
	 * @param parameter
	 * @return
	 */
	public DataDicTypeBean queryDataDicTypeById(Map<String,Object> parameter);
	
	/**
	 * 根据id修改数据字典分类
	 * @param parameter
	 * @return
	 */
	public int updateDataDicTypeById(Map<String,Object> parameter);
	
	/**
	 * 根据ids删除数据字典分类
	 * @param parameter
	 * @return
	 */
	public int deleteDataDicTypeByIds(Map<String,Object> parameter);
	
	/**
	 * 根数据字典分类id查询数据字典明细分页
	 * @param parameter
	 * @return
	 */
	public DataPaging<Object> queryDataDicItemByIdPaging(DataPaging<Object> pageParameter);
	
	/**
	 * 判断数据字典明细是否存在
	 * @param pageParameter
	 * @return
	 */
	public int queryDataDicItemIsExists(Map<String,Object> parameter);
	
	/**
	 * 根据分类编号查询明细最大序号
	 * @param pageParameter
	 * @return
	 */
	public int queryDataDiaItemMaxSortNumByTypeCode(Map<String,Object> parameter);
	
	
	/**
	 * 添加数据字典明细 
	 * @param parameter
	 * @return
	 */
	public int saveDataDicItem(Map<String,Object> parameter);
	
	/**
	 * 根据id查询数据字典明细
	 * @param parameter
	 * @return
	 */
	public DataDicItemBean queryDataDicItemById(Map<String,Object> parameter);
	
	/**
	 * 根据id修改数据字典明细
	 * @param parameter
	 * @return
	 */
	public int updateDataDicItemById(Map<String,Object> parameter);
	
	/**
	 * 根据ids删除数据字典明细
	 * @param parameter
	 * @return
	 */
	public int deleteDataDicItemByIds(Map<String,Object> parameter);
	
	/**
	 * 根据分类ids删除数据字典明细
	 * @param parameter
	 * @return
	 */
	public int deleteDataDicItemByTypeIds(Map<String,Object> parameter);
	
	/**
	 * 获得当前行的上一条明细
	 * @param parameter
	 * @return
	 */
	public DataDicItemBean queryPreviousDataDicItemById(Map<String,Object> parameter);
	
	/**
	 * 获得当前行的下一条明细
	 * @param parameter
	 * @return
	 */
	public DataDicItemBean queryNextDataDicItemById(Map<String,Object> parameter);
	
	/**
	 * 根据id移动数据字典序号
	 * @param parameter
	 */
	public int updateDataDicItemSortNumById(Map<String,Object> parameter);
	
	
	/**
	 * 根据数据字典明细id查询数据字典明细角色映射
	 */
	public List<DataDicItemRoleMappingBean> queryDataDicItemRoleMappingById(Map<String,Object> parameter);
	
	/**
	 * 根据数据字典明细id删除数据字典明细角色映射
	 * @param parameter
	 * @return
	 */
	public int deleteDataDicItemRoleMappingById(Map<String,Object> parameter);
	
	/**
	 * 添加数据字典明细角色映射
	 * @param parameter
	 * @return
	 */
	public int saveDataDicItemRoleMapping(Map<String,Object> parameter);
	
	/**
	 * 根据数据字典明细ids删除数据字典明细角色映射
	 * @param parameter
	 * @return
	 */
    public int deleteDataDicItemRoleMappingByIds(Map<String,Object> parameter);
    
    /**
     * 根据条件查询数据字典明细 
     * @param parameter
     * @return
     */
	public List<TreeNodeBean> queryDataDicItemByCondition(Map<String,Object> parameter);
	
	/**
	 * 根据移动字典明细id查询明细最大排序 
	 * @param parameter
	 * @return
	 */
	public String queryMaxSortNumByParentId(Map<String, Object> parameter);
	

	
	
}
