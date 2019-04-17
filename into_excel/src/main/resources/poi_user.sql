/*
Navicat MySQL Data Transfer

Source Server         : 本地MySQL
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : poi

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-04-17 15:21:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for poi_user
-- ----------------------------
DROP TABLE IF EXISTS `poi_user`;
CREATE TABLE `poi_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of poi_user
-- ----------------------------
INSERT INTO `poi_user` VALUES ('1', '张三', '14', '14245678991');
