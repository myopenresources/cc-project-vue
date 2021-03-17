package com.cjhme.system.impl.service.dept.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjhme.common.model.base.CascadeTreeNodeBean;
import com.cjhme.common.model.base.DataPaging;
import com.cjhme.common.model.base.TreeNodeBean;
import com.cjhme.common.model.dept.DeptBean;
import com.cjhme.common.util.CreateTreeUtil;
import com.cjhme.system.impl.dao.dept.DeptDao;
import com.cjhme.system.impl.service.common.impl.CascadeTreeServiceImpl;
import com.cjhme.system.impl.service.dept.DeptService;

/**
 * 
 * <p>
 * Title: DeptServiceImpl.java
 * </p>
 * Description: 部门
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
@Service("system.deptService")
public class DeptServiceImpl extends CascadeTreeServiceImpl<CascadeTreeNodeBean<String>, String>
		implements DeptService {

	private DeptDao deptDao;

	/**
	 * 查询所有部门结构
	 * 
	 * @return
	 */
	public String queryDeptList(String rootState, String childState) throws Exception {
		List<TreeNodeBean> list = this.deptDao.queryDeptList();
		return CreateTreeUtil.getTreeJson(list, "", rootState, childState);
	}

	/**
	 * 根据条件查询部门
	 * 
	 * @param parameter
	 * @return
	 */
	public List<TreeNodeBean> queryDeptByCondition(Map<String, Object> parameter) throws Exception {
		return this.deptDao.queryDeptByCondition(parameter);

	}

	/**
	 * 根据条件分页查询部门
	 * 
	 * @param pageParameter
	 * @return
	 */
	public DataPaging<Object> queryDeptByConditionPaging(DataPaging<Object> pageParameter) {
		return this.deptDao.queryDeptByConditionPaging(pageParameter);
	}

	/**
	 * 判断部门是否存在
	 * 
	 * @param parameter
	 * @return
	 */
	public int queryDeptIsExists(Map<String, Object> parameter) {
		return this.deptDao.queryDeptIsExists(parameter);
	}

	/**
	 * 添加部门
	 * 
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int saveDept(Map<String, Object> parameter) {
		Map<String, Object> tableParameter = getTableParameter();
		CascadeTreeNodeBean<String> cascadeTreeNodeBean = new CascadeTreeNodeBean<>();
		cascadeTreeNodeBean.setId(String.valueOf(parameter.get("deptId")));
		cascadeTreeNodeBean.setParentId(String.valueOf(parameter.get("parentDeptId")));
		addLast(tableParameter, cascadeTreeNodeBean);

		parameter.put("rightValue", cascadeTreeNodeBean.getRightValue());
		parameter.put("leftValue", cascadeTreeNodeBean.getLeftValue());
		return this.deptDao.saveDept(parameter);
	}

	/**
	 * 根据id查询部门
	 * 
	 * @param parameter
	 * @return
	 */
	public DeptBean queryDeptByDeptId(Map<String, Object> parameter) {
		return this.deptDao.queryDeptByDeptId(parameter);
	}

	/**
	 * 根据id修改部门
	 * 
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int updateDeptByDeptId(Map<String, Object> parameter) {
		String parentDeptId = String.valueOf(parameter.get("parentDeptId"));
		DeptBean oldDept = this.deptDao.queryDeptByDeptId(parameter);

		if ((oldDept.getParentDeptId() != null && !oldDept.getParentDeptId().equals(parentDeptId))
				|| (parentDeptId != null && !parentDeptId.equals(oldDept.getParentDeptId()))) {
			Map<String, Object> tableParameter = getTableParameter();
			shiftLayer(tableParameter, String.valueOf(parameter.get("deptId")), parentDeptId);
		}

		return this.deptDao.updateDeptByDeptId(parameter);
	}

	/**
	 * 根据ids删除部门
	 * 
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int deleteDeptByIds(Map<String, Object> parameter) {
		// 删除部门用户映射
		this.deptDao.deleteDeptUserMappingByIds(parameter);

		// 删除部门
		this.deptDao.deleteDeptByIds(parameter);
		return 1;
	}

	/**
	 * 根据id移动部门
	 * 
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int updateDeptParentDeptIdById(Map<String, Object> parameter) {
		return this.deptDao.updateDeptParentDeptIdById(parameter);
	}

	/**
	 * 根据组织ids删除部门用户映射
	 * 
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int deleteDeptUserMappingByIds(Map<String, Object> parameter) {
		return this.deptDao.deleteDeptUserMappingByIds(parameter);
	}

	public DeptDao getDeptDao() {
		return deptDao;
	}

	@Resource(name = "system.deptDao")
	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}

	private Map<String, Object> getTableParameter() {
		Map<String, Object> tableParameter = new HashMap<>();
		tableParameter.put("tableName", "T_DEPT");
		tableParameter.put("idCol", "DEPT_ID");
		tableParameter.put("parentIdCol", "PARENT_DEPT_ID");
		tableParameter.put("leftCol", "LEFT_VALUE");
		tableParameter.put("rightCol", "RIGHT_VALUE");
		tableParameter.put("layerCol", "DEPT_LEVEL");

		return tableParameter;
	}

}
