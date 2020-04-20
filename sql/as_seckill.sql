/*
 Navicat Premium Data Transfer

 Source Server         : as
 Source Server Type    : MySQL
 Source Server Version : 50647
 Source Host           : localhost:3306
 Source Schema         : as_seckill

 Target Server Type    : MySQL
 Target Server Version : 50647
 File Encoding         : 65001

 Date: 20/04/2020 12:22:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `goods_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品名称',
  `goods_title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品标题',
  `goods_img` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品图片',
  `goods_detail` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品明细',
  `goods_price` decimal(10, 2) NOT NULL COMMENT '商品价格',
  `goods_stock` int(11) NOT NULL COMMENT '商品库存',
  `version` int(255) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_goods_name`(`goods_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1020 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1001, 'iphone8', '华为 Mate 9 4GB+32GB版 月光银 移动联通电信4G手机 双卡双待', 'a10b30fc-503a-4da3-a8bb-05229de31254.jpg', '华为 Mate 9 4GB+32GB版 月光银 移动联通电信4G手机 双卡双待', 3212.00, 1000, 0);
INSERT INTO `goods` VALUES (1002, 'iphone8', 'Apple iPhone 8 (A1865) 64GB 银色 移动联通电信4G手机', 'a10b30fc-503a-4da3-a8bb-05229de31254.jpg', 'Apple iPhone 8 (A1865) 64GB 银色 移动联通电信4G手机', 5589.00, 9997, 1);
INSERT INTO `goods` VALUES (1003, '小米6', '小米6 4GB+32GB版 月光银 移动联通电信4G手机 双卡双待', 'a10b30fc-503a-4da3-a8bb-05229de31254.jpg', '小米6 4GB+32GB版 月光银 移动联通电信4G手机 双卡双待', 2999.00, 10000, 3);
INSERT INTO `goods` VALUES (1011, 'iphoneX', 'Apple iPhone X (A1865) 64GB 银色 移动联通电信4G手机', 'a10b30fc-503a-4da3-a8bb-05229de31254.jpg', 'Apple iPhone X (A1865) 64GB 银色 移动联通电信4G手机', 8765.00, 10000, 0);
INSERT INTO `goods` VALUES (1015, 'iphone 17', '华强北抢先发布', 'a10b30fc-503a-4da3-a8bb-05229de31254.jpg', '还可以', 999.98, 838, 1);
INSERT INTO `goods` VALUES (1016, 'iphone 13', '华强北iphone 13抢先发布', 'a10b30fc-503a-4da3-a8bb-05229de31254.jpg', '真的很牛逼', 999.00, 180, 14);
INSERT INTO `goods` VALUES (1017, '华强北三星S20', '抢先发布', 'a10b30fc-503a-4da3-a8bb-05229de31254.jpg', '三星内部数据泄露。。。。。。。。', 999.00, 1000, 0);
INSERT INTO `goods` VALUES (1018, '8848钛金手机', '成功男人的标配', 'a10b30fc-503a-4da3-a8bb-05229de31254.jpg', '没有它还算什么男人', 8888.00, 799, 1);
INSERT INTO `goods` VALUES (1019, 'iPhone 18', 'Apple 苹果 iPhone 18 手机 黑色 512G 全网通', 'a358e403-ad69-4c8e-8464-aa4bc053a77e.jpg', '商品名称：Apple苹果18商品编号：59741239006商品毛重：0.6kg运行内存：其它内存机身存储：128GB存储卡：其它存储卡后摄主摄像素：1200万像素前摄主摄像素：1200万像素屏幕前摄组合：刘海屏充电器：其他', 112.00, 1200, 3);

-- ----------------------------
-- Table structure for seckill
-- ----------------------------
DROP TABLE IF EXISTS `seckill`;
CREATE TABLE `seckill`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '秒杀表id',
  `goods_id` bigint(20) NOT NULL COMMENT '秒杀的商品id',
  `goods_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '秒杀的商品名称',
  `seckill_stock` int(11) NOT NULL COMMENT '秒杀商品的库存',
  `seckill_starttime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '秒杀开始时间',
  `seckill_endtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '秒杀结束时间',
  `seckill_creationtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '秒杀创建时间',
  `seckill_price` decimal(10, 2) NOT NULL COMMENT '秒杀价',
  `version` int(255) NOT NULL COMMENT '版本',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_starttime`(`seckill_starttime`) USING BTREE,
  INDEX `idx_endtime`(`seckill_endtime`) USING BTREE,
  INDEX `idx_creationtime`(`seckill_creationtime`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1010 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '秒杀表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of seckill
-- ----------------------------
INSERT INTO `seckill` VALUES (1006, 1015, 'iphone 17', 50, '2020-04-15 18:00:16', '2020-04-15 19:00:18', '2020-04-15 18:00:22', 888.00, 0);
INSERT INTO `seckill` VALUES (1007, 1019, 'af', 12, '2020-04-15 18:05:50', '2020-04-15 18:05:52', '2020-04-15 18:05:53', 100.00, 0);
INSERT INTO `seckill` VALUES (1008, 1019, 'iPhone 18', 100, '2020-04-20 00:47:01', '2020-04-21 23:59:59', '2020-04-15 18:07:16', 100.00, 0);
INSERT INTO `seckill` VALUES (1009, 1019, 'iPhone 18', 100, '2020-04-20 05:00:00', '2020-04-20 08:00:00', '2020-04-19 18:15:48', 100.00, 0);

-- ----------------------------
-- Table structure for seckilled
-- ----------------------------
DROP TABLE IF EXISTS `seckilled`;
CREATE TABLE `seckilled`  (
  `seckill_id` bigint(20) NOT NULL COMMENT '秒杀id',
  `user_phone` bigint(20) NOT NULL COMMENT '用户手机号',
  `seckilled_state` tinyint(4) NOT NULL DEFAULT -1 COMMENT '状态标识 ： -1 无效 0成功 1已付款',
  `seckilled_creationtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `seckilled_price` decimal(10, 2) NOT NULL COMMENT '订单价格',
  `seckilled_number` int(11) NOT NULL COMMENT '数量',
  `goods_id` bigint(20) NOT NULL COMMENT '商品id',
  PRIMARY KEY (`seckill_id`, `user_phone`) USING BTREE,
  INDEX `idx_creationtime`(`seckilled_creationtime`) USING BTREE,
  INDEX `idx_userPhone`(`user_phone`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '秒杀成功明细表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of seckilled
-- ----------------------------
INSERT INTO `seckilled` VALUES (1008, 15398923162, -1, '2020-04-20 02:46:27', 100.00, 1, 1019);

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime(0) NOT NULL,
  `log_modified` datetime(0) NOT NULL,
  `ext` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of undo_log
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;


