package com.cjhme.common.model.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>
 * Title: ManyTreeNodeBean.java
 * </p>
 * Description: 多树节点
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public class ManyTreeNodeBean {
	/**
	 * 根节点
	 */
	private TreeNodeBean treeNodeBean;

	/**
	 * 子节点列列表
	 */
	private List<ManyTreeNodeBean> childTreeList;

	/**
	 * 构造方法
	 */
	public ManyTreeNodeBean() {
		super();
	}

	/**
	 * 构造方法
	 */
	public ManyTreeNodeBean(TreeNodeBean treeNodeBean) {
		this.treeNodeBean = treeNodeBean;
		this.childTreeList = new ArrayList<ManyTreeNodeBean>();
	}

	public TreeNodeBean getTreeNodeBean() {
		return treeNodeBean;
	}

	public void setTreeNodeBean(TreeNodeBean treeNodeBean) {
		this.treeNodeBean = treeNodeBean;
	}

	public List<ManyTreeNodeBean> getChildTreeList() {
		return childTreeList;
	}

	public void setChildTreeList(List<ManyTreeNodeBean> childTreeList) {
		this.childTreeList = childTreeList;
	}

}