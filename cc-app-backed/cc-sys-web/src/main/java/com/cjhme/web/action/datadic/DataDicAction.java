package com.cjhme.web.action.datadic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cjhme.common.constant.BusniessMarkConstants;
import com.cjhme.common.constant.CommonConstants;
import com.cjhme.common.constant.DataDicConstants;
import com.cjhme.common.model.base.DataPaging;
import com.cjhme.common.model.base.ResultData;
import com.cjhme.common.model.button.ButtonBean;
import com.cjhme.common.model.datadic.DataDicItemBean;
import com.cjhme.common.model.datadic.DataDicItemRoleMappingBean;
import com.cjhme.common.model.datadic.DataDicTypeBean;
import com.cjhme.common.model.role.RoleBean;
import com.cjhme.common.model.user.UserBean;
import com.cjhme.common.util.CreateTreeUtil;
import com.cjhme.common.util.JsonUtil;
import com.cjhme.common.util.RequestParamToObjectUtil;
import com.cjhme.common.util.SpringServletContextUtil;
import com.cjhme.common.util.UUIDUtil;
import com.cjhme.system.impl.base.loader.DataDicLoader;
import com.cjhme.system.impl.service.datadic.DataDicService;
import com.cjhme.system.impl.service.role.RoleService;
import com.cjhme.web.base.action.SysBaseAction;


