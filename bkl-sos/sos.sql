/*
Navicat MySQL Data Transfer

Source Server         : mysql5.7
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : sos

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2014-10-27 14:04:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL DEFAULT '0',
  `username` varchar(50) NOT NULL DEFAULT '',
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '123456');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `reg_ip` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '注册ip',
  `pin` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nick_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '昵称',
  `ctime` int(11) NOT NULL,
  `rmb` decimal(65,4) NOT NULL DEFAULT '0.0000' COMMENT '可用的rmb金额',
  `rmb_frozen` decimal(65,4) NOT NULL DEFAULT '0.0000' COMMENT '冻结的rmb金额',
  `email_validated` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0表示邮箱未验证，1表示邮箱已经验证',
  `realname_validated` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0表示未进行实名验证，1表示已经实名验证，2表示实名认证不通过',
  `mobile_validated` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0表示手机未验证，1表示手机已经验证',
  `secret` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `received` decimal(65,4) NOT NULL DEFAULT '0.0000',
  `bank_account` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bank` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `identity_type` tinyint(4) DEFAULT '0' COMMENT '证件类型',
  `identity_no` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '证件号',
  `frozen` tinyint(4) DEFAULT '0' COMMENT '1表示用户被冻结;0表示用户状态正常',
  `passwd_modify_overtime` int(11) DEFAULT NULL COMMENT '忘记密码的修改限期',
  `bank_number` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mobile` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `headIcon` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', 'cb371030349@sina.com', 'fcea920f7412b5da7be0cf42b8c93759', '192.168.0.149', '', '', '毛毛', '1411984210', '0.0000', '0.0000', '1', '0', '0', '', '', '0.0000', null, null, '0', null, '0', '0', null, null, 'uploads/05f2227c-380d-4ac8-966c-6d4687e0c28e.jpg');
INSERT INTO `user` VALUES ('3', 'fantianmi@gmail.com', 'daeb71dabbc39de1b407317897764505', '0:0:0:0:0:0:0:1', '', '', '', '1412236401', '0.0000', '0.0000', '1', '0', '0', '', '', '0.0000', null, null, '0', null, '0', '1413787505', null, null, 'upload/head/default.jpg');
INSERT INTO `user` VALUES ('4', 'fantianmi@163.com', 'daeb71dabbc39de1b407317897764505', '192.168.0.177', '', '', '', '1412648333', '0.0000', '0.0000', '0', '0', '0', '', '', '0.0000', null, null, '0', null, '0', '0', null, null, 'upload/head/default.jpg');
INSERT INTO `user` VALUES ('5', '', 'd41d8cd98f00b204e9800998ecf8427e', '192.168.0.149', '1158074223', '', '', '1413800337', '0.0000', '0.0000', '1', '0', '0', '', '', '0.0000', null, null, '0', null, '0', '0', null, null, '');

-- ----------------------------
-- Table structure for version
-- ----------------------------
DROP TABLE IF EXISTS `version`;
CREATE TABLE `version` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `version` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `downloadurl` varchar(1000) NOT NULL,
  `apktype` mediumint(9) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of version
-- ----------------------------
INSERT INTO `version` VALUES ('1', '1.0.0', 'examine_v1.0.0.apk', 'test/examine_v1.0.0.apk', '0');
INSERT INTO `version` VALUES ('2', '1.0.1', 'examine_v1.0.1.apk', 'test/examine_v1.0.0.apk', '0');
INSERT INTO `version` VALUES ('3', '1.0.2', 'examine_v1.0.2.apk', 'test/examine_v1.0.0.apk', '0');
INSERT INTO `version` VALUES ('4', '1.0.0', 'examine_v1.0.0.ipa', 'test/examine_v1.0.0.ipa', '1');
INSERT INTO `version` VALUES ('5', '1.0.1', 'examine_v1.0.1.ipa', 'test/examine_v1.0.1.ipa', '1');
INSERT INTO `version` VALUES ('6', '1.0.3', 'examine_v1.0.3.apk', 'test/examine_v1.0.0.apk', '0');
