package com.cjhme.system.impl.dao.common;

import java.util.Map;

import com.cjhme.common.model.base.CascadeTreeNodeBean;

/**
 * 
 * <p>
 * Title: CascadeTreeDao.java
 * </p>
 * Description: 层级树
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public interface CascadeTreeDao<T extends CascadeTreeNodeBean<K>, K> {

	/**
	 * 通过ID找到节点信息
	 */
	public T findNode(Map<String, Object> tabletableParameter,K id);
	
	/**
	 * 查找兄弟节点
	 */
	public CascadeTreeNodeBean<K> findBrother(Map<String, Object> tableParameter,int idx, int layer, int direction);
	/**
	 * 将右边的节点移动几个位置，腾出空间给新元素
	 */
	public int shiftRightNode(Map<String, Object> tableParameter,int baseLeft, int baseRight, int step);
	

	/**
	 * 调整某个节点的容量,以适应新的大小
	 */
	public int resize(Map<String, Object> tableParameter,int baseLeft, int baseRight, int n);
	
	/**
	 * 删除基准节点,包括所有子节点
	 */
	public int delete(Map<String, Object> tableParameter,int baseLeft, int baseRight);
	
	/**
	 * 修改单个节点信息
	 */
	public int shift(Map<String, Object> tableParameter,T node, int layerDelta, int distance);
	
	/**
	 * 获取整棵树最大的右值
	 * @return
	 */
	public int getMaxRight(Map<String, Object> tableParameter);
}
