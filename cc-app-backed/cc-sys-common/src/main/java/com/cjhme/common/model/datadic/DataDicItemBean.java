package com.cjhme.common.model.datadic;

import java.util.List;

import com.cjhme.common.model.base.BaseBean;
import com.cjhme.common.model.role.RoleBean;

/**
 * 
 * <p>
 * Title: DictionaryItemBean.java
 * </p>
 * Description: 数据字典明细
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public class DataDicItemBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String dicItemId;

	/**
	 * 编号
	 */
	private String dicItemCode;

	/**
	 * 数据字典分类id
	 */
	private String dicTypeId;

	/**
	 * 数据字典分类编号
	 */
	private String dicTypeCode;

	/**
	 * 明细信息父id
	 */
	private String dicItemParentId;
	
	/**
	 * 明细信息父名称
	 */
	private String dicItemParentName;

	/**
	 * 名称
	 */
	private String dicItemName;

	/**
	 * 值
	 */
	private String dicItemValue;
	
	/**
	 * 备用值
	 */
	private String spareValue;
	
	/**
	 * 默认值
	 */
	private String defaultValue;
	

	/**
	 * 描述
	 */
	private String dicItemDesc;

	/**
	 * 样式
	 */
	private String iconCls;

	/**
	 * 排序号
	 */
	private String sortNum;

	/**
	 * 明细分配分配角色
	 */
	private List<RoleBean> distributeRoles;

	

	/**
	 * 数据字典明细子数据
	 */
	private List<DataDicItemBean> children;

	/**
	 * 展开状态
	 */
	private String state;
	

	
	

	

	public String getState() {
		if (null != children && children.size() > 0) {
			state = "closed";
		} else {
			state = "open";
		}
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDicItemId() {
		return dicItemId;
	}

	public void setDicItemId(String dicItemId) {
		this.dicItemId = dicItemId;
	}

	public String getDicTypeId() {
		return dicTypeId;
	}

	public void setDicTypeId(String dicTypeId) {
		this.dicTypeId = dicTypeId;
	}

	public String getDicItemParentId() {
		return dicItemParentId;
	}

	public void setDicItemParentId(String dicItemParentId) {
		this.dicItemParentId = dicItemParentId;
	}

	public String getDicItemName() {
		return dicItemName;
	}

	public void setDicItemName(String dicItemName) {
		this.dicItemName = dicItemName;
		this.setLabel(dicItemName);
	}

	public String getDicItemValue() {
		return dicItemValue;
	}

	public void setDicItemValue(String dicItemValue) {
		this.dicItemValue = dicItemValue;
		this.setValue(dicItemValue);
	}

	public String getDicItemDesc() {
		return dicItemDesc;
	}

	public void setDicItemDesc(String dicItemDesc) {
		this.dicItemDesc = dicItemDesc;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getSortNum() {
		return sortNum;
	}

	public void setSortNum(String sortNum) {
		this.sortNum = sortNum;
	}

	public String getDicItemParentName() {
		return dicItemParentName;
	}

	public void setDicItemParentName(String dicItemParentName) {
		this.dicItemParentName = dicItemParentName;
	}

	public String getDicItemCode() {
		return dicItemCode;
	}

	public void setDicItemCode(String dicItemCode) {
		this.dicItemCode = dicItemCode;
	}

	public String getDicTypeCode() {
		return dicTypeCode;
	}

	public void setDicTypeCode(String dicTypeCode) {
		this.dicTypeCode = dicTypeCode;
	}

	public List<RoleBean> getDistributeRoles() {
		return distributeRoles;
	}

	public void setDistributeRoles(List<RoleBean> distributeRoles) {
		this.distributeRoles = distributeRoles;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public List<DataDicItemBean> getChildren() {
		return children;
	}

	public void setChildren(List<DataDicItemBean> children) {
		this.children = children;
	}

	public String getSpareValue() {
		return spareValue;
	}

	public void setSpareValue(String spareValue) {
		this.spareValue = spareValue;
	}
	

}
