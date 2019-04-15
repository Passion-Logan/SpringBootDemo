/*
Navicat MySQL Data Transfer

Source Server         : 本地MySQL
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : redis_demo

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-04-15 17:25:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for demo
-- ----------------------------
DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of demo
-- ----------------------------
INSERT INTO `demo` VALUES ('1', 'username', '12');
INSERT INTO `demo` VALUES ('2', 'username', '111');
INSERT INTO `demo` VALUES ('3', 'user', '12');
INSERT INTO `demo` VALUES ('4', 'user', '12');
