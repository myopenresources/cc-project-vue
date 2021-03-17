/*
Navicat MySQL Data Transfer

Source Server         : def
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : sys_db

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2020-08-17 14:46:49
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
INSERT INTO `t_access_url` VALUES ('4sdfbgfdfssssgsd1', '/system/initHomeData.jhtml', '初始化主页', 'SYS_COMMON', '1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2018-06-10 09:55:01', '2019-11-09 16:52:48');
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
INSERT INTO `t_button` VALUES ('12hfghdfhfdhfgh', '按钮编号', '添加', 'roleAdd', 'ROLE_MANAGE', 'TOOL_BTN', 'plus', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-20 17:36:12', null, 'primary', null);
INSERT INTO `t_button` VALUES ('13hdfhdfghfds', '按钮编号', '查看', 'roleView', 'ROLE_MANAGE', 'ROW_BTN', 'search', '', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-20 17:36:43', null, 'info', null);
INSERT INTO `t_button` VALUES ('16gdfsgdfgdsg', '按钮编号', '角色用户', 'roleUsers', 'ROLE_MANAGE', 'ROW_BTN', 'user', '', '5', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-20 17:38:55', null, 'info', null);
INSERT INTO `t_button` VALUES ('1bfbvcbcvxbvcxbbbxcb7', '按钮编号', '权限分配', 'rolePermissionDistribute', 'ROLE_MANAGE', 'ROW_BTN', 'setting', '', '7', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-20 17:40:13', null, 'warning', null);
INSERT INTO `t_button` VALUES ('1fsdfsdfsdfsadf', '按钮编号', '添加', 'userAdd', 'USER_MANAGE', 'TOOL_BTN', 'plus', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-19 17:28:31', null, 'primary', null);
INSERT INTO `t_button` VALUES ('1fsgdfsgddf5', '按钮编号', '删除', 'deleteRole', 'ROLE_MANAGE', 'TOOL_BTN', 'delete', '', '4', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-20 17:38:21', null, 'error', null);
INSERT INTO `t_button` VALUES ('1hdfghfghfghfgh1', '按钮编号', '部门人员', 'deptUsers', 'DEPT_MANAGE', 'RIGHT_TOOL_BTN', 'usergroup-add', '', '6', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-20 17:33:41', null, 'info', null);
INSERT INTO `t_button` VALUES ('1hgfhfghfg0', '按钮编号', '删除', 'deleteDept', 'DEPT_MANAGE', 'LEFT_TOOL_BTN', 'delete', '', '4', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-20 17:32:02', null, 'error', null);
INSERT INTO `t_button` VALUES ('1safsafsafbfdgsg4', '按钮编号', '编辑', 'roleEdit', 'ROLE_MANAGE', 'ROW_BTN', 'edit', '', '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-20 17:37:10', null, 'success', null);
INSERT INTO `t_button` VALUES ('1tretrgsdgdfsgsdgsdg8', '按钮编号', '添加', 'menuAdd', 'MENU_MANAGE', 'LEFT_TOOL_BTN', 'plus', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:30:58', null, 'primary', null);
INSERT INTO `t_button` VALUES ('1yy54yhtrbbbb9', '按钮编号', '编辑', 'menuEdit', 'MENU_MANAGE', 'RIGHT_TOOL_BTN', 'edit', '', '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:31:48', null, 'success', null);
INSERT INTO `t_button` VALUES ('24c0d24080ee406c9d88fe93ecb78b81', '按钮编号', '编辑', 'dataPermissionsEdit', 'DATA_PERMISSIONS_MANAGE', 'ROW_BTN', 'edit', '', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-27 09:19:05', null, 'success', '');
INSERT INTO `t_button` VALUES ('26fdsgdfsgdsfggdsf', '按钮编号', '编辑', 'dataDicTypeEdit', 'DATA_DIC_MANAGE', 'LEFT_ROW_BTN', 'edit', '', '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:37:19', null, 'success', null);
INSERT INTO `t_button` VALUES ('29hfdfghfghdfhdfh', '按钮编号', '查看', 'dataDicItemView', 'DATA_DIC_MANAGE', 'RIGHT_ROW_BTN', 'search', '', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:39:08', null, 'info', null);
INSERT INTO `t_button` VALUES ('2aadsafsdfsdf1', '按钮编号', '添加', 'buttonAdd', 'BTN_MANAGE', 'TOOL_BTN', 'plus', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:34:57', null, 'primary', null);
INSERT INTO `t_button` VALUES ('2dfsfgdgfdsuiutiuyi4', '按钮编号', '删除', 'deleteButton', 'BTN_MANAGE', 'TOOL_BTN', 'delete', '', '4', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:35:27', null, 'error', null);
INSERT INTO `t_button` VALUES ('2dsfgdgdfgdfgdgdsg2', '按钮编号', '查看', 'buttonView', 'BTN_MANAGE', 'ROW_BTN', 'search', '', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:34:37', null, 'info', null);
INSERT INTO `t_button` VALUES ('2fsdfsdfsdfsdf', '按钮编号', '查看', 'userView', 'USER_MANAGE', 'ROW_BTN', 'search', '', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-19 17:34:08', null, 'info', null);
INSERT INTO `t_button` VALUES ('2gdfsbdsjhghfdsgdgsd0', '按钮编号', '删除', 'deleteMenu', 'MENU_MANAGE', 'LEFT_TOOL_BTN', 'delete', '', '5', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 10:34:54', null, 'error', null);
INSERT INTO `t_button` VALUES ('2hjhgjkklhjghgjfhfdh3', '按钮编号', '编辑', 'buttonEdit', 'BTN_MANAGE', 'ROW_BTN', 'edit', '', '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:35:03', null, 'success', null);
INSERT INTO `t_button` VALUES ('2tryeyruiibbxvcb5', '按钮编号', '添加', 'dataDicTypeAdd', 'DATA_DIC_MANAGE', 'LEFT_TOOL_BTN', 'plus', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:36:48', null, 'primary', null);
INSERT INTO `t_button` VALUES ('2vzxvxzvzxv8', '按钮编号', '添加', 'dataDicItemAdd', 'DATA_DIC_MANAGE', 'RIGHT_TOOL_BTN', 'plus', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:38:37', null, 'primary', null);
INSERT INTO `t_button` VALUES ('2xvxcvzvzxvdsgxv7', '按钮编号', '删除', 'deleteDataDic', 'DATA_DIC_MANAGE', 'LEFT_TOOL_BTN', 'delete', '', '4', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:37:55', null, 'error', null);
INSERT INTO `t_button` VALUES ('33afasfsafas', '按钮编号', '下载', 'downloadFile', 'SYS_LOG_MANAGE', 'ROW_BTN', 'download', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:45:43', null, 'success', null);
INSERT INTO `t_button` VALUES ('38gfdsgsdfg', '按钮编号', '查看', 'accessUrlView', 'ACCESS_URL_MANAGE', 'ROW_BTN', 'search', '', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-08-29 16:28:27', null, 'info', null);
INSERT INTO `t_button` VALUES ('3d224750b9854b56b3f072e09129ea1b', '按钮编号', '部门结构图', 'deptStructure', 'DEPT_MANAGE', 'TOOL_BTN', 'apartment', '', '4', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-13 19:20:27', null, 'primary', '');
INSERT INTO `t_button` VALUES ('3dgfdsgvvzcvfg9', '按钮编号', '编辑', 'accessUrlEdit', 'ACCESS_URL_MANAGE', 'ROW_BTN', 'edit', '', '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-08-29 16:29:06', null, 'success', null);
INSERT INTO `t_button` VALUES ('3fsadfasfas1', '按钮编号', '删除', 'deleteDicItem', 'DATA_DIC_MANAGE', 'RIGHT_TOOL_BTN', 'delete', '', '4', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:40:08', null, 'error', null);
INSERT INTO `t_button` VALUES ('3fsafsdfsaf2', '按钮编号', '分配角色', 'dicItemRoleDistribute', 'DATA_DIC_MANAGE', 'RIGHT_ROW_BTN', 'setting', '', '7', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:41:20', null, 'warning', null);
INSERT INTO `t_button` VALUES ('3fsdafasfsafb6', '按钮编号', '查看', 'taskView', 'USER_TASK_MANAGE', 'ROW_BTN', 'search', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-07-22 17:02:50', null, 'info', null);
INSERT INTO `t_button` VALUES ('3fsfsdfsdffsafsad', '按钮编号', '编辑', 'userEdit', 'USER_MANAGE', 'ROW_BTN', 'edit', '', '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-19 17:35:19', null, 'success', null);
INSERT INTO `t_button` VALUES ('3gdsfgvbcgdhdgh7', '按钮编号', '添加', 'accessUrlAdd', 'ACCESS_URL_MANAGE', 'TOOL_BTN', 'plus', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2018-08-29 16:12:05', '2019-11-10 11:12:27', 'primary', null);
INSERT INTO `t_button` VALUES ('3gfdsgsdfgdsfg0', '按钮编号', '编辑', 'dataDicItemEdit', 'DATA_DIC_MANAGE', 'RIGHT_ROW_BTN', 'edit', '', '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:39:31', null, 'success', null);
INSERT INTO `t_button` VALUES ('3sdafsavasdfs5', '按钮编号', '取消待办', 'updateTaskStatus', 'USER_TASK_MANAGE', 'ROW_BTN', 'edit', '', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-07-22 16:46:32', null, 'success', 'data.status===\'1\'');
INSERT INTO `t_button` VALUES ('3vcxzcvzvxzcv4', '按钮编号', '删除', 'deleteFile', 'SYS_LOG_MANAGE', 'ROW_BTN', 'delete', '', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-21 20:46:07', null, 'error', null);
INSERT INTO `t_button` VALUES ('41sgdfgdsfg4454', '按钮编号', '重置密码', 'userPasswordReset', 'USER_MANAGE', 'TOOL_BTN', 'key', '', '6', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-10-17 18:37:50', null, 'success', null);
INSERT INTO `t_button` VALUES ('4543gdfsfg53454', '按钮编号', '编辑', 'sysNoticeEdit', 'SYS_NOTICE_MANAGE', 'ROW_BTN', 'edit', '', '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2016-04-04 15:38:30', null, 'success', null);
INSERT INTO `t_button` VALUES ('46fsdfsadfsadf', '按钮编号', '查看', 'dataDicTypeView', 'DATA_DIC_MANAGE', 'LEFT_ROW_BTN', 'search', '', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-01-24 20:26:03', null, 'info', null);
INSERT INTO `t_button` VALUES ('47gsdgfdsgdsfgdsg', '按钮编号', '查看', 'userNoticeView', 'USER_NOTICE_MANAGE', 'ROW_BTN', 'search', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-05-26 15:09:53', null, 'info', null);
INSERT INTO `t_button` VALUES ('4fsfsadfsddf', '按钮编号', '删除', 'deleteUser', 'USER_MANAGE', 'TOOL_BTN', 'delete', '', '4', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-19 17:51:33', null, 'error', null);
INSERT INTO `t_button` VALUES ('4gfdsgdfs2', '按钮编号', '添加', 'sysNoticeAdd', 'SYS_NOTICE_MANAGE', 'TOOL_BTN', 'plus', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2016-04-03 14:28:27', null, 'primary', null);
INSERT INTO `t_button` VALUES ('4gfdsgsgf0', '按钮编号', '删除', 'deleteAccessUrl', 'ACCESS_URL_MANAGE', 'TOOL_BTN', 'delete', '', '4', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-08-29 16:29:42', null, 'error', null);
INSERT INTO `t_button` VALUES ('4gsggsdgdgdfg3', '按钮编号', '查看', 'sysNoticeView', 'SYS_NOTICE_MANAGE', 'ROW_BTN', 'search', '', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2016-04-04 15:06:04', null, 'info', null);
INSERT INTO `t_button` VALUES ('4htetgdfsg435t53455', '按钮编号', '删除', 'deleteSysNotice', 'SYS_NOTICE_MANAGE', 'TOOL_BTN', 'delete', '', '4', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2016-04-04 15:39:08', null, 'error', null);
INSERT INTO `t_button` VALUES ('5038fc7f3b5846a0a075193079f74dff', '按钮编号', '查看', 'dataPermissionsView', 'DATA_PERMISSIONS_MANAGE', 'ROW_BTN', 'search', '', '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-27 09:20:47', null, 'info', '');
INSERT INTO `t_button` VALUES ('5fsdfsadfsafsdf', '按钮编号', '修改密码', 'userPasswordEdit', 'USER_MANAGE', 'ROW_BTN', 'key', '', '5', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-19 17:52:48', null, 'success', null);
INSERT INTO `t_button` VALUES ('76b6091761d04de39f8bd5436c1806dc', '按钮编号', '删除', 'deleteDataPermissions', 'DATA_PERMISSIONS_MANAGE', 'TOOL_BTN', 'delete', '', '4', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-27 09:22:18', null, 'error', ' ');
INSERT INTO `t_button` VALUES ('9hghghfghfghfghh', '按钮编号', '编辑', 'deptEdit', 'DEPT_MANAGE', 'RIGHT_TOOL_BTN', 'edit', '', '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-20 17:31:18', null, 'success', null);
INSERT INTO `t_button` VALUES ('e546d68fd1a24fddae70212ee1a6a5d5', '按钮编号', '添加', 'dataPermissionsAdd', 'DATA_PERMISSIONS_MANAGE', 'TOOL_BTN', 'plus', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-27 09:17:02', null, 'primary', '');
INSERT INTO `t_button` VALUES ('fsadfsadfasdff6', '按钮编号', '角色分配', 'userRoleDistribute', 'USER_MANAGE', 'ROW_BTN', 'setting', '', '7', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-19 17:56:34', null, 'warning', null);
INSERT INTO `t_button` VALUES ('ggsdgsg7', '按钮编号', '部门分配', 'userDeptDistribute', 'USER_MANAGE', 'ROW_BTN', 'setting', '', '8', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-19 18:01:58', null, 'warning', null);
INSERT INTO `t_button` VALUES ('gsdgsdgdsgsag8', '按钮编号', '添加', 'deptAdd', 'DEPT_MANAGE', 'LEFT_TOOL_BTN', 'plus', '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-20 17:30:07', null, 'primary', null);

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
INSERT INTO `t_data_dic_type` VALUES ('10afdsfsdfsdfsdfdsfsd', 'DIC_CATEGORY_TYPE', '数据字典类别', '1', 'database', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-01-24 20:26:46', null);
INSERT INTO `t_data_dic_type` VALUES ('10fdfdfdfdfdfdfdfaad', 'BUTTON_CLS_TYPE', '按钮样式', '1', 'border', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-11-10 10:26:37', null);
INSERT INTO `t_data_dic_type` VALUES ('11fsdfsdfsdfsdfsdfsdf', 'SYS_FILE_UPLOAD_PATH', '系统文件上传路径', '1', 'upload', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-01-24 20:26:46', null);
INSERT INTO `t_data_dic_type` VALUES ('12fdsfsdfsdfsdfsdfsdf', 'BUSNIESS_MARK_TYPE', '业务标识', '1', 'platform/icon:business-mark', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-01-24 20:26:46', null);
INSERT INTO `t_data_dic_type` VALUES ('13fsdfsdfsdfsdfsdfsdf', 'FORM_STATUS_TYPE', '表单状态类型', '1', 'platform/icon:status', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-01-24 20:26:46', null);
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
INSERT INTO `t_dept` VALUES ('0b0b2c801064448394457e4fc1eb8a0d', '005', '某国际股份有限公司-研发部', '研发部', '3', '', 'e4a59e69b3f943a6906392eb6915d2e7', '1aaaaaaaaaaaaaaaaaaaa', '', '', 'platform/icon:dev', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2020-01-08 16:17:18', null, '5', '6');
INSERT INTO `t_dept` VALUES ('1aaaaaaaaaaaaaaaaaaaafsadf', '001', '某国际股份有限公司', '某国际股份有限公司', '1', '某国际股份有限公司总公司', '', '1aaaaaaaaaaaaaaaaaaaa', '', '中国', 'apartment', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-06-10 17:58:16', null, '1', '10');
INSERT INTO `t_dept` VALUES ('220ba8e14bc545ac8924a42b0a37176a', '003', '某国际股份有限公司-人力部', '人力部', '2', '', '1aaaaaaaaaaaaaaaaaaaafsadf', '1aaaaaaaaaaaaaaaaaaaa', '', '', 'user', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2020-01-08 16:16:03', null, '8', '9');
INSERT INTO `t_dept` VALUES ('864824f014754b6186d2ef6356e9ed3f', '004', '某国际股份有限公司-测试部', '测试部', '3', '', 'e4a59e69b3f943a6906392eb6915d2e7', '1aaaaaaaaaaaaaaaaaaaa', '', '', 'platform/icon:test', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2020-01-08 16:16:37', null, '3', '4');
INSERT INTO `t_dept` VALUES ('e4a59e69b3f943a6906392eb6915d2e7', '002', '某国际股份有限公司-软件部', '软件部', '2', '', '1aaaaaaaaaaaaaaaaaaaafsadf', '1aaaaaaaaaaaaaaaaaaaa', '', '', 'platform/icon:dev', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2020-01-08 16:15:25', null, '2', '7');

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
INSERT INTO `t_login_log` VALUES ('50637a226fb84d03a046057aee097149', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-27 09:24:03');
INSERT INTO `t_login_log` VALUES ('5bd10bc308754c9cbff6ac361e33bdd7', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-26 09:18:06');
INSERT INTO `t_login_log` VALUES ('65a722bf6b9c4fa68a1bc2bafad41b30', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-26 08:58:31');
INSERT INTO `t_login_log` VALUES ('7d16620ff27d4b7b824ba4dfd755d2cf', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2020-01-09 18:49:31');
INSERT INTO `t_login_log` VALUES ('81c599e78e384bcfa042118abe8db719', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-26 08:51:03');
INSERT INTO `t_login_log` VALUES ('8ff718d31fbf4d258cd598a77ff64d4e', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2020-01-09 18:36:01');
INSERT INTO `t_login_log` VALUES ('9e27da80fb38475d8bce06c6846d86e9', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-30 16:58:34');
INSERT INTO `t_login_log` VALUES ('a5f638e42e6c4ebfad62610cdd4b2b49', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-26 09:11:38');
INSERT INTO `t_login_log` VALUES ('ade37334831d4480910af227ff40b971', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-26 09:19:51');
INSERT INTO `t_login_log` VALUES ('b46f9c9ee3b748d79fd67f3e4edb5e18', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-27 09:27:34');
INSERT INTO `t_login_log` VALUES ('c206defc11eb4144bd92e6b36c1f47ce', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-27 09:01:31');
INSERT INTO `t_login_log` VALUES ('c6d7884b2dc54b63930b2a40cb02bc47', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-28 21:44:25');
INSERT INTO `t_login_log` VALUES ('cbfdd6f6d8674b6e9f94d3aaaaf623c2', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-30 16:35:52');
INSERT INTO `t_login_log` VALUES ('d379c6552c964139bb0f613d8a8fa5ae', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-27 09:09:50');
INSERT INTO `t_login_log` VALUES ('d82df13b512c4e0d8d6d26ffa7f64356', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-30 16:36:30');
INSERT INTO `t_login_log` VALUES ('db512d3e22b3461282f20e206d1f253d', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-28 21:46:39');
INSERT INTO `t_login_log` VALUES ('de74e00e077541aab4be1b239d87f390', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-26 08:47:07');
INSERT INTO `t_login_log` VALUES ('e69408d909b94aab8df1c91c82b002df', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-28 21:44:50');
INSERT INTO `t_login_log` VALUES ('fbe7e5b55e1a43b49a08a483f773fd85', '127.0.0.1', '1', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-30 11:02:11');

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
INSERT INTO `t_menu` VALUES ('1gdfdggfdgdfggdfg1', '009', '访问地址管理', '3', 'api', '/app/accessUrl/accessUrlList', '3yrtyerhgfyerty', '6', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2015-08-27 20:01:00', null);
INSERT INTO `t_menu` VALUES ('1gdfgdfgdfgdfg2', '010', '数据字典管理', '3', 'platform/icon:data-dic', '/app/dataDic/dataDicList', '3yrtyerhgfyerty', '8', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2014-09-29 17:18:23', null);
INSERT INTO `t_menu` VALUES ('1gdfgdfgdfgdfgdfg3', '015', '系统公告管理', '3', 'notification', '/app/notice/sysNoticeList', 'ytryrtyrety4', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2015-04-26 09:30:46', null);
INSERT INTO `t_menu` VALUES ('1gdfgdfgdfgdfsg5', '012', '我的公告', '3', 'notification', '/app/workbench/noticeList', 'ytryedhhdtryrty2', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2014-12-21 16:33:05', null);
INSERT INTO `t_menu` VALUES ('1gdfgdfgdfgfdg4', '011', '我的待办', '3', 'tags', '/app/workbench/taskList', 'ytryedhhdtryrty2', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2014-12-21 16:31:41', null);
INSERT INTO `t_menu` VALUES ('1gdfgdsfgdsfgdfg6', '014', '登录日志管理', '3', 'platform/icon:login-log', '/app/log/loginLogList', '5tytryreyyrtyrt', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2015-04-26 09:30:46', null);
INSERT INTO `t_menu` VALUES ('1gdfsgfgfdg7', '013', '系统日志管理', '3', 'platform/icon:sys-log', '/app/log/sysLogList', '5tytryreyyrtyrt', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2015-04-24 21:25:28', null);
INSERT INTO `t_menu` VALUES ('1gfdgsdgsdgfdgsdfg0', '010', '按钮管理', '3', 'border', '/app/button/buttonList', '3yrtyerhgfyerty', '5', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2014-12-21 16:26:20', null);
INSERT INTO `t_menu` VALUES ('2cce82e8b5274b6d8acb65c888415bb2', '019', '数据权限管理', '3', 'platform/icon:data-permissions', '/app/dataPermissions/dataPermissionsList', '3yrtyerhgfyerty', '7', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-26 09:13:30', null);
INSERT INTO `t_menu` VALUES ('3yrtyerhgfyerty', '003', '系统管理', '2', 'platform/icon:sys-manage', null, 'dfhgfhhgf3gdgdsgfh1', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2014-09-28 19:42:29', null);
INSERT INTO `t_menu` VALUES ('5tytryreyyrtyrt', '005', '日志管理', '2', 'platform/icon:log-manage', '', 'dfhgfhhgf3gdgdsgfh1', '4', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2018-01-24 20:25:12', null);
INSERT INTO `t_menu` VALUES ('6ytryetryeeytrt', '006', '用户管理', '3', 'user', '/app/user/userList', '3yrtyerhgfyerty', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2014-09-28 19:48:52', null);
INSERT INTO `t_menu` VALUES ('7ereeet', '007', '部门管理', '3', 'apartment', '/app/dept/deptList', '3yrtyerhgfyerty', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2014-11-21 21:28:03', null);
INSERT INTO `t_menu` VALUES ('82273d9bb3144250a6997a1bfbd90bfe', '016', '系统信息', '3', 'dashboard', '/app/sysInfo', 'ytryrtyrety4', '2', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-12 05:57:28', null);
INSERT INTO `t_menu` VALUES ('8ytryrtytry', '008', '角色管理', '3', 'team', '/app/role/roleList', '3yrtyerhgfyerty', '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2014-10-16 14:50:03', null);
INSERT INTO `t_menu` VALUES ('9stryytrytrytr', '011', '菜单管理', '3', 'menu', '/app/menu/menuList', '3yrtyerhgfyerty', '4', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2014-11-21 21:28:03', null);
INSERT INTO `t_menu` VALUES ('a2cf1e8ab74449c29861404214421841', '018', '学生管理', '3', 'user', 'app/student/studentList', 'e041386708254e31beae8df1c92a4a8c', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-24 07:28:37', null);
INSERT INTO `t_menu` VALUES ('dfhgfhhgf3gdgdsgfh1', '001', '系统菜单', '1', 'menu', null, '', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2014-09-28 19:41:19', null);
INSERT INTO `t_menu` VALUES ('e041386708254e31beae8df1c92a4a8c', '017', '演示', '2', 'platform/icon:demo', '', 'dfhgfhhgf3gdgdsgfh1', '5', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-12-24 07:26:52', null);
INSERT INTO `t_menu` VALUES ('ytryedhhdtryrty2', '002', '个人工作台', '2', 'platform/icon:workbench', '', 'dfhgfhhgf3gdgdsgfh1', '1', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2014-09-29 17:17:20', null);
INSERT INTO `t_menu` VALUES ('ytryrtyrety4', '004', '系统功能', '2', 'appstore', '', 'dfhgfhhgf3gdgdsgfh1', '3', '1', '1aaaaaaaaaaaaaaaaaaaa', null, '2014-12-21 16:28:19', null);

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
INSERT INTO `t_notice` VALUES ('1', 'CC2.0 正在不断开发与完善中，即将闪联登场，期待!', 'CC2.0 正在不断开发与完善中，即将闪联登场，期待!', '1', '1', '2020-02-29', '1aaaaaaaaaaaaaaaaaaaa', null, '2019-09-26 19:58:44', null);
INSERT INTO `t_notice` VALUES ('1f46c8f22f1b467ca76bd8247ff33724', '根据目标将项目工作分解成具体工作项根据目标将根据目标将项目工作分解成具体工作项根据目标将', '第一步：根据目标将项目工作分解成具体工作项\n\n以网易严选为例，整合营销项目既要承担拉高GMV的职责，也要考虑市场做大声量。\n\n我们将一个项目拆分成6个子项目：\n\n意见征集：征集项目管理、活动策略玩法的建议；\n营销策略产出：根据竞品调研，产出核心玩法，促销节奏，核心沟通策略；\nKPI制定BD，渠道等各资源方的推广计划，避免资源错配的情况。\n第二步：根据各个子项目的目标，再将各个子项目拆解成各个工作包\n\n如“KPI的制定与拆解”子项目，目的是帮助严选各业务线规划目标，帮助具体执行人员根据当前目标做足准备。\n\n根据财务指标，拆分完整体业绩KPI以后，我们从用户线、流量线、商品线三个角度分别看应该如何完成总的目标。\n\n\n第三步：明确各个工作包的工作内容以及责任人和用时\n\n以“KPI的制定与拆解”子项目中“整体财务KPI”这个工作包为例，整体KPI不仅影响促销的玩法策略制定，会对“供应链订货”“仓配资源”上下游的业务线的策略制定产生影响。\n\n我们一般用5个工作日，协同数据分析师根据历史数据及财务指标推测出整体目标，并将目标拆分至：销售额KPI、毛利额KPI、毛利率KPI等财务指标，再将这些财务指标细分到各个销售渠道。\n\n\n\n我们将一个项目拆分成6个子项目：\n\n意见征集：征集项目管理、活动策略玩法的建议；\n营销策略产出：根据竞品调研，产出核心玩法，促销节奏，核心沟通策略；\nKPI制定与拆解：根据财务目标，拆分出销售毛利目标，并分拆至销售，用户，流量，商品四个角度；\n具体方案制定与落地：根据核心策略策划各业务线落地方案；\n商品盘货统筹：通过策划案中各线玩法制定盘点策略，盘点出不同商品在不同节点做什么价格，做什么推荐；\n促销推广落地：根据整体沟通策略和货品盘点结果，梳理品牌，BD，渠道等各资源方的推广计划，避免资源错配的情况。\n第二步：根据各个子项目的目标，再将各个子项目拆解成各个工作包\n\n如“KPI的制定与拆解”子项目，目的是帮助严选各业务线规划目标，帮助具体执行人员根据当前目标做足准备。\n\n\n', '1', '2', '2019-12-26', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2019-12-04 20:28:22', '2019-12-04 20:43:47');

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
INSERT INTO `t_role_access_url_mapping` VALUES ('029d9866bc724eb19c6da63edacf9be7', '1fsdfsdafasfqfs234fgdgsfsdf', '1gdfgdfgdfgdfgdfg4');
INSERT INTO `t_role_access_url_mapping` VALUES ('05d7bca753244beea2fc4d47c494b9e1', '1fsdfsdafasfqfs234fgdgsfsdf', '1gdfgdfgdfgdfgdfgdfgdsfgdfg7');
INSERT INTO `t_role_access_url_mapping` VALUES ('0cac98dd770746c492b3ac7240c1851c', '1fsdfsdafasfqfs234fgdgsfsdf', '1esgrgregergergerg3');
INSERT INTO `t_role_access_url_mapping` VALUES ('0f24e397911c475089a1bad2e3339ef5', '1fsdfsdafasfqfs234fgdgsfsdf', '2gsdfgdsfgdsf1');
INSERT INTO `t_role_access_url_mapping` VALUES ('14f39917e6b5453e85cc9aa7d2aceae1', '1fsdfsdafasfqfs234fgdgsfsdf', '5gfdssdg5sggwg0');
INSERT INTO `t_role_access_url_mapping` VALUES ('17768709548c418aa0c7d84b9b106f79', '1fsdfsdafasfqfs234fgdgsfsdf', '62gfdgdfgdfgdf');
INSERT INTO `t_role_access_url_mapping` VALUES ('185107bbc6e94d7eb1aedc198b5d1d7d', '1fsdfsdafasfqfs234fgdgsfsdf', '9gfdgdfgdfgdfgdfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('19de0133d0cf489bb0b3d4a0473e13f6', '1fsdfsdafasfqfs234fgdgsfsdf', '3gdfgdfgdfg1');
INSERT INTO `t_role_access_url_mapping` VALUES ('21817c9484cc478db80a21b8b145e253', '1fsdfsdafasfqfs234fgdgsfsdf', 'ae162d184bbd494882c29db315586ef9');
INSERT INTO `t_role_access_url_mapping` VALUES ('21c8a7ad87464be1a7a1f2a114dfa230', '1fsdfsdafasfqfs234fgdgsfsdf', '36867u7b5b54b56b754b7b');
INSERT INTO `t_role_access_url_mapping` VALUES ('266b3a1fca2c491080b98532b7ee24af', '1fsdfsdafasfqfs234fgdgsfsdf', '5fsdfsdfsadfsdf2');
INSERT INTO `t_role_access_url_mapping` VALUES ('2805cdf6a5b74eb68a3d711c9f9687e5', '1fsdfsdafasfqfs234fgdgsfsdf', '6gdgdfgdffgdgdfgdfg5');
INSERT INTO `t_role_access_url_mapping` VALUES ('28ffdc3158f44bd2aaef92025971c2b1', '1fsdfsdafasfqfs234fgdgsfsdf', '7fdsaffsfsaf4');
INSERT INTO `t_role_access_url_mapping` VALUES ('29b4f9a87b5f44b0a5eb832e7de867b5', '1fsdfsdafasfqfs234fgdgsfsdf', '4sdfbgfdfssssgsd1');
INSERT INTO `t_role_access_url_mapping` VALUES ('2e6f1b00fd834c5caae21d4387f6b769', '1fsdfsdafasfqfs234fgdgsfsdf', 'a4fe8b2c9ce543719596e7f92ef0e12b');
INSERT INTO `t_role_access_url_mapping` VALUES ('2f54897b69564ebf99e41567c1e7c8d2', '1fsdfsdafasfqfs234fgdgsfsdf', '4gdfgdgdfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('3612ffd6d9f24d6aa2b4c559290dc720', '1fsdfsdafasfqfs234fgdgsfsdf', '48gdfgdfgdfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('3808c07e70bc4cf587bda3c149adcf3c', '1fsdfsdafasfqfs234fgdgsfsdf', '3gdfgdfgdfgdfgdfg3');
INSERT INTO `t_role_access_url_mapping` VALUES ('392d9b6e185e44b2bb9cad34c9973717', '1fsdfsdafasfqfs234fgdgsfsdf', '5fsdafsdghfjfh1');
INSERT INTO `t_role_access_url_mapping` VALUES ('3a1ddbbcfcf846e987602fb36ca28fac', '1fsdfsdafasfqfs234fgdgsfsdf', '84fsdfsdfsdafsadf');
INSERT INTO `t_role_access_url_mapping` VALUES ('3cbc34cfbda7476ba59c37126b8fb1ea', '1fsdfsdafasfqfs234fgdgsfsdf', '6gdgfdgdfgfgdf4');
INSERT INTO `t_role_access_url_mapping` VALUES ('41037caff52f430ab25503b28c577c5e', '1fsdfsdafasfqfs234fgdgsfsdf', '1gdfsgsdfgdfsgdsgssdsdghj3gre1');
INSERT INTO `t_role_access_url_mapping` VALUES ('417f6e9cdae346e1bcae1dbbdf5a8a90', '1fsdfsdafasfqfs234fgdgsfsdf', 'jhg59hgjghjghjghjgfhjgh');
INSERT INTO `t_role_access_url_mapping` VALUES ('449421ffa674416785d93d11cca1dd6c', '1fsdfsdafasfqfs234fgdgsfsdf', '88380e50bd004745a3ccfb85e72aabf3');
INSERT INTO `t_role_access_url_mapping` VALUES ('4857cc638da54e83b346989c5a44fad7', '1fsdfsdafasfqfs234fgdgsfsdf', '2gfdgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('4c3370cbbf0549acb4864ba9d9b9c981', '1fsdfsdafasfqfs234fgdgsfsdf', '1gfdgdfgdsgdsgdfgsdg5');
INSERT INTO `t_role_access_url_mapping` VALUES ('4f3fd2788ac742b2934f7ada75ae70ff', '1fsdfsdafasfqfs234fgdgsfsdf', '7gsdgsadgsagsag5');
INSERT INTO `t_role_access_url_mapping` VALUES ('50e346207e364ddb9503c8747135cd9d', '1fsdfsdafasfqfs234fgdgsfsdf', '6gdsfgdfgdsfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('5262aec2ada2462c848eae72a0885e44', '1fsdfsdafasfqfs234fgdgsfsdf', '43gdfsgdfgdfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('551d3a2734564a4db1044aeaa7ea8aae', '1fsdfsdafasfqfs234fgdgsfsdf', '1gdsfgsdfgdfgdsfgdsfgdfsg9');
INSERT INTO `t_role_access_url_mapping` VALUES ('55acec1bec104150ab0a53e1f8cae23c', '1fsdfsdafasfqfs234fgdgsfsdf', '3gdfgdfgdgdgdfg5');
INSERT INTO `t_role_access_url_mapping` VALUES ('57140ddc24924439bedf0731d4e08339', '1fsdfsdafasfqfs234fgdgsfsdf', 'fafdsfsdfsadfa1sdfas11');
INSERT INTO `t_role_access_url_mapping` VALUES ('57153b5911ec41caa135fb14d4321af4', '1fsdfsdafasfqfs234fgdgsfsdf', '7gdfgdfgdfgdfg0');
INSERT INTO `t_role_access_url_mapping` VALUES ('582ded47e1ef49e1b3849480bbba66e1', '1fsdfsdafasfqfs234fgdgsfsdf', '47gdfgdfgdfgdfgdfgdfgdf');
INSERT INTO `t_role_access_url_mapping` VALUES ('5ee709d5ab3947c4aa4df24ed5e52c47', '1fsdfsdafasfqfs234fgdgsfsdf', '83afdsadafsdfdssf');
INSERT INTO `t_role_access_url_mapping` VALUES ('5efaab804eef432987ff6dc0ccfc3dfc', '1fsdfsdafasfqfs234fgdgsfsdf', '44gfdsgdfgdfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('5efe46c1b95a40c2b953179f2c277ff8', '1fsdfsdafasfqfs234fgdgsfsdf', '8fsfsadfsdf1');
INSERT INTO `t_role_access_url_mapping` VALUES ('623b0d503b2d48c8ba68239d8ffeb20b', '1fsdfsdafasfqfs234fgdgsfsdf', '79fsdfsdfsdgsgsag');
INSERT INTO `t_role_access_url_mapping` VALUES ('717da67305a248278a2b45f4b55c0f6a', '1fsdfsdafasfqfs234fgdgsfsdf', '49gdfsgdsfgdsfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('72569d0fcbde4e468e72511ba5daf2fc', '1fsdfsdafasfqfs234fgdgsfsdf', '7gsagsagsdagsag3');
INSERT INTO `t_role_access_url_mapping` VALUES ('72bcf335ed044ca7a914de910c50d76c', '1fsdfsdafasfqfs234fgdgsfsdf', '3f696ebe5d8d4181afde428239284412');
INSERT INTO `t_role_access_url_mapping` VALUES ('74996957df70461f85a0b86a49a77e7f', '1fsdfsdafasfqfs234fgdgsfsdf', 'd15a0d4d5aef496d8d3a912ecb7980b6');
INSERT INTO `t_role_access_url_mapping` VALUES ('755d3ccfad8643b09d4e8b9ee559aa21', '1fsdfsdafasfqfs234fgdgsfsdf', '4sggdfgdsfdfsgdfg2');
INSERT INTO `t_role_access_url_mapping` VALUES ('759f0469f8b446e7a26a59ff9eb0ef2b', '1fsdfsdafasfqfs234fgdgsfsdf', '7sdgsdgsdagasggh1');
INSERT INTO `t_role_access_url_mapping` VALUES ('75c7f3a3c3434f77854c56645c379ccb', '1fsdfsdafasfqfs234fgdgsfsdf', '5fsaffsdfsdfsdf3');
INSERT INTO `t_role_access_url_mapping` VALUES ('7624f4366e044d598cabef4967039dfc', '1fsdfsdafasfqfs234fgdgsfsdf', '67gdfgdsfgsdgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('78599bef83444905b01510504eec3c5f', '1fsdfsdafasfqfs234fgdgsfsdf', '375b54b75b75b8');
INSERT INTO `t_role_access_url_mapping` VALUES ('8084104c6a984472b86e084fb97cfbd0', '1fsdfsdafasfqfs234fgdgsfsdf', '25gdfgdfsgdfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('83f2a87029f74e07a3a9eb959f4a6e6e', '1fsdfsdafasfqfs234fgdgsfsdf', 'gdfggfdsgsdfgdfgd5');
INSERT INTO `t_role_access_url_mapping` VALUES ('85d05bf4136648e693b421e0ab9e771b', '1fsdfsdafasfqfs234fgdgsfsdf', '2gdfgdfgdfggdsfgsdg4');
INSERT INTO `t_role_access_url_mapping` VALUES ('8d5e8c9bda0d48e198be97d7b0a242e4', '1fsdfsdafasfqfs234fgdgsfsdf', '37b754b754b75b75b546b');
INSERT INTO `t_role_access_url_mapping` VALUES ('8fd7585ecf8b451984c788b077532768', '1fsdfsdafasfqfs234fgdgsfsdf', '85fdsfsdfsdfsdfsdf');
INSERT INTO `t_role_access_url_mapping` VALUES ('91cd7d308513405fa39fd2871062dde2', '1fsdfsdafasfqfs234fgdgsfsdf', '5fsdfsfsdfdfsdf6');
INSERT INTO `t_role_access_url_mapping` VALUES ('944a564906e448e5bdcebeab805dc872', '1fsdfsdafasfqfs234fgdgsfsdf', '68sgfdgdfgdfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('967815dbeaab41c48fd69b7cb788206c', '1fsdfsdafasfqfs234fgdgsfsdf', '1gdfgdfgdfgdfgdfgdfgdfg6');
INSERT INTO `t_role_access_url_mapping` VALUES ('9a4897421370450d8bc371f23ee7892c', '1fsdfsdafasfqfs234fgdgsfsdf', '45gfdsgdfgdfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('9b6a15ba804a4214a29637f3c98c7d1e', '1fsdfsdafasfqfs234fgdgsfsdf', '2gdfgdsfgdsfgdf9');
INSERT INTO `t_role_access_url_mapping` VALUES ('9da27b8e95894404a1a8f7256c0f83d4', '1fsdfsdafasfqfs234fgdgsfsdf', '3gdfgdfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('a232b800f5874915b543f880c6a0d0f3', '1fsdfsdafasfqfs234fgdgsfsdf', '82asffsdfasdfsfsdaf');
INSERT INTO `t_role_access_url_mapping` VALUES ('a24d7a6f8a17487380fea3b8716ef910', '1fsdfsdafasfqfs234fgdgsfsdf', '3gdfsfgsdgdgdsfgdfg2');
INSERT INTO `t_role_access_url_mapping` VALUES ('a4d18f0633b34d3c9ea31ba5ebd6666c', '1fsdfsdafasfqfs234fgdgsfsdf', '22gdfsgdfgdfgdfgdf');
INSERT INTO `t_role_access_url_mapping` VALUES ('a6fbec207dfd406e89ed9d2dc816cd2d', '1fsdfsdafasfqfs234fgdgsfsdf', '7gsadgasdgasdgas2');
INSERT INTO `t_role_access_url_mapping` VALUES ('a855f369ee044824a3d6ee93a1b9dc3e', '1fsdfsdafasfqfs234fgdgsfsdf', '61gdfsgsdfghgjhgjghjjhg');
INSERT INTO `t_role_access_url_mapping` VALUES ('aa98e8d19d454e50b6bc6f3cd38e82ad', '1fsdfsdafasfqfs234fgdgsfsdf', '46gdfgdfgdfgdfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('ac05f8b1e45f495ca955b6c3ae88db70', '1fsdfsdafasfqfs234fgdgsfsdf', '23gdfgdfsgsdg');
INSERT INTO `t_role_access_url_mapping` VALUES ('b0daaedc4d1f4b0ea431cc6ace4b73dd', '1fsdfsdafasfqfs234fgdgsfsdf', '20gdfgdfgdfgdfgdffg');
INSERT INTO `t_role_access_url_mapping` VALUES ('b1d94a41b4cd49e99d8e98e34126c5de', '1fsdfsdafasfqfs234fgdgsfsdf', '3gdfggdfgdfgdfgd0');
INSERT INTO `t_role_access_url_mapping` VALUES ('b21823781f8d4a318caab3445e5bb3c6', '1fsdfsdafasfqfs234fgdgsfsdf', '96c4075e36c643e0af43028005fe0569');
INSERT INTO `t_role_access_url_mapping` VALUES ('b9d7737ac8a24199b5aac93dfcaad19e', '1fsdfsdafasfqfs234fgdgsfsdf', '5fdsfsfsfdfsdaf5');
INSERT INTO `t_role_access_url_mapping` VALUES ('bc1912343af54a9684d7a3dfec82843d', '1fsdfsdafasfqfs234fgdgsfsdf', '5fsdfsdfsdfsdfsd7');
INSERT INTO `t_role_access_url_mapping` VALUES ('bfe1172c8d8b43ac8641794da4dbece2', '1fsdfsdafasfqfs234fgdgsfsdf', '2gdfdfgdfgdfgd8');
INSERT INTO `t_role_access_url_mapping` VALUES ('c55d8704d2834cdcbd617220cca0d48a', '1fsdfsdafasfqfs234fgdgsfsdf', '80ffdsfsdafasfdsf');
INSERT INTO `t_role_access_url_mapping` VALUES ('c5ffa147f517415db52eb63666959e01', '1fsdfsdafasfqfs234fgdgsfsdf', '1dffgsdfgsdgdfgdfgdfgdfg0');
INSERT INTO `t_role_access_url_mapping` VALUES ('c6fc3ed9f71a4a4ab5abd076bf8a840c', '1fsdfsdafasfqfs234fgdgsfsdf', '375b5bbtbytrb9');
INSERT INTO `t_role_access_url_mapping` VALUES ('c7cd98b51aff4187bf2203a4fb197fbd', '1fsdfsdafasfqfs234fgdgsfsdf', '5fsdafsafsdaf4');
INSERT INTO `t_role_access_url_mapping` VALUES ('c84e27b5208141a799847427e167a914', '1fsdfsdafasfqfs234fgdgsfsdf', '7asgsadgsagsadg8');
INSERT INTO `t_role_access_url_mapping` VALUES ('c90aa4fa1a21485ab5333d9a0b4e5081', '1fsdfsdafasfqfs234fgdgsfsdf', '3bc110a666b6490d820819f3a2329d3f');
INSERT INTO `t_role_access_url_mapping` VALUES ('d05f383bbc074c8791e242fdf4d1f3ed', '1fsdfsdafasfqfs234fgdgsfsdf', '6jhgjgjghjhgjghjgh0');
INSERT INTO `t_role_access_url_mapping` VALUES ('d5985c10457c459ebff7d1c23b2deb9c', '1fsdfsdafasfqfs234fgdgsfsdf', '26gdfgdfgdfgdgdgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('d5ac4469044a46d8a8c21dada7e5de40', '1fsdfsdafasfqfs234fgdgsfsdf', '12ggdfgdfrewrgwrg');
INSERT INTO `t_role_access_url_mapping` VALUES ('d665955e710146e9b07519200e2e5148', '1fsdfsdafasfqfs234fgdgsfsdf', '3sdgsdgdgdfgdg4');
INSERT INTO `t_role_access_url_mapping` VALUES ('d6d3ff3e2f924efe9eb9d7f5ff783521', '1fsdfsdafasfqfs234fgdgsfsdf', '5fjfhdgsdfasdfsfsdf8');
INSERT INTO `t_role_access_url_mapping` VALUES ('d7aa7e98b9644f87b95a5685f43bfb96', '1fsdfsdafasfqfs234fgdgsfsdf', '7gdfgsdfgdfgdfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('d80e5c0e53c94b448139657b1ea307c4', '1fsdfsdafasfqfs234fgdgsfsdf', '1gdfgdfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('d826293e3a584346a2cc52ebc4783d21', '1fsdfsdafasfqfs234fgdgsfsdf', '66gfdgdfgfdgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('dea7f2f5519240d387017fdd9aa401ff', '1fsdfsdafasfqfs234fgdgsfsdf', 'gdfgdfsgdsgdfsgdfg18');
INSERT INTO `t_role_access_url_mapping` VALUES ('e1e3d815e7ea4ff18d0f929e6c37b3ba', '1fsdfsdafasfqfs234fgdgsfsdf', '8gdfgdfgdfgdfgdgdfgdfgdfg');
INSERT INTO `t_role_access_url_mapping` VALUES ('e6b244a3bac042f6a543e27a153b6d62', '1fsdfsdafasfqfs234fgdgsfsdf', '6gdfgfdgdfggdgdfg3');
INSERT INTO `t_role_access_url_mapping` VALUES ('f0e8d05a92ed4aab88aa33537fd5dd0d', '1fsdfsdafasfqfs234fgdgsfsdf', '7asgasdgsag6');
INSERT INTO `t_role_access_url_mapping` VALUES ('f34b3c91bd47418c9601ed31e40c7900', '1fsdfsdafasfqfs234fgdgsfsdf', 'gdfgdfgdfgdfg27');
INSERT INTO `t_role_access_url_mapping` VALUES ('f36ee37a8cee46248127201fe79d7a54', '1fsdfsdafasfqfs234fgdgsfsdf', '6gdfgdfsgdsg9');
INSERT INTO `t_role_access_url_mapping` VALUES ('f60a116998ad47359aa9729df61d9269', '1fsdfsdafasfqfs234fgdgsfsdf', '4ybtybytryebtrb0');
INSERT INTO `t_role_access_url_mapping` VALUES ('fb43f0d9f93c4f9d8671ce0ca583900c', '1fsdfsdafasfqfs234fgdgsfsdf', '7agsdagsagsadggsg7');

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
INSERT INTO `t_role_button_mapping` VALUES ('003af848ee874858a51959d93f542e16', '1fsdfsdafasfqfs234fgdgsfsdf', '16gdfsgdfgdsg');
INSERT INTO `t_role_button_mapping` VALUES ('0b83053b79564ed495bafa5703550e8c', '1fsdfsdafasfqfs234fgdgsfsdf', '38gfdsgsdfg');
INSERT INTO `t_role_button_mapping` VALUES ('0d85312b8e1f4a27b7c4a3439b08a33c', '1fsdfsdafasfqfs234fgdgsfsdf', '46fsdfsadfsadf');
INSERT INTO `t_role_button_mapping` VALUES ('1386826932d34aa3bbb11fdc724049f4', '1fsdfsdafasfqfs234fgdgsfsdf', '3fsdafasfsafb6');
INSERT INTO `t_role_button_mapping` VALUES ('1ea84a7636c64b17b3c69d6a7fef0417', '1fsdfsdafasfqfs234fgdgsfsdf', '29hfdfghfghdfhdfh');
INSERT INTO `t_role_button_mapping` VALUES ('237cbea1d37c464e8786b0ef827e5e0e', '1fsdfsdafasfqfs234fgdgsfsdf', '1hgfhfghfg0');
INSERT INTO `t_role_button_mapping` VALUES ('2a3e69095f0b4c978a34bff2b0bc0a95', '1fsdfsdafasfqfs234fgdgsfsdf', '2gdfsbdsjhghfdsgdgsd0');
INSERT INTO `t_role_button_mapping` VALUES ('2a8567cc4aac4efcba3bc323b21f0a0c', '1fsdfsdafasfqfs234fgdgsfsdf', '3sdafsavasdfs5');
INSERT INTO `t_role_button_mapping` VALUES ('2ecd40c22a3a47989edc079fd7a43921', '1fsdfsdafasfqfs234fgdgsfsdf', '3gfdsgsdfgdsfg0');
INSERT INTO `t_role_button_mapping` VALUES ('389e290678af4e56b9de516ee0ddb4a1', '1fsdfsdafasfqfs234fgdgsfsdf', '47gsdgfdsgdsfgdsg');
INSERT INTO `t_role_button_mapping` VALUES ('39456d939a71418bb304bd54a905caaf', '1fsdfsdafasfqfs234fgdgsfsdf', '3fsfsdfsdffsafsad');
INSERT INTO `t_role_button_mapping` VALUES ('3c071d181c2247c3a59d05e651527165', '1fsdfsdafasfqfs234fgdgsfsdf', '33afasfsafas');
INSERT INTO `t_role_button_mapping` VALUES ('3da785a2dcdf4bf2821da78a49b098fd', '1fsdfsdafasfqfs234fgdgsfsdf', '12hfghdfhfdhfgh');
INSERT INTO `t_role_button_mapping` VALUES ('3dc97fd5176548be9cd0450f1bf39bd3', '1fsdfsdafasfqfs234fgdgsfsdf', '41sgdfgdsfg4454');
INSERT INTO `t_role_button_mapping` VALUES ('3dec82966bd14d26a86168d428464007', '1fsdfsdafasfqfs234fgdgsfsdf', '1bfbvcbcvxbvcxbbbxcb7');
INSERT INTO `t_role_button_mapping` VALUES ('4bf8574d5a664a1382804c30fa7a94ff', '1fsdfsdafasfqfs234fgdgsfsdf', '9hghghfghfghfghh');
INSERT INTO `t_role_button_mapping` VALUES ('4c62f80ec57343c19df91488778e5d6e', '1fsdfsdafasfqfs234fgdgsfsdf', '1safsafsafbfdgsg4');
INSERT INTO `t_role_button_mapping` VALUES ('51d86747febb406dbcca5eb8b001ef90', '1fsdfsdafasfqfs234fgdgsfsdf', '1hdfghfghfghfgh1');
INSERT INTO `t_role_button_mapping` VALUES ('5ceed7156b544c16adcb3ae2c7312fc3', '1fsdfsdafasfqfs234fgdgsfsdf', '26fdsgdfsgdsfggdsf');
INSERT INTO `t_role_button_mapping` VALUES ('5f2a658fee0c42bfa5f0c0a04a109f42', '1fsdfsdafasfqfs234fgdgsfsdf', '2tryeyruiibbxvcb5');
INSERT INTO `t_role_button_mapping` VALUES ('60ca822f2c2c442c9dfc2e7bf96c5cd4', '1fsdfsdafasfqfs234fgdgsfsdf', '1tretrgsdgdfsgsdgsdg8');
INSERT INTO `t_role_button_mapping` VALUES ('640a5c41500044bd8840fbe8350ae329', '1fsdfsdafasfqfs234fgdgsfsdf', '2dsfgdgdfgdfgdgdsg2');
INSERT INTO `t_role_button_mapping` VALUES ('652e122d7fc148989da652ee28b66088', '1fsdfsdafasfqfs234fgdgsfsdf', 'gsdgsdgdsgsag8');
INSERT INTO `t_role_button_mapping` VALUES ('65db163a1413474ba0943b1263d81293', '1fsdfsdafasfqfs234fgdgsfsdf', '76b6091761d04de39f8bd5436c1806dc');
INSERT INTO `t_role_button_mapping` VALUES ('67f1924b0f224b5c863db7192e6864b3', '1fsdfsdafasfqfs234fgdgsfsdf', '5fsdfsadfsafsdf');
INSERT INTO `t_role_button_mapping` VALUES ('6d564262a14c4041aab35baa6a52ae92', '1fsdfsdafasfqfs234fgdgsfsdf', '4fsfsadfsddf');
INSERT INTO `t_role_button_mapping` VALUES ('6d6376dd5d524a0bacf7e465e3416cbb', '1fsdfsdafasfqfs234fgdgsfsdf', 'e546d68fd1a24fddae70212ee1a6a5d5');
INSERT INTO `t_role_button_mapping` VALUES ('77b296bb49f74325a8bd41165162edca', '1fsdfsdafasfqfs234fgdgsfsdf', '4gfdsgsgf0');
INSERT INTO `t_role_button_mapping` VALUES ('78dd516a4cef434f899e74fc4e7c6b65', '1fsdfsdafasfqfs234fgdgsfsdf', '2hjhgjkklhjghgjfhfdh3');
INSERT INTO `t_role_button_mapping` VALUES ('7ee489af2caf43958390747e8884aaa0', '1fsdfsdafasfqfs234fgdgsfsdf', '2vzxvxzvzxv8');
INSERT INTO `t_role_button_mapping` VALUES ('8b66ae06aa30480c875963444fa7c843', '1fsdfsdafasfqfs234fgdgsfsdf', '1fsgdfsgddf5');
INSERT INTO `t_role_button_mapping` VALUES ('99f77880c332430c933f9601d3852bff', '1fsdfsdafasfqfs234fgdgsfsdf', '3fsafsdfsaf2');
INSERT INTO `t_role_button_mapping` VALUES ('9b0a37fe197c4f6e9bfd1d41ceb09420', '1fsdfsdafasfqfs234fgdgsfsdf', '4543gdfsfg53454');
INSERT INTO `t_role_button_mapping` VALUES ('a466ba76e56e4d2a8fb9e19aa7004aeb', '1fsdfsdafasfqfs234fgdgsfsdf', '3vcxzcvzvxzcv4');
INSERT INTO `t_role_button_mapping` VALUES ('a921fc226b774e66915cd209a963b491', '1fsdfsdafasfqfs234fgdgsfsdf', '3dgfdsgvvzcvfg9');
INSERT INTO `t_role_button_mapping` VALUES ('b21c84575e4a4d8e94cd018a7c28a254', '1fsdfsdafasfqfs234fgdgsfsdf', 'fsadfsadfasdff6');
INSERT INTO `t_role_button_mapping` VALUES ('bafe4d3e7e3f4c449a6123ea0c1467ac', '1fsdfsdafasfqfs234fgdgsfsdf', 'ggsdgsg7');
INSERT INTO `t_role_button_mapping` VALUES ('bc9cc6bb092c4e65bf207d400a07c912', '1fsdfsdafasfqfs234fgdgsfsdf', '5038fc7f3b5846a0a075193079f74dff');
INSERT INTO `t_role_button_mapping` VALUES ('c01046ea4db140059c7846dfb9e52a37', '1fsdfsdafasfqfs234fgdgsfsdf', '2fsdfsdfsdfsdf');
INSERT INTO `t_role_button_mapping` VALUES ('c3aff66826bb4f33a13cc22eede179ce', '1fsdfsdafasfqfs234fgdgsfsdf', '2aadsafsdfsdf1');
INSERT INTO `t_role_button_mapping` VALUES ('c7cef892904442d7923caa8966589e53', '1fsdfsdafasfqfs234fgdgsfsdf', '4gsggsdgdgdfg3');
INSERT INTO `t_role_button_mapping` VALUES ('d6463cc9a1e64216a29be313bae86522', '1fsdfsdafasfqfs234fgdgsfsdf', '4gfdsgdfs2');
INSERT INTO `t_role_button_mapping` VALUES ('da707ebef9f24dc483af04a30924a21e', '1fsdfsdafasfqfs234fgdgsfsdf', '3fsadfasfas1');
INSERT INTO `t_role_button_mapping` VALUES ('e1a4a3c08d944d11b120b8321ef8df10', '1fsdfsdafasfqfs234fgdgsfsdf', '3gdsfgvbcgdhdgh7');
INSERT INTO `t_role_button_mapping` VALUES ('e27f65c1bc8f43429aab49e5b81cc7aa', '1fsdfsdafasfqfs234fgdgsfsdf', '4htetgdfsg435t53455');
INSERT INTO `t_role_button_mapping` VALUES ('e4ed8617c2744f76beceba3dd8f4f418', '1fsdfsdafasfqfs234fgdgsfsdf', '13hdfhdfghfds');
INSERT INTO `t_role_button_mapping` VALUES ('e7fd2c36d005458d8c06aa533ce05bd8', '1fsdfsdafasfqfs234fgdgsfsdf', '2xvxcvzvzxvdsgxv7');
INSERT INTO `t_role_button_mapping` VALUES ('ead0e82b9b64420492992130a0605a84', '1fsdfsdafasfqfs234fgdgsfsdf', '1fsdfsdfsdfsadf');
INSERT INTO `t_role_button_mapping` VALUES ('eb87e60bf3524b17b14e79ac0c564b7c', '1fsdfsdafasfqfs234fgdgsfsdf', '1yy54yhtrbbbb9');
INSERT INTO `t_role_button_mapping` VALUES ('f0deafea49b94bb0b57a002a095b0e91', '1fsdfsdafasfqfs234fgdgsfsdf', '2dfsfgdgfdsuiutiuyi4');
INSERT INTO `t_role_button_mapping` VALUES ('f2eaba36d6194615b3d3631be9b39652', '1fsdfsdafasfqfs234fgdgsfsdf', '3d224750b9854b56b3f072e09129ea1b');
INSERT INTO `t_role_button_mapping` VALUES ('f86c657c23a2417dbbe326908a43a786', '1fsdfsdafasfqfs234fgdgsfsdf', '24c0d24080ee406c9d88fe93ecb78b81');

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
INSERT INTO `t_role_data_permit_mapping` VALUES ('767197840e9b4de58643d2be31585b35', 'asdfghj1234566fsfasdfaddsf', '1fsdfsdafasfqfs234fgdgsfsdf');
INSERT INTO `t_role_data_permit_mapping` VALUES ('a3918c05362f43208ef70342318be6b4', 'asdfghj1234566fsfasdfadsf', '1fsdfsdafasfqfs234fgdgsfsdf');
INSERT INTO `t_role_data_permit_mapping` VALUES ('b77877ef68574e1181deafafc7131a7d', 'asdfff1234566fsfasdfaddsaaf', '1fsdfsdafasfqfs234fgdgsfsdf');

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
INSERT INTO `t_role_menu_mapping` VALUES ('0df3fd647e8b4ab5ab29d729318f15a5', '1fsdfsdafasfqfs234fgdgsfsdf', '8ytryrtytry');
INSERT INTO `t_role_menu_mapping` VALUES ('33c36f6fe7324bd9b2c9bdea03cb006b', '1fsdfsdafasfqfs234fgdgsfsdf', '1gdfgdsfgdsfgdfg6');
INSERT INTO `t_role_menu_mapping` VALUES ('3a06adf2c45e454db512a7017d81cd82', '1fsdfsdafasfqfs234fgdgsfsdf', '1gdfgdfgdfgdfsg5');
INSERT INTO `t_role_menu_mapping` VALUES ('3f69aad1792b4ef58d1d945d19e8b1cc', '1fsdfsdafasfqfs234fgdgsfsdf', '3yrtyerhgfyerty');
INSERT INTO `t_role_menu_mapping` VALUES ('46891f56f6a846bbaf017137906b6df2', '1fsdfsdafasfqfs234fgdgsfsdf', 'e041386708254e31beae8df1c92a4a8c');
INSERT INTO `t_role_menu_mapping` VALUES ('476ef38c268a4f3cbf0f200c1a69c499', '1fsdfsdafasfqfs234fgdgsfsdf', '2cce82e8b5274b6d8acb65c888415bb2');
INSERT INTO `t_role_menu_mapping` VALUES ('6f390d180c72491c85ee37ac3db8ad5e', '1fsdfsdafasfqfs234fgdgsfsdf', '7ereeet');
INSERT INTO `t_role_menu_mapping` VALUES ('905c385690ee463dbaecc06a826a4a4d', '1fsdfsdafasfqfs234fgdgsfsdf', '5tytryreyyrtyrt');
INSERT INTO `t_role_menu_mapping` VALUES ('955c09774da24fd3bcaa49353d2a7876', '1fsdfsdafasfqfs234fgdgsfsdf', '1gdfgdfgdfgdfgdfg3');
INSERT INTO `t_role_menu_mapping` VALUES ('95f52a4724804c74a1c30044a60465c4', '1fsdfsdafasfqfs234fgdgsfsdf', '1gdfgdfgdfgfdg4');
INSERT INTO `t_role_menu_mapping` VALUES ('ab866d9030f8425eaa9072ea5405e2d7', '1fsdfsdafasfqfs234fgdgsfsdf', '1gdfgdfgdfgdfg2');
INSERT INTO `t_role_menu_mapping` VALUES ('b12d422e857543daadbf452190545c83', '1fsdfsdafasfqfs234fgdgsfsdf', '82273d9bb3144250a6997a1bfbd90bfe');
INSERT INTO `t_role_menu_mapping` VALUES ('b95b384b514f4b78aec56747e5f16913', '1fsdfsdafasfqfs234fgdgsfsdf', '9stryytrytrytr');
INSERT INTO `t_role_menu_mapping` VALUES ('ba98527071434add96952b5ffa2b90f5', '1fsdfsdafasfqfs234fgdgsfsdf', 'ytryrtyrety4');
INSERT INTO `t_role_menu_mapping` VALUES ('bfbd7e27839a4cd6b759a54fec7f72aa', '1fsdfsdafasfqfs234fgdgsfsdf', 'a2cf1e8ab74449c29861404214421841');
INSERT INTO `t_role_menu_mapping` VALUES ('c0c150196607417dbf5b87596c7558b5', '1fsdfsdafasfqfs234fgdgsfsdf', '1gdfdggfdgdfggdfg1');
INSERT INTO `t_role_menu_mapping` VALUES ('d3b60747df124a9a98a5b5505cb84229', '1fsdfsdafasfqfs234fgdgsfsdf', 'ytryedhhdtryrty2');
INSERT INTO `t_role_menu_mapping` VALUES ('d3ec061dacf54c7c9b3532ac43b2de2a', '1fsdfsdafasfqfs234fgdgsfsdf', 'dfhgfhhgf3gdgdsgfh1');
INSERT INTO `t_role_menu_mapping` VALUES ('db5329cc472541f1999b93cfcdc0fa54', '1fsdfsdafasfqfs234fgdgsfsdf', '6ytryetryeeytrt');
INSERT INTO `t_role_menu_mapping` VALUES ('e95c81a7a2da47a197ef8028e202c712', '1fsdfsdafasfqfs234fgdgsfsdf', '1gfdgsdgsdgfdgsdfg0');
INSERT INTO `t_role_menu_mapping` VALUES ('ed1ed4bea19e4cb1a894644608fcd783', '1fsdfsdafasfqfs234fgdgsfsdf', '1gdfsgfgfdg7');

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
INSERT INTO `t_task` VALUES ('1', null, null, '您有一个用户信息待查看！！！', '/app/user/userView', '{\"id\":\"1aaaaaaaaaaaaaaaaaaaa\"}', '1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2019-09-26 20:01:07', '2019-12-05 20:43:34');
INSERT INTO `t_task` VALUES ('2', null, null, '您有一个角色信息待查看！！！', '/app/role/roleView', '{\"id\":\"1fsdfsdafasfqfs234fgdgsfsdf\"}', '1', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '1aaaaaaaaaaaaaaaaaaaa', '2019-09-26 20:15:40', '2019-12-05 20:43:21');

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
