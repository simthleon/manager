/*
 Navicat Premium Data Transfer

 Source Server         : Test
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : db_studentinfo

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 23/03/2020 20:58:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_class
-- ----------------------------
DROP TABLE IF EXISTS `t_class`;
CREATE TABLE `t_class`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `className` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `classDesc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_class
-- ----------------------------
INSERT INTO `t_class` VALUES (1, '08计本', '08计算机本科');
INSERT INTO `t_class` VALUES (2, '09计本', '09计算机本科');
INSERT INTO `t_class` VALUES (3, 'XX', 'XXXX');
INSERT INTO `t_class` VALUES (4, 'XX', 'XXXX');
INSERT INTO `t_class` VALUES (12, 'XX', 'XXXX');
INSERT INTO `t_class` VALUES (13, 'XX', 'XXXX');
INSERT INTO `t_class` VALUES (14, 'XX', 'XXXX');
INSERT INTO `t_class` VALUES (15, 'XX', 'XXXX');
INSERT INTO `t_class` VALUES (16, 'XX', 'XXXX');
INSERT INTO `t_class` VALUES (17, 'XX', 'XXXX');
INSERT INTO `t_class` VALUES (18, 'XX', 'XXXX');
INSERT INTO `t_class` VALUES (19, 'XX', 'XXXX');
INSERT INTO `t_class` VALUES (20, 'XX', 'XXXX');
INSERT INTO `t_class` VALUES (21, '123', '123');
INSERT INTO `t_class` VALUES (23, '计算机1班', '计算机软件工程');
INSERT INTO `t_class` VALUES (24, '09电子', '电子信息工程');

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student`  (
  `stuId` int(11) NOT NULL AUTO_INCREMENT,
  `stuNo` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `stuName` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `brithday` date NULL DEFAULT NULL,
  `classId` int(11) NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `stuDesc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`stuId`) USING BTREE,
  INDEX `classId`(`classId`) USING BTREE,
  CONSTRAINT `t_student_ibfk_1` FOREIGN KEY (`classId`) REFERENCES `t_class` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES (2, '123', 'a啊', '男', '2020-02-08', 2, 'aa@qq.com', '他');
INSERT INTO `t_student` VALUES (3, '54321', '退出', '男', '2020-01-31', 2, 'yyy@qq.com', 'ww');
INSERT INTO `t_student` VALUES (4, '132134', '1314', '女', '2020-02-10', 1, '12314@qq.com', '123');
INSERT INTO `t_student` VALUES (5, '132134', '1314', '女', '2020-02-10', 1, '12314@qq.com', '123');
INSERT INTO `t_student` VALUES (10, '132134', '1314', '女', '2020-02-10', 1, '12314@qq.com', '123');
INSERT INTO `t_student` VALUES (11, '132134', '1314', '女', '2020-02-10', 1, '12314@qq.com', '123');
INSERT INTO `t_student` VALUES (12, '132134', '1314', '女', '2020-02-10', 1, '12314@qq.com', '123');
INSERT INTO `t_student` VALUES (13, '132134', '1314', '女', '2020-02-10', 1, '12314@qq.com', '123');
INSERT INTO `t_student` VALUES (14, '132134', '1314', '女', '2020-02-10', 1, '12314@qq.com', '123');
INSERT INTO `t_student` VALUES (15, '132134', '1314', '女', '2020-02-10', 1, '12314@qq.com', '123');
INSERT INTO `t_student` VALUES (16, '132134', '1314', '女', '2020-02-10', 1, '12314@qq.com', '123');
INSERT INTO `t_student` VALUES (17, '132134', '1314', '女', '2020-02-10', 1, '12314@qq.com', '123');
INSERT INTO `t_student` VALUES (18, '132134', '1314', '女', '2020-02-10', 1, '12314@qq.com', '123');
INSERT INTO `t_student` VALUES (19, '132134', '1314', '女', '2020-02-10', 1, '12314@qq.com', '123');
INSERT INTO `t_student` VALUES (20, '132134', '1314', '女', '2020-02-10', 1, '12314@qq.com', '123');
INSERT INTO `t_student` VALUES (21, '132134', '1314', '女', '2020-02-10', 1, '12314@qq.com', '123');
INSERT INTO `t_student` VALUES (22, '132134', '1314', '女', '2020-02-10', 1, '12314@qq.com', '123');
INSERT INTO `t_student` VALUES (23, '132134', '1314', '女', '2020-02-10', 1, '12314@qq.com', '123');
INSERT INTO `t_student` VALUES (24, '132134', '1314', '女', '2020-02-10', 1, '12314@qq.com', '123');
INSERT INTO `t_student` VALUES (25, '132134', '1314', '女', '2020-02-10', 1, '12314@qq.com', '123');
INSERT INTO `t_student` VALUES (26, '132134', '1314', '女', '2020-02-10', 1, '12314@qq.com', '123');
INSERT INTO `t_student` VALUES (27, '132134', '1314', '女', '2020-02-10', 1, '12314@qq.com', '123');
INSERT INTO `t_student` VALUES (28, '132134', '1314', '女', '2020-02-10', 1, '12314@qq.com', '123');
INSERT INTO `t_student` VALUES (29, '132134', '1314', '女', '2020-02-10', 1, '12314@qq.com', '123');
INSERT INTO `t_student` VALUES (30, '132134', '1314', '女', '2020-02-10', 1, '12314@qq.com', '123');
INSERT INTO `t_student` VALUES (31, '132134', '1314', '女', '2020-02-10', 1, '12314@qq.com', '123');
INSERT INTO `t_student` VALUES (32, '132134', '1314', '女', '2020-02-10', 1, '12314@qq.com', '123');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', 'admin');

SET FOREIGN_KEY_CHECKS = 1;
