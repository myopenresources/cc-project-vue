package com.cjhme.common.model.datadic;

import java.util.ArrayList;
import java.util.List;

import com.cjhme.common.model.base.BaseBean;

/**
 * 
 * <p>  
 * Title: DictionaryTypeBean.java 
 * </p>  
 * Description: 数据字典分类
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 */
public class DataDicTypeBean extends BaseBean {

	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	private String dicTypeId;
	
	/**
	 * 分类编号
	 */
	private String dicTypeCode;
	
	/**
	 * 分类名称
	 */
	private String dicTypeName;
	
	/**
	 * 数据字典类别:1系统字典，2业务字典
	 */
	private String dicCategory;
	
	/**
	 * 样式
	 */
	private String iconCls;
	
	/**
	 * 明细
	 */
	private List<DataDicItemBean> dataDicItems = new ArrayList<DataDicItemBean>();
	
	
	
	

	public String getDicTypeId() {
		return dicTypeId;
	}

	public void setDicTypeId(String dicTypeId) {
		this.dicTypeId = dicTypeId;
	}

	public String getDicTypeName() {
		return dicTypeName;
	}

	public void setDicTypeName(String dicTypeName) {
		this.dicTypeName = dicTypeName;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getDicTypeCode() {
		return dicTypeCode;
	}

	public void setDicTypeCode(String dicTypeCode) {
		this.dicTypeCode = dicTypeCode;
	}

	public List<DataDicItemBean> getDataDicItems() {
		return dataDicItems;
	}

	public void setDataDicItems(List<DataDicItemBean> dataDicItems) {
		
		this.dataDicItems = dataDicItems;
	}

	public String getDicCategory() {
		return dicCategory;
	}

	public void setDicCategory(String dicCategory) {
		this.dicCategory = dicCategory;
	}
	
	
	

}