/**
 * 
 * <p>
 * Title: DataDicAction.java
 * </p>
 * Description: 数据字典
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
@Controller
@Scope("prototype")
@RequestMapping("/dataDic")
public class DataDicAction extends SysBaseAction {

	Log LOGGER = LogFactory.getLog(DataDicAction.class);

	private DataDicService dataDicService;

	private RoleService roleService;

	/**
	 * 初始数据字典管理数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/initDataDicManageData", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void initDataDicManageData(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			if (null != userBean && null != parameter) {
				Map<String, Object> map = new HashMap<String, Object>();
				// 绑定数据字典
				this.bindDataDicParam(map, new String[] { DataDicConstants.QUERY_STATUS_TYPE, DataDicConstants.STATUS_TYPE, DataDicConstants.DIC_CATEGORY_TYPE });

				parameter.put("userId", userBean.getUserId());
				parameter.put("busniessMark", BusniessMarkConstants.DATA_DIC_MANAGE);
				List<ButtonBean> buttonList = this.getBusniessBtn(parameter);
				map.put("buttonList", buttonList);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("初始数据字典管理数据成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("初始数据字典管理数据未成功！");
				LOGGER.error("初始数据字典管理数据未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始数据字典管理数据失败！");
			e.printStackTrace();
			LOGGER.error("初始数据字典管理数据失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}

	/**
	 * 查询数据字典类别列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryDataDicTypeList", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void queryDataDicTypeList(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			List<DataDicTypeBean> dataDicTypes = this.dataDicService.queryDataDicTypeList(parameter);
			if (StringUtils.isEmpty(dataDicTypes)) {
				dataDicTypes = new ArrayList<DataDicTypeBean>();
			}
			resultData.setResultType(CommonConstants.RESULT_SUCCESS);
			resultData.setResultData(dataDicTypes);
			resultData.setResultMsg("查询数据字典类别列表成功!");
			LOGGER.info("查询数据字典类别列表成功！");
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("添加数据字典类别失败！");
			e.printStackTrace();
			LOGGER.error("添加数据字典类别失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 查询数据字典类别信息
	 * 
	 * @param request
	 * @return
	 */
	// todo
	@RequestMapping(value = "/queryDataDicTypeById", method = RequestMethod.GET)
	public void queryDataDicTypeById(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			String dicTypeId = String.valueOf(request.getParameter("dicTypeId"));
			if (!StringUtils.isEmpty(dicTypeId)) {

				Map<String, Object> map = new HashMap<String, Object>();

				// 绑定数据字典
				this.bindDataDicParam(map, new String[] { DataDicConstants.DIC_CATEGORY_TYPE });

				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("dicTypeId", dicTypeId);
				DataDicTypeBean dataDicTypeData = this.dataDicService.queryDataDicTypeById(parameter);
				map.put("dataDicTypeData", dataDicTypeData);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("查询数据字典类别信息成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("查询数据字典类别信息未成功！");
				LOGGER.error("查询数据字典类别信息未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("查询数据字典类别信息失败！");
			e.printStackTrace();
			LOGGER.error("查询数据字典类别信息失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}

	/**
	 * 初始化数据字典类别数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/initDataDicTypeAddData", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void initDataDicTypeAddData(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			if (!StringUtils.isEmpty(parameter)) {
				Map<String, Object> map = new HashMap<String, Object>();

				// 绑定数据字典
				this.bindDataDicParam(map, new String[] { DataDicConstants.DIC_CATEGORY_TYPE });

				if (!StringUtils.isEmpty(parameter.get("dicTypeId"))) {
					DataDicTypeBean dataDicTypeData = this.dataDicService.queryDataDicTypeById(parameter);
					map.put("dataDicTypeData", dataDicTypeData);
				}
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("初始化数据字典类别数据成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("初始化数据字典类别数据未成功！");
				LOGGER.error("初始化数据字典类别数据未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始化数据字典类别数据失败！");
			e.printStackTrace();
			LOGGER.error("初始化数据字典类别数据失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}

	/**
	 * 保存数据字典类别
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveDataDicType", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveDataDicType(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);

			// 检测是否存在
			int isExists = this.dataDicService.queryDataDicTypeIsExists(parameter);
			if (isExists > 0) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("数据字典类别已存在！");
				LOGGER.info("数据字典类别已存在！");
			} else {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);

				int result = 0;
				if (StringUtils.isEmpty(parameter.get("dicTypeId"))) {
					parameter.put("createUserId", userBean.getUserId());
					parameter.put("dicTypeId", UUIDUtil.createUUID32());
					result = this.dataDicService.saveDataDicType(parameter);
				} else {
					parameter.put("updateUserId", userBean.getUserId());
					result = this.dataDicService.updateDataDicTypeById(parameter);
				}

				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("保存数据字典类别成功！");
					LOGGER.info("保存数据字典类别成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("保存数据字典类别未成功！");
					LOGGER.info("保存数据字典类别未成功！");
				}
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("保存数据字典类别失败！");
			e.printStackTrace();
			LOGGER.error("保存数据字典类别失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 根据ids删除数据字典类别
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteDataDicTypeByIds", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void deleteDataDicTypeByIds(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			String idsStr = parameter.get("ids").toString();
			if (!StringUtils.isEmpty(parameter) && !StringUtils.isEmpty(idsStr)) {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("updateUserId", userBean.getUserId());
				int result = this.dataDicService.deleteDataDicTypeByIds(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("删除数据字典类别成功！");
					LOGGER.info("根据ids删除数据字典类别成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("删除数据字典类别未成功！");
					LOGGER.info("根据ids删除数据字典类别未成功！");
				}
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("删除数据字典类别失败！");
			e.printStackTrace();
			LOGGER.error("根据ids删除数据字典类别失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 根数据字典类别id查询数据字典明细
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryDataDicItemByIdPaging", method = RequestMethod.POST)
	public void queryDataDicItemByIdPaging(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			DataPaging<Object> parameter = RequestParamToObjectUtil.requestParamParseToPageMap(request);
			String dicTypeId = parameter.getParameter().get("dicTypeId").toString();
			if (!StringUtils.isEmpty(parameter) && !StringUtils.isEmpty(dicTypeId)) {
				DataPaging<Object> result = this.dataDicService.queryDataDicItemByIdPaging(parameter);

				if (null != result) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("rows", result.getRows());
					map.put("total", result.getTotal());

					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultData(map);
					LOGGER.info("查询数据字典明细成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("查询数据字典明细未成功！");
					LOGGER.error("查询数据字典明细未成功");
				}
			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("rows", new ArrayList<Object>());
				map.put("total", 0);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("请求参数为空！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("查询数据字典明细失败！");
			e.printStackTrace();
			LOGGER.error("查询数据字典明细失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}

	/**
	 * 重新载入数据字典
	 */
	@RequestMapping(value = "/reloadDataDic", method = RequestMethod.POST)
	public void reloadDataDic(HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> dataDicMap = this.dataDicService.queryInitDataDicType();
			if (null != dataDicMap) {
				DataDicLoader dataDicLoader = SpringServletContextUtil.getBeanByName("system.dataDicLoader");
				dataDicLoader.setDataDicMap(dataDicMap);
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultMsg("重新载入数据字典成功！");
				LOGGER.info("重新载入数据字典成功,数据字典类别条数：" + dataDicMap.size());
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("重新载入数据字典未成功！");
				LOGGER.info("重新载入数据字典未成功！");
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("重新载入数据字典失败！");
			e.printStackTrace();
			LOGGER.error("重新载入数据字典失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}

	/**
	 * 初始化数据字典明细数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/initDataDataDicItemAddData", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void initDataDataDicItemAddData(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			if (!StringUtils.isEmpty(parameter) && !StringUtils.isEmpty(parameter.get("dicTypeId"))) {
				Map<String, Object> map = new HashMap<String, Object>();
				// 绑定数据字典
				this.bindDataDicParam(map, new String[] {DataDicConstants.STATUS_TYPE});
				
				String dataDicItems = this.dataDicService.queryDataDicItemByCondition(parameter, CreateTreeUtil.STATE_CLOSED, CreateTreeUtil.STATE_CLOSED);
				map.put("dataDicItemList", JsonUtil.jsonStrToList(dataDicItems, Map.class));
				
				
				if(!StringUtils.isEmpty(parameter.get("dicItemId"))){
					DataDicItemBean dataDicItemData=this.dataDicService.queryDataDicItemById(parameter);
					map.put("dataDicItemData", dataDicItemData);
				}
				
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("初始化数据字典明细数据成功！");

			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("初始化数据字典明细数据未成功！");
				LOGGER.error("初始化数据字典明细数据未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始化数据字典明细数据失败！");
			e.printStackTrace();
			LOGGER.error("初始化数据字典明细数据失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}

	/**
	 * 保存数据字典明细
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveDataDicItem", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveDataDicItem(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);

			// 检测是否存在
			int isExists = this.dataDicService.queryDataDicItemIsExists(parameter);
			if (isExists > 0) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("数据字典明细已存在！");
				LOGGER.info("数据字典明细已存在！");
			} else {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				
				DataDicTypeBean dataDicTypeBean=this.dataDicService.queryDataDicTypeById(parameter);
				parameter.put("dicTypeCode", dataDicTypeBean.getDicTypeCode());
				
				int result = 0;
				if (StringUtils.isEmpty(parameter.get("dicItemId"))) {
					parameter.put("createUserId", userBean.getUserId());
					parameter.put("dicItemId", UUIDUtil.createUUID32());
					result = this.dataDicService.saveDataDicItem(parameter);
				} else {
					parameter.put("updateUserId", userBean.getUserId());
					result = this.dataDicService.updateDataDicItemById(parameter);
				}

				if (result > 0) {
					String dataDicItems = this.dataDicService.queryDataDicItemByCondition(parameter, CreateTreeUtil.STATE_CLOSED, CreateTreeUtil.STATE_CLOSED);
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultData(JsonUtil.jsonStrToList(dataDicItems, Map.class));
					resultData.setResultMsg("保存数据字典明细成功！");
					LOGGER.info("保存数据字典明细成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("保存数据字典明细未成功！");
					LOGGER.info("保存数据字典明细未成功！");
				}
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("保存数据字典明细失败！");
			e.printStackTrace();
			LOGGER.error("保存数据字典明细失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}


	/**
	 * 查询数据字典明细信息
	 * 
	 * @param request
	 * @return
	 */
	// todo
	@RequestMapping(value = "/queryDataDicItemById", method = RequestMethod.GET)
	public void queryDataDicItemById(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			String dicItemId = String.valueOf(request.getParameter("dicItemId"));
			if (!StringUtils.isEmpty(dicItemId)) {

				Map<String, Object> map = new HashMap<String, Object>();

				// 绑定数据字典
				this.bindDataDicParam(map, new String[] {  DataDicConstants.STATUS_TYPE});

				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("dicItemId", dicItemId);
				DataDicItemBean dataDicItemData = this.dataDicService.queryDataDicItemById(parameter);
				map.put("dataDicItemData", dataDicItemData);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("查询数据字典明细信息成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("查询数据字典明细信息未成功！");
				LOGGER.error("查询数据字典明细信息未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("查询数据字典明细信息失败！");
			e.printStackTrace();
			LOGGER.error("查询数据字典明细信息失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}
	
	

	
	/**
	 * 根据ids删除数据字典明细
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteDataDicItemByIds", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void deleteDataDicItemByIds(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			if (!StringUtils.isEmpty(parameter)) {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("updateUserId", userBean.getUserId());

				int result = this.dataDicService.deleteDataDicItemByIds(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("删除数据字典明细成功！");
					LOGGER.info("根据ids删除数据字典明细成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("删除数据字典明细未成功！");
					LOGGER.info("根据ids删除数据字典明细未成功！");
				}
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("删除数据字典明细失败！");
			e.printStackTrace();
			LOGGER.error("根据ids删除数据字典明细失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}
	
	
	
	/**
	 * 查询数据字典明细分配角色
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryDataDicItemRoleDistribute", method = RequestMethod.GET)
	public void queryDataDicItemRoleDistribute(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ResultData resultData = new ResultData();
		try {
			String dicItemId = String.valueOf(request.getParameter("dicItemId"));
			if (!StringUtils.isEmpty(dicItemId)) {
				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("dicItemId", dicItemId);
				
				//角色
				List<RoleBean> roleData = this.roleService.queryAllRoleList();
				
				//明细角色
				List<DataDicItemRoleMappingBean> dataDicItemRoleData=this.dataDicService.queryDataDicItemRoleMappingById(parameter);
				
				Map<String, Object> map = new HashMap<>();
				map.put("roleData", roleData);
				map.put("dataDicItemRoleData", dataDicItemRoleData);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("查询数据字典明细分配角色成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("查询数据字典明细分配角色未成功！");
				LOGGER.error("查询数据字典明细分配角色未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("查询数据字典明细分配角色失败！");
			e.printStackTrace();
			LOGGER.error("查询数据字典明细分配角色失败,错误——>" + e);
		}
		
		resultData.printJsonData(response);

	}

	/**
	 * 保存数据字典明细角色映射
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveDataDicItemRoleMapping", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveDataDicItemRoleMapping(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			if (null != parameter) {
				String dicItemId = String.valueOf(parameter.get("dicItemId"));

				if (!StringUtils.isEmpty(dicItemId)) {
					int result = this.dataDicService.saveDataDicItemRoleMapping(parameter);
					if (result > 0) {
						resultData.setResultType(CommonConstants.RESULT_SUCCESS);
						resultData.setResultMsg("保存数据字典明细角色映射成功！");
						LOGGER.info("保存数据字典明细角色映射成功！");
					} else {
						resultData.setResultType(CommonConstants.RESULT_FAILURE);
						resultData.setResultMsg("保存数据字典明细角色映射未成功！");
						LOGGER.info("保存数据字典明细角色映射未成功！");
					}
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("请求参数错误！");
					LOGGER.info("请求参数错误！");
				}

			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("保存数据字典明细角色映射失败！");
			e.printStackTrace();
			LOGGER.error("保存数据字典明细角色映射失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}
	
	
	
	

	/**
	 * 数据字典明细移动序号(-1失败，1成功，-2已到顶、已到底)
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/updateDataDicItemSortNumById", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void updateDataDicItemSortNumById(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);

			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			parameter.put("updateUserId", userBean.getUserId());
			int result = this.dataDicService.updateDataDicItemSortNumById(parameter);
			if (result == 1) {
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultMsg("数据字典明细移动序号成功！");
				LOGGER.info("数据字典明细移动序号成功！");
			} else if (result == -2) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("数据字典明细移动序号已到顶或已到底！");
				LOGGER.info("数据字典明细移动序号已到顶或已到底！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_ERROR);
				resultData.setResultMsg("数据字典明细移动序号失败！");
				LOGGER.info("数据字典明细移动序号失败！");
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("数据字典明细移动序号失败！");
			e.printStackTrace();
			LOGGER.error("数据字典明细移动序号失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}

	

	public DataDicService getDataDicService() {
		return dataDicService;
	}

	@Resource(name="system.dataDicService")
	public void setDataDicService(DataDicService dataDicService) {
		this.dataDicService = dataDicService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	@Resource(name="system.roleService")
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

}
