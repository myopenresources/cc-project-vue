/*
Navicat MySQL Data Transfer

Source Server         : def
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : demo_db

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2020-01-28 17:07:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_student`
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `dept_Id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `CREATE_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES ('1212121', '牛二', '864824f014754b6186d2ef6356e9ed3f', '1aaaaaaaaaaaaaaaaaaaa');
INSERT INTO `t_student` VALUES ('1212122', '张三', '864824f014754b6186d2ef6356e9ed3f', '1aaaaaaaaaaaaaaaaaaaa');
INSERT INTO `t_student` VALUES ('adfadsfasf', '钱六', '4aaaaaaaaaaaaaaaaaaaafsadf', '1aaaaaaaaaaaaaaaaaaaa');
INSERT INTO `t_student` VALUES ('adfdsfsdf', '田七', '2aaaaaaaaaaaaaaaaaaaafsadf', '');
INSERT INTO `t_student` VALUES ('afsadfsdf', '李四', '3aaaaaaaaaaaaaaaaaaaafsadf', null);
INSERT INTO `t_student` VALUES ('fasdfdff', '王五', '1aaaaaaaaaaaaaaaaaaaafsadf', '');
