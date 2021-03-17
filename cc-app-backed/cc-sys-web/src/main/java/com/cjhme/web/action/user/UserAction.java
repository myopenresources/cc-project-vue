package com.cjhme.web.action.user;

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
import com.cjhme.common.constant.DataDicItemConstants;
import com.cjhme.common.model.base.DataPaging;
import com.cjhme.common.model.base.ResultData;
import com.cjhme.common.model.button.ButtonBean;
import com.cjhme.common.model.datadic.DataDicItemBean;
import com.cjhme.common.model.fileupload.FileDataBean;
import com.cjhme.common.model.role.RoleBean;
import com.cjhme.common.model.user.UserBean;
import com.cjhme.common.model.user.UserDeptMappingBean;
import com.cjhme.common.model.user.UserRoleMappingBean;
import com.cjhme.common.util.CreateTreeUtil;
import com.cjhme.common.util.DigestUtil;
import com.cjhme.common.util.JsonUtil;
import com.cjhme.common.util.ReadImgUtil;
import com.cjhme.common.util.RequestParamToObjectUtil;
import com.cjhme.common.util.UUIDUtil;
import com.cjhme.system.impl.service.dept.DeptService;
import com.cjhme.system.impl.service.fileupload.FileDataService;
import com.cjhme.system.impl.service.role.RoleService;
import com.cjhme.system.impl.service.user.UserService;
import com.cjhme.web.base.action.SysBaseAction;


