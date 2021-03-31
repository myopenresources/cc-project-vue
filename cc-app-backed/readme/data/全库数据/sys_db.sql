/*
Navicat MySQL Data Transfer

Source Server         : def
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : sys_db

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2021-03-31 17:57:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_access_url`
-- ----------------------------
DROP TABLE IF EXISTS `t_access_url`;
CREATE TABLE `t_access_url` (
  `ACCESS_URL_ID` varchar(64) NOT NULL COMMENT '访问地址ID',
  `ACCESS_URL` varchar(300) DEFAULT NULL COMMENT '访问地址',
  `ACCESS_URL_NAME` varchar(100) DEFAULT NULL COMMENT '访问地址名称',
  `BUSNIESS_MARK` varchar(100) DEFAULT NULL COMMENT '业务标识',
  `STATUS` char(1) DEFAULT NULL COMMENT '状态(1正常，2锁定，3删除)',
  `CREATE_USER_ID` varchar(64) DEFAULT NULL COMMENT '创建人ID',
  `UPDATE_USER_ID` varchar(64) DEFAULT NULL COMMENT '修改人ID',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ACCESS_URL_ID`),
  KEY `FK_ACCESS_URL_C_USER_ID` (`CREATE_USER_ID`),
  KEY `FK_ACCESS_URL_U_USER_ID` (`UPDATE_USER_ID`),
  CONSTRAINT `FK_ACCESS_URL_C_USER_ID` FOREIGN KEY (`CREATE_USER_ID`) REFERENCES `t_user` (`USER_ID`),
  CONSTRAINT `FK_ACCESS_URL_U_USER_ID` FOREIGN KEY (`UPDATE_USER_ID`) REFERENCES `t_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_access_url
-- ----------------------------
INSERT INTO `t_access_url` VALUES ('12ggdfgdfrewrgwrg', '/button/queryButtonById.jhtml', '查询按钮信息', 'BTN_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-09 15:54:11', null);
INSERT INTO `t_access_url` VALUES ('1dffgsdfgsdgdfgdfgdfgdfg0', '/button/saveButton.jhtml', '保存按钮', 'BTN_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-09 15:52:48', null);
INSERT INTO `t_access_url` VALUES ('1esgrgregergergerg3', '/dataDic/initDataDicManageData.jhtml', '初始数据字典管理数据', 'DATA_DIC_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:31:19', null);
INSERT INTO `t_access_url` VALUES ('1gdfgdfgdfgdfg', '/accessUrl/initAccessUrlManageData.jhtml', '初始访问地址管理数据', 'ACCESS_URL_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-09 15:30:44', '2018-06-09 15:37:03');
INSERT INTO `t_access_url` VALUES ('1gdfgdfgdfgdfgdfg4', '/dataDic/queryDataDicTypeList.jhtml', '查询数据字典类别列表', 'DATA_DIC_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:31:48', null);
INSERT INTO `t_access_url` VALUES ('1gdfgdfgdfgdfgdfgdfgdfg6', '/dataDic/saveDataDicType.jhtml', '保存数据字典类别', 'DATA_DIC_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:33:03', null);
INSERT INTO `t_access_url` VALUES ('1gdfgdfgdfgdfgdfgdfgdsfgdfg7', '/dataDic/deleteDataDicTypeByIds.jhtml', '根据ids删除数据字典类别', 'DATA_DIC_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:33:33', null);
INSERT INTO `t_access_url` VALUES ('1gdfsgsdfgdfsgdsgssdsdghj3gre1', '/button/deleteButtonByIds.jhtml', '根据ids删除按钮', 'BTN_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-09 15:53:40', null);
INSERT INTO `t_access_url` VALUES ('1gdsfgsdfgdfgdsfgdsfgdfsg9', '/dataDic/reloadDataDic.jhtml', '重新载入数据字典', 'DATA_DIC_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:34:21', null);
INSERT INTO `t_access_url` VALUES ('1gfdgdfgdsgdsgdfgsdg5', '/dataDic/initDataDicTypeAddData.jhtml', '初始化数据字典类别数据', 'DATA_DIC_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:32:28', null);
INSERT INTO `t_access_url` VALUES ('20gdfgdfgdfgdfgdffg', '/dataDic/initDataDataDicItemAddData.jhtml', '初始化数据字典明细数据', 'DATA_DIC_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:34:51', null);
INSERT INTO `t_access_url` VALUES ('22gdfsgdfgdfgdfgdf', '/dataDic/queryDataDicItemById.jhtml', '查询数据字典明细信息', 'DATA_DIC_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:35:43', null);
INSERT INTO `t_access_url` VALUES ('23gdfgdfsgsdg', '/dataDic/deleteDataDicItemByIds.jhtml', '根据ids删除数据字典明细', 'DATA_DIC_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:36:24', null);
INSERT INTO `t_access_url` VALUES ('25gdfgdfsgdfgdfgdfg', '/dataDic/saveDataDicItemRoleMapping.jhtml', '保存数据字典明细角色映射', 'DATA_DIC_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:37:19', null);
INSERT INTO `t_access_url` VALUES ('26gdfgdfgdfgdgdgdfgdfg', '/dataDic/queryDataDicTypeById.jhtml', '查询数据字典类别信息', 'DATA_DIC_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:39:13', null);
INSERT INTO `t_access_url` VALUES ('2gdfdfgdfgdfgd8', '/dept/queryDeptByDeptId.jhtml', '查询部门信息', 'DEPT_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:40:45', null);
INSERT INTO `t_access_url` VALUES ('2gdfgdfgdfggdsfgsdg4', '/dataDic/queryDataDicItemRoleDistribute.jhtml', '查询数据字典明细分配角色', 'DATA_DIC_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:36:55', null);
INSERT INTO `t_access_url` VALUES ('2gdfgdsfgdsfgdf9', '/dept/queryDeptByCondition.jhtml', '根据条件查询部门', 'DEPT_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:41:14', null);
INSERT INTO `t_access_url` VALUES ('2gfdgdfgdfg', '/accessUrl/queryAccessUrlByConditionPaging.jhtml', '根据条件分页查询访问地址', 'ACCESS_URL_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-09 15:31:31', null);
INSERT INTO `t_access_url` VALUES ('2gsdfgdsfgdsf1', '/dataDic/saveDataDicItem.jhtml', '保存数据字典明细', 'DATA_DIC_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:35:15', null);
INSERT INTO `t_access_url` VALUES ('36867u7b5b54b56b754b7b', '/loginLog/queryLoginLogByConditionPaging.jhtml', '根据条件分页查询登录日志', 'LOGIN_LOG_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:49:51', null);
INSERT INTO `t_access_url` VALUES ('375b54b75b75b8', '/systemLog/downloadSystemLog.jhtml', '系统日志下载', 'SYS_LOG_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:51:42', null);
INSERT INTO `t_access_url` VALUES ('375b5bbtbytrb9', '/systemLog/deleteSystemLog.jhtml', '删除系统日志', 'SYS_LOG_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:53:37', null);
INSERT INTO `t_access_url` VALUES ('37b754b754b75b75b546b', '/systemLog/initSystemLogManageData.jhtml', '初始系统日志管理数据', 'SYS_LOG_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:50:26', null);
INSERT INTO `t_access_url` VALUES ('3bc110a666b6490d820819f3a2329d3f', '/dataPermissions/initDataPermissionsManageData.jhtml', '初始数据权限管理数据', 'DATA_PERMISSIONS_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-27 09:10:38', null);
INSERT INTO `t_access_url` VALUES ('3f696ebe5d8d4181afde428239284412', '/sysInfo/initSysInfoData.jhtml', '初始系统信息数据', 'SYS_INFO', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-12 05:51:57', null);
INSERT INTO `t_access_url` VALUES ('3gdfgdfgdfg1', '/dept/saveDept.jhtml', '保存部门', 'DEPT_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:42:11', null);
INSERT INTO `t_access_url` VALUES ('3gdfgdfgdfgdfg', '/accessUrl/initAccessUrlAddData.jhtml', '初始化访问地址添加数据', 'ACCESS_URL_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2018-06-09 15:33:15', '2019-12-27 09:13:40');
INSERT INTO `t_access_url` VALUES ('3gdfgdfgdfgdfgdfg3', '/dept/queryDeptUsers.jhtml', '查询部门用户', 'DEPT_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:43:00', null);
INSERT INTO `t_access_url` VALUES ('3gdfgdfgdgdgdfg5', '/loginLog/initLoginLogManageData.jhtml', '初始登录日志管理数据', 'LOGIN_LOG_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:49:20', null);
INSERT INTO `t_access_url` VALUES ('3gdfggdfgdfgdfgd0', '/dept/initDeptAddData.jhtml', '初始化部门添加数据', 'DEPT_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:41:45', null);
INSERT INTO `t_access_url` VALUES ('3gdfsfgsdgdgdsfgdfg2', '/dept/deleteDeptByIds.jhtml', '根据ids删除部门', 'DEPT_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:42:38', null);
INSERT INTO `t_access_url` VALUES ('3sdgsdgdgdfgdg4', '/dept/queryDeptList.jhtml', '查询所有部门结构', 'DEPT_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:43:34', null);
INSERT INTO `t_access_url` VALUES ('43gdfsgdfgdfgdfgdfg', '/menu/queryMenuByMenuId.jhtml', '查询菜单信息', 'MENU_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:56:57', null);
INSERT INTO `t_access_url` VALUES ('44gfdsgdfgdfgdfgdfg', '/menu/queryMenuList.jhtml', '查询菜单列表', 'MENU_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:57:37', null);
INSERT INTO `t_access_url` VALUES ('45gfdsgdfgdfgdfgdfg', '/menu/queryMenuByCondition.jhtml', '根据条件查询菜单', 'MENU_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:58:00', null);
INSERT INTO `t_access_url` VALUES ('46gdfgdfgdfgdfgdfgdfg', '/menu/initMenuAddData.jhtml', '初始化菜单添加数据', 'MENU_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:58:23', null);
INSERT INTO `t_access_url` VALUES ('47gdfgdfgdfgdfgdfgdfgdf', '/menu/saveMenu.jhtml', '保存菜单', 'MENU_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:58:46', null);
INSERT INTO `t_access_url` VALUES ('48gdfgdfgdfgdfgdfg', '/menu/deleteMenuByIds.jhtml', '根据ids删除菜单', 'MENU_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:59:06', null);
INSERT INTO `t_access_url` VALUES ('49gdfsgdsfgdsfgdfg', '/notice/initSysNoticeManageData.jhtml', '初始系统公告管理数据', 'SYS_NOTICE_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:00:00', null);
INSERT INTO `t_access_url` VALUES ('4gdfgdgdfgdfgdfg', '/accessUrl/saveAccessUrl.jhtml', '保存访问地址', 'ACCESS_URL_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-09 15:34:45', null);
INSERT INTO `t_access_url` VALUES ('4sdfbgfdfssssgsd1', '/system/initHomeData.jhtml', '初始化主页', 'SYS_COMMON', '1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2018-06-10 09:55:01', '2021-03-23 16:03:04');
INSERT INTO `t_access_url` VALUES ('4sggdfgdsfdfsgdfg2', '/menu/initMenuManageData.jhtml', '初始菜单管理数据', 'MENU_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:56:32', null);
INSERT INTO `t_access_url` VALUES ('4ybtybytryebtrb0', '/system/initMainData.jhtml', '初始化首页', 'SYS_COMMON', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:54:41', null);
INSERT INTO `t_access_url` VALUES ('5fdsfsfsfdfsdaf5', '/notice/queryUserSysNoticeById.jhtml', '查询用户系统公告信息', 'SYS_NOTICE_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:03:24', null);
INSERT INTO `t_access_url` VALUES ('5fjfhdgsdfasdfsfsdf8', '/role/initRoleManageData.jhtml', '初始角色管理数据', 'ROLE_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:06:55', null);
INSERT INTO `t_access_url` VALUES ('5fsaffsdfsdfsdf3', '/notice/deleteSysNoticeByIds.jhtml', '根据ids删除系统公告', 'SYS_NOTICE_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:02:33', null);
INSERT INTO `t_access_url` VALUES ('5fsdafsafsdaf4', '/notice/querySysNoticeById.jhtml', '查询系统公告信息', 'SYS_NOTICE_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:02:56', null);
INSERT INTO `t_access_url` VALUES ('5fsdafsdghfjfh1', '/notice/initSysNoticeAddData.jhtml', '初始化系统公告数据', 'SYS_NOTICE_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:01:42', null);
INSERT INTO `t_access_url` VALUES ('5fsdfsdfsadfsdf2', '/notice/saveSysNotice.jhtml', '保存系统公告', 'SYS_NOTICE_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:02:08', null);
INSERT INTO `t_access_url` VALUES ('5fsdfsdfsdfsdfsd7', '/notice/queryUserSysNoticeByConditionPaging.jhtml', '根据条件分页查询用户系统公告', 'USER_NOTICE_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:05:05', null);
INSERT INTO `t_access_url` VALUES ('5fsdfsfsdfdfsdf6', '/notice/initUserSysNoticeManageData.jhtml', '初始用户系统公告管理数据', 'USER_NOTICE_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:04:35', null);
INSERT INTO `t_access_url` VALUES ('5gfdssdg5sggwg0', '/notice/querySysNoticeByConditionPaging.jhtml', '根据条件分页查询系统公告', 'SYS_NOTICE_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:01:13', null);
INSERT INTO `t_access_url` VALUES ('61gdfsgsdfghgjhgjghjjhg', '/role/saveRole.jhtml', '保存角色', 'ROLE_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:07:58', null);
INSERT INTO `t_access_url` VALUES ('62gfdgdfgdfgdf', '/role/deleteRoleByIds.jhtml', '根据ids删除角色', 'ROLE_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:08:17', null);
INSERT INTO `t_access_url` VALUES ('66gfdgdfgfdgdfg', '/role/saveRolePermissionDistribute.jhtml', '保存角色权限分配', 'ROLE_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:09:42', null);
INSERT INTO `t_access_url` VALUES ('67gdfgdsfgsdgdfg', '/task/initTaskManageData.jhtml', '初始待办管理数据', 'USER_TASK_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:11:17', null);
INSERT INTO `t_access_url` VALUES ('68sgfdgdfgdfgdfgdfg', '/task/queryTaskByConditionPaging.jhtml', '根据条件分页查询待办', 'USER_TASK_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:11:39', null);
INSERT INTO `t_access_url` VALUES ('6gdfgdfsgdsg9', '/task/updateTaskById.jhtml', '根据id修改待办状态', 'USER_TASK_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:12:08', null);
INSERT INTO `t_access_url` VALUES ('6gdfgfdgdfggdgdfg3', '/role/queryRoleById.jhtml', '查询角色信息', 'ROLE_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:08:35', null);
INSERT INTO `t_access_url` VALUES ('6gdgdfgdffgdgdfgdfg5', '/role/queryRolePermissionDistribute.jhtml', '查询角色权限分配', 'ROLE_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:09:17', null);
INSERT INTO `t_access_url` VALUES ('6gdgfdgdfgfgdf4', '/role/queryRoleUsers.jhtml', '查询角色用户', 'ROLE_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:08:55', null);
INSERT INTO `t_access_url` VALUES ('6gdsfgdfgdsfgdfgdfg', '/accessUrl/queryAccessUrlById.jhtml', '查询访问地址信息', 'ACCESS_URL_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-09 15:35:52', null);
INSERT INTO `t_access_url` VALUES ('6jhgjgjghjhgjghjgh0', '/role/initRoleAddData.jhtml', '初始化角色数据', 'ROLE_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:07:37', null);
INSERT INTO `t_access_url` VALUES ('79fsdfsdfsdgsgsag', '/user/resetUserPwdByIds.jhtml', '根据ids重置用户密码', 'USER_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:18:35', null);
INSERT INTO `t_access_url` VALUES ('7agsdagsagsadggsg7', '/user/queryUserById.jhtml', '查询用户信息', 'USER_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:17:50', null);
INSERT INTO `t_access_url` VALUES ('7asgasdgsag6', '/user/saveUser.jhtml', '保存用户', 'USER_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:17:27', null);
INSERT INTO `t_access_url` VALUES ('7asgsadgsagsadg8', '/user/deleteUserByIds.jhtml', '根据ids删除用户', 'USER_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:18:15', null);
INSERT INTO `t_access_url` VALUES ('7fdsaffsfsaf4', '/user/updateUserPasswordById.jhtml', '根据id修改密码', 'USER_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:15:03', null);
INSERT INTO `t_access_url` VALUES ('7gdfgdfgdfgdfg0', '/user/updateCurrentUserPassword.jhtml', '修改当前用户密码', 'USER_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:13:11', null);
INSERT INTO `t_access_url` VALUES ('7gdfgsdfgdfgdfgdfgdfg', '/button/initButtonManageData.jhtml', '初始按钮管理数据', 'BTN_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-09 15:47:48', null);
INSERT INTO `t_access_url` VALUES ('7gsadgasdgasdgas2', '/user/initUserManageData.jhtml', '初始用户管理数据', 'USER_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:13:59', null);
INSERT INTO `t_access_url` VALUES ('7gsagsagsdagsag3', '/user/queryUserByConditionPaging.jhtml', '根据条件分页查询用户', 'USER_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:15:59', null);
INSERT INTO `t_access_url` VALUES ('7gsdgsadgsagsag5', '/user/initUserAddData.jhtml', '初始化用户数据', 'USER_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:17:01', null);
INSERT INTO `t_access_url` VALUES ('7sdgsdgsdagasggh1', '/user/queryCurrentUserInfo.jhtml', '个人资料查询', 'USER_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:13:39', null);
INSERT INTO `t_access_url` VALUES ('80ffdsfsdafasfdsf', '/user/initUserRoleDistributeData.jhtml', '初始化用户角色分配数据', 'USER_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:18:56', null);
INSERT INTO `t_access_url` VALUES ('82asffsdfasdfsfsdaf', '/user/initUserDeptDistributeData.jhtml', '初始化用户部门分配数据', 'USER_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:19:39', null);
INSERT INTO `t_access_url` VALUES ('83afdsadafsdfdssf', '/user/saveUserDeptMapping.jhtml', '保存用户部门', 'USER_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:20:01', null);
INSERT INTO `t_access_url` VALUES ('84fsdfsdfsdafsadf', '/user/previewUserAvatar.jhtml', '用户头像预览', 'USER_HEAD_IMG', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:20:40', null);
INSERT INTO `t_access_url` VALUES ('85fdsfsdfsdfsdfsdf', '/user/uploadUserAvatarImg.jhtml', '上传头像', 'USER_HEAD_IMG', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:20:57', null);
INSERT INTO `t_access_url` VALUES ('88380e50bd004745a3ccfb85e72aabf3', '/dataPermissions/queryDataPermissionsById.jhtml', '查询数据权限信息', 'DATA_PERMISSIONS_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-27 09:15:14', null);
INSERT INTO `t_access_url` VALUES ('8fsfsadfsdf1', '/user/saveUserRoleMapping.jhtml', '保存用户角色', 'USER_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:19:15', null);
INSERT INTO `t_access_url` VALUES ('8gdfgdfgdfgdfgdgdfgdfgdfg', '/button/queryButtonByConditionPaging.jhtml', '根据条件分页查询按钮', 'BTN_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-09 15:48:52', null);
INSERT INTO `t_access_url` VALUES ('96c4075e36c643e0af43028005fe0569', '/dataPermissions/initDataPermissionsAddData.jhtml', '初始化数据权限添加数据', 'DATA_PERMISSIONS_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-27 09:14:11', null);
INSERT INTO `t_access_url` VALUES ('9gfdgdfgdfgdfgdfgdfgdfg', '/button/initButtonAddData.jhtml', '初始化按钮数据', 'BTN_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-09 15:51:57', null);
INSERT INTO `t_access_url` VALUES ('a4fe8b2c9ce543719596e7f92ef0e12b', '/dataPermissions/saveDataPermissions.jhtml', '保存数据权限', 'DATA_PERMISSIONS_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-27 09:14:34', null);
INSERT INTO `t_access_url` VALUES ('ae162d184bbd494882c29db315586ef9', '/dataPermissions/queryDataPermissionsByConditionPaging.jhtml', '根据条件分页查询数据权限', 'DATA_PERMISSIONS_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-27 09:11:16', null);
INSERT INTO `t_access_url` VALUES ('d15a0d4d5aef496d8d3a912ecb7980b6', '/dataPermissions/deleteDataPermissionsByIds.jhtml', '根据ids删除数据权限', 'DATA_PERMISSIONS_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-27 09:14:52', null);
INSERT INTO `t_access_url` VALUES ('fafdsfsdfsadfa1sdfas11', '/dept/queryDeptByConditionPaging.jhtml', '根据条件分页查询部门', 'DEPT_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-11-30 10:53:59', null);
INSERT INTO `t_access_url` VALUES ('gdfgdfgdfgdfg27', '/dept/initDeptManageData.jhtml', '初始部门管理数据', 'DEPT_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:40:16', null);
INSERT INTO `t_access_url` VALUES ('gdfgdfsgdsgdfsgdfg18', '/dataDic/queryDataDicItemByIdPaging.jhtml', '根数据字典类别id查询数据字典明细', 'DATA_DIC_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 09:33:57', null);
INSERT INTO `t_access_url` VALUES ('gdfggfdsgsdfgdfgd5', '/accessUrl/deleteAccessUrlByIds.jhtml', '根据ids删除访问地址', 'ACCESS_URL_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-09 15:35:23', null);
INSERT INTO `t_access_url` VALUES ('jhg59hgjghjghjghjgfhjgh', '/role/queryRoleByConditionPaging.jhtml', '根据条件分页查询角色', 'ROLE_MANAGE', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:07:17', null);

-- ----------------------------
-- Table structure for `t_button`
-- ----------------------------
DROP TABLE IF EXISTS `t_button`;
CREATE TABLE `t_button` (
  `BUTTON_ID` varchar(64) NOT NULL COMMENT 'ID',
  `BUTTON_CODE` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '鎸夐挳缂栧彿',
  `BUTTON_NAME` varchar(50) DEFAULT NULL COMMENT '按钮名称',
  `FUNCTION_NAME` varchar(60) DEFAULT NULL COMMENT '函数名称',
  `BUSNIESS_MARK` varchar(100) DEFAULT NULL COMMENT '业务标识',
  `LAYOUT_MARK` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '布局标识',
  `ICON_CLS` varchar(64) DEFAULT NULL COMMENT '样式',
  `BUTTON_DESCRIBE` varchar(200) DEFAULT NULL COMMENT '按钮描述',
  `SORT_NUM` int(2) DEFAULT NULL COMMENT '排序',
  `STATUS` char(1) DEFAULT NULL COMMENT '状态(1正常，2锁定，3删除)',
  `CREATE_USER_ID` varchar(64) DEFAULT NULL COMMENT '创建人ID',
  `UPDATE_USER_ID` varchar(64) DEFAULT NULL COMMENT '修改人ID',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `BTN_CLS` varchar(64) DEFAULT NULL COMMENT '按钮样式',
  `BTN_EXP` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '按钮表达式',
  PRIMARY KEY (`BUTTON_ID`),
  KEY `FK_BUTTON_C_USER_ID` (`CREATE_USER_ID`),
  KEY `FK_BUTTON_U_USER_ID` (`UPDATE_USER_ID`),
  CONSTRAINT `FK_BUTTON_C_USER_ID` FOREIGN KEY (`CREATE_USER_ID`) REFERENCES `t_user` (`USER_ID`),
  CONSTRAINT `FK_BUTTON_U_USER_ID` FOREIGN KEY (`UPDATE_USER_ID`) REFERENCES `t_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_button
-- ----------------------------
INSERT INTO `t_button` VALUES ('12hfghdfhfdhfgh', 'roleAdd', '添加', 'roleAdd', 'ROLE_MANAGE', 'TOOL_BTN', 'icon-plus', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-20 17:36:12', null, 'primary', null);
INSERT INTO `t_button` VALUES ('13hdfhdfghfds', 'roleView', '查看', 'roleView', 'ROLE_MANAGE', 'ROW_BTN', 'icon-search', '', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-20 17:36:43', null, 'info', null);
INSERT INTO `t_button` VALUES ('16gdfsgdfgdsg', 'roleUsers', '角色用户', 'roleUsers', 'ROLE_MANAGE', 'ROW_BTN', 'icon-user', '', '5', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-20 17:38:55', null, 'info', null);
INSERT INTO `t_button` VALUES ('1bfbvcbcvxbvcxbbbxcb7', 'rolePermissionDistribute', '权限分配', 'rolePermissionDistribute', 'ROLE_MANAGE', 'ROW_BTN', 'icon-setting', '', '7', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-20 17:40:13', null, 'warning', null);
INSERT INTO `t_button` VALUES ('1fsdfsdfsdfsadf', 'userAdd', '添加', 'userAdd', 'USER_MANAGE', 'TOOL_BTN', 'icon-plus', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-19 17:28:31', null, 'primary', null);
INSERT INTO `t_button` VALUES ('1fsgdfsgddf5', 'deleteRole', '删除', 'deleteRole', 'ROLE_MANAGE', 'TOOL_BTN', 'icon-delete', '', '4', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-20 17:38:21', null, 'error', null);
INSERT INTO `t_button` VALUES ('1hdfghfghfghfgh1', 'deptUsers', '部门人员', 'deptUsers', 'DEPT_MANAGE', 'RIGHT_TOOL_BTN', 'icon-usergroup-add', '', '6', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-20 17:33:41', null, 'info', null);
INSERT INTO `t_button` VALUES ('1hgfhfghfg0', 'deleteDept', '删除', 'deleteDept', 'DEPT_MANAGE', 'LEFT_TOOL_BTN', 'icon-delete', '', '4', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-20 17:32:02', null, 'error', null);
INSERT INTO `t_button` VALUES ('1safsafsafbfdgsg4', 'roleEdit', '编辑', 'roleEdit', 'ROLE_MANAGE', 'ROW_BTN', 'icon-edit', '', '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-20 17:37:10', null, 'success', null);
INSERT INTO `t_button` VALUES ('1tretrgsdgdfsgsdgsdg8', 'menuAdd', '添加', 'menuAdd', 'MENU_MANAGE', 'LEFT_TOOL_BTN', 'icon-plus', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:30:58', null, 'primary', null);
INSERT INTO `t_button` VALUES ('1yy54yhtrbbbb9', 'menuEdit', '编辑', 'menuEdit', 'MENU_MANAGE', 'RIGHT_TOOL_BTN', 'icon-edit', '', '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:31:48', null, 'success', null);
INSERT INTO `t_button` VALUES ('24c0d24080ee406c9d88fe93ecb78b81', 'dataPermissionsEdit', '编辑', 'dataPermissionsEdit', 'DATA_PERMISSIONS_MANAGE', 'ROW_BTN', 'icon-edit', '', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-27 09:19:05', null, 'success', '');
INSERT INTO `t_button` VALUES ('26fdsgdfsgdsfggdsf', 'dataDicTypeEdit', '编辑', 'dataDicTypeEdit', 'DATA_DIC_MANAGE', 'LEFT_ROW_BTN', 'icon-edit', '', '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:37:19', null, 'success', null);
INSERT INTO `t_button` VALUES ('29hfdfghfghdfhdfh', 'dataDicItemView', '查看', 'dataDicItemView', 'DATA_DIC_MANAGE', 'RIGHT_ROW_BTN', 'icon-search', '', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:39:08', null, 'info', null);
INSERT INTO `t_button` VALUES ('2aadsafsdfsdf1', 'buttonAdd', '添加', 'buttonAdd', 'BTN_MANAGE', 'TOOL_BTN', 'icon-plus', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:34:57', null, 'primary', null);
INSERT INTO `t_button` VALUES ('2dfsfgdgfdsuiutiuyi4', 'deleteButton', '删除', 'deleteButton', 'BTN_MANAGE', 'TOOL_BTN', 'icon-delete', '', '4', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:35:27', null, 'error', null);
INSERT INTO `t_button` VALUES ('2dsfgdgdfgdfgdgdsg2', 'buttonView', '查看', 'buttonView', 'BTN_MANAGE', 'ROW_BTN', 'icon-search', '', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:34:37', null, 'info', null);
INSERT INTO `t_button` VALUES ('2fsdfsdfsdfsdf', 'userView', '查看', 'userView', 'USER_MANAGE', 'ROW_BTN', 'icon-search', '', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-19 17:34:08', null, 'info', null);
INSERT INTO `t_button` VALUES ('2gdfsbdsjhghfdsgdgsd0', 'deleteMenu', '删除', 'deleteMenu', 'MENU_MANAGE', 'LEFT_TOOL_BTN', 'icon-delete', '', '5', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:34:54', null, 'error', null);
INSERT INTO `t_button` VALUES ('2hjhgjkklhjghgjfhfdh3', 'buttonEdit', '编辑', 'buttonEdit', 'BTN_MANAGE', 'ROW_BTN', 'icon-edit', '', '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:35:03', null, 'success', null);
INSERT INTO `t_button` VALUES ('2tryeyruiibbxvcb5', 'dataDicTypeAdd', '添加', 'dataDicTypeAdd', 'DATA_DIC_MANAGE', 'LEFT_TOOL_BTN', 'icon-plus', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:36:48', null, 'primary', null);
INSERT INTO `t_button` VALUES ('2vzxvxzvzxv8', 'dataDicItemAdd', '添加', 'dataDicItemAdd', 'DATA_DIC_MANAGE', 'RIGHT_TOOL_BTN', 'icon-plus', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:38:37', null, 'primary', null);
INSERT INTO `t_button` VALUES ('2xvxcvzvzxvdsgxv7', 'deleteDataDic', '删除', 'deleteDataDic', 'DATA_DIC_MANAGE', 'LEFT_TOOL_BTN', 'icon-delete', '', '4', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:37:55', null, 'error', null);
INSERT INTO `t_button` VALUES ('33afasfsafas', 'downloadFile', '下载', 'downloadFile', 'SYS_LOG_MANAGE', 'ROW_BTN', 'icon-download', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:45:43', null, 'success', null);
INSERT INTO `t_button` VALUES ('38gfdsgsdfg', 'accessUrlView', '查看', 'accessUrlView', 'ACCESS_URL_MANAGE', 'ROW_BTN', 'icon-search', '', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-08-29 16:28:27', null, 'info', null);
INSERT INTO `t_button` VALUES ('3dgfdsgvvzcvfg9', 'accessUrlEdit', '编辑', 'accessUrlEdit', 'ACCESS_URL_MANAGE', 'ROW_BTN', 'icon-edit', '', '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-08-29 16:29:06', null, 'success', null);
INSERT INTO `t_button` VALUES ('3fsadfasfas1', 'deleteDicItem', '删除', 'deleteDicItem', 'DATA_DIC_MANAGE', 'RIGHT_TOOL_BTN', 'icon-delete', '', '4', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:40:08', null, 'error', null);
INSERT INTO `t_button` VALUES ('3fsafsdfsaf2', 'dicItemRoleDistribute', '分配角色', 'dicItemRoleDistribute', 'DATA_DIC_MANAGE', 'RIGHT_ROW_BTN', 'icon-setting', '', '7', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:41:20', null, 'warning', null);
INSERT INTO `t_button` VALUES ('3fsdafasfsafb6', 'taskView', '查看', 'taskView', 'USER_TASK_MANAGE', 'ROW_BTN', 'icon-search', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-07-22 17:02:50', null, 'info', null);
INSERT INTO `t_button` VALUES ('3fsfsdfsdffsafsad', 'userEdit', '编辑', 'userEdit', 'USER_MANAGE', 'ROW_BTN', 'icon-edit', '', '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-19 17:35:19', null, 'success', null);
INSERT INTO `t_button` VALUES ('3gdsfgvbcgdhdgh7', 'accessUrlAdd', '添加', 'accessUrlAdd', 'ACCESS_URL_MANAGE', 'TOOL_BTN', 'icon-plus', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2018-08-29 16:12:05', '2019-11-10 11:12:27', 'primary', null);
INSERT INTO `t_button` VALUES ('3gfdsgsdfgdsfg0', 'dataDicItemEdit', '编辑', 'dataDicItemEdit', 'DATA_DIC_MANAGE', 'RIGHT_ROW_BTN', 'icon-edit', '', '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:39:31', null, 'success', null);
INSERT INTO `t_button` VALUES ('3sdafsavasdfs5', 'updateTaskStatus', '取消待办', 'updateTaskStatus', 'USER_TASK_MANAGE', 'ROW_BTN', 'icon-edit', '', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-07-22 16:46:32', null, 'success', 'data.status===\'1\'');
INSERT INTO `t_button` VALUES ('3vcxzcvzvxzcv4', 'deleteFile', '删除', 'deleteFile', 'SYS_LOG_MANAGE', 'ROW_BTN', 'icon-delete', '', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:46:07', null, 'error', null);
INSERT INTO `t_button` VALUES ('41sgdfgdsfg4454', 'userPasswordReset', '重置密码', 'userPasswordReset', 'USER_MANAGE', 'TOOL_BTN', 'icon-key', '', '6', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-10-17 18:37:50', null, 'success', null);
INSERT INTO `t_button` VALUES ('4543gdfsfg53454', 'sysNoticeEdit', '编辑', 'sysNoticeEdit', 'SYS_NOTICE_MANAGE', 'ROW_BTN', 'icon-edit', '', '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2016-04-04 15:38:30', null, 'success', null);
INSERT INTO `t_button` VALUES ('46fsdfsadfsadf', 'dataDicTypeView', '查看', 'dataDicTypeView', 'DATA_DIC_MANAGE', 'LEFT_ROW_BTN', 'icon-search', '', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-01-24 20:26:03', null, 'info', null);
INSERT INTO `t_button` VALUES ('47gsdgfdsgdsfgdsg', 'userNoticeView', '查看', 'userNoticeView', 'USER_NOTICE_MANAGE', 'ROW_BTN', 'icon-search', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-26 15:09:53', null, 'info', null);
INSERT INTO `t_button` VALUES ('4fsfsadfsddf', 'deleteUser', '删除', 'deleteUser', 'USER_MANAGE', 'TOOL_BTN', 'icon-delete', '', '4', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-19 17:51:33', null, 'error', null);
INSERT INTO `t_button` VALUES ('4gfdsgdfs2', 'sysNoticeAdd', '添加', 'sysNoticeAdd', 'SYS_NOTICE_MANAGE', 'TOOL_BTN', 'icon-plus', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2016-04-03 14:28:27', null, 'primary', null);
INSERT INTO `t_button` VALUES ('4gfdsgsgf0', 'deleteAccessUrl', '删除', 'deleteAccessUrl', 'ACCESS_URL_MANAGE', 'TOOL_BTN', 'icon-delete', '', '4', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-08-29 16:29:42', null, 'error', null);
INSERT INTO `t_button` VALUES ('4gsggsdgdgdfg3', 'sysNoticeView', '查看', 'sysNoticeView', 'SYS_NOTICE_MANAGE', 'ROW_BTN', 'icon-search', '', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2016-04-04 15:06:04', null, 'info', null);
INSERT INTO `t_button` VALUES ('4htetgdfsg435t53455', 'deleteSysNotice', '删除', 'deleteSysNotice', 'SYS_NOTICE_MANAGE', 'TOOL_BTN', 'icon-delete', '', '4', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2016-04-04 15:39:08', null, 'error', null);
INSERT INTO `t_button` VALUES ('5038fc7f3b5846a0a075193079f74dff', 'dataPermissionsView', '查看', 'dataPermissionsView', 'DATA_PERMISSIONS_MANAGE', 'ROW_BTN', 'icon-search', '', '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-27 09:20:47', null, 'info', '');
INSERT INTO `t_button` VALUES ('5fsdfsadfsafsdf', 'userPasswordEdit', '修改密码', 'userPasswordEdit', 'USER_MANAGE', 'ROW_BTN', 'icon-key', '', '5', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-19 17:52:48', null, 'success', null);
INSERT INTO `t_button` VALUES ('76b6091761d04de39f8bd5436c1806dc', 'deleteDataPermissions', '删除', 'deleteDataPermissions', 'DATA_PERMISSIONS_MANAGE', 'TOOL_BTN', 'icon-delete', '', '4', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-27 09:22:18', null, 'error', ' ');
INSERT INTO `t_button` VALUES ('9hghghfghfghfghh', 'deptEdit', '编辑', 'deptEdit', 'DEPT_MANAGE', 'RIGHT_TOOL_BTN', 'icon-edit', '', '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-20 17:31:18', null, 'success', null);
INSERT INTO `t_button` VALUES ('e546d68fd1a24fddae70212ee1a6a5d5', 'dataPermissionsAdd', '添加', 'dataPermissionsAdd', 'DATA_PERMISSIONS_MANAGE', 'TOOL_BTN', 'icon-plus', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-27 09:17:02', null, 'primary', '');
INSERT INTO `t_button` VALUES ('fsadfsadfasdff6', 'userRoleDistribute', '角色分配', 'userRoleDistribute', 'USER_MANAGE', 'ROW_BTN', 'icon-setting', '', '7', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-19 17:56:34', null, 'warning', null);
INSERT INTO `t_button` VALUES ('ggsdgsg7', 'userDeptDistribute', '部门分配', 'userDeptDistribute', 'USER_MANAGE', 'ROW_BTN', 'icon-setting', '', '8', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-19 18:01:58', null, 'warning', null);
INSERT INTO `t_button` VALUES ('gsdgsdgdsgsag8', 'deptAdd', '添加', 'deptAdd', 'DEPT_MANAGE', 'LEFT_TOOL_BTN', 'icon-plus', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-20 17:30:07', null, 'primary', null);

-- ----------------------------
-- Table structure for `t_data_dic_item`
-- ----------------------------
DROP TABLE IF EXISTS `t_data_dic_item`;
CREATE TABLE `t_data_dic_item` (
  `DIC_ITEM_ID` varchar(64) NOT NULL COMMENT 'ID',
  `DIC_TYPE_ID` varchar(64) DEFAULT NULL COMMENT '数据字典分类ID',
  `DIC_TYPE_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '鏁版嵁瀛楀吀鍒嗙被缂栧彿',
  `DIC_ITEM_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '鏄庣粏缂栧彿',
  `DIC_ITEM_PARENT_ID` varchar(64) DEFAULT NULL COMMENT '明细信息父ID',
  `DIC_ITEM_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '鍚嶇О',
  `DIC_ITEM_VALUE` varchar(512) DEFAULT NULL COMMENT '值',
  `SPARE_VALUE` varchar(512) DEFAULT NULL COMMENT '备用值',
  `DEFAULT_VALUE` varchar(512) DEFAULT NULL COMMENT '默认值',
  `DIC_ITEM_DESC` varchar(512) DEFAULT NULL COMMENT '描述',
  `ICON_CLS` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '样式',
  `SORT_NUM` int(3) DEFAULT NULL COMMENT '排序',
  `STATUS` char(1) DEFAULT NULL COMMENT '状态(1正常，2锁定，3删除)',
  `CREATE_USER_ID` varchar(64) DEFAULT NULL COMMENT '创建人ID',
  `UPDATE_USER_ID` varchar(64) DEFAULT NULL COMMENT '修改人ID',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `BY_ROLE` char(1) DEFAULT NULL COMMENT '通过角色过滤(1是，2否)',
  PRIMARY KEY (`DIC_ITEM_ID`),
  KEY `FK_DATA_DIC_ITEM_C_USER_ID` (`CREATE_USER_ID`),
  KEY `FK_DATA_DIC_ITEM_U_USER_ID` (`UPDATE_USER_ID`),
  KEY `FK_DATA_DIC_ITEM_TYPE_ID` (`DIC_TYPE_ID`),
  CONSTRAINT `FK_DATA_DIC_ITEM_C_USER_ID` FOREIGN KEY (`CREATE_USER_ID`) REFERENCES `t_user` (`USER_ID`),
  CONSTRAINT `FK_DATA_DIC_ITEM_DIC_TYPE_ID` FOREIGN KEY (`DIC_TYPE_ID`) REFERENCES `t_data_dic_type` (`DIC_TYPE_ID`),
  CONSTRAINT `FK_DATA_DIC_ITEM_U_USER_ID` FOREIGN KEY (`UPDATE_USER_ID`) REFERENCES `t_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_data_dic_item
-- ----------------------------
INSERT INTO `t_data_dic_item` VALUES ('0b5ad5f78a204021b998df4d58394ed9', 'e2222b2f55714b48b72b82bbee6c8d0e', 'DATA_PERMIT_PROCESSOR', 'system.queryAllProcessor', '-1', '查询所有数据', 'system.queryAllProcessor', '', '', '', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2020-01-09 18:43:42', null, null);
INSERT INTO `t_data_dic_item` VALUES ('10fsdfsdfsdfsdafsdfsdff', '5fdsfdsfdsfsdfsdfsdfs', 'SMS_SWITCH_TYPE', 'OPEN', '-1', '开', '1', null, null, null, null, '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('11fsdfsdfsdfsfjsfsfsdff', '5fdsfdsfdsfsdfsdfsdfs', 'SMS_SWITCH_TYPE', 'CLOSE', '-1', '关', '2', null, null, null, null, '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('12fsdfsdfsdfsdkdfsdfsdfs', '6fsdfsdfsdfsdfdsfsdff', 'IS_SUPER_TYPE', 'YES', '-1', '是', '1', null, null, null, null, '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('13fsdfsdfsdfmsdffsdsdfsdff', '6fsdfsdfsdfsdfdsfsdff', 'IS_SUPER_TYPE', 'NO', '-1', '否', '2', null, null, null, null, '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('14affafsdafdaoaaaafadsfsf', '7fsdfsdfsdfdsfsdfdsff', 'QUERY_IS_SUPER_TYPE', 'QUERY_ALL', '-1', '所有', '-1', null, null, null, null, '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('15fsdfsdffsdaaofsdfsdfsdfs', '7fsdfsdfsdfdsfsdfdsff', 'QUERY_IS_SUPER_TYPE', 'QUERY_YES', '-1', '是', '1', null, null, null, null, '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('16fsdfsdddffsdfsdfsdfsdfs', '7fsdfsdfsdfdsfsdfdsff', 'QUERY_IS_SUPER_TYPE', 'QUERY_NO', '-1', '否', '2', null, null, null, null, '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('1fsdfsdfsdfsdfsdafsdfsadf', '1FSDFSDFSFAAdfasdfsdf', 'STATUS_TYPE', 'STATUS_NORMAL', '-1', '正常', '1', null, null, null, null, '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('20fsdfsdfdsdfsdfsdfsdfef', '8fsdfsdfsdfsdfsdfasfd', 'BUTTON_LAYOUT_MARK_TYPE', 'LEFT_TOOL_BTN', '-1', '左侧工具条按钮', 'LEFT_TOOL_BTN', null, null, null, null, '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('20fsdfsdfdsdfsdfsdfsdfs', '8fsdfsdfsdfsdfsdfasfd', 'BUTTON_LAYOUT_MARK_TYPE', 'TOOL_BTN', '-1', '工具条按钮', 'TOOL_BTN', null, null, null, null, '6', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('21fsdfsdfsddfsdfsdfsdfs', '8fsdfsdfsdfsdfsdfasfd', 'BUTTON_LAYOUT_MARK_TYPE', 'ROW_BTN', '-1', '行按钮', 'ROW_BTN', null, null, null, null, '5', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('22fsdfsdfsddfsdfsdofsdfs', '9aafdsfsdfsdfdsffdfdf', 'QUERY_TASK_STATUS_TYPE', 'QUERY_TASK_STATUS_ALL', '-1', '所有', '-1', null, null, null, null, '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('23fsdfsdfasdfsdfsodfsdfs', '9aafdsfsdfsdfdsffdfdf', 'QUERY_TASK_STATUS_TYPE', 'QUERY_TASK_STATUS_UNTREATED', '-1', '未处理', '1', null, null, null, null, '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('24fsdfsdfsdfsdfosdfsdfs', '9aafdsfsdfsdfdsffdfdf', 'QUERY_TASK_STATUS_TYPE', 'QUERY_TASK_STATUS_TREATED', '-1', '已处理', '2', null, null, null, null, '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('25fsdfsdfsdfsddfsdfsdfs', '10afdsfsdfsdfsdfdsfsd', 'DIC_CATEGORY_TYPE', 'SYS_DIC', '-1', '系统字典', '1', null, null, null, null, '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('26fsdfsdfsdfsddfsdfsdfs', '10afdsfsdfsdfsdfdsfsd', 'DIC_CATEGORY_TYPE', 'BUSSNIESS_DIC', '-1', '业务字典', '2', null, null, null, null, '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('27fsdfsdfsdfdsdfsdofsdfs', '11fsdfsdfsdfsdfsdfsdf', 'SYS_FILE_UPLOAD_PATH', 'AVATAR_PATH', '-1', '头像路径', 'D:/cjhme/images/userAvatar/', null, null, null, null, '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('28fsdfsdfsddfsdfsdofsdfs', '12fdsfsdfsdfsdfsdfsdf', 'BUSNIESS_MARK_TYPE', 'SYS_COMMON', '-1', '系统公共', 'SYS_COMMON', '', '', '', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2018-05-20 22:42:01', '2019-12-12 05:46:35', null);
INSERT INTO `t_data_dic_item` VALUES ('29fsdfsdfsddfsdfsdfosdfs', '12fdsfsdfsdfsdfsdfsdf', 'BUSNIESS_MARK_TYPE', 'USER_HEAD_IMG', '-1', '用户头像', 'USER_HEAD_IMG', null, null, null, null, '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('2fsdfsdfsafsdfsadfsdfsdf', '1FSDFSDFSFAAdfasdfsdf', 'STATUS_TYPE', 'STATUS_LOCK', '-1', '锁定', '2', null, null, null, null, '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('30fsdfsdfsddfsdfsdfsdfs', '12fdsfsdfsdfsdfsdfsdf', 'BUSNIESS_MARK_TYPE', 'USER_MANAGE', '-1', '用户管理', 'USER_MANAGE', null, null, null, null, '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('31fsdfsdfsdfdskdfsdfosdfs', '12fdsfsdfsdfsdfsdfsdf', 'BUSNIESS_MARK_TYPE', 'DEPT_MANAGE', '-1', '部门管理', 'DEPT_MANAGE', null, null, null, null, '4', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('32fsdfsfdfsdfsdfsdfsdfs', '12fdsfsdfsdfsdfsdfsdf', 'BUSNIESS_MARK_TYPE', 'ROLE_MANAGE', '-1', '角色管理', 'ROLE_MANAGE', null, null, null, null, '5', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('33fsdfsdfsdfsdhdfsdfsdfs', '12fdsfsdfsdfsdfsdfsdf', 'BUSNIESS_MARK_TYPE', 'MENU_MANAGE', '-1', '菜单管理', 'MENU_MANAGE', null, null, null, null, '6', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('34fsdfssdfssdfsdhfsdfsdfs', '12fdsfsdfsdfsdfsdfsdf', 'BUSNIESS_MARK_TYPE', 'BTN_MANAGE', '-1', '按钮管理', 'BTN_MANAGE', null, null, null, null, '7', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('35fsdfsdfsd5fsd3fsdfsdfs', '12fdsfsdfsdfsdfsdfsdf', 'BUSNIESS_MARK_TYPE', 'DATA_DIC_MANAGE', '-1', '数据字典管理', 'DATA_DIC_MANAGE', null, null, null, null, '8', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('36fsdfsdf5sdffsdsfsdfsdfs', '12fdsfsdfsdfsdfsdfsdf', 'BUSNIESS_MARK_TYPE', 'SYS_LOG_MANAGE', '-1', '系统日志管理', 'SYS_LOG_MANAGE', null, null, null, null, '9', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('37fsdfsdfs5dffsdffasdfsdfs', '12fdsfsdfsdfsdfsdfsdf', 'BUSNIESS_MARK_TYPE', 'USER_TASK_MANAGE', '-1', '用户待办管理', 'USER_TASK_MANAGE', null, null, null, null, '10', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('38fsdfsdfsdgfsfffsdfsdfs', '12fdsfsdfsdfsdfsdfsdf', 'BUSNIESS_MARK_TYPE', 'ACCESS_URL_MANAGE', '-1', '访问管理', 'ACCESS_URL_MANAGE', null, null, null, null, '11', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('39fsdfsdfs5ffdfsdfsdfsdfs', '12fdsfsdfsdfsdfsdfsdf', 'BUSNIESS_MARK_TYPE', 'SYS_NOTICE_MANAGE', '-1', '系统公告管理', 'SYS_NOTICE_MANAGE', null, null, null, null, '12', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('3fsdfsdfsdfmsfsddfsdfsdfsd', '2fsdfsdfsdfsdfsdfsdfs', 'QUERY_STATUS_TYPE', 'QUERY_STATUS_ALL', '-1', '所有', '-1', null, null, null, null, '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('40fsdfsdfsdbfsfdsfsdfsdfs', '12fdsfsdfsdfsdfsdfsdf', 'BUSNIESS_MARK_TYPE', 'USER_NOTICE_MANAGE', '-1', '用户公告管理', 'USER_NOTICE_MANAGE', null, null, null, null, '13', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('41fsdfsdfsn5dfdfsfdfsdfsdfs', '12fdsfsdfsdfsdfsdfsdf', 'BUSNIESS_MARK_TYPE', 'LOGIN_LOG_MANAGE', '-1', '登录日志管理', 'LOGIN_LOG_MANAGE', null, null, null, null, '13', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('42fsdfsaafsdydfdsfdfsdfsdfs', '13fsdfsdfsdfsdfsdfsdf', 'FORM_STATUS_TYPE', 'FORM_STATUS_NORMAL', '-1', '正常', '1', null, null, null, null, '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('4373eb8e37194a2b8eb241242e4b90f1', 'e2222b2f55714b48b72b82bbee6c8d0e', 'DATA_PERMIT_PROCESSOR', 'system.designatedDeptAndSubsProcessor', '-1', '指定部门及其下级部门', 'system.designatedDeptAndSubsProcessor', '', '', '', '', '7', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2020-01-09 18:50:18', null, null);
INSERT INTO `t_data_dic_item` VALUES ('43fsdfsdfsndffsdfsdfsdfs', '13fsdfsdfsdfsdfsdfsdf', 'FORM_STATUS_TYPE', 'FORM_STATUS_CLOSE', '-1', '关闭', '2', null, null, null, null, '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('44fsdfsdfsdfrgfdgfsdfsdfs', '14fsdfsdfsdfsdfsdfsa', 'QUERY_FORM_STATUS_TYPE', 'QUERY_FORM_STATUS_ALL', '-1', '所有', '-1', null, null, null, null, '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('45d2c3f08f4f4e9298d44ab275f5ea00', 'e2222b2f55714b48b72b82bbee6c8d0e', 'DATA_PERMIT_PROCESSOR', 'system.designatedDeptSubsProcessor', '-1', '指定部门的下级部门', 'system.designatedDeptSubsProcessor', '', '', '', '', '6', '1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2020-01-09 18:45:02', '2020-01-09 18:45:15', null);
INSERT INTO `t_data_dic_item` VALUES ('45fsdfsdfsdfsdfsdfsdfs', '14fsdfsdfsdfsdfsdfsa', 'QUERY_FORM_STATUS_TYPE', 'QUERY_FORM_STATUS_NORMAL', '-1', '正常', '1', null, null, null, null, '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('46fsdfsdfsdffs5dgfsdfsdfs', '14fsdfsdfsdfsdfsdfsa', 'QUERY_FORM_STATUS_TYPE', 'QUERY_FORM_STATUS_CLOSE', '-1', '关闭', '2', null, null, null, null, '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('4fsdfsdfsdfmsdfsddfsdfsdfs', '2fsdfsdfsdfsdfsdfsdfs', 'QUERY_STATUS_TYPE', 'QUERY_STATUS_NORMAL', '-1', '正常', '1', null, null, null, null, '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('545c7ea9efd44c1e8523008a311edaea', '4f0fcd49c8384e209ef66f26ac0fc92b', 'DATA_PERMIT_CATEGORY', 'DEPT_DATA_PERMIT', '-1', '部门数据权限', 'DEPT_DATA_PERMIT', '', '', '', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-27 09:05:14', null, null);
INSERT INTO `t_data_dic_item` VALUES ('5fsfafsafsdfmsdfsadfsdfsdf', '2fsdfsdfsdfsdfsdfsdfs', 'QUERY_STATUS_TYPE', 'QUERY_STATUS_LOCK', '-1', '锁定', '2', null, null, null, null, '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('6fsdfsdfsdfsdfsdafsdfsdf', '3fsdfsdfsdfsdfsdfsdfs', 'SEX_TYPE', 'MALE', '-1', '男', '1', null, null, null, null, '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('79d80bb872a145bbb56142eaa45c9774', 'e2222b2f55714b48b72b82bbee6c8d0e', 'DATA_PERMIT_PROCESSOR', 'system.currentUserProcessor', '-1', '当前用户', 'system.currentUserProcessor', '', '', '', '', '5', '1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-30 16:37:22', '2020-01-09 18:44:30', null);
INSERT INTO `t_data_dic_item` VALUES ('7fsdfsdfsdfsddfsfmsdfsdf', '3fsdfsdfsdfsdfsdfsdfs', 'SEX_TYPE', 'FEMALE', '-1', '女', '2', null, null, null, null, '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('8fsdfsdfsddfsdfsdfmsdfsdf', '4afdsfsdfsdfdsfdsfdff', 'EMAIL_SWITCH_TYPE', 'OPEN', '-1', '开', '1', null, null, null, null, '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('9fsdfsdfsdfsddfsdfsdfsf', '4afdsfsdfsdfdsfdsfdff', 'EMAIL_SWITCH_TYPE', 'CLOSE', '-1', '关', '2', null, null, null, null, '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('a096a99cbc0e4383b692228cdf576038', 'e2222b2f55714b48b72b82bbee6c8d0e', 'DATA_PERMIT_PROCESSOR', 'system.currentUserDeptsProcessor', '-1', '当前用户所有部门', 'system.currentUserDeptsProcessor', '', '', '', '', '3', '1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-30 11:12:47', '2020-01-09 18:44:05', null);
INSERT INTO `t_data_dic_item` VALUES ('a486c4a645b64817a854524860c147d5', '14316fbcd58148f4af8586a6650cf30e', '11', '1', '-1', '1', '1', '', '', '', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2021-03-30 09:28:31', null, null);
INSERT INTO `t_data_dic_item` VALUES ('a7a7c90c73564877a0ed18105c17cabe', 'e2222b2f55714b48b72b82bbee6c8d0e', 'DATA_PERMIT_PROCESSOR', 'system.designatedDeptProcessor', '-1', '指定部门', 'system.designatedDeptProcessor', '', '', '', '', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-30 11:09:07', '2020-01-09 18:43:55', null);
INSERT INTO `t_data_dic_item` VALUES ('afdfsdfdsfdfsdfaaaaf', '12fdsfsdfsdfsdfsdfsdf', 'BUSNIESS_MARK_TYPE', 'SYS_INFO', '-1', '系统信息', 'SYS_INFO', '', '', '', '', '14', '1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-12 13:44:16', '2019-12-12 05:46:47', null);
INSERT INTO `t_data_dic_item` VALUES ('c20c2b6f836c42749d1e0792e51872c9', '4f0fcd49c8384e209ef66f26ac0fc92b', 'DATA_PERMIT_CATEGORY', 'USER_DATA_PERMIT', '-1', '用户数据权限', 'USER_DATA_PERMIT', '', '', '', '', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-27 09:06:02', null, null);
INSERT INTO `t_data_dic_item` VALUES ('c7a8bffe1ea44064bdeb727859205bb8', 'e2222b2f55714b48b72b82bbee6c8d0e', 'DATA_PERMIT_PROCESSOR', 'system.designatedUserProcessor', '-1', '指定用户', 'system.designatedUserProcessor', null, null, null, null, '4', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-30 16:37:49', null, null);
INSERT INTO `t_data_dic_item` VALUES ('e2a22893163d4b35939528a52c4ab449', '12fdsfsdfsdfsdfsdfsdf', 'BUSNIESS_MARK_TYPE', 'DATA_PERMISSIONS_MANAGE', '-1', '数据权限管理', 'DATA_PERMISSIONS_MANAGE', '', '', '', '', '15', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-27 09:08:13', null, null);
INSERT INTO `t_data_dic_item` VALUES ('fdfkkl1relf1djasljflskajflf', '10fdfdfdfdfdfdfdfaad', 'BUTTON_CLS_TYPE', 'BTN_INFO', '-1', '信息', 'info', null, null, null, null, '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-11-10 10:29:30', null, null);
INSERT INTO `t_data_dic_item` VALUES ('fdfkkl1relkfjasljflskajflff', '10fdfdfdfdfdfdfdfaad', 'BUTTON_CLS_TYPE', 'BTN_PRIMARY', '-1', '主调', 'primary', null, null, null, null, '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-11-10 10:29:30', null, null);
INSERT INTO `t_data_dic_item` VALUES ('fefkkl1relkfjasljflskajfdff', '10fdfdfdfdfdfdfdfaad', 'BUTTON_CLS_TYPE', 'BTN_SUCCESS', '-1', '成功', 'success', null, null, null, null, '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-11-10 10:29:30', null, null);
INSERT INTO `t_data_dic_item` VALUES ('fhfadkl1relkfjdsljflskajafdf', '10fdfdfdfdfdfdfdfaad', 'BUTTON_CLS_TYPE', 'BTN_WARNING', '-1', '警告', 'warning', null, null, null, null, '4', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-11-10 10:29:30', null, null);
INSERT INTO `t_data_dic_item` VALUES ('fhfkkl1relkfjasljdskajafdf', '10fdfdfdfdfdfdfdfaad', 'BUTTON_CLS_TYPE', 'BTN_DANGER', '-1', '错误', 'error', null, null, null, null, '5', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-11-10 10:29:30', null, null);
INSERT INTO `t_data_dic_item` VALUES ('fsdafasdfaewfsdgsfdg', '10fdfdfdfdfdfdfdfaad', 'BUTTON_CLS_TYPE', 'BTN_DEFAULT', '-1', '默认', 'default', null, null, null, null, '6', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-11-10 11:07:35', null, null);
INSERT INTO `t_data_dic_item` VALUES ('oi0ffffsdfdsdfsdfadadfaf', '8fsdfsdfsdfsdfsdfasfd', 'BUTTON_LAYOUT_MARK_TYPE', 'LEFT_ROW_BTN', '-1', '左侧行按钮', 'LEFT_ROW_BTN', null, null, null, null, '4', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('oi0fsdfsdfdsdfsdffmadfaf', '8fsdfsdfsdfsdfsdfasfd', 'BUTTON_LAYOUT_MARK_TYPE', 'RIGHT_ROW_BTN', '-1', '右侧行按钮', 'RIGHT_ROW_BTN', null, null, null, null, '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);
INSERT INTO `t_data_dic_item` VALUES ('oi0fsdfsdfdsdfsdfsdfsdaf', '8fsdfsdfsdfsdfsdfasfd', 'BUTTON_LAYOUT_MARK_TYPE', 'RIGHT_TOOL_BTN', '-1', '右侧工具条按钮', 'RIGHT_TOOL_BTN', null, null, null, null, '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-20 22:42:01', null, null);

-- ----------------------------
-- Table structure for `t_data_dic_item_role_mapping`
-- ----------------------------
DROP TABLE IF EXISTS `t_data_dic_item_role_mapping`;
CREATE TABLE `t_data_dic_item_role_mapping` (
  `DIRM_ID` varchar(64) NOT NULL COMMENT 'ID',
  `DIC_ITEM_ID` varchar(64) DEFAULT NULL COMMENT '数据字典明细ID',
  `ROLE_ID` varchar(64) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`DIRM_ID`),
  KEY `FK_DIC_ITEM_ROLE_M_DIC_ITEM_ID` (`DIC_ITEM_ID`),
  KEY `FK_DIC_ITEM_ROLE_M_ROLE_ID` (`ROLE_ID`),
  CONSTRAINT `FK_DIC_ITEM_ROLE_M_DIC_ITEM_ID` FOREIGN KEY (`DIC_ITEM_ID`) REFERENCES `t_data_dic_item` (`DIC_ITEM_ID`),
  CONSTRAINT `FK_DIC_ITEM_ROLE_M_ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `t_role` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_data_dic_item_role_mapping
-- ----------------------------
INSERT INTO `t_data_dic_item_role_mapping` VALUES ('dfaf5ed37a55431e92538a012f6b0ad9', 'a486c4a645b64817a854524860c147d5', '1fsdfsdafasfqfs234fgdgsfsdf');

-- ----------------------------
-- Table structure for `t_data_dic_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_data_dic_type`;
CREATE TABLE `t_data_dic_type` (
  `DIC_TYPE_ID` varchar(64) NOT NULL COMMENT 'ID',
  `DIC_TYPE_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '鍒嗙被缂栫爜',
  `DIC_TYPE_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '鍒嗙被鍚嶇О',
  `DIC_CATEGORY` char(1) DEFAULT NULL COMMENT '1系统字典，2业务字典',
  `ICON_CLS` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '样式',
  `STATUS` char(1) DEFAULT NULL COMMENT '状态(1正常，3删除)',
  `CREATE_USER_ID` varchar(64) DEFAULT NULL COMMENT '创建人ID',
  `UPDATE_USER_ID` varchar(64) DEFAULT NULL COMMENT '修改人ID',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`DIC_TYPE_ID`),
  KEY `FK_DATA_DIC_TYPE_C_USER_ID` (`CREATE_USER_ID`),
  KEY `FK_DATA_DIC_TYPE_U_USER_ID` (`UPDATE_USER_ID`),
  CONSTRAINT `FK_DATA_DIC_TYPE_C_USER_ID` FOREIGN KEY (`CREATE_USER_ID`) REFERENCES `t_user` (`USER_ID`),
  CONSTRAINT `FK_DATA_DIC_TYPE_U_USER_ID` FOREIGN KEY (`UPDATE_USER_ID`) REFERENCES `t_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_data_dic_type
-- ----------------------------
INSERT INTO `t_data_dic_type` VALUES ('10afdsfsdfsdfsdfdsfsd', 'DIC_CATEGORY_TYPE', '数据字典类别', '1', 'icon-database', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-01-24 20:26:46', null);
INSERT INTO `t_data_dic_type` VALUES ('10fdfdfdfdfdfdfdfaad', 'BUTTON_CLS_TYPE', '按钮样式', '1', 'icon-border', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-11-10 10:26:37', null);
INSERT INTO `t_data_dic_type` VALUES ('11fsdfsdfsdfsdfsdfsdf', 'SYS_FILE_UPLOAD_PATH', '系统文件上传路径', '1', 'upload', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-01-24 20:26:46', null);
INSERT INTO `t_data_dic_type` VALUES ('12fdsfsdfsdfsdfsdfsdf', 'BUSNIESS_MARK_TYPE', '业务标识', '1', 'platform/icon:business-mark', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-01-24 20:26:46', null);
INSERT INTO `t_data_dic_type` VALUES ('13fsdfsdfsdfsdfsdfsdf', 'FORM_STATUS_TYPE', '表单状态类型', '1', 'platform/icon:status', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-01-24 20:26:46', null);
INSERT INTO `t_data_dic_type` VALUES ('14316fbcd58148f4af8586a6650cf30e', '11', '11', '2', 'icon-user', '1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-29 16:50:01', '2021-03-29 16:58:06');
INSERT INTO `t_data_dic_type` VALUES ('14fsdfsdfsdfsdfsdfsa', 'QUERY_FORM_STATUS_TYPE', '表单查询状态', '1', 'search', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-01-24 20:26:46', null);
INSERT INTO `t_data_dic_type` VALUES ('1FSDFSDFSFAAdfasdfsdf', 'STATUS_TYPE', '状态类型', '1', 'platform/icon:status', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-01-24 20:26:46', null);
INSERT INTO `t_data_dic_type` VALUES ('2fsdfsdfsdfsdfsdfsdfs', 'QUERY_STATUS_TYPE', '查询状态类型', '1', 'search', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-01-24 20:26:46', null);
INSERT INTO `t_data_dic_type` VALUES ('3fsdfsdfsdfsdfsdfsdfs', 'SEX_TYPE', '性别', '1', 'platform/icon:sex', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-01-24 20:26:46', null);
INSERT INTO `t_data_dic_type` VALUES ('4afdsfsdfsdfdsfdsfdff', 'EMAIL_SWITCH_TYPE', '邮件开关', '1', 'mail', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-01-24 20:26:46', null);
INSERT INTO `t_data_dic_type` VALUES ('4f0fcd49c8384e209ef66f26ac0fc92b', 'DATA_PERMIT_CATEGORY', '数据权限分类', '1', 'platform/icon:data-permissions', '1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-27 09:03:28', '2019-12-27 09:03:50');
INSERT INTO `t_data_dic_type` VALUES ('5fdsfdsfdsfsdfsdfsdfs', 'SMS_SWITCH_TYPE', '短信开关', '1', 'platform/icon:sms', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-01-24 20:26:46', null);
INSERT INTO `t_data_dic_type` VALUES ('6fsdfsdfsdfsdfdsfsdff', 'IS_SUPER_TYPE', '超级管理员', '1', 'platform/icon:super-admin', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-01-24 20:26:46', null);
INSERT INTO `t_data_dic_type` VALUES ('7fsdfsdfsdfdsfsdfdsff', 'QUERY_IS_SUPER_TYPE', '表单查询超级管理员', '1', 'search', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-01-24 20:26:46', null);
INSERT INTO `t_data_dic_type` VALUES ('8fsdfsdfsdfsdfsdfasfd', 'BUTTON_LAYOUT_MARK_TYPE', '按钮布局标识', '1', 'border', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-01-24 20:26:46', null);
INSERT INTO `t_data_dic_type` VALUES ('9aafdsfsdfsdfdsffdfdf', 'QUERY_TASK_STATUS_TYPE', '表单查询待办状态', '1', 'search', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-01-24 20:26:46', null);
INSERT INTO `t_data_dic_type` VALUES ('e2222b2f55714b48b72b82bbee6c8d0e', 'DATA_PERMIT_PROCESSOR', '数据权限处理器', '1', 'platform/icon:processor', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-30 11:07:07', null);

-- ----------------------------
-- Table structure for `t_data_permissions`
-- ----------------------------
DROP TABLE IF EXISTS `t_data_permissions`;
CREATE TABLE `t_data_permissions` (
  `DATA_PERMISSIONS_ID` varchar(64) NOT NULL COMMENT 'ID',
  `DATA_PERMISSIONS_NAME` varchar(128) DEFAULT NULL COMMENT '数据权限名称',
  `ACCESS_URL` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '璁块棶鍦板潃',
  `SQL_ID` varchar(255) DEFAULT NULL COMMENT 'sql语句ID',
  `PRIMARY_PROCESSOR` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '基本处理器',
  `STATUS` char(1) DEFAULT NULL COMMENT '状态(1正常，2锁定，3删除)',
  `CREATE_USER_ID` varchar(64) DEFAULT NULL COMMENT '创建人ID',
  `UPDATE_USER_ID` varchar(64) DEFAULT NULL COMMENT '修改人ID',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `PROCESSOR_PRIORITY` int(2) DEFAULT NULL COMMENT '处理器优先级',
  `MATCH_COLUMN` varchar(128) DEFAULT NULL COMMENT '匹配列',
  `DATA_PERMISSIONS_CATEGORY` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据权限分组',
  `SECONDARY_PROCESSOR` varchar(255) DEFAULT NULL COMMENT '辅助处理器',
  `PARAM_KEY` varchar(128) DEFAULT NULL COMMENT ' 参数KEY',
  PRIMARY KEY (`DATA_PERMISSIONS_ID`),
  KEY `FK_DATA_PERMIT_C_USER_ID` (`CREATE_USER_ID`),
  KEY `FK_DATA_PERMIT_U_USER_ID` (`UPDATE_USER_ID`),
  CONSTRAINT `FK_DATA_PERMIT_C_USER_ID` FOREIGN KEY (`CREATE_USER_ID`) REFERENCES `t_user` (`USER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_DATA_PERMIT_U_USER_ID` FOREIGN KEY (`UPDATE_USER_ID`) REFERENCES `t_user` (`USER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_data_permissions
-- ----------------------------
INSERT INTO `t_data_permissions` VALUES ('asdfff1234566fsfasdfaddsaaf', '查询学生信息', '/student/queryStudentByConditionPaging.jhtml', 'com.cjhme.demo.impl.dao.student.StudentDao.queryStudentByMap', 'system.designatedUserProcessor', '1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-24 15:44:24', '2020-01-09 18:36:10', '1', 'CREATE_USER_ID', 'DEPT_DATA_PERMIT', null, 'designatedDepts');
INSERT INTO `t_data_permissions` VALUES ('asdfghj1234566fsfasdfaddsf', '查询学生信息', '/student/queryStudentByConditionPaging.jhtml', 'com.cjhme.demo.impl.dao.student.StudentDao.queryStudentByBean', 'system.designatedUserProcessor', '1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-24 15:44:24', '2020-01-09 18:36:10', '1', 'CREATE_USER_ID', 'DEPT_DATA_PERMIT', null, 'designatedDepts');
INSERT INTO `t_data_permissions` VALUES ('asdfghj1234566fsfasdfadsf', '查询学生信息', '/student/queryStudentByConditionPaging.jhtml', 'com.cjhme.demo.impl.dao.student.StudentDao.queryStudentByConditionPaging', 'system.designatedDeptAndSubsProcessor', '1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-24 15:44:24', '2020-01-09 18:36:10', '1', 'DEPT_ID', 'DEPT_DATA_PERMIT', null, 'designatedDepts');

-- ----------------------------
-- Table structure for `t_dept`
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `DEPT_ID` varchar(64) NOT NULL COMMENT '组织ID',
  `DEPT_CODE` char(30) DEFAULT NULL COMMENT '组织编号',
  `DEPT_NAME` varchar(100) DEFAULT NULL COMMENT '组织名称',
  `DEPT_SORT_NAME` varchar(50) DEFAULT NULL COMMENT '组织简称',
  `DEPT_LEVEL` int(3) DEFAULT NULL COMMENT '组织级别',
  `DEPT_DESCRIPT` varchar(1000) DEFAULT NULL COMMENT '组织描述',
  `PARENT_DEPT_ID` varchar(64) DEFAULT NULL COMMENT '上一级组织',
  `DEPT_LEADER_ID` varchar(64) DEFAULT NULL COMMENT '组织负责人ID',
  `DEPT_TEL` varchar(20) DEFAULT NULL COMMENT '组织联系电话',
  `DEPT_ADDR` varchar(400) DEFAULT NULL COMMENT '组织地址',
  `ICON_CLS` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `STATUS` char(1) DEFAULT NULL COMMENT '状态(1正常，3删除)',
  `CREATE_USER_ID` varchar(64) DEFAULT NULL COMMENT '创建人ID',
  `UPDATE_USER_ID` varchar(64) DEFAULT NULL COMMENT '修改人ID',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `LEFT_VALUE` int(6) DEFAULT NULL,
  `RIGHT_VALUE` int(6) DEFAULT NULL,
  PRIMARY KEY (`DEPT_ID`),
  KEY `FK_DEPT_C_USER_ID` (`CREATE_USER_ID`),
  KEY `FK_DEPT_U_USER_ID` (`UPDATE_USER_ID`),
  KEY `FK_DEPT_L_USER_ID` (`DEPT_LEADER_ID`),
  CONSTRAINT `FK_DEPT_C_USER_ID` FOREIGN KEY (`CREATE_USER_ID`) REFERENCES `t_user` (`USER_ID`),
  CONSTRAINT `FK_DEPT_L_USER_ID` FOREIGN KEY (`DEPT_LEADER_ID`) REFERENCES `t_user` (`USER_ID`),
  CONSTRAINT `FK_DEPT_U_USER_ID` FOREIGN KEY (`UPDATE_USER_ID`) REFERENCES `t_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES ('0b0b2c801064448394457e4fc1eb8a0d', '005', '某国际股份有限公司-研发部', '研发部', '3', '', 'e4a59e69b3f943a6906392eb6915d2e7', '1aaaaaaaaaaaaaaaaaaaa', '', '', 'icon-dev', '1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2020-01-08 16:17:18', '2021-03-26 15:44:01', '5', '6');
INSERT INTO `t_dept` VALUES ('1aaaaaaaaaaaaaaaaaaaafsadf', '001', '某国际股份有限公司', '某国际股份有限公司', '1', '某国际股份有限公司总公司', '', '1aaaaaaaaaaaaaaaaaaaa', '', '中国', 'icon-apartment', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 17:58:16', null, '1', '12');
INSERT INTO `t_dept` VALUES ('220ba8e14bc545ac8924a42b0a37176a', '003', '某国际股份有限公司-人力部', '人力部', '2', '北京', '1aaaaaaaaaaaaaaaaaaaafsadf', '1aaaaaaaaaaaaaaaaaaaa', '18989898999', '北京', 'icon-user', '1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2020-01-08 16:16:03', '2021-03-26 17:49:41', '8', '9');
INSERT INTO `t_dept` VALUES ('864824f014754b6186d2ef6356e9ed3f', '004', '某国际股份有限公司-测试部', '测试部', '3', '', 'e4a59e69b3f943a6906392eb6915d2e7', '1aaaaaaaaaaaaaaaaaaaa', '', '', 'icon-test', '1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2020-01-08 16:16:37', '2021-03-26 15:44:01', '3', '4');
INSERT INTO `t_dept` VALUES ('ba87fb8be24246feb9d66f55542d663d', '008', '中国区软件部', '软件部', '2', '', '1aaaaaaaaaaaaaaaaaaaafsadf', '1aaaaaaaaaaaaaaaaaaaa', '18989898988', '', 'icon-dev', '1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-26 17:46:15', '2021-03-26 17:49:59', '10', '11');
INSERT INTO `t_dept` VALUES ('e4a59e69b3f943a6906392eb6915d2e7', '006', '某国际股份有限公司行政部', '行政部', '2', '', '1aaaaaaaaaaaaaaaaaaaafsadf', '1aaaaaaaaaaaaaaaaaaaa', '18989898999', '', 'icon-user', '1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2020-01-08 16:15:25', '2021-03-26 17:31:10', '2', '7');

-- ----------------------------
-- Table structure for `t_file_data`
-- ----------------------------
DROP TABLE IF EXISTS `t_file_data`;
CREATE TABLE `t_file_data` (
  `FILE_ID` varchar(64) NOT NULL COMMENT '文件ID',
  `BUSNIESS_MARK` varchar(100) DEFAULT NULL COMMENT '业务标识',
  `BUSNIESS_ID` varchar(64) DEFAULT NULL COMMENT '业务ID',
  `FILE_NAME` varchar(100) DEFAULT NULL COMMENT '文件名',
  `FILE_URL` varchar(300) DEFAULT NULL COMMENT '文件存储路径',
  `FILE_DESCRIPT` varchar(200) DEFAULT NULL COMMENT '文件描述',
  `STATUS` char(1) DEFAULT NULL COMMENT '状态(1正常，2锁定，3删除)',
  `CREATE_USER_ID` varchar(64) DEFAULT NULL COMMENT '创建人ID',
  `UPDATE_USER_ID` varchar(64) DEFAULT NULL COMMENT '修改人ID',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`FILE_ID`),
  KEY `FK_FILE_DATA_C_USER_ID` (`CREATE_USER_ID`),
  KEY `FK_FILE_DATA_U_USER_ID` (`UPDATE_USER_ID`),
  CONSTRAINT `FK_FILE_DATA_C_USER_ID` FOREIGN KEY (`CREATE_USER_ID`) REFERENCES `t_user` (`USER_ID`),
  CONSTRAINT `FK_FILE_DATA_U_USER_ID` FOREIGN KEY (`UPDATE_USER_ID`) REFERENCES `t_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_file_data
-- ----------------------------
INSERT INTO `t_file_data` VALUES ('2a5711921044498e965ef7fc869b4b2e', 'USER_HEAD_IMG', '1aaaaaaaaaaaaaaaaaaaa', 'a9aaa4b0-d87d-4ed1-910a-23ca8704ba5c.png', 'D:/cjhme/images/userAvatar/a9aaa4b0-d87d-4ed1-910a-23ca8704ba5c.png', '管理员的用户头像！', '1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-30 18:20:35', '2021-03-31 10:17:14');

-- ----------------------------
-- Table structure for `t_login_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_login_log`;
CREATE TABLE `t_login_log` (
  `LOGIN_ID` varchar(64) NOT NULL COMMENT 'ID',
  `LOGIN_IP` varchar(60) DEFAULT NULL COMMENT '登录IP',
  `STATUS` char(1) DEFAULT NULL COMMENT '状态(1正常，3删除)',
  `CREATE_USER_ID` varchar(64) NOT NULL COMMENT '创建人ID',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`LOGIN_ID`),
  KEY `FK_LOGIN_LOG_C_USER_ID` (`CREATE_USER_ID`),
  CONSTRAINT `FK_LOGIN_LOG_C_USER_ID` FOREIGN KEY (`CREATE_USER_ID`) REFERENCES `t_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_login_log
-- ----------------------------
INSERT INTO `t_login_log` VALUES ('15b16a2c6f6046f2a8a28e40e9c5d285', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-19 17:57:58');
INSERT INTO `t_login_log` VALUES ('16ab7ba643f44c86bb939cc4b067887f', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-31 10:02:02');
INSERT INTO `t_login_log` VALUES ('22b1089956f84e33a90a73f8988ae0a3', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-19 13:51:42');
INSERT INTO `t_login_log` VALUES ('26dbd8d8a737422fb43fd724872588e8', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-20 17:15:55');
INSERT INTO `t_login_log` VALUES ('30a4060b290040ddbda23664fefd6eac', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-25 10:44:53');
INSERT INTO `t_login_log` VALUES ('34aa8991d8ac4bafa2460ed4677e5f3e', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-25 11:00:39');
INSERT INTO `t_login_log` VALUES ('42d9e1770fe94c51a568678a3c975e88', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-23 13:48:02');
INSERT INTO `t_login_log` VALUES ('4ddc968617794fabbe9cabc28cdcfee1', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-22 13:48:50');
INSERT INTO `t_login_log` VALUES ('50637a226fb84d03a046057aee097149', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-27 09:24:03');
INSERT INTO `t_login_log` VALUES ('5bd10bc308754c9cbff6ac361e33bdd7', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-26 09:18:06');
INSERT INTO `t_login_log` VALUES ('63c094149eae4f578737b1f1611e14d0', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-23 10:56:14');
INSERT INTO `t_login_log` VALUES ('65a722bf6b9c4fa68a1bc2bafad41b30', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-26 08:58:31');
INSERT INTO `t_login_log` VALUES ('6c2fc59685674ab3af4f668ea8856c11', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-19 23:04:27');
INSERT INTO `t_login_log` VALUES ('70fdb05cae394ff59a603c20b907868c', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-19 22:20:00');
INSERT INTO `t_login_log` VALUES ('76f7429d43e940c5b253797bf763f27a', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-23 15:59:52');
INSERT INTO `t_login_log` VALUES ('7d16620ff27d4b7b824ba4dfd755d2cf', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2020-01-09 18:49:31');
INSERT INTO `t_login_log` VALUES ('7fd279f216a54a39878683ccb9f9e004', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-25 11:21:04');
INSERT INTO `t_login_log` VALUES ('7fda1e0f233e4b37a09d72606d608d2b', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-25 09:32:56');
INSERT INTO `t_login_log` VALUES ('81c599e78e384bcfa042118abe8db719', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-26 08:51:03');
INSERT INTO `t_login_log` VALUES ('8567459f2c84496b97ee367965f525e8', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-20 10:16:37');
INSERT INTO `t_login_log` VALUES ('87e35d524803491b9d4e3b397b17a055', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-22 18:08:54');
INSERT INTO `t_login_log` VALUES ('8913d0e3b3ee4fc7acaac6c4f33ddeca', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-29 11:30:32');
INSERT INTO `t_login_log` VALUES ('8ff718d31fbf4d258cd598a77ff64d4e', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2020-01-09 18:36:01');
INSERT INTO `t_login_log` VALUES ('926b5487559248fe9ce9b6e0d0f43b65', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-23 09:23:00');
INSERT INTO `t_login_log` VALUES ('9390cbd89d014caf8fb9eab482ffad4b', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-21 16:03:08');
INSERT INTO `t_login_log` VALUES ('95d7aec9bd3746959b54499dd953f1b6', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-25 10:42:42');
INSERT INTO `t_login_log` VALUES ('9e27da80fb38475d8bce06c6846d86e9', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-30 16:58:34');
INSERT INTO `t_login_log` VALUES ('9fdceb6a3f5742a1bf4b4f9e3d0404d2', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-19 09:56:20');
INSERT INTO `t_login_log` VALUES ('a398e1eaec9547a6962c3c02194bc9df', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-22 15:12:54');
INSERT INTO `t_login_log` VALUES ('a5f638e42e6c4ebfad62610cdd4b2b49', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-26 09:11:38');
INSERT INTO `t_login_log` VALUES ('a766d1d10c784031b5f17ae99f8f962f', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-19 10:28:18');
INSERT INTO `t_login_log` VALUES ('a9f64e02c085484f857ec5e52a0ca7c2', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-25 11:20:32');
INSERT INTO `t_login_log` VALUES ('ade37334831d4480910af227ff40b971', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-26 09:19:51');
INSERT INTO `t_login_log` VALUES ('af7c39d7b1a9490c987a4ba52fd41f58', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-25 11:03:15');
INSERT INTO `t_login_log` VALUES ('b46f9c9ee3b748d79fd67f3e4edb5e18', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-27 09:27:34');
INSERT INTO `t_login_log` VALUES ('bd97ea194e4f41378576ad94b9d1c89d', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-21 10:14:35');
INSERT INTO `t_login_log` VALUES ('c0c8dadf5f9f40f0b2b3e592afef6fd1', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-25 11:35:21');
INSERT INTO `t_login_log` VALUES ('c206defc11eb4144bd92e6b36c1f47ce', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-27 09:01:31');
INSERT INTO `t_login_log` VALUES ('c4259867bd0147908eca87eddee9abec', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-31 11:02:52');
INSERT INTO `t_login_log` VALUES ('c6d7884b2dc54b63930b2a40cb02bc47', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-28 21:44:25');
INSERT INTO `t_login_log` VALUES ('c7bba96b91a9495d9a8ea2a10b2d8f53', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-24 15:46:38');
INSERT INTO `t_login_log` VALUES ('cbfdd6f6d8674b6e9f94d3aaaaf623c2', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-30 16:35:52');
INSERT INTO `t_login_log` VALUES ('cd82f9cf607b4b4188d7d46e1bcae35a', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-24 09:22:25');
INSERT INTO `t_login_log` VALUES ('d06cda2e544f405d8f2011edd5a8af6d', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-30 09:26:26');
INSERT INTO `t_login_log` VALUES ('d1923617851c47388f0d268b2d03139e', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-19 17:14:11');
INSERT INTO `t_login_log` VALUES ('d3659252e2fe4181b59351a588e6a8fa', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-25 11:05:31');
INSERT INTO `t_login_log` VALUES ('d379c6552c964139bb0f613d8a8fa5ae', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-27 09:09:50');
INSERT INTO `t_login_log` VALUES ('d475e0b6151144cd897e6d946dce53ef', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-22 16:34:54');
INSERT INTO `t_login_log` VALUES ('d5e4b05172a4457e9c01eb4474d10789', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-26 10:10:22');
INSERT INTO `t_login_log` VALUES ('d82df13b512c4e0d8d6d26ffa7f64356', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-30 16:36:30');
INSERT INTO `t_login_log` VALUES ('db512d3e22b3461282f20e206d1f253d', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-28 21:46:39');
INSERT INTO `t_login_log` VALUES ('ddd2ad142a5045dca66811085dd5fd9b', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-25 10:54:30');
INSERT INTO `t_login_log` VALUES ('de74e00e077541aab4be1b239d87f390', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-26 08:47:07');
INSERT INTO `t_login_log` VALUES ('e69408d909b94aab8df1c91c82b002df', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-28 21:44:50');
INSERT INTO `t_login_log` VALUES ('ed626c35065d4e5882e08031f0f8bbb6', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-25 11:19:15');
INSERT INTO `t_login_log` VALUES ('f58e0fcd9d8b40cc81797d5fffe78258', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-24 17:49:28');
INSERT INTO `t_login_log` VALUES ('fbe7e5b55e1a43b49a08a483f773fd85', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-30 11:02:11');
INSERT INTO `t_login_log` VALUES ('fd709f41316f47e7a35d7406a20007f6', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-21 16:55:53');

-- ----------------------------
-- Table structure for `t_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `MENU_ID` varchar(64) NOT NULL COMMENT '菜单ID',
  `MENU_CODE` char(30) DEFAULT NULL COMMENT '菜单编号',
  `MENU_NAME` varchar(60) DEFAULT NULL COMMENT '名称',
  `MENU_LEVEL` int(3) DEFAULT NULL COMMENT '菜单级别',
  `ICON_CLS` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '样式',
  `ACCESS_URL` varchar(300) DEFAULT NULL COMMENT '访问URL',
  `PARENT_MENU_ID` varchar(64) DEFAULT NULL COMMENT '父菜单ID',
  `SORT_NUM` int(20) DEFAULT NULL COMMENT '排序',
  `STATUS` char(1) DEFAULT NULL COMMENT '状态(1正常，3删除)',
  `CREATE_USER_ID` varchar(64) DEFAULT NULL COMMENT '创建人ID',
  `UPDATE_USER_ID` varchar(64) DEFAULT NULL COMMENT '修改人ID',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`MENU_ID`),
  KEY `FK_MENU_C_USER_ID` (`CREATE_USER_ID`),
  KEY `FK_MENU_U_USER_ID` (`UPDATE_USER_ID`),
  CONSTRAINT `FK_MENU_C_USER_ID` FOREIGN KEY (`CREATE_USER_ID`) REFERENCES `t_user` (`USER_ID`),
  CONSTRAINT `FK_MENU_U_USER_ID` FOREIGN KEY (`UPDATE_USER_ID`) REFERENCES `t_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1gdfdggfdgdfggdfg1', '009', '访问地址管理', '3', 'icon-api', '/app/accessUrlList', '3yrtyerhgfyerty', '6', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2015-08-27 20:01:00', null);
INSERT INTO `t_menu` VALUES ('1gdfgdfgdfgdfg2', '010', '数据字典管理', '3', 'icon-data-dic', '/app/dataDicList', '3yrtyerhgfyerty', '8', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2014-09-29 17:18:23', null);
INSERT INTO `t_menu` VALUES ('1gdfgdfgdfgdfgdfg3', '015', '系统公告管理', '3', 'icon-notification', '/app/sysNoticeList', 'ytryrtyrety4', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2015-04-26 09:30:46', null);
INSERT INTO `t_menu` VALUES ('1gdfgdfgdfgdfsg5', '012', '我的公告', '3', 'icon-notification', '/app/myNoticeList', 'ytryedhhdtryrty2', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2014-12-21 16:33:05', null);
INSERT INTO `t_menu` VALUES ('1gdfgdfgdfgfdg4', '011', '我的待办', '3', 'icon-tags', '/app/myTaskList', 'ytryedhhdtryrty2', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2014-12-21 16:31:41', null);
INSERT INTO `t_menu` VALUES ('1gdfgdsfgdsfgdfg6', '014', '登录日志管理', '3', 'icon-login-log', '/app/loginLogList', '5tytryreyyrtyrt', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2015-04-26 09:30:46', null);
INSERT INTO `t_menu` VALUES ('1gdfsgfgfdg7', '013', '系统日志管理', '3', 'icon-sys-log', '/app/sysLogList', '5tytryreyyrtyrt', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2015-04-24 21:25:28', null);
INSERT INTO `t_menu` VALUES ('1gfdgsdgsdgfdgsdfg0', '010', '按钮管理', '3', 'icon-border', '/app/buttonList', '3yrtyerhgfyerty', '5', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2014-12-21 16:26:20', null);
INSERT INTO `t_menu` VALUES ('2cce82e8b5274b6d8acb65c888415bb2', '019', '数据权限管理', '3', 'icon-data-permissions', '/app/dataPermissionsList', '3yrtyerhgfyerty', '7', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-26 09:13:30', null);
INSERT INTO `t_menu` VALUES ('3yrtyerhgfyerty', '003', '系统管理', '2', 'icon-sys-manage', null, 'dfhgfhhgf3gdgdsgfh1', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2014-09-28 19:42:29', null);
INSERT INTO `t_menu` VALUES ('5tytryreyyrtyrt', '005', '日志管理', '2', 'icon-log-manage', '', 'dfhgfhhgf3gdgdsgfh1', '4', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-01-24 20:25:12', null);
INSERT INTO `t_menu` VALUES ('6ytryetryeeytrt', '006', '用户管理', '3', 'icon-user', '/app/userList', '3yrtyerhgfyerty', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2014-09-28 19:48:52', null);
INSERT INTO `t_menu` VALUES ('7ereeet', '007', '部门管理', '3', 'icon-apartment', '/app/deptList', '3yrtyerhgfyerty', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2014-11-21 21:28:03', null);
INSERT INTO `t_menu` VALUES ('82273d9bb3144250a6997a1bfbd90bfe', '016', '系统信息', '3', 'icon-dashboard', '/app/sysInfo', 'ytryrtyrety4', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-12 05:57:28', null);
INSERT INTO `t_menu` VALUES ('8ytryrtytry', '008', '角色管理', '3', 'icon-team', '/app/roleList', '3yrtyerhgfyerty', '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2014-10-16 14:50:03', null);
INSERT INTO `t_menu` VALUES ('9stryytrytrytr', '011', '菜单管理', '3', 'icon-menu', '/app/menuList', '3yrtyerhgfyerty', '4', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2014-11-21 21:28:03', null);
INSERT INTO `t_menu` VALUES ('a2cf1e8ab74449c29861404214421841', '018', '卡片列表', '3', 'icon-demo', '/app/cardList', 'e041386708254e31beae8df1c92a4a8c', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-24 07:28:37', '2021-03-31 15:33:33');
INSERT INTO `t_menu` VALUES ('caf02e05617f4b3ea81b372bd5a10a35', '0013', '页签列表', '3', 'icon-demo', '/app/tabList', 'e041386708254e31beae8df1c92a4a8c', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2021-03-31 15:33:19', '2021-03-31 15:33:38');
INSERT INTO `t_menu` VALUES ('d2801e0e39654be8a57785cde7430bba', '0015', '图表列表', '3', 'icon-demo', '/app/chartList', 'e041386708254e31beae8df1c92a4a8c', '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2021-03-31 16:26:36', null);
INSERT INTO `t_menu` VALUES ('dfhgfhhgf3gdgdsgfh1', '001', '系统菜单', '1', 'icon-menu', null, '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2014-09-28 19:41:19', null);
INSERT INTO `t_menu` VALUES ('e041386708254e31beae8df1c92a4a8c', '017', '界面示例', '2', 'icon-demo', '', 'dfhgfhhgf3gdgdsgfh1', '5', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-24 07:26:52', '2021-03-31 13:49:12');
INSERT INTO `t_menu` VALUES ('ytryedhhdtryrty2', '002', '个人工作台', '2', 'icon-workbench', '', 'dfhgfhhgf3gdgdsgfh1', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2014-09-29 17:17:20', null);
INSERT INTO `t_menu` VALUES ('ytryrtyrety4', '004', '系统功能', '2', 'icon-appstore', '', 'dfhgfhhgf3gdgdsgfh1', '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2014-12-21 16:28:19', null);

-- ----------------------------
-- Table structure for `t_notice`
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `NOTICE_ID` varchar(64) NOT NULL COMMENT 'ID',
  `NOTICE_TITLE` varchar(200) DEFAULT NULL COMMENT '公告标题',
  `NOTICE_CONTENT` text COMMENT '公告内容',
  `NOTICE_TYPE` char(1) DEFAULT NULL COMMENT '公告类型(1 系统公告，2部门公告，3角色公告）',
  `STATUS` char(1) DEFAULT NULL COMMENT '状态(1正常，2关闭，3删除)',
  `CUTOFF_DATE` date DEFAULT NULL COMMENT '截止时间',
  `CREATE_USER_ID` varchar(64) DEFAULT NULL COMMENT '创建人ID',
  `UPDATE_USER_ID` varchar(64) DEFAULT NULL COMMENT '修改人ID',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`NOTICE_ID`),
  KEY `FK_NOTICE_C_USER_ID` (`CREATE_USER_ID`),
  KEY `FK_NOTICE_U_USER_ID` (`UPDATE_USER_ID`),
  CONSTRAINT `FK_NOTICE_C_USER_ID` FOREIGN KEY (`CREATE_USER_ID`) REFERENCES `t_user` (`USER_ID`),
  CONSTRAINT `FK_NOTICE_U_USER_ID` FOREIGN KEY (`UPDATE_USER_ID`) REFERENCES `t_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_notice
-- ----------------------------
INSERT INTO `t_notice` VALUES ('1', 'CC2.0 正在不断开发与完善中，即将闪联登场，期待!', 'CC2.0 正在不断开发与完善中，即将闪联登场，期待!', '1', '1', '2021-03-18', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2019-09-26 19:58:44', '2021-03-22 17:30:38');
INSERT INTO `t_notice` VALUES ('1f46c8f22f1b467ca76bd8247ff33724', '根据目标将项目工作分解成具体工作项根据目标将根据目标将项目工作分解成具体工作项根据目标将', '第一步：根据目标将项目工作分解成具体工作项\n\n以网易严选为例，整合营销项目既要承担拉高GMV的职责，也要考虑市场做大声量。\n\n我们将一个项目拆分成6个子项目：\n\n意见征集：征集项目管理、活动策略玩法的建议；\n营销策略产出：根据竞品调研，产出核心玩法，促销节奏，核心沟通策略；\nKPI制定BD，渠道等各资源方的推广计划，避免资源错配的情况。\n第二步：根据各个子项目的目标，再将各个子项目拆解成各个工作包\n\n如“KPI的制定与拆解”子项目，目的是帮助严选各业务线规划目标，帮助具体执行人员根据当前目标做足准备。\n\n根据财务指标，拆分完整体业绩KPI以后，我们从用户线、流量线、商品线三个角度分别看应该如何完成总的目标。\n\n\n第三步：明确各个工作包的工作内容以及责任人和用时\n\n以“KPI的制定与拆解”子项目中“整体财务KPI”这个工作包为例，整体KPI不仅影响促销的玩法策略制定，会对“供应链订货”“仓配资源”上下游的业务线的策略制定产生影响。\n\n我们一般用5个工作日，协同数据分析师根据历史数据及财务指标推测出整体目标，并将目标拆分至：销售额KPI、毛利额KPI、毛利率KPI等财务指标，再将这些财务指标细分到各个销售渠道。\n\n\n\n我们将一个项目拆分成6个子项目：\n\n意见征集：征集项目管理、活动策略玩法的建议；\n营销策略产出：根据竞品调研，产出核心玩法，促销节奏，核心沟通策略；\nKPI制定与拆解：根据财务目标，拆分出销售毛利目标，并分拆至销售，用户，流量，商品四个角度；\n具体方案制定与落地：根据核心策略策划各业务线落地方案；\n商品盘货统筹：通过策划案中各线玩法制定盘点策略，盘点出不同商品在不同节点做什么价格，做什么推荐；\n促销推广落地：根据整体沟通策略和货品盘点结果，梳理品牌，BD，渠道等各资源方的推广计划，避免资源错配的情况。\n第二步：根据各个子项目的目标，再将各个子项目拆解成各个工作包\n\n如“KPI的制定与拆解”子项目，目的是帮助严选各业务线规划目标，帮助具体执行人员根据当前目标做足准备。\n\n\n', '1', '1', '2021-03-18', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-04 20:28:22', '2021-03-22 17:30:38');
INSERT INTO `t_notice` VALUES ('340fd2a2a2984e65a2c70bd00268a809', 'DSF', 'FDSF', '1', '1', '2021-03-23', '1aaaaaaaaaaaaaaaaaaaa', null, '2021-03-23 09:30:22', null);
INSERT INTO `t_notice` VALUES ('939c1be63fa04e84b4200f7e4c88cea3', 'FASDF1', 'ASDFASDF1', '1', '1', '2021-03-28', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-23 09:30:43', '2021-03-23 09:42:12');

-- ----------------------------
-- Table structure for `t_notice_user_mapping`
-- ----------------------------
DROP TABLE IF EXISTS `t_notice_user_mapping`;
CREATE TABLE `t_notice_user_mapping` (
  `NUM_ID` varchar(64) NOT NULL COMMENT 'ID',
  `NOTICE_ID` varchar(64) NOT NULL COMMENT '公告ID',
  `USER_ID` varchar(64) NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`NUM_ID`),
  KEY `FK_NOTICE_USER_M_NOTICE_ID` (`NOTICE_ID`),
  KEY `FK_NOTICE_USER_M_USER_ID` (`USER_ID`),
  CONSTRAINT `FK_NOTICE_USER_M_NOTICE_ID` FOREIGN KEY (`NOTICE_ID`) REFERENCES `t_notice` (`NOTICE_ID`),
  CONSTRAINT `FK_NOTICE_USER_M_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `t_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_notice_user_mapping
-- ----------------------------

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `ROLE_ID` varchar(64) NOT NULL COMMENT '角色ID',
  `ROLE_CODE` char(30) DEFAULT NULL COMMENT '角色编码',
  `ROLE_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  `ROLE_DESCRIBE` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色描述',
  `IS_SUPER` char(1) DEFAULT '1' COMMENT '是否超级管理员(1是，2否)',
  `STATUS` char(1) DEFAULT NULL COMMENT '状态(1正常，2锁定，3删除)',
  `CREATE_USER_ID` varchar(64) DEFAULT NULL COMMENT '创建人ID',
  `UPDATE_USER_ID` varchar(64) DEFAULT NULL COMMENT '修改人ID',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ROLE_ID`),
  KEY `FK_ROLE_C_USER_ID` (`CREATE_USER_ID`),
  KEY `FK_ROLE_U_USER_ID` (`UPDATE_USER_ID`),
  CONSTRAINT `FK_ROLE_C_USER_ID` FOREIGN KEY (`CREATE_USER_ID`) REFERENCES `t_user` (`USER_ID`),
  CONSTRAINT `FK_ROLE_U_USER_ID` FOREIGN KEY (`UPDATE_USER_ID`) REFERENCES `t_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1fsdfsdafasfqfs234fgdgsfsdf', '001', '开发超级管理员', '开发最高权限', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 17:58:16', null);
INSERT INTO `t_role` VALUES ('3b370aa1bc464890a0bc99d9b7c42a4b', '111', '用户1', '', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2021-03-23 16:45:40', '2021-03-31 16:27:11');

-- ----------------------------
-- Table structure for `t_role_access_url_mapping`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_access_url_mapping`;
CREATE TABLE `t_role_access_url_mapping` (
  `RUM_ID` varchar(64) NOT NULL COMMENT 'ID',
  `ROLE_ID` varchar(64) NOT NULL COMMENT '角色ID',
  `ACCESS_URL_ID` varchar(64) NOT NULL COMMENT '访问地址ID',
  PRIMARY KEY (`RUM_ID`),
  KEY `FK_ROLE_ACCESS_URL_M_ROLE_ID` (`ROLE_ID`),
  KEY `FK_ROLE_ACCESS_URL_M_ACCESS_URL_ID` (`ACCESS_URL_ID`),
  CONSTRAINT `FK_ROLE_ACCESS_URL_M_ACCESS_URL_ID` FOREIGN KEY (`ACCESS_URL_ID`) REFERENCES `t_access_url` (`ACCESS_URL_ID`),
  CONSTRAINT `FK_ROLE_ACCESS_URL_M_ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `t_role` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_access_url_mapping
-- ----------------------------
INSERT INTO `t_role_access_url_mapping` VALUES ('0203d8ee333a43c8b9412ff017f1974f', '1fsdfsdafasfqfs234fgdgsfsdf', '25gdfgdfsgdfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('03695b7db8c340028dbbb51ba1dcd016', '1fsdfsdafasfqfs234fgdgsfsdf', '83afdsadafsdfdssf');
INSERT INTO `t_role_access_url_mapping` VALUES ('0af0b99ac0d640ca94083f7db7d72447', '1fsdfsdafasfqfs234fgdgsfsdf', '2gfdgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('0b94234b56a74103b3302a1d73b22813', '1fsdfsdafasfqfs234fgdgsfsdf', '3gdfsfgsdgdgdsfgdfg2');
INSERT INTO `t_role_access_url_mapping` VALUES ('11d0ea115e5341b591c130cddebcb77b', '1fsdfsdafasfqfs234fgdgsfsdf', '3gdfgdfgdfg1');
INSERT INTO `t_role_access_url_mapping` VALUES ('13bdd8b0842349c2ba32bfff7153c273', '1fsdfsdafasfqfs234fgdgsfsdf', '7fdsaffsfsaf4');
INSERT INTO `t_role_access_url_mapping` VALUES ('16df07bba8fb4a7cb69351319ca02811', '1fsdfsdafasfqfs234fgdgsfsdf', 'gdfgdfgdfgdfg27');
INSERT INTO `t_role_access_url_mapping` VALUES ('1abf0a6010e545ceac3d893fbc19698e', '1fsdfsdafasfqfs234fgdgsfsdf', '43gdfsgdfgdfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('1cb115dd85964ddfa5d4a72dce79ef48', '1fsdfsdafasfqfs234fgdgsfsdf', '1gdfgdfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('1df896e35b844e9692197c3dc867bf6c', '1fsdfsdafasfqfs234fgdgsfsdf', '4sdfbgfdfssssgsd1');
INSERT INTO `t_role_access_url_mapping` VALUES ('1fd5d346c6ba4a75ba1efce8d9f7d70d', '1fsdfsdafasfqfs234fgdgsfsdf', '8fsfsadfsdf1');
INSERT INTO `t_role_access_url_mapping` VALUES ('20cfc77de92545a3a4f7a484cc30ece9', '1fsdfsdafasfqfs234fgdgsfsdf', '62gfdgdfgdfgdf');
INSERT INTO `t_role_access_url_mapping` VALUES ('21503b89ae754489bb82ee75e8a2ea73', '1fsdfsdafasfqfs234fgdgsfsdf', '23gdfgdfsgsdg');
INSERT INTO `t_role_access_url_mapping` VALUES ('227615a05c234869a0e444d19fcccc04', '1fsdfsdafasfqfs234fgdgsfsdf', '6gdfgfdgdfggdgdfg3');
INSERT INTO `t_role_access_url_mapping` VALUES ('269927b0cdfb42228ac8666e3d8c7e95', '1fsdfsdafasfqfs234fgdgsfsdf', '88380e50bd004745a3ccfb85e72aabf3');
INSERT INTO `t_role_access_url_mapping` VALUES ('296c713b5fe14f429b7cdf6416ab1452', '1fsdfsdafasfqfs234fgdgsfsdf', '1gdfsgsdfgdfsgdsgssdsdghj3gre1');
INSERT INTO `t_role_access_url_mapping` VALUES ('2ca6f21e42c24bb7922f45f8697fb9f4', '1fsdfsdafasfqfs234fgdgsfsdf', '7gsagsagsdagsag3');
INSERT INTO `t_role_access_url_mapping` VALUES ('2ce5c3093edd471e9ad1da7c9c46f921', '1fsdfsdafasfqfs234fgdgsfsdf', '84fsdfsdfsdafsadf');
INSERT INTO `t_role_access_url_mapping` VALUES ('2cfa706aac2e497cab735e10fab13e26', '1fsdfsdafasfqfs234fgdgsfsdf', '26gdfgdfgdfgdgdgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('2d907c0f408e457cbc11c5c592b22c85', '1fsdfsdafasfqfs234fgdgsfsdf', '49gdfsgdsfgdsfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('30853be49bef4df4bb059d7a5a238228', '1fsdfsdafasfqfs234fgdgsfsdf', 'gdfggfdsgsdfgdfgd5');
INSERT INTO `t_role_access_url_mapping` VALUES ('3296d9d9f1994d578886122ba9ab3cc2', '1fsdfsdafasfqfs234fgdgsfsdf', '3bc110a666b6490d820819f3a2329d3f');
INSERT INTO `t_role_access_url_mapping` VALUES ('36bb46aaf6264f2cb722b1dc44afcbbe', '1fsdfsdafasfqfs234fgdgsfsdf', '46gdfgdfgdfgdfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('3732cb95542742bf96747dc7d2b5b943', '1fsdfsdafasfqfs234fgdgsfsdf', 'gdfgdfsgdsgdfsgdfg18');
INSERT INTO `t_role_access_url_mapping` VALUES ('389cad2afd2f42a78506a846efb25242', '1fsdfsdafasfqfs234fgdgsfsdf', '96c4075e36c643e0af43028005fe0569');
INSERT INTO `t_role_access_url_mapping` VALUES ('39d38a5f9b6a4be691360fab666bd196', '1fsdfsdafasfqfs234fgdgsfsdf', '82asffsdfasdfsfsdaf');
INSERT INTO `t_role_access_url_mapping` VALUES ('3a9b834ae9084272b7b5387ee26403d0', '1fsdfsdafasfqfs234fgdgsfsdf', '1esgrgregergergerg3');
INSERT INTO `t_role_access_url_mapping` VALUES ('3b586fe2125545149e1e0de445d30205', '1fsdfsdafasfqfs234fgdgsfsdf', '12ggdfgdfrewrgwrg');
INSERT INTO `t_role_access_url_mapping` VALUES ('3f59136982684dd3abe76c89b058fb2f', '1fsdfsdafasfqfs234fgdgsfsdf', '4gdfgdgdfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('3fd0350b03c746728f4b93890193d272', '1fsdfsdafasfqfs234fgdgsfsdf', '37b754b754b75b75b546b');
INSERT INTO `t_role_access_url_mapping` VALUES ('4080d40975c243a08a13b63a38096f66', '1fsdfsdafasfqfs234fgdgsfsdf', '375b54b75b75b8');
INSERT INTO `t_role_access_url_mapping` VALUES ('42cf99ff14394be7a84c7e93c0330fce', '1fsdfsdafasfqfs234fgdgsfsdf', '2gsdfgdsfgdsf1');
INSERT INTO `t_role_access_url_mapping` VALUES ('4398d2a30717456db86ae196af528529', '1fsdfsdafasfqfs234fgdgsfsdf', 'd15a0d4d5aef496d8d3a912ecb7980b6');
INSERT INTO `t_role_access_url_mapping` VALUES ('503284b5881e46f88ab03483ea8982fd', '1fsdfsdafasfqfs234fgdgsfsdf', '80ffdsfsdafasfdsf');
INSERT INTO `t_role_access_url_mapping` VALUES ('53f925ec412e45e5b495181c571d9c15', '1fsdfsdafasfqfs234fgdgsfsdf', '6gdfgdfsgdsg9');
INSERT INTO `t_role_access_url_mapping` VALUES ('5472a07c278d49159fc27c990fed30c8', '1fsdfsdafasfqfs234fgdgsfsdf', '5fsdafsdghfjfh1');
INSERT INTO `t_role_access_url_mapping` VALUES ('57a1cafa4e894437ae3b0c7b519b4c3e', '1fsdfsdafasfqfs234fgdgsfsdf', '375b5bbtbytrb9');
INSERT INTO `t_role_access_url_mapping` VALUES ('57d81dc524894e5cb60004c092ed4740', '1fsdfsdafasfqfs234fgdgsfsdf', '6jhgjgjghjhgjghjgh0');
INSERT INTO `t_role_access_url_mapping` VALUES ('58380f732dc546ecbe1a815c17892369', '1fsdfsdafasfqfs234fgdgsfsdf', '22gdfsgdfgdfgdfgdf');
INSERT INTO `t_role_access_url_mapping` VALUES ('58fd2526fb384aa483411e88ffec3731', '1fsdfsdafasfqfs234fgdgsfsdf', '3gdfgdfgdfgdfgdfg3');
INSERT INTO `t_role_access_url_mapping` VALUES ('5b390a9bd76b4c8f9cbef5869d4c241e', '1fsdfsdafasfqfs234fgdgsfsdf', '6gdgfdgdfgfgdf4');
INSERT INTO `t_role_access_url_mapping` VALUES ('5ce12f3bd2434967b6568454c5a08573', '1fsdfsdafasfqfs234fgdgsfsdf', '6gdsfgdfgdsfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('5d5dc7365db34de5b1bf88a2158bab8f', '1fsdfsdafasfqfs234fgdgsfsdf', '8gdfgdfgdfgdfgdgdfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('5fc95f068a8544f895837e77593cf792', '1fsdfsdafasfqfs234fgdgsfsdf', '44gfdsgdfgdfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('6019bc0024044ccbb39e90519225dd8f', '1fsdfsdafasfqfs234fgdgsfsdf', '7gdfgsdfgdfgdfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('650e183570e240ddaf3a1dcbe5ee1992', '1fsdfsdafasfqfs234fgdgsfsdf', '7asgasdgsag6');
INSERT INTO `t_role_access_url_mapping` VALUES ('65204066ea724e89a7c511bfc00c26c2', '1fsdfsdafasfqfs234fgdgsfsdf', '85fdsfsdfsdfsdfsdf');
INSERT INTO `t_role_access_url_mapping` VALUES ('69336e5a6fe5411e8eda70add548d5f6', '1fsdfsdafasfqfs234fgdgsfsdf', '5fdsfsfsfdfsdaf5');
INSERT INTO `t_role_access_url_mapping` VALUES ('6a037f98410d4a22853593ae24b73819', '1fsdfsdafasfqfs234fgdgsfsdf', '7agsdagsagsadggsg7');
INSERT INTO `t_role_access_url_mapping` VALUES ('70bfb9d5c5674f76941a99085851f2aa', '1fsdfsdafasfqfs234fgdgsfsdf', '2gdfgdsfgdsfgdf9');
INSERT INTO `t_role_access_url_mapping` VALUES ('72653eca48eb4f379eafbd2d41f65e5b', '1fsdfsdafasfqfs234fgdgsfsdf', '5gfdssdg5sggwg0');
INSERT INTO `t_role_access_url_mapping` VALUES ('75624c9647b94ec78e69b1e8191eee89', '1fsdfsdafasfqfs234fgdgsfsdf', '5fsaffsdfsdfsdf3');
INSERT INTO `t_role_access_url_mapping` VALUES ('76e34975c75940ce84d48e483a65b09b', '1fsdfsdafasfqfs234fgdgsfsdf', '3gdfggdfgdfgdfgd0');
INSERT INTO `t_role_access_url_mapping` VALUES ('77eb75959bce4be99d6e3cc2da4285fc', '1fsdfsdafasfqfs234fgdgsfsdf', '68sgfdgdfgdfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('7b717ce970474fc485f5830a0c0ff57b', '1fsdfsdafasfqfs234fgdgsfsdf', '61gdfsgsdfghgjhgjghjjhg');
INSERT INTO `t_role_access_url_mapping` VALUES ('7e51284fa37c46dfa293b07146ca1591', '1fsdfsdafasfqfs234fgdgsfsdf', '1gdfgdfgdfgdfgdfg4');
INSERT INTO `t_role_access_url_mapping` VALUES ('823f932817734724a66f3a82a2b64fb2', '1fsdfsdafasfqfs234fgdgsfsdf', '2gdfdfgdfgdfgd8');
INSERT INTO `t_role_access_url_mapping` VALUES ('8a6895a1e17541ff8db15547bd3f039b', '1fsdfsdafasfqfs234fgdgsfsdf', 'a4fe8b2c9ce543719596e7f92ef0e12b');
INSERT INTO `t_role_access_url_mapping` VALUES ('8c37fc91d17a430b9da1c1903d33eac8', '1fsdfsdafasfqfs234fgdgsfsdf', '7sdgsdgsdagasggh1');
INSERT INTO `t_role_access_url_mapping` VALUES ('8cae4b72021f48a3923867a639aa82d1', '1fsdfsdafasfqfs234fgdgsfsdf', '5fjfhdgsdfasdfsfsdf8');
INSERT INTO `t_role_access_url_mapping` VALUES ('8d2047cedc974865ac4eeb0167819f7e', '1fsdfsdafasfqfs234fgdgsfsdf', '45gfdsgdfgdfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('932b26bc578f4e54bf66fde9ccb67cec', '1fsdfsdafasfqfs234fgdgsfsdf', '2gdfgdfgdfggdsfgsdg4');
INSERT INTO `t_role_access_url_mapping` VALUES ('99f50f52d06b4f29b6d22ccedc16d3ce', '1fsdfsdafasfqfs234fgdgsfsdf', '7asgsadgsagsadg8');
INSERT INTO `t_role_access_url_mapping` VALUES ('9a2997d69bbb4114a97383d35da91400', '1fsdfsdafasfqfs234fgdgsfsdf', '1dffgsdfgsdgdfgdfgdfgdfg0');
INSERT INTO `t_role_access_url_mapping` VALUES ('9ad1a23d183f4a97bd4e2653877f3b72', '1fsdfsdafasfqfs234fgdgsfsdf', '3gdfgdfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('9b02dd3aace348dfa072ed3fe02ad278', '1fsdfsdafasfqfs234fgdgsfsdf', '4sggdfgdsfdfsgdfg2');
INSERT INTO `t_role_access_url_mapping` VALUES ('9cd82ea07c1b43a5979c60d8021dd12a', '1fsdfsdafasfqfs234fgdgsfsdf', '20gdfgdfgdfgdfgdffg');
INSERT INTO `t_role_access_url_mapping` VALUES ('9da14003ff6f4132a5a3e1eaca48deaf', '1fsdfsdafasfqfs234fgdgsfsdf', 'jhg59hgjghjghjghjgfhjgh');
INSERT INTO `t_role_access_url_mapping` VALUES ('9f1f889f613d4e99bdd09c5059204d68', '1fsdfsdafasfqfs234fgdgsfsdf', '7gdfgdfgdfgdfg0');
INSERT INTO `t_role_access_url_mapping` VALUES ('a23950ced8c0420683a0a57befd94cd1', '1fsdfsdafasfqfs234fgdgsfsdf', '47gdfgdfgdfgdfgdfgdfgdf');
INSERT INTO `t_role_access_url_mapping` VALUES ('a76a798fe7fd4e4f80966de4f8ee6bdc', '1fsdfsdafasfqfs234fgdgsfsdf', '66gfdgdfgfdgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('adaca6c802514f1f996a71da6ba92676', '1fsdfsdafasfqfs234fgdgsfsdf', '7gsadgasdgasdgas2');
INSERT INTO `t_role_access_url_mapping` VALUES ('ae18166749704d0b8feef6c6046629ca', '1fsdfsdafasfqfs234fgdgsfsdf', '3f696ebe5d8d4181afde428239284412');
INSERT INTO `t_role_access_url_mapping` VALUES ('b2f74bf1e1cd4d938ae2e61069986e68', '1fsdfsdafasfqfs234fgdgsfsdf', '5fsdfsfsdfdfsdf6');
INSERT INTO `t_role_access_url_mapping` VALUES ('b34240ff696049ef8f38437594c2268e', '1fsdfsdafasfqfs234fgdgsfsdf', '3gdfgdfgdgdgdfg5');
INSERT INTO `t_role_access_url_mapping` VALUES ('b3847c401a4d4c7f965db4667069487c', '1fsdfsdafasfqfs234fgdgsfsdf', 'ae162d184bbd494882c29db315586ef9');
INSERT INTO `t_role_access_url_mapping` VALUES ('b6658956cf5e44658fc177107c8454a5', '1fsdfsdafasfqfs234fgdgsfsdf', '3sdgsdgdgdfgdg4');
INSERT INTO `t_role_access_url_mapping` VALUES ('b7a9a8d1800a4a7dbf03ccd3497d83d0', '1fsdfsdafasfqfs234fgdgsfsdf', '1gdfgdfgdfgdfgdfgdfgdfg6');
INSERT INTO `t_role_access_url_mapping` VALUES ('badd83985c104091beae2bcb761ae2cf', '1fsdfsdafasfqfs234fgdgsfsdf', '6gdgdfgdffgdgdfgdfg5');
INSERT INTO `t_role_access_url_mapping` VALUES ('c14477fe115c48329f795be402f4163e', '1fsdfsdafasfqfs234fgdgsfsdf', '9gfdgdfgdfgdfgdfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('c17a9bd319a344b49664a1a3b0968f4a', '1fsdfsdafasfqfs234fgdgsfsdf', '7gsdgsadgsagsag5');
INSERT INTO `t_role_access_url_mapping` VALUES ('c3a58550caf74c5fb082730c3643c654', '1fsdfsdafasfqfs234fgdgsfsdf', '67gdfgdsfgsdgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('c68ce6a6d1ec428681447c4836d0dd96', '1fsdfsdafasfqfs234fgdgsfsdf', '5fsdfsdfsdfsdfsd7');
INSERT INTO `t_role_access_url_mapping` VALUES ('c8e41c387812456d955d1dbd999099bc', '1fsdfsdafasfqfs234fgdgsfsdf', '1gdsfgsdfgdfgdsfgdsfgdfsg9');
INSERT INTO `t_role_access_url_mapping` VALUES ('cb07b749024c49db97e1ad6eba87eb14', '1fsdfsdafasfqfs234fgdgsfsdf', '1gfdgdfgdsgdsgdfgsdg5');
INSERT INTO `t_role_access_url_mapping` VALUES ('cbd704a02c734ad4ab8179859168e45f', '1fsdfsdafasfqfs234fgdgsfsdf', '48gdfgdfgdfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('e341b1470a7d42969bbade2b5989cfd2', '1fsdfsdafasfqfs234fgdgsfsdf', '79fsdfsdfsdgsgsag');
INSERT INTO `t_role_access_url_mapping` VALUES ('e7ee0c54e1a340439fef4bb6b9efc1cf', '1fsdfsdafasfqfs234fgdgsfsdf', '1gdfgdfgdfgdfgdfgdfgdsfgdfg7');
INSERT INTO `t_role_access_url_mapping` VALUES ('e8d80cb9dcd24385bf148fafaebf4bee', '1fsdfsdafasfqfs234fgdgsfsdf', 'fafdsfsdfsadfa1sdfas11');
INSERT INTO `t_role_access_url_mapping` VALUES ('ef6026fbad4449ddb3cff51b26e30a43', '1fsdfsdafasfqfs234fgdgsfsdf', '5fsdfsdfsadfsdf2');
INSERT INTO `t_role_access_url_mapping` VALUES ('f1f2ee2e4c464a51aa068fe265b58461', '1fsdfsdafasfqfs234fgdgsfsdf', '4ybtybytryebtrb0');
INSERT INTO `t_role_access_url_mapping` VALUES ('fc84cd984e724be7a975f3f7f1f17f71', '1fsdfsdafasfqfs234fgdgsfsdf', '36867u7b5b54b56b754b7b');
INSERT INTO `t_role_access_url_mapping` VALUES ('fd303d28477a4db58d5b0761ab0ad299', '1fsdfsdafasfqfs234fgdgsfsdf', '5fsdafsafsdaf4');

-- ----------------------------
-- Table structure for `t_role_button_mapping`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_button_mapping`;
CREATE TABLE `t_role_button_mapping` (
  `RBM_ID` varchar(64) NOT NULL COMMENT 'ID',
  `ROLE_ID` varchar(64) NOT NULL COMMENT '角色ID',
  `BUTTON_ID` varchar(64) NOT NULL COMMENT '按钮ID',
  PRIMARY KEY (`RBM_ID`),
  KEY `FK_ROLE_BUTTON_M_ROLE_ID` (`ROLE_ID`),
  KEY `FK_ROLE_BUTTON_M_BUTTON_ID` (`BUTTON_ID`),
  CONSTRAINT `FK_ROLE_BUTTON_M_BUTTON_ID` FOREIGN KEY (`BUTTON_ID`) REFERENCES `t_button` (`BUTTON_ID`),
  CONSTRAINT `FK_ROLE_BUTTON_M_ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `t_role` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_button_mapping
-- ----------------------------
INSERT INTO `t_role_button_mapping` VALUES ('072f09f8e76c495ca0839baaaedb5fb8', '1fsdfsdafasfqfs234fgdgsfsdf', '2fsdfsdfsdfsdf');
INSERT INTO `t_role_button_mapping` VALUES ('089c9871074149e49378b49349b4b865', '1fsdfsdafasfqfs234fgdgsfsdf', '3sdafsavasdfs5');
INSERT INTO `t_role_button_mapping` VALUES ('0f489585e2d548179ad644b826f54e07', '1fsdfsdafasfqfs234fgdgsfsdf', '4htetgdfsg435t53455');
INSERT INTO `t_role_button_mapping` VALUES ('10be6806fb9d427e95f0ef24f06a7518', '1fsdfsdafasfqfs234fgdgsfsdf', '3fsdafasfsafb6');
INSERT INTO `t_role_button_mapping` VALUES ('13a851ed883b4aeba179a615631dc35b', '1fsdfsdafasfqfs234fgdgsfsdf', 'ggsdgsg7');
INSERT INTO `t_role_button_mapping` VALUES ('13d7550458374eda9cc111e88c9d5248', '1fsdfsdafasfqfs234fgdgsfsdf', '3dgfdsgvvzcvfg9');
INSERT INTO `t_role_button_mapping` VALUES ('1997f4af60c54af7aa06944174b30648', '1fsdfsdafasfqfs234fgdgsfsdf', '2aadsafsdfsdf1');
INSERT INTO `t_role_button_mapping` VALUES ('203b5d7a0e3c404a8c0cc2553240b0b1', '1fsdfsdafasfqfs234fgdgsfsdf', '2vzxvxzvzxv8');
INSERT INTO `t_role_button_mapping` VALUES ('21fb6448955e424d85651e222bba8693', '1fsdfsdafasfqfs234fgdgsfsdf', '1hgfhfghfg0');
INSERT INTO `t_role_button_mapping` VALUES ('25fe8026519749f1b98731c47c61f835', '1fsdfsdafasfqfs234fgdgsfsdf', '3vcxzcvzvxzcv4');
INSERT INTO `t_role_button_mapping` VALUES ('276355fcd6424c91be8e741c918ba98c', '1fsdfsdafasfqfs234fgdgsfsdf', '13hdfhdfghfds');
INSERT INTO `t_role_button_mapping` VALUES ('28b7847b83c6416f8bd7ddd24a6a0226', '1fsdfsdafasfqfs234fgdgsfsdf', '46fsdfsadfsadf');
INSERT INTO `t_role_button_mapping` VALUES ('2c7b5a55e8ad4eb2a8ad7c2039354cbc', '1fsdfsdafasfqfs234fgdgsfsdf', '4fsfsadfsddf');
INSERT INTO `t_role_button_mapping` VALUES ('2d0e470a4d05405b9d652cf5576f0492', '1fsdfsdafasfqfs234fgdgsfsdf', '41sgdfgdsfg4454');
INSERT INTO `t_role_button_mapping` VALUES ('3157637eab4640a78d8f3d2b228fe968', '1fsdfsdafasfqfs234fgdgsfsdf', '3gfdsgsdfgdsfg0');
INSERT INTO `t_role_button_mapping` VALUES ('3799ad4f6a00469e8918186258a6207b', '1fsdfsdafasfqfs234fgdgsfsdf', '1yy54yhtrbbbb9');
INSERT INTO `t_role_button_mapping` VALUES ('37a30284e7ad470f877ac6fa39ab8b7a', '1fsdfsdafasfqfs234fgdgsfsdf', '24c0d24080ee406c9d88fe93ecb78b81');
INSERT INTO `t_role_button_mapping` VALUES ('37dc534701f8405dade85c42c41ca546', '1fsdfsdafasfqfs234fgdgsfsdf', 'gsdgsdgdsgsag8');
INSERT INTO `t_role_button_mapping` VALUES ('3d43ac5b8d0345279c9d380e49a35846', '1fsdfsdafasfqfs234fgdgsfsdf', '3fsafsdfsaf2');
INSERT INTO `t_role_button_mapping` VALUES ('4351a1d8fe254c53aee12eb94774b1a0', '1fsdfsdafasfqfs234fgdgsfsdf', '4gsggsdgdgdfg3');
INSERT INTO `t_role_button_mapping` VALUES ('447148b0c49b4652a8e30c0eb074caaf', '1fsdfsdafasfqfs234fgdgsfsdf', '5fsdfsadfsafsdf');
INSERT INTO `t_role_button_mapping` VALUES ('49e09c83933640c584e4d8c0b1a1b30c', '1fsdfsdafasfqfs234fgdgsfsdf', '1fsdfsdfsdfsadf');
INSERT INTO `t_role_button_mapping` VALUES ('4a69ce872812423d8ce9ff8222f02976', '1fsdfsdafasfqfs234fgdgsfsdf', '1safsafsafbfdgsg4');
INSERT INTO `t_role_button_mapping` VALUES ('4cde70d14d6f4841829dd31d1fe8ad79', '1fsdfsdafasfqfs234fgdgsfsdf', '2dfsfgdgfdsuiutiuyi4');
INSERT INTO `t_role_button_mapping` VALUES ('561a9e9db2f7444fa9cad6b958618b3e', '1fsdfsdafasfqfs234fgdgsfsdf', '12hfghdfhfdhfgh');
INSERT INTO `t_role_button_mapping` VALUES ('562466e3e134417ca53d5e1bb5237293', '1fsdfsdafasfqfs234fgdgsfsdf', '33afasfsafas');
INSERT INTO `t_role_button_mapping` VALUES ('5ea9711f3e5f43ccad0b605540dc6c7d', '1fsdfsdafasfqfs234fgdgsfsdf', '26fdsgdfsgdsfggdsf');
INSERT INTO `t_role_button_mapping` VALUES ('5f38d0bd5f0a4eae9cffd9e054530aa3', '1fsdfsdafasfqfs234fgdgsfsdf', '2dsfgdgdfgdfgdgdsg2');
INSERT INTO `t_role_button_mapping` VALUES ('6a98b2e41a0e4e259346d20d64a22e60', '1fsdfsdafasfqfs234fgdgsfsdf', '38gfdsgsdfg');
INSERT INTO `t_role_button_mapping` VALUES ('6e190a26fdfa47c78e1daa112c95007d', '1fsdfsdafasfqfs234fgdgsfsdf', '1fsgdfsgddf5');
INSERT INTO `t_role_button_mapping` VALUES ('7d24e400b9674439be557f729adc196f', '1fsdfsdafasfqfs234fgdgsfsdf', '1hdfghfghfghfgh1');
INSERT INTO `t_role_button_mapping` VALUES ('7ee636041533468fa02b5a10732fdd3a', '1fsdfsdafasfqfs234fgdgsfsdf', '2gdfsbdsjhghfdsgdgsd0');
INSERT INTO `t_role_button_mapping` VALUES ('7f941f55b3b64aa281cb79a46f4da33c', '1fsdfsdafasfqfs234fgdgsfsdf', '29hfdfghfghdfhdfh');
INSERT INTO `t_role_button_mapping` VALUES ('81df807a37b54207a5ac7b6944486d0d', '1fsdfsdafasfqfs234fgdgsfsdf', '3fsfsdfsdffsafsad');
INSERT INTO `t_role_button_mapping` VALUES ('89505832e0874cd6af8f541eb25232bd', '1fsdfsdafasfqfs234fgdgsfsdf', '2xvxcvzvzxvdsgxv7');
INSERT INTO `t_role_button_mapping` VALUES ('8a55323733884a3c974a82c727015413', '1fsdfsdafasfqfs234fgdgsfsdf', '1bfbvcbcvxbvcxbbbxcb7');
INSERT INTO `t_role_button_mapping` VALUES ('96780f7e34d8487590d559ef030281aa', '1fsdfsdafasfqfs234fgdgsfsdf', 'fsadfsadfasdff6');
INSERT INTO `t_role_button_mapping` VALUES ('9b4fd3271e2f4699b196ba4577d6f356', '3b370aa1bc464890a0bc99d9b7c42a4b', '1hgfhfghfg0');
INSERT INTO `t_role_button_mapping` VALUES ('a04c08d68cbe4117b3aaea0aaa07d1e1', '1fsdfsdafasfqfs234fgdgsfsdf', '47gsdgfdsgdsfgdsg');
INSERT INTO `t_role_button_mapping` VALUES ('ad9e85e8225147c98ac2cfc731bb54a8', '1fsdfsdafasfqfs234fgdgsfsdf', '2tryeyruiibbxvcb5');
INSERT INTO `t_role_button_mapping` VALUES ('afdc51c3fce74434b44c7d2655f6322d', '1fsdfsdafasfqfs234fgdgsfsdf', '4gfdsgdfs2');
INSERT INTO `t_role_button_mapping` VALUES ('b47838a8313646e5b6cbd8ac9a7d8cd9', '1fsdfsdafasfqfs234fgdgsfsdf', '9hghghfghfghfghh');
INSERT INTO `t_role_button_mapping` VALUES ('baaccf2ab0bf4fc494e13eafd998dbec', '1fsdfsdafasfqfs234fgdgsfsdf', 'e546d68fd1a24fddae70212ee1a6a5d5');
INSERT INTO `t_role_button_mapping` VALUES ('be32ad09c5d74c5495fbfbbd66dfdb7e', '1fsdfsdafasfqfs234fgdgsfsdf', '76b6091761d04de39f8bd5436c1806dc');
INSERT INTO `t_role_button_mapping` VALUES ('d979de802cdb435f9b65de497773113d', '1fsdfsdafasfqfs234fgdgsfsdf', '3gdsfgvbcgdhdgh7');
INSERT INTO `t_role_button_mapping` VALUES ('dbd21af8941245b6aeff777a8840c2f8', '1fsdfsdafasfqfs234fgdgsfsdf', '4gfdsgsgf0');
INSERT INTO `t_role_button_mapping` VALUES ('dc2f4cf674804332a091afc8f0a54ebb', '1fsdfsdafasfqfs234fgdgsfsdf', '4543gdfsfg53454');
INSERT INTO `t_role_button_mapping` VALUES ('e29bad6df9db4016b911f0e6c31a5e75', '1fsdfsdafasfqfs234fgdgsfsdf', '5038fc7f3b5846a0a075193079f74dff');
INSERT INTO `t_role_button_mapping` VALUES ('e642a497c2b54e0f9c91b74efb75b97e', '1fsdfsdafasfqfs234fgdgsfsdf', '16gdfsgdfgdsg');
INSERT INTO `t_role_button_mapping` VALUES ('f45aab22a8ac499b84433aa97b28576c', '1fsdfsdafasfqfs234fgdgsfsdf', '3fsadfasfas1');
INSERT INTO `t_role_button_mapping` VALUES ('f4730f5f02f24f328102a7efee634d84', '1fsdfsdafasfqfs234fgdgsfsdf', '1tretrgsdgdfsgsdgsdg8');
INSERT INTO `t_role_button_mapping` VALUES ('faf04b292bf04a4482be44e0ffaf0bbc', '1fsdfsdafasfqfs234fgdgsfsdf', '2hjhgjkklhjghgjfhfdh3');

-- ----------------------------
-- Table structure for `t_role_data_permit_mapping`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_data_permit_mapping`;
CREATE TABLE `t_role_data_permit_mapping` (
  `RDPM_ID` varchar(64) NOT NULL COMMENT '角色数据权限映射ID',
  `DATA_PERMISSIONS_ID` varchar(64) NOT NULL COMMENT '数据权限ID',
  `ROLE_ID` varchar(64) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`RDPM_ID`),
  KEY `FK_ROLE_DP_M_DATA_PERMIT_ID` (`DATA_PERMISSIONS_ID`),
  KEY `FK_ROLE_DP_M_DATA_ROLE_ID` (`ROLE_ID`),
  CONSTRAINT `FK_ROLE_DP_M_DATA_PERMIT_ID` FOREIGN KEY (`DATA_PERMISSIONS_ID`) REFERENCES `t_data_permissions` (`DATA_PERMISSIONS_ID`),
  CONSTRAINT `FK_ROLE_DP_M_DATA_ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `t_role` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_data_permit_mapping
-- ----------------------------
INSERT INTO `t_role_data_permit_mapping` VALUES ('ade589d717b541289374ffbdc7621070', 'asdfff1234566fsfasdfaddsaaf', '3b370aa1bc464890a0bc99d9b7c42a4b');
INSERT INTO `t_role_data_permit_mapping` VALUES ('bd65a19a3d3b41f58e3b549767654ed5', 'asdfghj1234566fsfasdfaddsf', '1fsdfsdafasfqfs234fgdgsfsdf');
INSERT INTO `t_role_data_permit_mapping` VALUES ('c78ef8b3c8e8455e9bf67c1136a4d5e4', 'asdfff1234566fsfasdfaddsaaf', '1fsdfsdafasfqfs234fgdgsfsdf');
INSERT INTO `t_role_data_permit_mapping` VALUES ('d40878b1cf1d467caef88becaf60cf25', 'asdfghj1234566fsfasdfadsf', '1fsdfsdafasfqfs234fgdgsfsdf');

-- ----------------------------
-- Table structure for `t_role_menu_mapping`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu_mapping`;
CREATE TABLE `t_role_menu_mapping` (
  `RMM_ID` varchar(64) NOT NULL COMMENT 'ID',
  `ROLE_ID` varchar(64) NOT NULL COMMENT '角色ID',
  `MENU_ID` varchar(64) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`RMM_ID`),
  KEY `FK_ROLE_MENU_M_ROLE_ID` (`ROLE_ID`),
  KEY `FK_ROLE_MENU_M_MENU_ID` (`MENU_ID`),
  CONSTRAINT `FK_ROLE_MENU_M_MENU_ID` FOREIGN KEY (`MENU_ID`) REFERENCES `t_menu` (`MENU_ID`),
  CONSTRAINT `FK_ROLE_MENU_M_ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `t_role` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_menu_mapping
-- ----------------------------
INSERT INTO `t_role_menu_mapping` VALUES ('037f1cbb97914f1da1e0db0c81cf4bd0', '1fsdfsdafasfqfs234fgdgsfsdf', 'dfhgfhhgf3gdgdsgfh1');
INSERT INTO `t_role_menu_mapping` VALUES ('0617e4a281a24f6c9aa02decc4c3b190', '1fsdfsdafasfqfs234fgdgsfsdf', '1gfdgsdgsdgfdgsdfg0');
INSERT INTO `t_role_menu_mapping` VALUES ('07f598e7e1cc4138bad28c60f0485766', '3b370aa1bc464890a0bc99d9b7c42a4b', '1gdfgdsfgdsfgdfg6');
INSERT INTO `t_role_menu_mapping` VALUES ('08836804da9f4cc8aec4c904e63cc6f7', '3b370aa1bc464890a0bc99d9b7c42a4b', 'dfhgfhhgf3gdgdsgfh1');
INSERT INTO `t_role_menu_mapping` VALUES ('10efb2f006554dfea520d9611cb0cf7f', '3b370aa1bc464890a0bc99d9b7c42a4b', '5tytryreyyrtyrt');
INSERT INTO `t_role_menu_mapping` VALUES ('16223eedda6a44d7a2fc42fec547c357', '1fsdfsdafasfqfs234fgdgsfsdf', '3yrtyerhgfyerty');
INSERT INTO `t_role_menu_mapping` VALUES ('320501b308224233a72621af72ab2a1c', '1fsdfsdafasfqfs234fgdgsfsdf', '5tytryreyyrtyrt');
INSERT INTO `t_role_menu_mapping` VALUES ('3d7b759901854919975b37e05c15129c', '1fsdfsdafasfqfs234fgdgsfsdf', '1gdfgdfgdfgdfg2');
INSERT INTO `t_role_menu_mapping` VALUES ('3ec58b06715147ce8111b94e557c425d', '1fsdfsdafasfqfs234fgdgsfsdf', '2cce82e8b5274b6d8acb65c888415bb2');
INSERT INTO `t_role_menu_mapping` VALUES ('41ce156e15de481abf755be5382a8d63', '1fsdfsdafasfqfs234fgdgsfsdf', 'ytryedhhdtryrty2');
INSERT INTO `t_role_menu_mapping` VALUES ('4333fb0eea784eb082f9150af7ae14e9', '3b370aa1bc464890a0bc99d9b7c42a4b', '1gdfgdfgdfgdfsg5');
INSERT INTO `t_role_menu_mapping` VALUES ('4b622f7b617d4cbfa998a53a824e7bc0', '1fsdfsdafasfqfs234fgdgsfsdf', '1gdfsgfgfdg7');
INSERT INTO `t_role_menu_mapping` VALUES ('4bb3795d19044125b29f0f493c40d4a2', '3b370aa1bc464890a0bc99d9b7c42a4b', '3yrtyerhgfyerty');
INSERT INTO `t_role_menu_mapping` VALUES ('51f24ad1158e4933bcd5f396ef2353d6', '1fsdfsdafasfqfs234fgdgsfsdf', '1gdfdggfdgdfggdfg1');
INSERT INTO `t_role_menu_mapping` VALUES ('5d8438c3c68f4c06b67b56fc0af4d6f1', '1fsdfsdafasfqfs234fgdgsfsdf', 'd2801e0e39654be8a57785cde7430bba');
INSERT INTO `t_role_menu_mapping` VALUES ('5dd2131f5d274a40bd08d52af1faecd8', '1fsdfsdafasfqfs234fgdgsfsdf', '1gdfgdfgdfgdfsg5');
INSERT INTO `t_role_menu_mapping` VALUES ('60c0d9c6e61a47f6b81071bb2db8a92a', '1fsdfsdafasfqfs234fgdgsfsdf', '1gdfgdsfgdsfgdfg6');
INSERT INTO `t_role_menu_mapping` VALUES ('6bdabdb57787434b878695e51ace9ed2', '1fsdfsdafasfqfs234fgdgsfsdf', 'e041386708254e31beae8df1c92a4a8c');
INSERT INTO `t_role_menu_mapping` VALUES ('734fe87f35c54f7bbe7e4ec7f7d1f1a4', '1fsdfsdafasfqfs234fgdgsfsdf', '9stryytrytrytr');
INSERT INTO `t_role_menu_mapping` VALUES ('7918763a82c04f8b82cb3785540a2c54', '1fsdfsdafasfqfs234fgdgsfsdf', '7ereeet');
INSERT INTO `t_role_menu_mapping` VALUES ('7baa5d41faa048789e3009f830fc1a3c', '1fsdfsdafasfqfs234fgdgsfsdf', '8ytryrtytry');
INSERT INTO `t_role_menu_mapping` VALUES ('a9d7e7309f7d42cbbc27f35fe677f55e', '1fsdfsdafasfqfs234fgdgsfsdf', '6ytryetryeeytrt');
INSERT INTO `t_role_menu_mapping` VALUES ('adde713f24764027926e0965d9b67150', '1fsdfsdafasfqfs234fgdgsfsdf', 'caf02e05617f4b3ea81b372bd5a10a35');
INSERT INTO `t_role_menu_mapping` VALUES ('b0269d7fb8fc4912bde7fe4f1a91d56a', '1fsdfsdafasfqfs234fgdgsfsdf', '1gdfgdfgdfgfdg4');
INSERT INTO `t_role_menu_mapping` VALUES ('b26be975666e4807932e2be3b987fb78', '3b370aa1bc464890a0bc99d9b7c42a4b', '1gdfsgfgfdg7');
INSERT INTO `t_role_menu_mapping` VALUES ('d78a4c0f3451460eb911eb1f7471ff4c', '1fsdfsdafasfqfs234fgdgsfsdf', 'ytryrtyrety4');
INSERT INTO `t_role_menu_mapping` VALUES ('d9f803cad1db4c4f9c380a235ada4671', '1fsdfsdafasfqfs234fgdgsfsdf', 'a2cf1e8ab74449c29861404214421841');
INSERT INTO `t_role_menu_mapping` VALUES ('e41f2475f0e34cbebfaf39ff811d5fea', '1fsdfsdafasfqfs234fgdgsfsdf', '82273d9bb3144250a6997a1bfbd90bfe');
INSERT INTO `t_role_menu_mapping` VALUES ('ea2433d1638246dcab5b9221857e3235', '3b370aa1bc464890a0bc99d9b7c42a4b', 'ytryedhhdtryrty2');
INSERT INTO `t_role_menu_mapping` VALUES ('fa684d41886047a1baff565902a4a735', '1fsdfsdafasfqfs234fgdgsfsdf', '1gdfgdfgdfgdfgdfg3');

-- ----------------------------
-- Table structure for `t_task`
-- ----------------------------
DROP TABLE IF EXISTS `t_task`;
CREATE TABLE `t_task` (
  `TASK_ID` varchar(64) NOT NULL COMMENT 'ID',
  `BUSNIESS_MARK` varchar(100) DEFAULT NULL COMMENT '业务标识',
  `BUSNIESS_ID` varchar(64) DEFAULT NULL COMMENT '业务ID',
  `TITLE` varchar(120) DEFAULT NULL COMMENT '标题',
  `TASK_URL` varchar(300) DEFAULT NULL COMMENT 'URL',
  `TASK_PARAMETER` varchar(60) DEFAULT NULL COMMENT '待办参数',
  `STATUS` char(1) DEFAULT NULL COMMENT '状态(1正常，2已处理，3删除)',
  `PROCESS_USER_ID` varchar(64) DEFAULT NULL COMMENT '处理人ID',
  `CREATE_USER_ID` varchar(64) DEFAULT NULL COMMENT '创建人ID',
  `UPDATE_USER_ID` varchar(64) DEFAULT NULL COMMENT '修改人ID',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`TASK_ID`),
  KEY `FK_TASK_C_USER_ID` (`CREATE_USER_ID`),
  KEY `FK_TASK_U_USER_ID` (`UPDATE_USER_ID`),
  KEY `FK_TASK_P_USER_ID` (`PROCESS_USER_ID`),
  CONSTRAINT `FK_TASK_C_USER_ID` FOREIGN KEY (`CREATE_USER_ID`) REFERENCES `t_user` (`USER_ID`),
  CONSTRAINT `FK_TASK_P_USER_ID` FOREIGN KEY (`PROCESS_USER_ID`) REFERENCES `t_user` (`USER_ID`),
  CONSTRAINT `FK_TASK_U_USER_ID` FOREIGN KEY (`UPDATE_USER_ID`) REFERENCES `t_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_task
-- ----------------------------
INSERT INTO `t_task` VALUES ('1', null, null, '您有一个用户信息待查看！！！', '/app/user/userView', '{\"id\":\"1aaaaaaaaaaaaaaaaaaaa\"}', '1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2019-09-26 20:01:07', '2021-03-23 11:26:23');
INSERT INTO `t_task` VALUES ('2', null, null, '您有一个角色信息待查看！！！', '/app/role/roleView', '{\"id\":\"1fsdfsdafasfqfs234fgdgsfsdf\"}', '1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2019-09-26 20:15:40', '2021-03-23 11:26:24');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `USER_ID` varchar(64) NOT NULL COMMENT '用户ID',
  `USER_CODE` char(30) DEFAULT NULL COMMENT '用户编号',
  `USER_NAME` varchar(50) DEFAULT NULL COMMENT '用户名',
  `PASSWORD` varchar(50) DEFAULT NULL COMMENT '密码',
  `REAL_NAME` varchar(30) DEFAULT NULL COMMENT '真实姓名',
  `SEX` char(1) DEFAULT NULL COMMENT '性别：1男，2女，3保密',
  `TELEPHONE` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `ID_CARD` char(20) DEFAULT NULL COMMENT '身份证',
  `EMAIL` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `EMAIL_SWITCH` char(1) DEFAULT NULL COMMENT '邮件开关(1开启，2关闭)',
  `SMS_SWITCH` char(1) DEFAULT NULL COMMENT '短信开关(1开启，2关闭)',
  `STATUS` char(1) DEFAULT NULL COMMENT '状态(1正常，2锁定，3删除)',
  `USER_DESCRIBE` varchar(200) DEFAULT NULL COMMENT '用户描述',
  `CREATE_USER_ID` varchar(64) DEFAULT NULL COMMENT '创建人ID',
  `UPDATE_USER_ID` varchar(64) DEFAULT NULL COMMENT '修改人ID',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`USER_ID`),
  KEY `FK_USER_C_USER_ID` (`CREATE_USER_ID`),
  KEY `FK_USER_U_USER_ID` (`UPDATE_USER_ID`),
  CONSTRAINT `FK_USER_C_USER_ID` FOREIGN KEY (`CREATE_USER_ID`) REFERENCES `t_user` (`USER_ID`),
  CONSTRAINT `FK_USER_U_USER_ID` FOREIGN KEY (`UPDATE_USER_ID`) REFERENCES `t_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1aaaaaaaaaaaaaaaaaaaa', '001', 'sysadmin', '534b4e6c744d34654d697056727041583839727777413d3d', '管理员', '1', '', '', '', '1', '1', '1', '系统超级管理员拥有最高的权限，操作时请注意!', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2018-06-10 11:58:15', '2019-10-18 18:51:15');

-- ----------------------------
-- Table structure for `t_user_dept_mapping`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_dept_mapping`;
CREATE TABLE `t_user_dept_mapping` (
  `UDM_ID` varchar(64) NOT NULL COMMENT 'ID',
  `USER_ID` varchar(64) NOT NULL COMMENT '用户ID',
  `DEPT_ID` varchar(64) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`UDM_ID`),
  KEY `FK_USER_DEPT_M_USER_ID` (`USER_ID`),
  KEY `FK_USER_DEPT_M_DEPT_ID` (`DEPT_ID`),
  CONSTRAINT `FK_USER_DEPT_M_DEPT_ID` FOREIGN KEY (`DEPT_ID`) REFERENCES `t_dept` (`DEPT_ID`),
  CONSTRAINT `FK_USER_DEPT_M_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `t_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_dept_mapping
-- ----------------------------
INSERT INTO `t_user_dept_mapping` VALUES ('1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaafsadf');

-- ----------------------------
-- Table structure for `t_user_role_mapping`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role_mapping`;
CREATE TABLE `t_user_role_mapping` (
  `URM_ID` varchar(64) NOT NULL COMMENT '用户角色映射ID',
  `USER_ID` varchar(64) NOT NULL COMMENT '用户ID',
  `ROLE_ID` varchar(64) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`URM_ID`),
  KEY `FK_USER_ROLE_M_USER_ID` (`USER_ID`),
  KEY `FK_USER_ROLE_M_ROLE_ID` (`ROLE_ID`),
  CONSTRAINT `FK_USER_ROLE_M_ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `t_role` (`ROLE_ID`),
  CONSTRAINT `FK_USER_ROLE_M_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `t_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role_mapping
-- ----------------------------
INSERT INTO `t_user_role_mapping` VALUES ('1', '1aaaaaaaaaaaaaaaaaaaa', '1fsdfsdafasfqfs234fgdgsfsdf');
