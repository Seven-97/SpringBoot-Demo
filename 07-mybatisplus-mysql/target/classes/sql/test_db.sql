/*
 Navicat Premium Data Transfer

 Source Server         : mysql5
 Source Server Type    : MySQL
 Source Server Version : 50739
 Source Host           : localhost:3307
 Source Schema         : test_db

 Target Server Type    : MySQL
 Target Server Version : 50739
 File Encoding         : 65001

 Date: 10/08/2024 23:07:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `province` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市',
  `town` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '县/区',
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `street` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `contact` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `is_default` bit(1) NULL DEFAULT b'0' COMMENT '是否是默认 1默认 0否',
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 71 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (59, 2, '北京', '北京', '朝阳区', '13900112222', '金燕龙办公楼', 'Rose', b'1', NULL, b'1');
INSERT INTO `address` VALUES (60, 1, '北京', '北京', '朝阳区', '13700221122', '修正大厦', 'Jack', b'0', NULL, b'0');
INSERT INTO `address` VALUES (61, 1, '上海', '上海', '浦东新区', '13301212233', '航头镇航头路', 'Jack', b'1', NULL, b'0');
INSERT INTO `address` VALUES (63, 2, '广东', '佛山', '永春', '13301212233', '永春武馆', 'Rose', b'0', NULL, b'0');
INSERT INTO `address` VALUES (64, 3, '浙江', '杭州', '拱墅区', '13567809102', '浙江大学', 'Hope', b'1', NULL, b'0');
INSERT INTO `address` VALUES (65, 3, '浙江', '杭州', '拱墅区', '13967589201', '左岸花园', 'Hope', b'0', NULL, b'0');
INSERT INTO `address` VALUES (66, 4, '湖北', '武汉', '汉口', '13967519202', '天天花园', 'Thomas', b'1', NULL, b'0');
INSERT INTO `address` VALUES (67, 3, '浙江', '杭州', '拱墅区', '13967589201', '左岸花园', 'Hopey', b'0', NULL, b'0');
INSERT INTO `address` VALUES (68, 4, '湖北', '武汉', '汉口', '13967519202', '天天花园', 'Thomas', b'1', NULL, b'0');
INSERT INTO `address` VALUES (69, 3, '浙江', '杭州', '拱墅区', '13967589201', '左岸花园', 'Hopey', b'0', NULL, b'0');
INSERT INTO `address` VALUES (70, 4, '湖北', '武汉', '汉口', '13967519202', '天天花园', 'Thomas', b'1', NULL, b'0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册手机号',
  `info` json NOT NULL COMMENT '详细信息',
  `status` tinyint(10) NULL DEFAULT 1 COMMENT '使用状态（1正常 2冻结）',
  `balance` int(10) NULL DEFAULT NULL COMMENT '账户余额',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'Jack', '123', '13900112224', '{\"age\": 20, \"intro\": \"佛系青年\", \"gender\": \"male\"}', 1, 1800, '2023-05-19 20:50:21', '2024-08-10 11:32:13');
INSERT INTO `user` VALUES (2, 'Rose', '123', '13900112223', '{\"age\": 19, \"intro\": \"青涩少女\", \"gender\": \"female\"}', 1, 200, '2023-05-19 21:00:23', '2024-08-10 12:56:02');
INSERT INTO `user` VALUES (3, 'Hope', '123', '13900112222', '{\"age\": 25, \"intro\": \"上进青年\", \"gender\": \"male\"}', 1, 100000, '2023-06-19 22:37:44', '2023-06-19 22:37:44');

SET FOREIGN_KEY_CHECKS = 1;
