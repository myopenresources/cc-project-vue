package com.cjhme.common.model.button;

import com.cjhme.common.model.base.BaseBean;

/**
 * 
 * <p>
 * Title: ButtonBean.java
 * </p>
 * Description: 按钮
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public class ButtonBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String buttonId;
	
	/**
	 * 按钮编号 
	 */
	private String buttonCode;


	/**
	 * 按钮名称
	 */
	private String buttonName;


	/**
	 * 函数名
	 */
	private String functionName;


	/**
	 * 业务标识
	 */
	private String busniessMark;

	/**
	 * 布局标识
	 */
	private String layoutMark;

	/**
	 * 图标样式
	 */
	private String iconCls;
	
	/**
	 * 按钮样式
	 */
	private String btnCls;

	/**
	 * 按钮描述
	 */
	private String buttonDescribe;
	
	/**
	 * 排序
	 */
	private int sortNum;
	
	/**
	 * 按钮表达式
	 */
	private String btnExp;

	public String getButtonId() {
		return buttonId;
	}

	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}


	public String getBusniessMark() {
		return busniessMark;
	}

	public void setBusniessMark(String busniessMark) {
		this.busniessMark = busniessMark;
	}

	public String getLayoutMark() {
		return layoutMark;
	}

	public void setLayoutMark(String layoutMark) {
		this.layoutMark = layoutMark;
	}

	public String getButtonDescribe() {
		return buttonDescribe;
	}

	public void setButtonDescribe(String buttonDescribe) {
		this.buttonDescribe = buttonDescribe;
	}



	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	public String getBtnCls() {
		return btnCls;
	}

	public void setBtnCls(String btnCls) {
		this.btnCls = btnCls;
	}

	public String getBtnExp() {
		return btnExp;
	}

	public void setBtnExp(String btnExp) {
		this.btnExp = btnExp;
	}

	public String getButtonCode() {
		return buttonCode;
	}

	public void setButtonCode(String buttonCode) {
		this.buttonCode = buttonCode;
	}

	
	
	

}
