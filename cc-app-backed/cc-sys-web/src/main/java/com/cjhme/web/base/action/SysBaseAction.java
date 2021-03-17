package com.cjhme.web.base.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;

import com.cjhme.common.constant.CommonConstants;
import com.cjhme.common.model.button.ButtonBean;
import com.cjhme.common.model.datadic.DataDicItemBean;
import com.cjhme.common.model.datadic.DataDicTypeBean;
import com.cjhme.common.model.role.RoleBean;
import com.cjhme.common.model.user.UserBean;
import com.cjhme.common.util.SpringServletContextUtil;
import com.cjhme.system.impl.base.loader.DataDicLoader;
import com.cjhme.system.impl.service.button.ButtonService;

/**
 * 
 * <p>
 * Title: SysBaseAction.java
 * </p>
 * Description: 系统基础action(实现数据字典相关操作)
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public class SysBaseAction{
	

	/**
	 * 获得所有数据字典数据
	 * 
	 * @return
	 */
	public final Map<String, Object> getAllDataDic() {
		DataDicLoader dataDicLoader=SpringServletContextUtil.getBeanByName("system.dataDicLoader");
		return dataDicLoader.getDataDicMap();
	}

	/**
	 * 根据typeCode获得数据字典类型与明细
	 * 
	 * @param typeCode 数据字典类型code
	 * @return
	 */
	public final DataDicTypeBean getDataDicTypeByDicTypeCode(String typeCode) {
		Map<String, Object> dataDicMap = this.getAllDataDic();
		if (null != dataDicMap && dataDicMap.size() > 0) {
			return (DataDicTypeBean) dataDicMap.get(typeCode);
		} else {
			return null;
		}
	}

	/**
	 * 根据typeCode获得数据字典明细
	 *  
	 * @param typeCode  数据字典明细code
	 * @return
	 */
	public final List<DataDicItemBean> getDataDicItemByDicTypeCode(String typeCode) {
		Map<String, Object> dataDicMap = this.getAllDataDic();
		if (null != dataDicMap && dataDicMap.size() > 0) {
			DataDicTypeBean dataDicTypeBean = (DataDicTypeBean) dataDicMap.get(typeCode);
			if (null != dataDicTypeBean) {
				return dataDicTypeBean.getDataDicItems();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	/**
	 * 根据typeCode与itemCode获得数据字典明细项
	 *  
	 * @param typeCode  数据字典明细code
	 * @return
	 */
	public final DataDicItemBean getDataDicItemByDicTypeCodeAndItemCode(String typeCode,String itemCode) {
		Map<String, Object> dataDicMap = this.getAllDataDic();
		if (null != dataDicMap && dataDicMap.size() > 0) {
			DataDicTypeBean dataDicTypeBean = (DataDicTypeBean) dataDicMap.get(typeCode);
			if (null != dataDicTypeBean) {
				DataDicItemBean findItem=null;
				List<DataDicItemBean> itemList=dataDicTypeBean.getDataDicItems();
				for(DataDicItemBean item:itemList){
					if(!StringUtils.isEmpty(item) && !StringUtils.isEmpty(item.getDicItemCode()) && item.getDicItemCode().equals(itemCode)){
						findItem=item;
						break;
					}
				}
				
				return findItem;
				 
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 根据typeCode和角色获得数据字典明细
	 * 
	 * @param typeCode 数据字典明细code
	 * @return
	 */
	public final List<DataDicItemBean> getDataDicItemByDicTypeCodeAndRole(HttpSession session,String typeCode) {
		Map<String, Object> dataDicMap = this.getAllDataDic();
		if (null != dataDicMap && dataDicMap.size() > 0) {
			DataDicTypeBean dataDicTypeBean = (DataDicTypeBean) dataDicMap.get(typeCode);
			if (null != dataDicTypeBean) {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				
				List<DataDicItemBean> allDataDicItem = dataDicTypeBean.getDataDicItems();
				Set<DataDicItemBean> subataDicItemSet=new HashSet<DataDicItemBean>();
				for (DataDicItemBean dataDicItemBean : allDataDicItem) {
					List<RoleBean> dataDicItemRoleMapping = dataDicItemBean.getDistributeRoles();
					
					for (RoleBean roleBean : dataDicItemRoleMapping) {
						for(RoleBean userRoleBean:userBean.getRoles()){
							if (roleBean.getRoleId().equals(userRoleBean.getRoleId())) {
								subataDicItemSet.add(dataDicItemBean);
							}
						}
					}
				}
				
				List<DataDicItemBean> subataDicItem = new ArrayList<DataDicItemBean>();
				subataDicItem.addAll(subataDicItemSet);
				return subataDicItem;

			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	
	/**
	 * 绑定数据字典参数
	 *  
	 * @param request request对象
	 * @param dataDicTypes  数据字典明细code数组
	 */
	public final void bindDataDicParam(Map<String, Object> map, String[] dataDicTypes) {
		for (String typeCode : dataDicTypes) {
			List<DataDicItemBean> dataDicItemBean = null;
			List<DataDicItemBean> tempItems = this.getDataDicItemByDicTypeCode(typeCode);
			if (null != tempItems && tempItems.size() > 0) {
				dataDicItemBean = tempItems;
			} else {
				dataDicItemBean = new ArrayList<DataDicItemBean>();
			}
			map.put(typeCode, dataDicItemBean);
		}
	}
	
	
	/**
	 * 绑定角色数据字典参数
	 * 
	 * @param session  session对象
	 * @param request  request对象
	 * @param dataDicTypes  数据字典明细code数组
	 */
	public final void bindRoleDataDicParam(HttpSession session,Map<String, Object> map, String[] dataDicTypes) {
		for (String typeCode : dataDicTypes) {
			List<DataDicItemBean> dataDicItemBean = null;
			List<DataDicItemBean> tempItems = this.getDataDicItemByDicTypeCodeAndRole(session,typeCode);
			if (null != tempItems && tempItems.size() > 0) {
				dataDicItemBean = tempItems;
			} else {
				dataDicItemBean = new ArrayList<DataDicItemBean>();
			}
			map.put(typeCode, dataDicItemBean);
		}
	}
	
	/**
	 * 查询业务按钮
	 * @param userId
	 * @param busniessMark
	 * @return
	 */
	public final List<ButtonBean> getBusniessBtn(Map<String, Object> parameter){
		return ((ButtonService) SpringServletContextUtil.getBeanByName("system.buttonService")).queryBtnByBusniessMarkAndUserId(parameter);
	}

	
}
