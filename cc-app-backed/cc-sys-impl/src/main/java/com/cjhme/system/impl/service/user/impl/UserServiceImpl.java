package com.cjhme.system.impl.service.user.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import com.cjhme.common.constant.BusniessMarkConstants;
import com.cjhme.common.constant.CommonConstants;
import com.cjhme.common.model.base.DataPaging;
import com.cjhme.common.model.fileupload.FileDataBean;
import com.cjhme.common.model.user.UserBean;
import com.cjhme.common.model.user.UserDeptMappingBean;
import com.cjhme.common.model.user.UserRoleMappingBean;
import com.cjhme.common.util.UUIDUtil;
import com.cjhme.system.impl.dao.fileupload.FileDataDao;
import com.cjhme.system.impl.dao.user.UserDao;
import com.cjhme.system.impl.service.user.UserService;

/**
 * 
 * <p>
 * Title: UserServiceImpl.java
 * </p>
 * Description: 用户
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
@Service("system.userService")
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	private FileDataDao  fileDataDao;


	/**
	 * 用户登录
	 * 
	 * @param parameter
	 * @return
	 */
	public UserBean login(Map<String, Object> parameter) {
		return this.userDao.login(parameter);
	}

	/**
	 * 判断用户是否存在
	 * 
	 * @param parameter
	 * @return
	 */
	public int queryUserIsExists(Map<String, Object> parameter) {
		return this.userDao.queryUserIsExists(parameter);
	}

	/**
	 * 添加用户
	 * 
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int saveUser(Map<String, Object> parameter) {
		return this.userDao.saveUser(parameter);
	}

	/**
	 * 根据条件分页查询用户
	 * 
	 * @param pageParameter
	 * @return
	 */
	public DataPaging<Object> queryUserByConditionPaging(DataPaging<Object> pageParameter) {
		return this.userDao.queryUserByConditionPaging(pageParameter);
	}

	/**
	 * 根据id查询用户
	 * 
	 * @param parameter
	 * @return
	 */
	public UserBean queryUserById(Map<String, Object> parameter) {
		return this.userDao.queryUserById(parameter);
	}

	/**
	 * 根据id修改用户
	 * 
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int updateUserById(Map<String, Object> parameter) {
		return this.userDao.updateUserById(parameter);
	}

	/**
	 * 根据id修改密码
	 * 
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int updateUserPasswordById(Map<String, Object> parameter) {
		return this.userDao.updateUserPasswordById(parameter);
	}

	/**
	 * 根据ids重置用户密码
	 * 
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int updateUserPwdByIds(Map<String, Object> parameter) {
		return this.userDao.updateUserPwdByIds(parameter);
	}

	/**
	 * 根据ids删除用户
	 * 
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int deleteUserByIds(Map<String, Object> parameter) {

		// 删除用户角色映射
		this.userDao.deleteUserRoleMappingByIds(parameter);

		// 删除用户部门映射
		this.userDao.deleteUserDeptMappingByIds(parameter);

		// 删除用户
		return this.userDao.deleteUserByIds(parameter);

	}

	/**
	 * 根据用户id查询用户角色映射
	 * 
	 * @param parameter
	 * @return
	 */
	public List<UserRoleMappingBean> queryUserRoleMappingById(Map<String, Object> parameter) {
		return this.userDao.queryUserRoleMappingById(parameter);
	}

	/**
	 * 保存用户角色映射
	 * 
	 * @param parameter
	 * @return
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public int saveUserRoleMapping(Map<String, Object> parameter) {
		// 删除用户角色映射
		this.userDao.deleteUserRoleMappingById(parameter);

		// 保存用户角色映射
		List<String> roleIds = (List<String>) parameter.get("roleIds");
		for (String roleId : roleIds) {
			parameter.put("roleId", roleId);
			parameter.put("urmId", UUIDUtil.createUUID32());
			this.userDao.saveUserRoleMapping(parameter);
		}
		return 1;
	}

	/**
	 * 根据角色id查询用户
	 * 
	 * @param parameter
	 * @return
	 */
	public List<UserBean> queryUserByRoleId(Map<String, Object> parameter) {
		return this.userDao.queryUserByRoleId(parameter);
	}

	/**
	 * 根据用户id查询用户部门映射
	 * 
	 * @param parameter
	 * @return
	 */
	public List<UserDeptMappingBean> queryUserDeptMappingById(Map<String, Object> parameter) {
		return this.userDao.queryUserDeptMappingById(parameter);
	}

	/**
	 * 保存用户部门映射
	 * 
	 * @param parameter
	 * @return
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public int saveUserDeptMapping(Map<String, Object> parameter) {
		this.userDao.deleteUserDeptMappingById(parameter);
		List<String> deptIds = (List<String>) parameter.get("deptIds");
		for (String deptId : deptIds) {
			parameter.put("deptId", deptId);
			parameter.put("udmId", UUIDUtil.createUUID32());
			this.userDao.saveUserDeptMapping(parameter);
		}
		return 1;
	}

	/**
	 * 根据部门id查询用户
	 * 
	 * @param parameter
	 * @return
	 */
	public List<UserBean> queryUserByDeptId(Map<String, Object> parameter) {
		return this.userDao.queryUserByDeptId(parameter);
	}

	/**
	 * 查询用户列表
	 * 
	 * @param
	 * @return
	 */
	public List<UserBean> queryUserList(Map<String, Object> parameter) {
		return this.userDao.queryUserList(parameter);
	}

	/**
	 * 保存用头像
	 * 
	 * @param
	 * @param userBean
	 * @return
	 */
	public String saveUserAvatarImg(Map<String, Object> parameter, UserBean userBean)throws Exception {
		String userAvatarPath = parameter.get("userAvatarPath").toString();
		String data = parameter.get("data").toString();

		// //去除base64标识
		if (data.startsWith("data:image")) {
			data = data.replace("data:image/png;base64,", "");
		}
		byte[] bs = Base64Utils.decodeFromString(data);
		for (int i = 0; i < bs.length; ++i) {
			if (bs[i] < 0) {// 调整异常数据
				bs[i] += 256;
			}
		}
		
		// 创建文件夹
		File f = new File(userAvatarPath);
		if (!f.exists()) {
			f.mkdirs();
		}

		String fileName = UUID.randomUUID()+CommonConstants.IMG_FILE_SUFFIX_PNG;
		String realPath=userAvatarPath+fileName;

		FileUtils.writeByteArrayToFile(new File(realPath), bs);
		
		//保存头像信息
		Map<String, Object> fileDataParameter = new HashMap<String, Object>();
		fileDataParameter.put("busniessMark",BusniessMarkConstants.USER_HEAD_IMG);
		fileDataParameter.put("busniessId", userBean.getUserId());
		fileDataParameter.put("fileUrl", realPath);
		fileDataParameter.put("fileName", fileName);
		fileDataParameter.put("fileDescript", userBean.getRealName()+"的用户头像！");
		
		List<FileDataBean> fileDataList=this.fileDataDao.queryFileDataByBMarkAndBId(fileDataParameter);
		if(null!=fileDataList && !fileDataList.isEmpty()){
			fileDataParameter.put("fileId", fileDataList.get(0).getFileId());
			fileDataParameter.put("updateUserId", userBean.getUserId());
			this.fileDataDao.updateFileDataByFileId(fileDataParameter);
		}else{
			fileDataParameter.put("createUserId", userBean.getUserId());
			fileDataParameter.put("fileId", UUIDUtil.createUUID32());
			this.fileDataDao.saveFileData(fileDataParameter);
		}
		
		
		
		return fileName;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	@Resource(name="system.userDao")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public FileDataDao getFileDataDao() {
		return fileDataDao;
	}

	@Resource(name="system.fileDataDao")
	public void setFileDataDao(FileDataDao fileDataDao) {
		this.fileDataDao = fileDataDao;
	}
	
	

}
