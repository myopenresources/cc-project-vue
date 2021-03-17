package com.cjhme.system.impl.dao.common.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cjhme.common.model.base.CascadeTreeNodeBean;
import com.cjhme.system.impl.base.dao.BaseDao;
import com.cjhme.system.impl.dao.common.CascadeTreeDao;

/**
 * 
 * <p>
 * Title: CascadeTreeDaoImpl.java
 * </p>
 * Description: 层级树
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
@Repository("system.cascadeTreeDao")
public class CascadeTreeDaoImpl<K> extends BaseDao implements CascadeTreeDao<CascadeTreeNodeBean<K>, K> {
	private final static String PACKAGE_PATH = "com.cjhme.system.impl.dao.common.CascadeTreeDao.";

	@Override
	public CascadeTreeNodeBean<K>  findNode(Map<String, Object> tableParameter,K id) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.putAll(tableParameter);
		parameter.put("id", id);
		return this.getSqlSessionTemplate().selectOne(PACKAGE_PATH + "queryNode", parameter);
	}

	@Override
	public CascadeTreeNodeBean<K> findBrother(Map<String, Object> tableParameter,int idx, int layer, int direction){
		Map<String, Object> parameter = new HashMap<>();
		parameter.putAll(tableParameter);
		parameter.put("layer", layer);
		
		if(direction == 0) {
			parameter.put("left", idx);
			return this.getSqlSessionTemplate().selectOne(PACKAGE_PATH + "queryLeftBrother", parameter);	
		}else {
			parameter.put("right", idx);
			return this.getSqlSessionTemplate().selectOne(PACKAGE_PATH + "queryRightBrother", parameter);
		}
	}

	@Override
	public int shiftRightNode(Map<String, Object> tableParameter,int baseLeft, int baseRight, int step) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.putAll(tableParameter);
		parameter.put("baseLeft", baseLeft);
		parameter.put("baseRight", baseRight);
		parameter.put("step", step);
		return this.getSqlSessionTemplate().update(PACKAGE_PATH + "updateRightAndLeftNode", parameter);
	}

	@Override
	public int resize(Map<String, Object> tableParameter,int baseLeft, int baseRight, int n){
		Map<String, Object> parameter = new HashMap<>();
		parameter.putAll(tableParameter);
		parameter.put("baseLeft", baseLeft);
		parameter.put("baseRight", baseRight);
		parameter.put("step", n);
		return this.getSqlSessionTemplate().update(PACKAGE_PATH + "updateRightNode", parameter);
	}

	@Override
	public int delete(Map<String, Object> tableParameter,int baseLeft, int baseRight) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.putAll(tableParameter);
		parameter.put("baseLeft", baseLeft);
		parameter.put("baseRight", baseRight);
		return this.getSqlSessionTemplate().update(PACKAGE_PATH + "deleteNodes", parameter); 
	}

	@Override
	public int shift(Map<String, Object> tableParameter,CascadeTreeNodeBean<K> node, int layerDelta, int distance) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.putAll(tableParameter);
		parameter.put("distance", distance);
		parameter.put("layerDelta", layerDelta);
		parameter.put("baseLeft", node.getLeftValue());
		parameter.put("baseRight", node.getRightValue());
		
		return this.getSqlSessionTemplate().update(PACKAGE_PATH + "updateNode", parameter); 
	}

	@Override
	public int getMaxRight(Map<String, Object> tableParameter) {
		return this.getSqlSessionTemplate().selectOne(PACKAGE_PATH + "queryMaxRight", tableParameter);
	}

}
