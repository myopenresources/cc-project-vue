package com.cjhme.common.model.base;

/**
*
* <p>
* Title: CascadeTreeNodeBean.java
* </p>
* Description: 树节点
* <p>
* Modify histoty:
*
* @author cjh
* @version 1.0
*/
public class CascadeTreeNodeBean<T> {

	/**
	 * ID
	 */
	private T id;
	
	/**
	 * 父ID
	 */
	private T parentId;
	
	/**
	 * 左值
	 */
	private int leftValue;
	
	/**
	 * 右值
	 */
	private int rightValue;
	
	/**
	 * 深度
	 */
	private int layer;
	
	public T getId() {
		return id;
	}
	public void setId(T id) {
		this.id = id;
	}
	public T getParentId() {
		return parentId;
	}
	public void setParentId(T parentId) {
		this.parentId = parentId;
	}

	public int getLeftValue() {
		return leftValue;
	}
	public void setLeftValue(int leftValue) {
		this.leftValue = leftValue;
	}
	
	
	public int getRightValue() {
		return rightValue;
	}
	public void setRightValue(int rightValue) {
		this.rightValue = rightValue;
	}
	public int getLayer() {
		return layer;
	}
	public void setLayer(int layer) {
		this.layer = layer;
	}
	
	public boolean contains(CascadeTreeNodeBean<T> sub){
		if(sub == null) {
			return false;
		}
		return sub.getLeftValue() >= this.getLeftValue() && sub.getRightValue() <= this.getRightValue() ; 
	}
	
	public Integer nodeCompare(CascadeTreeNodeBean<T> o) {
		if (this.getId().equals(o.getId())) {
			return 0;
		} else if (this.leftValue > o.getLeftValue() && this.rightValue < o.rightValue) {
			return -1;
		} else if (this.leftValue < o.getLeftValue() && this.rightValue > o.rightValue) {
			return 1;
		} else {
			return null;
		}
	}
}
