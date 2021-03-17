package com.cjhme.system.impl.service.common.impl;

import java.util.Map;

import javax.annotation.Resource;

import com.cjhme.common.model.base.CascadeTreeNodeBean;
import com.cjhme.system.impl.dao.common.CascadeTreeDao;

/**
 * 
 * <p>
 * Title: CascadeTreeServiceImpl.java
 * </p>
 * Description: 层级树左右值计算实现
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public class CascadeTreeServiceImpl<T extends CascadeTreeNodeBean<K>, K>{

	private CascadeTreeDao<T, K> cascadeTreeDao;

	public void addLast(Map<String, Object> tableParameter, T node) {
		T parent = null;
		if (node.getParentId() != null) {
			parent = cascadeTreeDao.findNode(tableParameter, node.getParentId());
		}

		if (parent != null) {
			node.setLeftValue(parent.getRightValue());
			node.setRightValue(node.getLeftValue() + 1);
			node.setLayer(parent.getLayer() + 1);
		} else {
			int maxRight = cascadeTreeDao.getMaxRight(tableParameter);
			node.setLeftValue(maxRight + 1);
			node.setRightValue(node.getLeftValue() + 1);
			node.setLayer(1);
		}

		doAdd(tableParameter, node, parent);
	}

	public void addFirst(Map<String, Object> tableParameter, T node) {
		T parent = null;
		if (node.getParentId() != null) {
			parent = cascadeTreeDao.findNode(tableParameter, node.getParentId());
		}

		if (parent != null) {
			// 取父节点左值+1为左值
			node.setLeftValue(parent.getLeftValue() + 1);
			// 新节点仅有一个元素，右值+1
			node.setRightValue(node.getLeftValue() + 1);
			node.setLayer(parent.getLayer() + 1);
		} else {
			node.setLeftValue(1);
			node.setRightValue(2);
			node.setLayer(1);
		}

		doAdd(tableParameter, node, parent);
	}

	public void addLeft(Map<String, Object> tableParameter, T node, K refId) {
		T ref = cascadeTreeDao.findNode(tableParameter, refId);
		T parent = null;
		if (node.getParentId() != null) {
			parent = cascadeTreeDao.findNode(tableParameter, ref.getParentId());
		}

		// 以参考节点左值为左值，占用参考节点的位置
		node.setLeftValue(ref.getLeftValue());
		node.setRightValue(node.getLeftValue() + 1);
		node.setLayer(ref.getLayer());
		node.setParentId(ref.getParentId());

		doAdd(tableParameter, node, parent);
	}

	public void addRight(Map<String, Object> tableParameter, T node, K refId) {
		T ref = cascadeTreeDao.findNode(tableParameter, refId);
		T parent = null;
		if (node.getParentId() != null) {
			parent = cascadeTreeDao.findNode(tableParameter, ref.getParentId());
		}

		// 以参考节点右值+1为左值，放在该节点右边
		node.setLeftValue(ref.getRightValue() + 1);
		node.setRightValue(node.getLeftValue() + 1);
		node.setLayer(ref.getLayer());
		node.setParentId(ref.getParentId());

		doAdd(tableParameter, node, parent);
	}

	public void delete(Map<String, Object> tableParameter, K id) {
		T node = cascadeTreeDao.findNode(tableParameter, id);
		int n = (node.getRightValue() - node.getLeftValue() + 1) / 2;

		cascadeTreeDao.delete(tableParameter, node.getLeftValue(), node.getRightValue());
		cascadeTreeDao.resize(tableParameter, node.getLeftValue(), node.getRightValue(), -n);
		cascadeTreeDao.shiftRightNode(tableParameter, node.getLeftValue(), node.getRightValue(), -n);
	}

	@SuppressWarnings("unchecked")
	public void shiftUp(Map<String, Object> tableParameter, K id) {
		T node = cascadeTreeDao.findNode(tableParameter, id);
		T left = (T) cascadeTreeDao.findBrother(tableParameter, node.getLeftValue(), node.getLayer(), 0);
		if (left == null) {
			return;
		}

		shift(tableParameter, node, -1);
	}

	@SuppressWarnings("unchecked")
	public void shiftDown(Map<String, Object> tableParameter, K id) {
		T node = cascadeTreeDao.findNode(tableParameter, id);
		T right = (T) cascadeTreeDao.findBrother(tableParameter, node.getLeftValue(), node.getLayer(), 1);
		if (right == null) {
			return;
		}

		shift(tableParameter, node, 1);
	}

	public void shiftBottom(Map<String, Object> tableParameter, K id) {
		T node = cascadeTreeDao.findNode(tableParameter, id);
		T parent = cascadeTreeDao.findNode(tableParameter, node.getParentId());
		int distance = 0;
		if (parent != null) {
			// 追加在末位，右向可扩展，距离比实际多1
			distance = (parent.getRightValue() - node.getRightValue() + 1) / 2;
		} else {
			distance = (cascadeTreeDao.getMaxRight(tableParameter) + 1 - node.getRightValue() + 1) / 2;
		}

		if (distance != 0) {
			shift(tableParameter, node, distance);
		}

	}

	public void shiftTop(Map<String, Object> tableParameter, K id) {
		T node = cascadeTreeDao.findNode(tableParameter, id);
		T parent = cascadeTreeDao.findNode(tableParameter, node.getParentId());
		int distance = 0;

		if (parent != null) {
			// 占用该层次首位的位置，左向不可扩展，因此计算出实际距离
			distance = (parent.getLeftValue() - node.getLeftValue() + 1) / 2;
		} else {
			distance = (1 - node.getLeftValue()) / 2;
		}

		if (distance != 0) {
			shift(tableParameter, node, distance);
		}

	}

	public boolean shiftLayer(Map<String, Object> tableParameter, K id, K parentId) {
		T node = cascadeTreeDao.findNode(tableParameter, id);
		T newParent = cascadeTreeDao.findNode(tableParameter, parentId);

		// 不能将一个节点移到它的子节点内
		if (isParentOf(tableParameter, node, newParent)) {
			return false;
		}

		T oldParent = cascadeTreeDao.findNode(tableParameter, node.getParentId());

		// 层次变化
		int layerDelta = newParent.getLayer() - node.getLayer() + 1;
		int distance = newParent.getRightValue() - node.getLeftValue();
		int n = (node.getRightValue() - node.getLeftValue() + 1) / 2;

		// 增加到新父节点的末位,新父节点后的所有元素右移n位，为新元素腾出空间
		cascadeTreeDao.shiftRightNode(tableParameter, newParent.getRightValue(), newParent.getRightValue() + 1, n);
		// 新父节点容量增加n位，以便容纳新元素
		cascadeTreeDao.resize(tableParameter, newParent.getLeftValue(), newParent.getRightValue(), n);
		// 左移的情况下，原节点左右值已经发生变化，需要重设；距离也发生了变化（增加了n*2），需要增加n*2
		if (distance < 0) {
			node.setLeftValue(node.getLeftValue() + n * 2);
			node.setRightValue(node.getRightValue() + n * 2);
			// 父节点右移了
			oldParent.setLeftValue(oldParent.getLeftValue() + n * 2);
			oldParent.setRightValue(oldParent.getRightValue() + n * 2);
			// 旧元素更改左右值及层次深度
			cascadeTreeDao.shift(tableParameter, node, layerDelta, distance - n * 2);
			// 右移情况下，原节点左右值未被更改，可直接移动
		} else {
			cascadeTreeDao.shift(tableParameter, node, layerDelta, distance);
		}

		// 原位置之后的元素,左移n位
		cascadeTreeDao.shiftRightNode(tableParameter, node.getLeftValue(), node.getRightValue(), -n);
		// 原父节点容量变小
		cascadeTreeDao.resize(tableParameter, oldParent.getLeftValue(), oldParent.getRightValue(), -n);

		return true;
	}

	public void shift(Map<String, Object> tableParameter, T node, int distance) {
		if (distance == 0) {
			return;
		}

		// node子节点数
		int n = (node.getRightValue() - node.getLayer() + 1) / 2;

		// 计算移动后的左值/右值
		int newLeft = node.getLeftValue() + distance;
		int newRight = node.getRightValue() + distance;

		// 往左移，此时node的左右值已经被更改，需要更新
		if (distance < 0) {
			// 左向不可扩展，目标位置原先已有元素，也要向后移
			cascadeTreeDao.shiftRightNode(tableParameter, newLeft - 1, newLeft - 1, n);
			node.setLeftValue(newLeft);
			node.setRightValue(newRight);
			// node与目标的的距离拉开了n个位置
			cascadeTreeDao.shift(tableParameter, node, 0, distance - n * 2);
			// node被挪走，node右边的所有节点前移
			cascadeTreeDao.shiftRightNode(tableParameter, node.getLeftValue(), node.getRightValue(), -n);
		} else {
			cascadeTreeDao.shiftRightNode(tableParameter, newLeft, newLeft, n);
			cascadeTreeDao.shift(tableParameter, node, 0, distance + n * 2);
			// node被挪走，node右边的所有节点前移
			cascadeTreeDao.shiftRightNode(tableParameter, node.getLeftValue(), node.getRightValue(), -n);
		}

	}

	private boolean isParentOf(Map<String, Object> tableParameter, T parent, T child) {
		if (parent.getLeftValue() < child.getLeftValue() && parent.getRightValue() > child.getRightValue()) {
			return true;
		}

		return false;
	}

	/**
	 * 为parent增加一个节点node
	 * 
	 * @param node   树节点，左值、右值及层次均已计算好
	 * @param parent 父节点
	 */
	private void doAdd(Map<String, Object> tableParameter, T node, T parent) {
		cascadeTreeDao.shiftRightNode(tableParameter, node.getLeftValue() - 1, node.getRightValue() - 1, 1);
		if (parent != null) {
			cascadeTreeDao.resize(tableParameter, parent.getLeftValue(), parent.getRightValue(), 1);
		}

	}

	public CascadeTreeDao<T, K> getCascadeTreeDao() {
		return cascadeTreeDao;
	}

	@Resource(name = "system.cascadeTreeDao")
	public void setCascadeTreeDao(CascadeTreeDao<T, K> cascadeTreeDao) {
		this.cascadeTreeDao = cascadeTreeDao;
	}

}