/**
 * 
 * <p>
 * Title: UserAction.java
 * </p>
 * Description: 用户
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
@Controller
@Scope("prototype")
@RequestMapping("/user")
public class UserAction extends SysBaseAction {

	Log LOGGER = LogFactory.getLog(UserAction.class);

	private UserService userService;

	private RoleService roleService;

	private DeptService deptService;

	private FileDataService fileDataService;


	/**
	 * 修改当前用户密码(-1：失败，-2：密码和确认密码为空，-3：密码或确认密码为空,-4:密码与确认密码不一致,1成功)
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/updateCurrentUserPassword", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void updateCurrentUserPassword(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);

			if (null != userBean  && null != parameter) {
				String password = String.valueOf(parameter.get("password"));
				String confPassword = String.valueOf(parameter.get("confPassword"));

				if (null != password && !"".equals(password) && null != confPassword && !"".equals(confPassword)) {
					if (password.equals(confPassword)) {
						parameter.put("userId", userBean.getUserId());
						parameter.put("password", DigestUtil.md5(password));
						parameter.put("updateUserId", userBean.getUserId());

						int result = this.userService.updateUserPasswordById(parameter);
						if (result > 0) {
							resultData.setResultType(CommonConstants.RESULT_SUCCESS);
							resultData.setResultMsg("修改密码成功！");
							LOGGER.info("修改密码成功！");
						} else {
							resultData.setResultType(CommonConstants.RESULT_FAILURE);
							resultData.setResultMsg("修改密码未成功！");
							LOGGER.info("修改密码未成功！");
						}
					} else {
						resultData.setResultType(CommonConstants.RESULT_FAILURE);
						resultData.setResultMsg("密码与确认密码不一致！");
						LOGGER.info("密码与确认密码不一致！");
					}
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("密码或确认密码为空！");
					LOGGER.info("密码或确认密码为空！");
				}

			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("密码和确认密码为空！");
				LOGGER.info("密码和确认密码为空！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("修改密码失败！");
			e.printStackTrace();
			LOGGER.error("修改密码失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}

	/**
	 * 个人资料查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryCurrentUserInfo", method = RequestMethod.GET)
	public void queryCurrentUserInfo(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			if (null != userBean && !"".equals(userBean) && !"null".equals(userBean)) {
				
				Map<String, Object> map = new HashMap<String, Object>();

				// 绑定数据字典
				this.bindDataDicParam(map, new String[] { DataDicConstants.SEX_TYPE, DataDicConstants.EMAIL_SWITCH_TYPE, DataDicConstants.SMS_SWITCH_TYPE, DataDicConstants.STATUS_TYPE });
				
				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("userId", userBean.getUserId());
				UserBean user = this.userService.queryUserById(parameter);
				
				
				map.put("userData", user);
				
				

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("个人资料查询成功！");

			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("个人资料查询未成功！");
				LOGGER.error("个人资料查询未成功");
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("个人资料查询失败！");
			LOGGER.error("个人资料查询,错误——>" + e);
		}
		resultData.printJsonData(response);

	}
	
	/**
	 * 用户头像预览
	 * 
	 * @param session
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/previewUserAvatar", method = { RequestMethod.POST, RequestMethod.GET })
	public void previewUserAvatar(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		try {
			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			if (!StringUtils.isEmpty(userBean)) {
				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("busniessId", userBean.getUserId());
				parameter.put("busniessMark", BusniessMarkConstants.USER_HEAD_IMG);
				FileDataBean fileDataBean = this.fileDataService.queryOneFileDataByBMarkAndBId(parameter);
				if (!StringUtils.isEmpty(fileDataBean)) {
					ReadImgUtil.readImgToResponst(response, fileDataBean.getFileUrl(), "用户头像", CommonConstants.IMG_FILE_SUFFIX_PNG);
				}else{
					LOGGER.error("用户头像预览未成功");
				}
				
			}else{
				LOGGER.error("用户头像预览未成功");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(" 用户头像预览失败,错误——>" + e);
		}
	}
	
	/**
	 * 上传头像
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/uploadUserAvatarImg", method = { RequestMethod.POST})
	@SuppressWarnings("unchecked")
	public void uploadImg(HttpSession session,HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			DataDicItemBean dataDicItemBean=this.getDataDicItemByDicTypeCodeAndItemCode(DataDicConstants.SYS_FILE_UPLOAD_PATH, DataDicItemConstants.AVATAR_PATH);
			if (!StringUtils.isEmpty(userBean) && !StringUtils.isEmpty(parameter) && !StringUtils.isEmpty(dataDicItemBean) ) {
				parameter.put("userAvatarPath", dataDicItemBean.getDicItemValue());
				
				String fileName=this.userService.saveUserAvatarImg(parameter, userBean);
				
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(fileName);
				LOGGER.info("上传头像成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("上传头像未成功！");
				LOGGER.error("上传头像未成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultData = new ResultData();
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("上传头像失败！");
			LOGGER.error("上传头像失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}


	/**
	 * 根据id修改密码(-1：失败，-2：密码和确认密码为空，-3：密码或确认密码为空,-4:密码与确认密码不一致,1成功)
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	// todo
	@RequestMapping(value = "/updateUserPasswordById", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void updateUserPasswordById(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);

			if (null != parameter && !"".equals(parameter)) {
				String password = String.valueOf(parameter.get("password"));
				String confPassword = String.valueOf(parameter.get("confPassword"));

				if (null != password && !"".equals(password) && null != confPassword && !"".equals(confPassword)) {
					if (password.equals(confPassword)) {
						parameter.put("password", DigestUtil.md5(password));

						UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
						parameter.put("updateUserId", userBean.getUserId());

						int result = this.userService.updateUserPasswordById(parameter);
						if (result > 0) {
							resultData.setResultType(CommonConstants.RESULT_SUCCESS);
							resultData.setResultMsg("修改密码成功！");
							LOGGER.info("修改密码成功！");
						} else {
							resultData.setResultType(CommonConstants.RESULT_FAILURE);
							resultData.setResultMsg("修改密码未成功！");
							LOGGER.info("修改密码未成功！");
						}
					} else {
						resultData.setResultType(CommonConstants.RESULT_FAILURE);
						resultData.setResultMsg("密码与确认密码不一致！");
						LOGGER.info("密码与确认密码不一致！");
					}
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("密码或确认密码为空！");
					LOGGER.info("密码或确认密码为空！");
				}

			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("密码和确认密码为空！");
				LOGGER.info("密码和确认密码为空！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("修改密码失败！");
			e.printStackTrace();
			LOGGER.error("修改密码失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}

	/**
	 * 初始用户管理数据
	 * 
	 * @param request
	 * @return
	 */
	// todo
	@RequestMapping(value = "/initUserManageData", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void initUserManageData(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			if (null != userBean && !"".equals(userBean) && !"null".equals(userBean) && null != parameter) {
				Map<String, Object> map = new HashMap<String, Object>();
				// 绑定数据字典
				this.bindDataDicParam(map, new String[] {DataDicConstants.STATUS_TYPE });

				parameter.put("userId", userBean.getUserId());
				parameter.put("busniessMark", BusniessMarkConstants.USER_MANAGE);
				List<ButtonBean> buttonList = this.getBusniessBtn(parameter);
				map.put("buttonList", buttonList);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("初始用户管理数据成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("初始用户管理数据未成功！");
				LOGGER.error("初始用户管理数据未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始用户管理数据失败！");
			e.printStackTrace();
			LOGGER.error("初始用户管理数据失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}

	/**
	 * 根据条件分页查询用户
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryUserByConditionPaging", method = RequestMethod.POST)
	public void queryUserByConditionPaging(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			DataPaging<Object> result = this.userService.queryUserByConditionPaging(RequestParamToObjectUtil.requestParamParseToPageMap(request));
			if (null != result) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("rows", result.getRows());
				map.put("total", result.getTotal());

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("根据条件分页查询用户成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("根据条件分页查询用户未成功！");
				LOGGER.error("根据条件分页查询用户未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("根据条件分页查询用户失败！");
			e.printStackTrace();
			LOGGER.error("根据条件分页查询用户失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}

	/**
	 * 初始化用户数据
	 * 
	 * @param request
	 * @return
	 */
	// todo
	@RequestMapping(value = "/initUserAddData", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void initUserAddData(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			if (!StringUtils.isEmpty(parameter)) {
				Map<String, Object> map = new HashMap<String, Object>();

				// 绑定数据字典
				this.bindDataDicParam(map, new String[] { DataDicConstants.SEX_TYPE, DataDicConstants.EMAIL_SWITCH_TYPE, DataDicConstants.SMS_SWITCH_TYPE, DataDicConstants.STATUS_TYPE });
               
				if(!StringUtils.isEmpty(parameter.get("userId"))){
					UserBean userBean = this.userService.queryUserById(parameter);
					map.put("userData", userBean);
                }
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("初始化用户数据成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("初始化用户数据未成功！");
				LOGGER.error("初始化用户数据未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始化用户数据数据失败！");
			e.printStackTrace();
			LOGGER.error("初始化用户数据失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}

	/**
	 * 保存用户
	 * 
	 * @param request
	 * @return
	 */
	// todo
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveUser(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);

			// 检测是否存在
			int isExists = this.userService.queryUserIsExists(parameter);
			if (isExists > 0) {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("编号或用户名已存在！");
				LOGGER.info("编号或用户名已存在！");
			} else {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				
				int result=0;
				if(StringUtils.isEmpty(parameter.get("userId"))){
					parameter.put("createUserId", userBean.getUserId());
					parameter.put("userId", UUIDUtil.createUUID32());
					parameter.put("password", DigestUtil.md5(CommonConstants.DEFAULT_PWD));
					result = this.userService.saveUser(parameter);
				}else{
					parameter.put("updateUserId", userBean.getUserId());
					result = this.userService.updateUserById(parameter);
				}
				
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("保存用户成功！");
					LOGGER.info("保存用户成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("保存用户未成功！");
					LOGGER.info("保存用户未成功！");
				}
			}
		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("保存用户失败！");
			e.printStackTrace();
			LOGGER.error("保存用户失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}
	
	
	/**
	 * 查询用户信息
	 * 
	 * @param request
	 * @return
	 */
	// todo
	@RequestMapping(value = "/queryUserById", method = RequestMethod.GET)
	public void queryUserById(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			String userId = String.valueOf(request.getParameter("userId"));
			if (!StringUtils.isEmpty(userId)) {
				
				Map<String, Object> map = new HashMap<String, Object>();

				// 绑定数据字典
				this.bindDataDicParam(map, new String[] { DataDicConstants.SEX_TYPE, DataDicConstants.EMAIL_SWITCH_TYPE, DataDicConstants.SMS_SWITCH_TYPE, DataDicConstants.STATUS_TYPE });
				
				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("userId", userId);
				UserBean user = this.userService.queryUserById(parameter);
				map.put("userData", user);

				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("查询用户信息成功！");
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("查询用户信息未成功！");
				LOGGER.error("查询用户信息未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("查询用户信息失败！");
			e.printStackTrace();
			LOGGER.error("查询用户信息失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}
	
	/**
	 * 根据ids删除用户
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	// todo
	@RequestMapping(value = "/deleteUserByIds", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void deleteUserByIds(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			String idsStr = parameter.get("ids").toString();
			if (!StringUtils.isEmpty(parameter) && !StringUtils.isEmpty(idsStr)) {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("updateUserId", userBean.getUserId());
				int result = this.userService.deleteUserByIds(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("删除用户成功！");
					LOGGER.info("根据ids删除用户成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("删除用户未成功！");
					LOGGER.info("根据ids删除用户未成功！");
				}
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("删除用户失败！");
			e.printStackTrace();
			LOGGER.error("根据ids删除用户失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}
	
	
	/**
	 * 根据ids重置用户密码
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/resetUserPwdByIds", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void resetUserPwdByIds(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			if (!StringUtils.isEmpty(parameter)) {
				UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
				parameter.put("updateUserId", userBean.getUserId());
				parameter.put("password", DigestUtil.md5(CommonConstants.DEFAULT_PWD));
				int result = this.userService.updateUserPwdByIds(parameter);
				if (result > 0) {
					resultData.setResultType(CommonConstants.RESULT_SUCCESS);
					resultData.setResultMsg("重置用户密码成功！");
					LOGGER.info("根据ids重置用户密码成功！");
				} else {
					resultData.setResultType(CommonConstants.RESULT_FAILURE);
					resultData.setResultMsg("重置用户密码未成功！");
					LOGGER.info("根据ids重置用户密码未成功！");
				}
			} else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("请求参数错误！");
				LOGGER.info("请求参数错误！");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("重置用户密码失败！");
			e.printStackTrace();
			LOGGER.error("根据ids重置用户密码失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}
	
	
	/**
	 * 初始化用户角色分配数据
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/initUserRoleDistributeData", method = RequestMethod.GET)
	public void initUserRoleDistributeData(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			
			String userId = String.valueOf(request.getParameter("userId"));
			if (null != userId && !"".equals(userId) && !"null".equals(userId)) {
				List<RoleBean> roleData = this.roleService.queryAllRoleList();

				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("userId", userId);
				List<UserRoleMappingBean> userRoleData = this.userService.queryUserRoleMappingById(parameter);

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("roleData", roleData);
				map.put("userRoleData", userRoleData);
				
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("初始化用户角色分配数据成功！");
				
			}else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("初始化用户角色分配数据未成功！");
				LOGGER.error("初始化用户角色分配数据未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始化用户角色分配数据失败！");
			e.printStackTrace();
			LOGGER.error("初始化用户角色分配数据失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}
	
	
	
	/**
	 * 保存用户角色
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveUserRoleMapping", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveUserRoleMapping(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			if (null != parameter) {
				List<String> roleIds = (List<String>) parameter.get("roleIds");
				String userId = String.valueOf(parameter.get("userId"));

				if (null != userId && !"".equals(userId) && null != roleIds) {
					int result = this.userService.saveUserRoleMapping(parameter);
					if (result > 0) {
						resultData.setResultType(CommonConstants.RESULT_SUCCESS);
						resultData.setResultMsg("添加用户角色成功！");
						LOGGER.info("保存用户角色成功！");
					} else {
						resultData.setResultType(CommonConstants.RESULT_FAILURE);
						resultData.setResultMsg("添加用户角色未成功！");
						LOGGER.info("保存用户角色未成功！");
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
			resultData.setResultMsg("保存用户角色失败！");
			e.printStackTrace();
			LOGGER.error("保存用户角色失败,错误——>" + e);
		}
		resultData.printJsonData(response);
	}
	
	
	/**
	 * 初始化用户部门分配数据
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/initUserDeptDistributeData", method = RequestMethod.GET)
	public void initUserDeptDistributeData(HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			
			String userId = String.valueOf(request.getParameter("userId"));
			if (null != userId && !"".equals(userId) && !"null".equals(userId)) {
				String deptTreeData =this.deptService.queryDeptList(CreateTreeUtil.STATE_OPEN, CreateTreeUtil.STATE_CLOSED);

				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("userId", userId);
				List<UserDeptMappingBean> userDeptList=this.userService.queryUserDeptMappingById(parameter);

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("deptTreeData", JsonUtil.jsonStrToList(deptTreeData, Map.class));
				map.put("userDeptData", userDeptList);
				
				resultData.setResultType(CommonConstants.RESULT_SUCCESS);
				resultData.setResultData(map);
				LOGGER.info("初始化用户部门分配数据成功！");
				
			}else {
				resultData.setResultType(CommonConstants.RESULT_FAILURE);
				resultData.setResultMsg("初始化用户部门分配数据未成功！");
				LOGGER.error("初始化用户部门分配数据未成功");
			}

		} catch (Exception e) {
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("初始化用户部门分配数据失败！");
			e.printStackTrace();
			LOGGER.error("初始化用户部门分配数据失败,错误——>" + e);
		}

		resultData.printJsonData(response);

	}
	
	/**
	 * 保存用户部门
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveUserDeptMapping", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public void saveUserDeptMapping(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ResultData resultData = new ResultData();
		try {
			Map<String, Object> parameter = (Map<String, Object>) RequestParamToObjectUtil.jsonParseToObject(request, Map.class);
			if (null != parameter) {
				List<String> deptIds = (List<String>) parameter.get("deptIds");
				String userId = String.valueOf(parameter.get("userId"));

				if (null != userId && !"".equals(userId) && null != deptIds) {
					int result = this.userService.saveUserDeptMapping(parameter);
					if (result > 0) {
						resultData.setResultType(CommonConstants.RESULT_SUCCESS);
						resultData.setResultMsg("添加用户部门成功！");
						LOGGER.info("保存用户部门成功！");
					} else {
						resultData.setResultType(CommonConstants.RESULT_FAILURE);
						resultData.setResultMsg("添加用户部门未成功！");
						LOGGER.info("添加用户部门未成功！");
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
			resultData.setResultMsg("添加用户部门失败！");
			e.printStackTrace();
			LOGGER.error("保存用户部门失败,错误——>" + e);
		}

		resultData.printJsonData(response);
	}
	
	

	public UserService getUserService() {
		return userService;
	}

	@Resource(name="system.userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	@Resource(name="system.roleService")
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public DeptService getDeptService() {
		return deptService;
	}

	@Resource(name="system.deptService")
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public FileDataService getFileDataService() {
		return fileDataService;
	}

	@Resource(name="system.fileDataService")
	public void setFileDataService(FileDataService fileDataService) {
		this.fileDataService = fileDataService;
	}


}
