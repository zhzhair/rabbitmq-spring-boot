/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : exercise_user

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-06-09 17:10:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `password` varchar(36) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'xiaoming', 'e10adc3949ba59abbe56e057f20f883e', '2019-06-09 13:43:36');
INSERT INTO `user` VALUES ('2', 'xiaohong', 'e10adc3949ba59abbe56e057f20f883e', '2019-06-09 13:43:36');
INSERT INTO `user` VALUES ('3', 'jingjing', 'e10adc3949ba59abbe56e057f20f883e', '2019-06-09 13:43:36');
INSERT INTO `user` VALUES ('4', 'xiaoqiang', 'e10adc3949ba59abbe56e057f20f883e', '2019-06-09 13:43:36');
