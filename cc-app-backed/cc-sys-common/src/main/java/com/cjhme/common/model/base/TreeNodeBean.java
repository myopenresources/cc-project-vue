package com.cjhme.common.model.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <p>
 * Title: TreeNodeBean.java
 * </p>
 * Description: 树节点
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public class TreeNodeBean extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private String id;
	/**
	 * 文本
	 */
	private String text;
	/**
	 * 图标
	 */
	private String iconCls;
	/**
	 * 父id
	 */
	private String parentId;

	/**
	 * 状态
	 */
	private String state;
	
	/**
	 * 选中
	 */
	private boolean checked;
	
	/**
	 * 属性0（临时变量）
	 */
	private String attr0;

	/**
	 * 属性1
	 */
	private String attr1;

	/**
	 * 属性2
	 */
	private String attr2;

	/**
	 * 属性3
	 */
	private String attr3;

	/**
	 * 属性4
	 */
	private String attr4;

	/**
	 * 属性5
	 */
	private String attr5;

	/**
	 * 属性6
	 */
	private String attr6;
	
	

	/**
	 * 属性
	 */
	private Map<String, Object> attributes = new HashMap<String, Object>();
	
	/**
	 * 子节点
	 */
	private List<TreeNodeBean> children=new ArrayList<TreeNodeBean>();

	/**
	 * 构造方法
	 */
	public TreeNodeBean() {

	}

	/**
	 * 构造方法
	 */
	public TreeNodeBean(String id, String text, String iconCls, String parentId) {
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
		this.parentId = parentId;
	}

	/**
	 * 构造方法
	 */
	public TreeNodeBean(String id, String text, String iconCls,
			String parentId, String state, Map<String, Object> attributes) {
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
		this.parentId = parentId;
		this.state = state;
		this.attributes = attributes;
	}

	/**
	 * 构造方法
	 */
	public TreeNodeBean(String id, String text, String iconCls,
			String parentId, String state, String attr1, String attr2,
			String attr3, String attr4, String attr5, String attr6) {
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
		this.parentId = parentId;
		this.state = state;
		this.attr1 = attr1;
		this.attr2 = attr2;
		this.attr3 = attr3;
		this.attr4 = attr4;
		this.attr5 = attr5;
		this.attr6 = attr6;
	}

	/**
	 * 构造方法
	 */
	public TreeNodeBean(String id, String text, String iconCls,
			String parentId, String state, String attr1, String attr2,
			String attr3, String attr4, String attr5, String attr6,
			Map<String, Object> attributes) {
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
		this.parentId = parentId;
		this.state = state;
		this.attr1 = attr1;
		this.attr2 = attr2;
		this.attr3 = attr3;
		this.attr4 = attr4;
		this.attr5 = attr5;
		this.attr6 = attr6;
		this.attributes = attributes;
	}

	public String getId() {
		if (null == id || "".equals(id) || "null".equals(id)) {
			id = "";
		}
		return id;
	}

	public void setId(String id) {
		this.id = id;
		this.setValue(id);
	}

	public String getText() {
		if (null == text || "".equals(text) || "null".equals(text)) {
			text = "";
		}
		return text;
	}

	public void setText(String text) {
		this.text = text;
		this.setLabel(text);
	}

	public String getIconCls() {
		if (null == iconCls || "".equals(iconCls) || "null".equals(iconCls)) {
			iconCls = "";
		}
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getParentId() {
		if (null == parentId || "".equals(parentId) || "null".equals(parentId)) {
			parentId = "";
		}
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getState() {
		if (null == state || "".equals(state) || "null".equals(state)) {
			state = "";
		}
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getAttr1() {
		return attr1;
	}

	public void setAttr1(String attr1) {
		if (null != attributes && null != attr1 && !"".equals(attr1)) {
			attributes.put("attr1", attr1);
		}
		this.attr1=attr1;
	}

	public String getAttr2() {
		return attr2;
	}

	public void setAttr2(String attr2) {
		if (null != attributes && null != attr2 && !"".equals(attr2)) {
			attributes.put("attr2", attr2);
		}
		this.attr2=attr2;
	}

	public String getAttr3() {
		return attr3;
	}

	public void setAttr3(String attr3) {
		if (null != attributes && null != attr3 && !"".equals(attr3)) {
			attributes.put("attr3", attr3);
		}
		this.attr3=attr3;
	}

	public String getAttr4() {
		return attr4;
	}

	public void setAttr4(String attr4) {
		if (null != attributes && null != attr4 && !"".equals(attr4)) {
			attributes.put("attr4", attr4);
		}
		
		this.attr4=attr4;
	}

	public String getAttr5() {
		return attr5;
	}

	public void setAttr5(String attr5) {
		if (null != attributes && null != attr5 && !"".equals(attr5)) {
			attributes.put("attr5", attr5);
		}
		this.attr5=attr5;
	}

	public String getAttr6() {
		return attr6;
	}

	public void setAttr6(String attr6) {
		if (null != attributes && null != attr6 && !"".equals(attr6)) {
			attributes.put("attr6", attr6);
		}
		
		this.attr6=attr6;
	}

	public String getAttr0() {
		return attr0;
	}

	public void setAttr0(String attr0) {
		this.attr0 = attr0;
	}

	public List<TreeNodeBean> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNodeBean> children) {
		this.children = children;
	}

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	

}