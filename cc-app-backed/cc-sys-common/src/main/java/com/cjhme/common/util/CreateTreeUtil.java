package com.cjhme.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cjhme.common.model.base.ManyTreeNodeBean;
import com.cjhme.common.model.base.TreeNodeBean;

/**
 * 
 * <p>
 * Title: CreateTreeUtil.java
 * </p>
 * Description: 创建树工具类
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public class CreateTreeUtil {

	/**
	 * 多树节点
	 */
	private ManyTreeNodeBean manyTreeNodeBean;

	/**
	 * 树根默认id
	 */
	public final static String ROOT_TREE_NODE_ID = "-1";

	/**
	 * 默认状态
	 */
	public final static String STATE_DEFAULT = "default";

	/**
	 * 打开状态
	 */
	public final static String STATE_OPEN = "open";

	/**
	 * 关闭状态
	 */
	public final static String STATE_CLOSED = "closed";

	/**
	 * 构造方法
	 */
	public CreateTreeUtil() {
		TreeNodeBean TreeNodeBean = new TreeNodeBean("1", "root", "circle", "-1");
		this.manyTreeNodeBean = new ManyTreeNodeBean(TreeNodeBean);
	}

	/**
	 * 创建树
	 */
	public static CreateTreeUtil createTree(List<TreeNodeBean> treeNodeList, String rootId, String rootState) throws Exception {

		// 判断列表是否为空
		if (null == treeNodeList || treeNodeList.size() <= 0) {
			return null;
		}

		CreateTreeUtil createTreeUtil = new CreateTreeUtil();

		// 遍历列表，构造树
		for (TreeNodeBean node : treeNodeList) {

			// 如果父id与树根相同或没有父id，添加树根
			if (node.getParentId().equals(rootId) || node.getParentId().equals("") || node.getParentId().equals("-1")) {
				node.setState(rootState);
				createTreeUtil.getManyTreeNodeBean().getChildTreeList().add(new ManyTreeNodeBean(node));

				// 添加子节点
			} else {
				nodeAddChild(createTreeUtil.getManyTreeNodeBean(), node);
			}
		}

		return createTreeUtil;
	}

	/**
	 * 添加子节点
	 */
	private static void nodeAddChild(ManyTreeNodeBean manyTreeNodeBean, TreeNodeBean node) {

		// 遍历列表，构造树
		for (ManyTreeNodeBean manyTreeNode : manyTreeNodeBean.getChildTreeList()) {

			// 如果id与父节点id相同直接添加
			if (manyTreeNode.getTreeNodeBean().getId().equals(node.getParentId())) {
				manyTreeNode.getChildTreeList().add(new ManyTreeNodeBean(node));
				break;

				// 添加子节点
			} else {
				if (null != manyTreeNode.getChildTreeList() && manyTreeNode.getChildTreeList().size() > 0) {
					nodeAddChild(manyTreeNode, node);
				}
			}
		}

	}

	/**
	 * 创建树JSON
	 */
	public static void createTreeJson(ManyTreeNodeBean manyTreeNodeBean, StringBuffer json, String childState) {
		if (null == manyTreeNodeBean) {
			return;
		}

		List<ManyTreeNodeBean> childTreeList = manyTreeNodeBean.getChildTreeList();

		if (null != childTreeList && childTreeList.size() > 0) {
			for (int i = 0; i < childTreeList.size(); i++) {

				TreeNodeBean treeNodeBean = childTreeList.get(i).getTreeNodeBean();
				json.append("{");
				json.append("\"id\":\"" + treeNodeBean.getId() + "\",");
				json.append("\"text\":\"" + treeNodeBean.getText() + "\",");
				json.append("\"iconCls\":\"" + treeNodeBean.getIconCls() + "\",");
				json.append("\"attr0\":\"" + treeNodeBean.getAttr0() + "\",");
				json.append("\"attr1\":\"" + treeNodeBean.getAttr1() + "\",");
				json.append("\"attr2\":\"" + treeNodeBean.getAttr2() + "\",");
				json.append("\"attr3\":\"" + treeNodeBean.getAttr3() + "\",");
				// antd tree
				json.append("\"key\":\"" + treeNodeBean.getId() + "\",");
				json.append("\"title\":\"" + treeNodeBean.getText() + "\",");
				json.append("\"icon\":\"" + treeNodeBean.getIconCls() + "\",");

				Map<String, Object> attributes = treeNodeBean.getAttributes();
				if (attributes.size() > 0) {
					json.append("\"attributes\":{");
					int j = 0;
					for (Map.Entry<String, Object> entry : attributes.entrySet()) {
						json.append("\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"");

						// 最后一个不添加逗号
						if (j != (attributes.entrySet().size() - 1)) {
							json.append(",");
						}

						j++;
					}
					json.append("},");
				}

				List<ManyTreeNodeBean> childTrees = childTreeList.get(i).getChildTreeList();
				if (null != childTrees && childTrees.size() > 0) {
					if (null != treeNodeBean.getState() && !"".equals(treeNodeBean.getState())) {
						json.append("\"state\":\"" + treeNodeBean.getState() + "\",");
						if (treeNodeBean.getState().equals(CreateTreeUtil.STATE_OPEN)) {
							json.append("\"open\":true,");
						} else {
							json.append("\"open\":false,");
						}
					} else {
						if (childState.equals(CreateTreeUtil.STATE_DEFAULT) || childState.equals(CreateTreeUtil.STATE_CLOSED)) {
							json.append("\"state\":\"" + CreateTreeUtil.STATE_CLOSED + "\",");
							json.append("\"open\":false,");
						} else if (childState.equals(CreateTreeUtil.STATE_OPEN)) {
							json.append("\"state\":\"" + CreateTreeUtil.STATE_OPEN + "\",");
							json.append("\"open\":true,");
						}

					}
					
					// antd tree
					json.append("\"isLeaf\":false,");

					json.append("\"children\":[");
					createTreeJson(childTreeList.get(i), json, childState);
					json.append("]");
				} else {
					// antd tree
					json.append("\"isLeaf\":true,");
					
					if (null != treeNodeBean.getState() && !"".equals(treeNodeBean.getState())) {
						json.append("\"state\":\"" + treeNodeBean.getState() + "\",");
						if (treeNodeBean.getState().equals(CreateTreeUtil.STATE_OPEN)) {
							json.append("\"open\":true");
						} else {
							json.append("\"open\":false");
						}
					} else {
						if (childState.equals(CreateTreeUtil.STATE_DEFAULT) || childState.equals(CreateTreeUtil.STATE_OPEN)) {
							json.append("\"state\":\"" + CreateTreeUtil.STATE_OPEN + "\",");
							json.append("\"open\":true");
						} else if (childState.equals(CreateTreeUtil.STATE_CLOSED)) {
							json.append("\"state\":\"" + CreateTreeUtil.STATE_CLOSED + "\",");
							json.append("\"open\":false");
						}
					}
				}

				// 最后一个不添加逗号
				if (i == (childTreeList.size() - 1)) {
					json.append("}");
				} else {
					json.append("},");
				}

			}
		}

	}

	/**
	 * 创建树JSON
	 */
	public static void createTreeJson(List<TreeNodeBean> treeNodes, StringBuffer json, String childState) {

		if (null != treeNodes && treeNodes.size() > 0) {
			for (int i = 0; i < treeNodes.size(); i++) {

				TreeNodeBean treeNodeBean = treeNodes.get(i);
				json.append("{");
				json.append("\"id\":\"" + treeNodeBean.getId() + "\",");
				json.append("\"text\":\"" + treeNodeBean.getText() + "\",");
				json.append("\"iconCls\":\"" + treeNodeBean.getIconCls() + "\",");
				json.append("\"attr0\":\"" + treeNodeBean.getAttr0() + "\",");
				json.append("\"attr1\":\"" + treeNodeBean.getAttr1() + "\",");
				json.append("\"attr2\":\"" + treeNodeBean.getAttr2() + "\",");
				json.append("\"attr3\":\"" + treeNodeBean.getAttr3() + "\",");
				// antd tree
				json.append("\"key\":\"" + treeNodeBean.getId() + "\",");
				json.append("\"title\":\"" + treeNodeBean.getText() + "\",");
				json.append("\"icon\":\"" + treeNodeBean.getIconCls() + "\",");

				Map<String, Object> attributes = treeNodeBean.getAttributes();
				if (attributes.size() > 0) {
					json.append("\"attributes\":{");
					int j = 0;
					for (Map.Entry<String, Object> entry : attributes.entrySet()) {
						json.append("\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"");

						// 最后一个不添加逗号
						if (j != (attributes.entrySet().size() - 1)) {
							json.append(",");
						}

						j++;
					}
					json.append("},");
				}

				List<TreeNodeBean> childTrees = treeNodeBean.getChildren();
				if (null != childTrees && childTrees.size() > 0) {
					if (null != treeNodeBean.getState() && !"".equals(treeNodeBean.getState())) {
						json.append("\"state\":\"" + treeNodeBean.getState() + "\",");
						if (treeNodeBean.getState().equals(CreateTreeUtil.STATE_OPEN)) {
							json.append("\"open\":true,");
						} else {
							json.append("\"open\":false,");
						}
					} else {
						json.append("\"state\":\"closed\",");
						json.append("\"open\":false,");
					}

					if (null != treeNodeBean.getState() && !"".equals(treeNodeBean.getState())) {
						json.append("\"state\":\"" + treeNodeBean.getState() + "\",");
						if (treeNodeBean.getState().equals(CreateTreeUtil.STATE_OPEN)) {
							json.append("\"open\":true,");
						} else {
							json.append("\"open\":false,");
						}
					} else {
						if (childState.equals(CreateTreeUtil.STATE_DEFAULT) || childState.equals(CreateTreeUtil.STATE_CLOSED)) {
							json.append("\"state\":\"" + CreateTreeUtil.STATE_CLOSED + "\",");
							json.append("\"open\":false,");
						} else if (childState.equals(CreateTreeUtil.STATE_OPEN)) {
							json.append("\"state\":\"" + CreateTreeUtil.STATE_OPEN + "\",");
							json.append("\"open\":true,");
						}

					}
					
					// antd tree
					json.append("\"isLeaf\":false,");

					json.append("\"children\":[");
					createTreeJson(childTrees, json, childState);
					json.append("]");
				} else {
					
					// antd tree
					json.append("\"isLeaf\":true,");

					if (null != treeNodeBean.getState() && !"".equals(treeNodeBean.getState())) {
						json.append("\"state\":\"" + treeNodeBean.getState() + "\",");
						if (treeNodeBean.getState().equals(CreateTreeUtil.STATE_OPEN)) {
							json.append("\"open\":true");
						} else {
							json.append("\"open\":false");
						}
					} else {
						if (childState.equals(CreateTreeUtil.STATE_DEFAULT) || childState.equals(CreateTreeUtil.STATE_OPEN)) {
							json.append("\"state\":\"" + CreateTreeUtil.STATE_OPEN + "\",");
							json.append("\"open\":true,");
						} else if (childState.equals(CreateTreeUtil.STATE_CLOSED)) {
							json.append("\"state\":\"" + CreateTreeUtil.STATE_CLOSED + "\",");
							json.append("\"open\":false,");
						}
					}

					if (null != treeNodeBean.getState() && !"".equals(treeNodeBean.getState())) {
						json.append("\"state\":\"" + treeNodeBean.getState() + "\",");
						if (treeNodeBean.getState().equals(CreateTreeUtil.STATE_OPEN)) {
							json.append("\"open\":true");
						} else {
							json.append("\"open\":false");
						}
					} else {
						json.append("\"state\":\"open\",");
						json.append("\"open\":true");
					}
				}

				// 最后一个不添加逗号
				if (i == (treeNodes.size() - 1)) {
					json.append("}");
				} else {
					json.append("},");
				}

			}
		}

	}

	/**
	 * 获得树JSON
	 */
	private static String getTreeJson(CreateTreeUtil createTreeUtil, String childState) throws Exception {
		if (null == createTreeUtil || "".equals(createTreeUtil)) {
			return "[]";
		}

		StringBuffer json = new StringBuffer();
		json.append("[");
		createTreeJson(createTreeUtil.getManyTreeNodeBean(), json, childState);
		json.append("]");

		return json.toString();
	}

	/**
	 * 获得树JSON
	 */
	public static String getTreeJson(List<TreeNodeBean> treeNodeList, String rootId, String rootState, String childState) throws Exception {
		return CreateTreeUtil.getTreeJson(CreateTreeUtil.createTree(treeNodeList, rootId, rootState), childState);
	}

	/**
	 * 获得树JSON
	 */
	public static String getTreeJson(List<TreeNodeBean> treeNodes, String childState) throws Exception {
		if (null == treeNodes || "".equals(treeNodes) || treeNodes.size() <= 0) {
			return "[]";
		}

		StringBuffer json = new StringBuffer();
		json.append("[");
		createTreeJson(treeNodes, json, childState);
		json.append("]");

		return json.toString();
	}

	public ManyTreeNodeBean getManyTreeNodeBean() {
		return manyTreeNodeBean;
	}

	public void setManyTreeNodeBean(ManyTreeNodeBean manyTreeNodeBean) {
		this.manyTreeNodeBean = manyTreeNodeBean;
	}

	public static void main(String[] args) {
		List<TreeNodeBean> treeNodeList = new ArrayList<TreeNodeBean>();
		TreeNodeBean TreeNodeBean0 = new TreeNodeBean("1", "dd", "icons-persion-center", "");
		TreeNodeBean0.setAttr1("aaaa");
		TreeNodeBean0.setAttr2("bbbb");
		TreeNodeBean0.setAttr3("bbbb");
		TreeNodeBean0.setAttr4("bbbb");
		TreeNodeBean0.setAttr5("bbbb");
		TreeNodeBean0.setAttr6("bbbb");

		TreeNodeBean TreeNodeBean1 = new TreeNodeBean("2", "哎呀", "icons-persion-center", "");
		TreeNodeBean1.setAttr1("aaaa");
		TreeNodeBean1.setAttr2("bbbb");
		TreeNodeBean1.setAttr3("bbbb");
		TreeNodeBean1.setAttr4("bbbb");
		TreeNodeBean1.setAttr5("bbbb");
		TreeNodeBean1.setAttr6("bbbb");

		TreeNodeBean TreeNodeBean2 = new TreeNodeBean("3", "在在", "icons-persion-center", "");
		TreeNodeBean TreeNodeBean3 = new TreeNodeBean("4", "CreateTreeDemo", "icons-persion-center", "");
		TreeNodeBean TreeNodeBean4 = new TreeNodeBean("5", "CreateTreeDemo", "icons-persion-center", "6");
		TreeNodeBean TreeNodeBean5 = new TreeNodeBean("6", "CreateTreeDemo", "icons-persion-center", "3");
		TreeNodeBean TreeNodeBean6 = new TreeNodeBean("7", "CreateTreeDemo", "icons-persion-center", "3");
		TreeNodeBean TreeNodeBean7 = new TreeNodeBean("8", "CreateTreeDemo", "icons-persion-center", "3");
		TreeNodeBean TreeNodeBean8 = new TreeNodeBean("9", "CreateTreeDemo־", "icons-persion-center", "4");
		TreeNodeBean TreeNodeBean9 = new TreeNodeBean("10", "CreateTreeDemo", "icons-persion-center", "6");
		TreeNodeBean TreeNodeBean10 = new TreeNodeBean("10", "CreateTreeDemo", "icons-persion-center", "7");

		treeNodeList.add(TreeNodeBean0);
		treeNodeList.add(TreeNodeBean1);
		treeNodeList.add(TreeNodeBean2);
		treeNodeList.add(TreeNodeBean3);
		treeNodeList.add(TreeNodeBean4);
		treeNodeList.add(TreeNodeBean5);
		treeNodeList.add(TreeNodeBean6);
		treeNodeList.add(TreeNodeBean7);
		treeNodeList.add(TreeNodeBean8);
		treeNodeList.add(TreeNodeBean9);
		treeNodeList.add(TreeNodeBean10);
		CreateTreeUtil createTreeUtil;
		try {
			System.out.println(System.currentTimeMillis());
			createTreeUtil = CreateTreeUtil.createTree(treeNodeList, CreateTreeUtil.ROOT_TREE_NODE_ID, CreateTreeUtil.STATE_OPEN);
			System.out.println(CreateTreeUtil.getTreeJson(createTreeUtil, CreateTreeUtil.STATE_CLOSED));
			System.out.println(System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}