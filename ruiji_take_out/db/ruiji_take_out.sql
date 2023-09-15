/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50719 (5.7.19)
 Source Host           : localhost:3306
 Source Schema         : ruiji_take_out

 Target Server Type    : MySQL
 Target Server Version : 50719 (5.7.19)
 File Encoding         : 65001

 Date: 15/09/2023 18:00:36
*/

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ruiji_take_out` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `ruiji_take_out`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address_book
-- ----------------------------
DROP TABLE IF EXISTS `address_book`;
CREATE TABLE `address_book`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `consignee` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '收货人',
  `sex` tinyint(4) NOT NULL COMMENT '性别 0 女 1 男',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `province_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省级区划编号',
  `province_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省级名称',
  `city_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '市级区划编号',
  `city_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '市级名称',
  `district_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '区级区划编号',
  `district_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '区级名称',
  `detail` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签',
  `is_default` tinyint(1) NOT NULL DEFAULT 0 COMMENT '默认 0 否 1是',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `is_deleted` int(11) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '地址管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address_book
-- ----------------------------
INSERT INTO `address_book` VALUES (1417414526093082626, 1417012167126876162, '小明', 1, '13812345678', NULL, NULL, NULL, NULL, NULL, NULL, '昌平区金燕龙办公楼', '公司', 1, '2021-07-20 17:22:12', '2021-07-20 17:26:33', 1417012167126876162, 1417012167126876162, 0);
INSERT INTO `address_book` VALUES (1417414926166769666, 1417012167126876162, '小李', 1, '13512345678', NULL, NULL, NULL, NULL, NULL, NULL, '测试', '家', 0, '2021-07-20 17:23:47', '2021-07-20 17:23:47', 1417012167126876162, 1417012167126876162, 0);
INSERT INTO `address_book` VALUES (1700071661065969665, 1700052775037718530, '刘', 1, '13111111111', NULL, NULL, NULL, NULL, NULL, NULL, '泗县', '家', 0, '2023-09-08 17:00:44', '2023-09-09 11:24:13', 1700052775037718530, 1700052775037718530, 1);
INSERT INTO `address_book` VALUES (1700073081529933825, 1700052775037718530, '张三', 1, '13211111111', NULL, NULL, NULL, NULL, NULL, NULL, '南京', '学校', 0, '2023-09-08 17:06:23', '2023-09-09 16:32:24', 1700052775037718500, 1700052775037718530, 0);
INSERT INTO `address_book` VALUES (1700357799194345473, 1700052775037718530, '李', 0, '13222222222', NULL, NULL, NULL, NULL, NULL, NULL, '上海', '学校', 1, '2023-09-09 11:57:45', '2023-09-09 16:32:30', 1700052775037718530, 1700052775037718530, 0);
INSERT INTO `address_book` VALUES (1700426516322287617, 1700052775037718530, 'li', 1, '13111111111', NULL, NULL, NULL, NULL, NULL, NULL, '芜湖', '公司', 0, '2023-09-09 16:30:49', '2023-09-09 16:32:19', 1700052775037718530, 1700052775037718530, 0);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型   1 菜品分类 2 套餐分类',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '分类名称',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '顺序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_category_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜品及套餐分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1397844263642378242, 1, '湘菜', 1, '2021-05-27 09:16:58', '2021-07-15 20:25:23', 1, 1);
INSERT INTO `category` VALUES (1397844303408574465, 1, '川菜', 2, '2021-05-27 09:17:07', '2021-06-02 14:27:22', 1, 1);
INSERT INTO `category` VALUES (1397844391040167938, 1, '粤菜', 3, '2021-05-27 09:17:28', '2021-07-09 14:37:13', 1, 1);
INSERT INTO `category` VALUES (1413341197421846529, 1, '饮品', 11, '2021-07-09 11:36:15', '2021-07-09 14:39:15', 1, 1);
INSERT INTO `category` VALUES (1413384954989060097, 1, '主食', 12, '2021-07-09 14:30:07', '2021-07-09 14:39:19', 1, 1);
INSERT INTO `category` VALUES (1413386191767674881, 2, '儿童套餐', 6, '2021-07-09 14:35:02', '2021-07-09 14:39:05', 1, 1);
INSERT INTO `category` VALUES (1698619397956657154, 1, '满汉全席', 1, '2023-09-04 16:49:58', '2023-09-05 10:29:19', 1, 1);
INSERT INTO `category` VALUES (1698619397956657155, 1, '徽菜2', 3, '2023-09-04 16:49:58', '2023-09-04 16:49:58', 1, 1);
INSERT INTO `category` VALUES (1698619397956657156, 1, '徽菜3', 4, '2023-09-04 16:49:58', '2023-09-04 16:49:58', 1, 1);
INSERT INTO `category` VALUES (1698619397956657157, 1, '徽菜4', 5, '2023-09-04 16:49:58', '2023-09-04 16:49:58', 1, 1);
INSERT INTO `category` VALUES (1698619398040543234, 1, '徽菜5', 6, '2023-09-04 16:49:58', '2023-09-04 16:49:58', 1, 1);
INSERT INTO `category` VALUES (1698619398040543235, 1, '徽菜6', 7, '2023-09-04 16:49:58', '2023-09-04 16:49:58', 1, 1);
INSERT INTO `category` VALUES (1698619398040543236, 1, '徽菜7', 8, '2023-09-04 16:49:58', '2023-09-04 16:49:58', 1, 1);
INSERT INTO `category` VALUES (1698619398040543237, 1, '徽菜8', 9, '2023-09-04 16:49:58', '2023-09-04 16:49:58', 1, 1);
INSERT INTO `category` VALUES (1698619398128623618, 1, '徽菜9', 10, '2023-09-04 16:49:58', '2023-09-04 16:49:58', 1, 1);
INSERT INTO `category` VALUES (1698624439442870273, 1, '主食1', 12, '2023-09-04 17:10:00', '2023-09-04 17:10:00', 1, 1);
INSERT INTO `category` VALUES (1698624898807238658, 2, '饮品分类', 44, '2023-09-04 17:11:49', '2023-09-04 22:38:38', 1, 1);
INSERT INTO `category` VALUES (1699649446604328961, 2, '黄焖鸡米饭', 22, '2023-09-07 13:03:01', '2023-09-07 13:03:01', 1, 1);
INSERT INTO `category` VALUES (1699649494159347714, 2, '红烧鱼', 1, '2023-09-07 13:03:12', '2023-09-07 13:03:12', 1, 1);

-- ----------------------------
-- Table structure for dish
-- ----------------------------
DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '菜品名称',
  `category_id` bigint(20) NOT NULL COMMENT '菜品分类id',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '菜品价格',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商品码',
  `image` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '图片',
  `description` varchar(400) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '描述信息',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '0 停售 1 起售',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '顺序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `is_deleted` int(11) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_dish_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜品管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dish
-- ----------------------------
INSERT INTO `dish` VALUES (1397849739276890114, '辣子鸡', 1397844263642378242, 7800.00, '222222222', 'f966a38e-0780-40be-bb52-5699d13cb3d9.jpg', '来自鲜嫩美味的小鸡，值得一尝', 1, 0, '2021-05-27 09:38:43', '2021-05-27 09:38:43', 1, 1, 0);
INSERT INTO `dish` VALUES (1397850140982161409, '毛氏红烧肉', 1397844263642378242, 6800.00, '123412341234', '0a3b3288-3446-4420-bbff-f263d0c02d8e.jpg', '毛氏红烧肉毛氏红烧肉，确定不来一份？', 1, 0, '2021-05-27 09:40:19', '2021-05-27 09:40:19', 1, 1, 0);
INSERT INTO `dish` VALUES (1397850392090947585, '组庵鱼翅', 1397844263642378242, 4800.00, '123412341234', '740c79ce-af29-41b8-b78d-5f49c96e38c4.jpg', '组庵鱼翅，看图足以表明好吃程度', 1, 0, '2021-05-27 09:41:19', '2021-05-27 09:41:19', 1, 1, 0);
INSERT INTO `dish` VALUES (1397850851245600769, '霸王别姬', 1397844263642378242, 12800.00, '123412341234', '057dd338-e487-4bbc-a74c-0384c44a9ca3.jpg', '还有什么比霸王别姬更美味的呢？', 1, 0, '2021-05-27 09:43:08', '2021-05-27 09:43:08', 1, 1, 0);
INSERT INTO `dish` VALUES (1397851099502260226, '全家福', 1397844263642378242, 11800.00, '23412341234', 'a53a4e6a-3b83-4044-87f9-9d49b30a8fdc.jpg', '别光吃肉啦，来份全家福吧，让你长寿又美味', 1, 0, '2021-05-27 09:44:08', '2021-05-27 09:44:08', 1, 1, 0);
INSERT INTO `dish` VALUES (1397851370462687234, '邵阳猪血丸子', 1397844263642378242, 13800.00, '1246812345678', '2a50628e-7758-4c51-9fbb-d37c61cdacad.jpg', '看，美味不？来嘛来嘛，这才是最爱吖', 1, 0, '2021-05-27 09:45:12', '2021-05-27 09:45:12', 1, 1, 0);
INSERT INTO `dish` VALUES (1397851668262465537, '口味蛇', 1397844263642378242, 16800.00, '1234567812345678', '0f4bd884-dc9c-4cf9-b59e-7d5958fec3dd.jpg', '爬行界的扛把子，东兴-口味蛇，让你欲罢不能', 1, 0, '2021-05-27 09:46:23', '2021-05-27 09:46:23', 1, 1, 0);
INSERT INTO `dish` VALUES (1397852391150759938, '辣子鸡丁', 1397844303408574465, 8800.00, '2346812468', 'ef2b73f2-75d1-4d3a-beea-22da0e1421bd.jpg', '辣子鸡丁，辣子鸡丁，永远的魂', 1, 0, '2021-05-27 09:49:16', '2021-05-27 09:49:16', 1, 1, 0);
INSERT INTO `dish` VALUES (1397853183287013378, '麻辣兔头', 1397844303408574465, 19800.00, '123456787654321', '2a2e9d66-b41d-4645-87bd-95f2cfeed218.jpg', '麻辣兔头的详细制作，麻辣鲜香，色泽红润，回味悠长', 1, 0, '2021-05-27 09:52:24', '2021-05-27 09:52:24', 1, 1, 0);
INSERT INTO `dish` VALUES (1397853709101740034, '蒜泥白肉', 1397844303408574465, 9800.00, '1234321234321', 'd2f61d70-ac85-4529-9b74-6d9a2255c6d7.jpg', '多么的有食欲啊', 1, 0, '2021-05-27 09:54:30', '2021-05-27 09:54:30', 1, 1, 0);
INSERT INTO `dish` VALUES (1397853890262118402, '鱼香肉丝', 1397844303408574465, 3800.00, '1234212321234', '8dcfda14-5712-4d28-82f7-ae905b3c2308.jpg', '鱼香肉丝简直就是我们童年回忆的一道经典菜，上学的时候点个鱼香肉丝盖饭坐在宿舍床上看着肥皂剧，绝了！现在完美复刻一下上学的时候感觉', 1, 0, '2021-05-27 09:55:13', '2021-05-27 09:55:13', 1, 1, 0);
INSERT INTO `dish` VALUES (1397854652581064706, '麻辣水煮鱼', 1397844303408574465, 14800.00, '2345312·345321', '1fdbfbf3-1d86-4b29-a3fc-46345852f2f8.jpg', '鱼片是买的切好的鱼片，放几个虾，增加味道', 1, 0, '2021-05-27 09:58:15', '2021-05-27 09:58:15', 1, 1, 0);
INSERT INTO `dish` VALUES (1397854865672679425, '鱼香炒鸡蛋', 1397844303408574465, 2000.00, '23456431·23456', '0f252364-a561-4e8d-8065-9a6797a6b1d3.jpg', '鱼香菜也是川味的特色。里面没有鱼却鱼香味', 1, 0, '2021-05-27 09:59:06', '2021-05-27 09:59:06', 1, 1, 0);
INSERT INTO `dish` VALUES (1397860242057375745, '脆皮烧鹅', 1397844391040167938, 12800.00, '123456786543213456', 'e476f679-5c15-436b-87fa-8c4e9644bf33.jpeg', '“广东烤鸭美而香，却胜烧鹅说古冈（今新会），燕瘦环肥各佳妙，君休偏重便宜坊”，可见烧鹅与烧鸭在粤菜之中已早负盛名。作为广州最普遍和最受欢迎的烧烤肉食，以它的“色泽金红，皮脆肉嫩，味香可口”的特色，在省城各大街小巷的烧卤店随处可见。', 1, 0, '2021-05-27 10:20:27', '2021-05-27 10:20:27', 1, 1, 0);
INSERT INTO `dish` VALUES (1397860578738352129, '白切鸡', 1397844391040167938, 6600.00, '12345678654', '9ec6fc2d-50d2-422e-b954-de87dcd04198.jpeg', '白切鸡是一道色香味俱全的特色传统名肴，又叫白斩鸡，是粤菜系鸡肴中的一种，始于清代的民间。白切鸡通常选用细骨农家鸡与沙姜、蒜茸等食材，慢火煮浸白切鸡皮爽肉滑，清淡鲜美。著名的泮溪酒家白切鸡，曾获商业部优质产品金鼎奖。湛江白切鸡更是驰名粤港澳。粤菜厨坛中，鸡的菜式有200余款之多，而最为人常食不厌的正是白切鸡，深受食家青睐。', 1, 0, '2021-05-27 10:21:48', '2021-05-27 10:21:48', 1, 1, 0);
INSERT INTO `dish` VALUES (1397860792492666881, '烤乳猪', 1397844391040167938, 38800.00, '213456432123456', '2e96a7e3-affb-438e-b7c3-e1430df425c9.jpeg', '广式烧乳猪主料是小乳猪，辅料是蒜，调料是五香粉、芝麻酱、八角粉等，本菜品主要通过将食材放入炭火中烧烤而成。烤乳猪是广州最著名的特色菜，并且是“满汉全席”中的主打菜肴之一。烤乳猪也是许多年来广东人祭祖的祭品之一，是家家都少不了的应节之物，用乳猪祭完先人后，亲戚们再聚餐食用。', 1, 0, '2021-05-27 10:22:39', '2021-05-27 10:22:39', 1, 1, 0);
INSERT INTO `dish` VALUES (1397860963880316929, '脆皮乳鸽', 1397844391040167938, 10800.00, '1234563212345', '3fabb83a-1c09-4fd9-892b-4ef7457daafa.jpeg', '“脆皮乳鸽”是广东菜中的一道传统名菜，属于粤菜系，具有皮脆肉嫩、色泽红亮、鲜香味美的特点，常吃可使身体强健，清肺顺气。随着菜品制作工艺的不断发展，逐渐形成了熟炸法、生炸法和烤制法三种制作方法。无论那种制作方法，都是在鸽子经过一系列的加工，挂脆皮水后再加工而成，正宗的“脆皮乳鸽皮脆肉嫩、色泽红亮、鲜香味美、香气馥郁。这三种方法的制作过程都不算复杂，但想达到理想的效果并不容易。', 1, 0, '2021-05-27 10:23:19', '2021-05-27 10:23:19', 1, 1, 0);
INSERT INTO `dish` VALUES (1397861683434139649, '清蒸河鲜海鲜', 1397844391040167938, 38800.00, '1234567876543213456', '1405081e-f545-42e1-86a2-f7559ae2e276.jpeg', '新鲜的海鲜，清蒸是最好的处理方式。鲜，体会为什么叫海鲜。清蒸是广州最经典的烹饪手法，过去岭南地区由于峻山大岭阻隔，交通不便，经济发展起步慢，自家打的鱼放在锅里煮了就吃，没有太多的讲究，但却发现这清淡的煮法能使鱼的鲜甜跃然舌尖。', 1, 0, '2021-05-27 10:26:11', '2021-05-27 10:26:11', 1, 1, 0);
INSERT INTO `dish` VALUES (1397862198033297410, '老火靓汤', 1397844391040167938, 49800.00, '123456786532455', '583df4b7-a159-4cfc-9543-4f666120b25f.jpeg', '老火靓汤又称广府汤，是广府人传承数千年的食补养生秘方，慢火煲煮的中华老火靓汤，火候足，时间长，既取药补之效，又取入口之甘甜。 广府老火汤种类繁多，可以用各种汤料和烹调方法，烹制出各种不同口味、不同功效的汤来。', 1, 0, '2021-05-27 10:28:14', '2021-05-27 10:28:14', 1, 1, 0);
INSERT INTO `dish` VALUES (1397862477831122945, '上汤焗龙虾', 1397844391040167938, 108800.00, '1234567865432', '5b8d2da3-3744-4bb3-acdc-329056b8259d.jpeg', '上汤焗龙虾是一道色香味俱全的传统名菜，属于粤菜系。此菜以龙虾为主料，配以高汤制成的一道海鲜美食。本品肉质洁白细嫩，味道鲜美，蛋白质含量高，脂肪含量低，营养丰富。是色香味俱全的传统名菜。', 1, 0, '2021-05-27 10:29:20', '2021-05-27 10:29:20', 1, 1, 0);
INSERT INTO `dish` VALUES (1413342036832100354, '北冰洋', 1413341197421846529, 500.00, '', 'c99e0aab-3cb7-4eaa-80fd-f47d4ffea694.png', '', 1, 0, '2021-07-09 11:39:35', '2023-09-06 23:10:20', 1, 1, 0);
INSERT INTO `dish` VALUES (1413384757047271425, '王老吉', 1413341197421846529, 500.00, '', '00874a5e-0df2-446b-8f69-a30eb7d88ee8.png', '', 1, 0, '2021-07-09 14:29:20', '2023-09-06 23:10:20', 1, 1, 1);
INSERT INTO `dish` VALUES (1413385247889891330, '米饭', 1413384954989060097, 200.00, '', 'ee04a05a-1230-46b6-8ad5-1a95b140fff3.png', '', 1, 0, '2021-07-09 14:31:17', '2023-09-06 23:10:20', 1, 1, 0);
INSERT INTO `dish` VALUES (1699337412331343874, '佛跳墙', 1397844263642378242, 12300.00, '', '3c44bd60-9d48-453b-8e3b-34f70d47d84c.jpeg', '非常好吃', 1, 0, '2023-09-06 16:23:06', '2023-09-07 21:44:35', 1, 1, 0);
INSERT INTO `dish` VALUES (1699441944205905921, '1', 1397844303408574465, 1100.00, '', 'a89b69f9-f235-4554-aab8-ed34cf40763e.jpg', '1', 1, 0, '2023-09-06 23:18:28', '2023-09-06 23:18:28', 1, 1, 1);
INSERT INTO `dish` VALUES (1699442224041480193, '2', 1397844391040167938, 2200.00, '', 'd333eee2-cf14-4528-bfb0-669ec7bacd1b.jpeg', '22', 1, 0, '2023-09-06 23:19:35', '2023-09-06 23:19:35', 1, 1, 1);
INSERT INTO `dish` VALUES (1699442312088309761, '3', 1397844391040167938, 3300.00, '', '386ed157-61b9-4332-8965-ee7b3da9225e.jpg', '33', 1, 0, '2023-09-06 23:19:56', '2023-09-06 23:19:56', 1, 1, 1);
INSERT INTO `dish` VALUES (1699442385387966466, '44', 1397844263642378242, 4400.00, '', 'e86d9dd0-cb2f-4c25-b779-d878708cdf66.jpg', '44', 1, 0, '2023-09-06 23:20:13', '2023-09-06 23:20:13', 1, 1, 1);

-- ----------------------------
-- Table structure for dish_flavor
-- ----------------------------
DROP TABLE IF EXISTS `dish_flavor`;
CREATE TABLE `dish_flavor`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `dish_id` bigint(20) NOT NULL COMMENT '菜品',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '口味名称',
  `value` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '口味数据list',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `is_deleted` int(11) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜品口味关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dish_flavor
-- ----------------------------
INSERT INTO `dish_flavor` VALUES (1397849417888346113, 1397849417854791681, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:37:27', '2021-05-27 09:37:27', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397849739297861633, 1397849739276890114, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 09:38:43', '2021-05-27 09:38:43', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397849739323027458, 1397849739276890114, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:38:43', '2021-05-27 09:38:43', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397849936421761025, 1397849936404983809, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 09:39:30', '2021-05-27 09:39:30', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397849936438538241, 1397849936404983809, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:39:30', '2021-05-27 09:39:30', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397850141015715841, 1397850140982161409, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 09:40:19', '2021-05-27 09:40:19', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397850141040881665, 1397850140982161409, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:40:19', '2021-05-27 09:40:19', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397850392120307713, 1397850392090947585, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:41:19', '2021-05-27 09:41:19', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397850392137084929, 1397850392090947585, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:41:19', '2021-05-27 09:41:19', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397850630734262274, 1397850630700707841, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 09:42:16', '2021-05-27 09:42:16', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397850630755233794, 1397850630700707841, '辣度', '[\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:42:16', '2021-05-27 09:42:16', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397850851274960898, 1397850851245600769, '忌口', '[\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 09:43:08', '2021-05-27 09:43:08', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397850851283349505, 1397850851245600769, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:43:08', '2021-05-27 09:43:08', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397851099523231745, 1397851099502260226, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 09:44:08', '2021-05-27 09:44:08', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397851099527426050, 1397851099502260226, '辣度', '[\"不辣\",\"微辣\",\"中辣\"]', '2021-05-27 09:44:08', '2021-05-27 09:44:08', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397851370483658754, 1397851370462687234, '温度', '[\"热饮\",\"常温\",\"去冰\",\"少冰\",\"多冰\"]', '2021-05-27 09:45:12', '2021-05-27 09:45:12', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397851370483658755, 1397851370462687234, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 09:45:12', '2021-05-27 09:45:12', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397851370483658756, 1397851370462687234, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:45:12', '2021-05-27 09:45:12', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397851668283437058, 1397851668262465537, '温度', '[\"热饮\",\"常温\",\"去冰\",\"少冰\",\"多冰\"]', '2021-05-27 09:46:23', '2021-05-27 09:46:23', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397852391180120065, 1397852391150759938, '忌口', '[\"不要葱\",\"不要香菜\",\"不要辣\"]', '2021-05-27 09:49:16', '2021-05-27 09:49:16', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397852391196897281, 1397852391150759938, '辣度', '[\"不辣\",\"微辣\",\"重辣\"]', '2021-05-27 09:49:16', '2021-05-27 09:49:16', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397853183307984898, 1397853183287013378, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:52:24', '2021-05-27 09:52:24', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397853423486414850, 1397853423461249026, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:53:22', '2021-05-27 09:53:22', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397853709126905857, 1397853709101740034, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 09:54:30', '2021-05-27 09:54:30', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397853890283089922, 1397853890262118402, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:55:13', '2021-05-27 09:55:13', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397854133632413697, 1397854133603053569, '温度', '[\"热饮\",\"常温\",\"去冰\",\"少冰\",\"多冰\"]', '2021-05-27 09:56:11', '2021-05-27 09:56:11', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397854652623007745, 1397854652581064706, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 09:58:15', '2021-05-27 09:58:15', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397854652635590658, 1397854652581064706, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:58:15', '2021-05-27 09:58:15', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397854865735593986, 1397854865672679425, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 09:59:06', '2021-05-27 09:59:06', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397855742303186946, 1397855742273826817, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 10:02:35', '2021-05-27 10:02:35', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397855906497605633, 1397855906468245506, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 10:03:14', '2021-05-27 10:03:14', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397856190573621250, 1397856190540066818, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 10:04:21', '2021-05-27 10:04:21', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397859056709316609, 1397859056684150785, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 10:15:45', '2021-05-27 10:15:45', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397859277837217794, 1397859277812051969, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 10:16:37', '2021-05-27 10:16:37', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397859487502086146, 1397859487476920321, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 10:17:27', '2021-05-27 10:17:27', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397859757061615618, 1397859757036449794, '甜味', '[\"无糖\",\"少糖\",\"半躺\",\"多糖\",\"全糖\"]', '2021-05-27 10:18:32', '2021-05-27 10:18:32', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397860242086735874, 1397860242057375745, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 10:20:27', '2021-05-27 10:20:27', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397860963918065665, 1397860963880316929, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 10:23:19', '2021-05-27 10:23:19', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397861135754506242, 1397861135733534722, '甜味', '[\"无糖\",\"少糖\",\"半躺\",\"多糖\",\"全糖\"]', '2021-05-27 10:24:00', '2021-05-27 10:24:00', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397861370035744769, 1397861370010578945, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-27 10:24:56', '2021-05-27 10:24:56', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397861683459305474, 1397861683434139649, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 10:26:11', '2021-05-27 10:26:11', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397861898467717121, 1397861898438356993, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 10:27:02', '2021-05-27 10:27:02', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397862198054268929, 1397862198033297410, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-27 10:28:14', '2021-05-27 10:28:14', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397862477835317250, 1397862477831122945, '辣度', '[\"不辣\",\"微辣\",\"中辣\"]', '2021-05-27 10:29:20', '2021-05-27 10:29:20', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398089545865015297, 1398089545676271617, '温度', '[\"热饮\",\"常温\",\"去冰\",\"少冰\",\"多冰\"]', '2021-05-28 01:31:38', '2021-05-28 01:31:38', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398089782323097601, 1398089782285348866, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:32:34', '2021-05-28 01:32:34', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398090003262255106, 1398090003228700673, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-28 01:33:27', '2021-05-28 01:33:27', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398090264554811394, 1398090264517062657, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-28 01:34:29', '2021-05-28 01:34:29', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398090455399837698, 1398090455324340225, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:35:14', '2021-05-28 01:35:14', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398090685449023490, 1398090685419663362, '温度', '[\"热饮\",\"常温\",\"去冰\",\"少冰\",\"多冰\"]', '2021-05-28 01:36:09', '2021-05-28 01:36:09', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398090825358422017, 1398090825329061889, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-28 01:36:43', '2021-05-28 01:36:43', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398091007051476993, 1398091007017922561, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:37:26', '2021-05-28 01:37:26', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398091296164851713, 1398091296131297281, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:38:35', '2021-05-28 01:38:35', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398091546531246081, 1398091546480914433, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2021-05-28 01:39:35', '2021-05-28 01:39:35', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398091729809747969, 1398091729788776450, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:40:18', '2021-05-28 01:40:18', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398091889499484161, 1398091889449152513, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:40:56', '2021-05-28 01:40:56', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398092095179763713, 1398092095142014978, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:41:45', '2021-05-28 01:41:45', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398092283877306370, 1398092283847946241, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:42:30', '2021-05-28 01:42:30', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398094018939236354, 1398094018893099009, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:49:24', '2021-05-28 01:49:24', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398094391494094850, 1398094391456346113, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-05-28 01:50:53', '2021-05-28 01:50:53', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1399574026165727233, 1399305325713600514, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]', '2021-06-01 03:50:25', '2021-06-01 03:50:25', 1399309715396669441, 1399309715396669441, 0);
INSERT INTO `dish_flavor` VALUES (1413389540592263169, 1413384757047271425, '温度', '[\"常温\",\"冷藏\"]', '2021-07-12 09:09:16', '2021-07-12 09:09:16', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1413389684020682754, 1413342036832100354, '温度', '[\"常温\",\"冷藏\"]', '2021-07-09 15:12:18', '2021-07-09 15:12:18', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1699429458568253442, 1699337412331343874, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2023-09-07 10:47:47', '2023-09-07 10:47:47', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1699442224175697921, 1699442224041480193, '甜味', '[\"无糖\",\"少糖\",\"半糖\",\"多糖\",\"全糖\"]', '2023-09-06 23:19:35', '2023-09-06 23:19:35', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1699442312151224321, 1699442312088309761, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]', '2023-09-06 23:19:56', '2023-09-06 23:19:56', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1699442385455075330, 1699442385387966466, '甜味', '[\"无糖\",\"少糖\",\"半糖\",\"多糖\",\"全糖\"]', '2023-09-06 23:20:13', '2023-09-06 23:20:13', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1699442385455075331, 1699442385387966466, '温度', '[\"热饮\",\"常温\",\"去冰\",\"少冰\",\"多冰\"]', '2023-09-06 23:20:13', '2023-09-06 23:20:13', 1, 1, 0);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '性别',
  `id_number` varchar(18) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '身份证号',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态 0:禁用，1:正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '员工信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, '管理员', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', 1, '2021-05-06 17:20:07', '2021-05-10 02:24:09', 1, 1);
INSERT INTO `employee` VALUES (1698531789570301954, 'jack', 'jack', 'e10adc3949ba59abbe56e057f20f883e', '13111111111', '1', '111111111111111111', 1, '2023-09-04 11:01:50', '2023-09-04 11:01:50', 1, 1);
INSERT INTO `employee` VALUES (1698540405337591810, 'lisi', 'lisi', 'e10adc3949ba59abbe56e057f20f883e', '13111111111', '1', '111111111111111111', 1, '2023-09-04 11:36:05', '2023-09-04 11:36:05', 1, 1);
INSERT INTO `employee` VALUES (1698561870090870785, 'test0', 'test0', 'e10adc3949ba59abbe56e057f20f883e', '13111111111', '1', '123456123456123456', 1, '2023-09-04 13:01:22', '2023-09-04 13:01:22', 1, 1);
INSERT INTO `employee` VALUES (1698561871126863873, 'test1', 'test1', 'e10adc3949ba59abbe56e057f20f883e', '13111111111', '1', '123456123456123456', 1, '2023-09-04 13:01:22', '2023-09-04 13:01:22', 1, 1);
INSERT INTO `employee` VALUES (1698561871126863874, 'test2', 'test2', 'e10adc3949ba59abbe56e057f20f883e', '13111111111', '1', '123456123456123456', 1, '2023-09-04 13:01:22', '2023-09-04 13:01:22', 1, 1);
INSERT INTO `employee` VALUES (1698561871126863875, 'test3', 'test3', 'e10adc3949ba59abbe56e057f20f883e', '13111111111', '1', '123456123456123456', 1, '2023-09-04 13:01:22', '2023-09-04 13:01:22', 1, 1);
INSERT INTO `employee` VALUES (1698561871126863876, 'test4', 'test4', 'e10adc3949ba59abbe56e057f20f883e', '13111111111', '1', '123456123456123456', 1, '2023-09-04 13:01:22', '2023-09-04 13:01:22', 1, 1);
INSERT INTO `employee` VALUES (1698561871193972737, 'test5', 'test5', 'e10adc3949ba59abbe56e057f20f883e', '13111111111', '1', '123456123456123456', 1, '2023-09-04 13:01:23', '2023-09-04 13:01:23', 1, 1);
INSERT INTO `employee` VALUES (1698561871193972738, 'test6', 'test6', 'e10adc3949ba59abbe56e057f20f883e', '13111111111', '1', '123456123456123456', 1, '2023-09-04 13:01:23', '2023-09-04 13:01:23', 1, 1);
INSERT INTO `employee` VALUES (1698561871193972739, 'test7', 'test7', 'e10adc3949ba59abbe56e057f20f883e', '13111111111', '1', '123456123456123456', 1, '2023-09-04 13:01:23', '2023-09-04 13:01:23', 1, 1);
INSERT INTO `employee` VALUES (1698561871193972740, 'tom', 'tom', 'e10adc3949ba59abbe56e057f20f883e', '13111111111', '0', '123456123456123456', 1, '2023-09-04 13:01:23', '2023-09-04 16:15:47', 1, 1);
INSERT INTO `employee` VALUES (1698561871193972741, 'test9', 'test9', 'e10adc3949ba59abbe56e057f20f883e', '13111111111', '1', '123456123456123456', 1, '2023-09-04 13:01:23', '2023-09-04 13:01:23', 1, 1);
INSERT INTO `employee` VALUES (1698706630634045441, 'king', 'king', 'e10adc3949ba59abbe56e057f20f883e', '13133333333', '0', '111222333444555666', 1, '2023-09-04 22:36:36', '2023-09-06 23:02:00', 1, 1);

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '名字',
  `image` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图片',
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `dish_id` bigint(20) NULL DEFAULT NULL COMMENT '菜品id',
  `setmeal_id` bigint(20) NULL DEFAULT NULL COMMENT '套餐id',
  `dish_flavor` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '口味',
  `number` int(11) NOT NULL DEFAULT 1 COMMENT '数量',
  `amount` decimal(10, 2) NOT NULL COMMENT '金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '订单明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES (1700499522327408642, '鱼香炒鸡蛋', '0f252364-a561-4e8d-8065-9a6797a6b1d3.jpg', 1700499438118367234, 1397854865672679425, NULL, '中辣', 1, 20.00);
INSERT INTO `order_detail` VALUES (1700499522327408643, '上汤焗龙虾', '5b8d2da3-3744-4bb3-acdc-329056b8259d.jpeg', 1700499438118367234, 1397862477831122945, NULL, '中辣', 1, 1088.00);
INSERT INTO `order_detail` VALUES (1700499522327408644, '麻辣水煮鱼', '1fdbfbf3-1d86-4b29-a3fc-46345852f2f8.jpg', 1700499438118367234, 1397854652581064706, NULL, '不要蒜,重辣', 1, 148.00);
INSERT INTO `order_detail` VALUES (1700513303904378882, '鱼香炒鸡蛋', '0f252364-a561-4e8d-8065-9a6797a6b1d3.jpg', 1700513303841464321, 1397854865672679425, NULL, '中辣', 1, 20.00);
INSERT INTO `order_detail` VALUES (1700513303904378883, '上汤焗龙虾', '5b8d2da3-3744-4bb3-acdc-329056b8259d.jpeg', 1700513303841464321, 1397862477831122945, NULL, '中辣', 1, 1088.00);
INSERT INTO `order_detail` VALUES (1700513303904378884, '老火靓汤', '583df4b7-a159-4cfc-9543-4f666120b25f.jpeg', 1700513303841464321, 1397862198033297410, NULL, '不要香菜', 1, 498.00);
INSERT INTO `order_detail` VALUES (1700513303904378885, '儿童套餐A计划', '0a3b3288-3446-4420-bbff-f263d0c02d8e.jpg', 1700513303841464321, NULL, 1415580119015145474, NULL, 3, 40.00);
INSERT INTO `order_detail` VALUES (1700513303904378886, '北冰洋', 'c99e0aab-3cb7-4eaa-80fd-f47d4ffea694.png', 1700513303841464321, 1413342036832100354, NULL, '冷藏', 1, 5.00);
INSERT INTO `order_detail` VALUES (1700513303904378887, '米饭', 'ee04a05a-1230-46b6-8ad5-1a95b140fff3.png', 1700513303841464321, 1413385247889891330, NULL, NULL, 3, 2.00);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `number` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '订单号',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '订单状态 1待付款，2待派送，3已派送，4已完成，5已取消',
  `user_id` bigint(20) NOT NULL COMMENT '下单用户',
  `address_book_id` bigint(20) NOT NULL COMMENT '地址id',
  `order_time` datetime NOT NULL COMMENT '下单时间',
  `checkout_time` datetime NOT NULL COMMENT '结账时间',
  `pay_method` int(11) NOT NULL DEFAULT 1 COMMENT '支付方式 1微信,2支付宝',
  `amount` decimal(10, 2) NOT NULL COMMENT '实收金额',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `consignee` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1700499438118367234, '1700499438118367234', 3, 1700052775037718530, 1700357799194345473, '2023-09-09 21:20:55', '2023-09-09 21:20:55', 1, 1256.00, '', '13222222222', '上海', '张三', '李');
INSERT INTO `orders` VALUES (1700513303841464321, '1700513303841464321', 2, 1700052775037718530, 1700357799194345473, '2023-09-09 22:15:40', '2023-09-09 22:15:40', 1, 1737.00, '餐具', '13222222222', '上海', '张三', '李');

-- ----------------------------
-- Table structure for setmeal
-- ----------------------------
DROP TABLE IF EXISTS `setmeal`;
CREATE TABLE `setmeal`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `category_id` bigint(20) NOT NULL COMMENT '菜品分类id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '套餐名称',
  `price` decimal(10, 2) NOT NULL COMMENT '套餐价格',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态 0:停用 1:启用',
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '编码',
  `description` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '描述信息',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图片',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `is_deleted` int(11) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_setmeal_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '套餐' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of setmeal
-- ----------------------------
INSERT INTO `setmeal` VALUES (1415580119015145474, 1413386191767674881, '儿童套餐A计划', 4000.00, 1, '', '', '0a3b3288-3446-4420-bbff-f263d0c02d8e.jpg', '2021-07-15 15:52:55', '2021-07-15 15:52:55', 1415576781934608386, 1415576781934608386, 0);
INSERT INTO `setmeal` VALUES (1699628584606011393, 1698624898807238658, '1111', 11100.00, 1, '', '1111', '7568b591-86fa-4efa-8312-0fd934d03e16.jpg', '2023-09-07 11:40:07', '2023-09-07 11:40:07', 1, 1, 0);
INSERT INTO `setmeal` VALUES (1699649989443735553, 1699649446604328961, '黄焖鸡', 2200.00, 1, '', '美味多汁', '7ad8a467-ecf1-4c68-92f1-ddb77520769c.jpg', '2023-09-07 13:05:10', '2023-09-07 13:05:10', 1, 1, 0);
INSERT INTO `setmeal` VALUES (1699650172843872257, 1699649494159347714, '333', 111100.00, 1, '', '除数', '00c91792-75e0-417e-bc69-82f808a24332.jpeg', '2023-09-07 13:05:54', '2023-09-07 13:05:54', 1, 1, 1);
INSERT INTO `setmeal` VALUES (1699650261792477185, 1413386191767674881, '222', 22200.00, 1, '', '', 'ce6fe585-b2bd-4626-8655-a02cd15f38c0.jpeg', '2023-09-07 13:06:15', '2023-09-07 13:06:15', 1, 1, 1);
INSERT INTO `setmeal` VALUES (1699650342713184257, 1413386191767674881, '4', 4400.00, 1, '', '', '6b29db09-6ac1-463c-b135-da90aa8e6808.jpeg', '2023-09-07 13:06:34', '2023-09-07 16:01:25', 1, 1, 1);
INSERT INTO `setmeal` VALUES (1699650443816882177, 1698624898807238658, '谁', 3300.00, 1, '', '', 'bda723fa-5aef-4e96-bf34-7e6b3189f2e8.jpeg', '2023-09-07 13:06:58', '2023-09-07 16:01:25', 1, 1, 1);
INSERT INTO `setmeal` VALUES (1699696340802093057, 1699649494159347714, 'test', 12300.00, 0, '', '222', '5a34f876-4741-4b81-aeba-5dac6fb732a7.jpg', '2023-09-07 16:09:21', '2023-09-07 22:55:44', 1, 1, 1);
INSERT INTO `setmeal` VALUES (1699696497643896834, 1699649494159347714, 'test2', 22200.00, 0, '', '111', '967e706f-7c63-4c15-9371-60f006ac70e3.jpg', '2023-09-07 16:09:59', '2023-09-07 22:55:01', 1, 1, 1);
INSERT INTO `setmeal` VALUES (1699696613842894849, 1699649446604328961, '哈哈哈', 12300.00, 1, '', '哈哈哈', 'de7af697-c4a8-4f8b-8f18-04b6c7a9c477.jpg', '2023-09-07 16:10:26', '2023-09-07 18:17:18', 1, 1, 0);
INSERT INTO `setmeal` VALUES (1699799261137035265, 1699649446604328961, '3333', 3300.00, 1, '', '333', '8ee31c01-4de0-4873-a130-8559f5615969.jpeg', '2023-09-07 22:58:19', '2023-09-07 22:58:19', 1, 1, 0);
INSERT INTO `setmeal` VALUES (1699799342267457538, 1699649494159347714, '4444', 3300.00, 0, '', '33', '3fda0d61-5526-4338-825a-2f134d83205b.jpg', '2023-09-07 22:58:39', '2023-09-07 22:59:02', 1, 1, 1);

-- ----------------------------
-- Table structure for setmeal_dish
-- ----------------------------
DROP TABLE IF EXISTS `setmeal_dish`;
CREATE TABLE `setmeal_dish`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `setmeal_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '套餐id ',
  `dish_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '菜品id',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜品名称 （冗余字段）',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '菜品原价（冗余字段）',
  `copies` int(11) NOT NULL COMMENT '份数',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  `is_deleted` int(11) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '套餐菜品关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of setmeal_dish
-- ----------------------------
INSERT INTO `setmeal_dish` VALUES (1415580119052894209, '1415580119015145474', '1397862198033297410', '老火靓汤', 49800.00, 1, 0, '2021-07-15 15:52:55', '2021-07-15 15:52:55', 1415576781934608386, 1415576781934608386, 0);
INSERT INTO `setmeal_dish` VALUES (1415580119061282817, '1415580119015145474', '1413342036832100354', '北冰洋', 500.00, 1, 0, '2021-07-15 15:52:55', '2021-07-15 15:52:55', 1415576781934608386, 1415576781934608386, 0);
INSERT INTO `setmeal_dish` VALUES (1415580119069671426, '1415580119015145474', '1413385247889891330', '米饭', 200.00, 1, 0, '2021-07-15 15:52:55', '2021-07-15 15:52:55', 1415576781934608386, 1415576781934608386, 0);
INSERT INTO `setmeal_dish` VALUES (1699629038882689025, '1699628584606011393', '1397854865672679425', '鱼香炒鸡蛋', 2000.00, 1, 0, '2023-09-07 11:41:55', '2023-09-07 11:41:55', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1699629038966575105, '1699628584606011393', '1397854652581064706', '麻辣水煮鱼', 14800.00, 1, 0, '2023-09-07 11:41:55', '2023-09-07 11:41:55', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1699629038966575106, '1699628584606011393', '1397853890262118402', '鱼香肉丝', 3800.00, 1, 0, '2023-09-07 11:41:55', '2023-09-07 11:41:55', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1699649989494067202, '1699649989443735553', '1397854865672679425', '鱼香炒鸡蛋', 2000.00, 1, 0, '2023-09-07 13:05:10', '2023-09-07 13:05:10', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1699649989561176066, '1699649989443735553', '1397852391150759938', '辣子鸡丁', 8800.00, 1, 0, '2023-09-07 13:05:10', '2023-09-07 13:05:10', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1699649989561176067, '1699649989443735553', '1397860578738352129', '白切鸡', 6600.00, 1, 0, '2023-09-07 13:05:10', '2023-09-07 13:05:10', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1699649989561176068, '1699649989443735553', '1413342036832100354', '北冰洋', 500.00, 1, 0, '2023-09-07 13:05:10', '2023-09-07 13:05:10', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1699649989561176069, '1699649989443735553', '1413385247889891330', '米饭', 200.00, 1, 0, '2023-09-07 13:05:10', '2023-09-07 13:05:10', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1699650172843872258, '1699650172843872257', '1397854865672679425', '鱼香炒鸡蛋', 2000.00, 2, 0, '2023-09-07 13:05:54', '2023-09-07 13:05:54', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1699650172843872259, '1699650172843872257', '1397853890262118402', '鱼香肉丝', 3800.00, 1, 0, '2023-09-07 13:05:54', '2023-09-07 13:05:54', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1699650261792477186, '1699650261792477185', '1397854652581064706', '麻辣水煮鱼', 14800.00, 1, 0, '2023-09-07 13:06:15', '2023-09-07 13:06:15', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1699650342713184258, '1699650342713184257', '1397854652581064706', '麻辣水煮鱼', 14800.00, 1, 0, '2023-09-07 13:06:34', '2023-09-07 13:06:34', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1699650443883991042, '1699650443816882177', '1397854865672679425', '鱼香炒鸡蛋', 2000.00, 1, 0, '2023-09-07 13:06:58', '2023-09-07 13:06:58', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1699696340881784833, '1699696340802093057', '1397854865672679425', '鱼香炒鸡蛋', 2000.00, 1, 0, '2023-09-07 16:09:21', '2023-09-07 16:09:21', 1, 1, 1);
INSERT INTO `setmeal_dish` VALUES (1699696340881784834, '1699696340802093057', '1397854652581064706', '麻辣水煮鱼', 14800.00, 1, 0, '2023-09-07 16:09:21', '2023-09-07 16:09:21', 1, 1, 1);
INSERT INTO `setmeal_dish` VALUES (1699696497706811394, '1699696497643896834', '1397854652581064706', '麻辣水煮鱼', 14800.00, 1, 0, '2023-09-07 16:09:59', '2023-09-07 16:09:59', 1, 1, 1);
INSERT INTO `setmeal_dish` VALUES (1699696497706811395, '1699696497643896834', '1397854865672679425', '鱼香炒鸡蛋', 2000.00, 1, 0, '2023-09-07 16:09:59', '2023-09-07 16:09:59', 1, 1, 1);
INSERT INTO `setmeal_dish` VALUES (1699696497706811396, '1699696497643896834', '1397853890262118402', '鱼香肉丝', 3800.00, 1, 0, '2023-09-07 16:09:59', '2023-09-07 16:09:59', 1, 1, 1);
INSERT INTO `setmeal_dish` VALUES (1699696497706811397, '1699696497643896834', '1397850392090947585', '组庵鱼翅', 4800.00, 1, 0, '2023-09-07 16:09:59', '2023-09-07 16:09:59', 1, 1, 1);
INSERT INTO `setmeal_dish` VALUES (1699696613905809410, '1699696613842894849', '1413342036832100354', '北冰洋', 500.00, 1, 0, '2023-09-07 16:10:26', '2023-09-07 16:10:26', 1, 1, 1);
INSERT INTO `setmeal_dish` VALUES (1699696613905809411, '1699696613842894849', '1397862198033297410', '老火靓汤', 49800.00, 1, 0, '2023-09-07 16:10:26', '2023-09-07 16:10:26', 1, 1, 1);
INSERT INTO `setmeal_dish` VALUES (1699728754169561090, '1699696613842894849', '1397854652581064706', '麻辣水煮鱼', 14800.00, 1, 0, '2023-09-07 18:18:09', '2023-09-07 18:18:09', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1699728754232475650, '1699696613842894849', '1397854865672679425', '鱼香炒鸡蛋', 2000.00, 1, 0, '2023-09-07 18:18:09', '2023-09-07 18:18:09', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1699728754232475651, '1699696613842894849', '1397853890262118402', '鱼香肉丝', 3800.00, 1, 0, '2023-09-07 18:18:09', '2023-09-07 18:18:09', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1699728754299584514, '1699696613842894849', '1397853709101740034', '蒜泥白肉', 9800.00, 1, 0, '2023-09-07 18:18:09', '2023-09-07 18:18:09', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1699728754299584515, '1699696613842894849', '1397853183287013378', '麻辣兔头', 19800.00, 1, 0, '2023-09-07 18:18:09', '2023-09-07 18:18:09', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1699728754366693377, '1699696613842894849', '1397852391150759938', '辣子鸡丁', 8800.00, 1, 0, '2023-09-07 18:18:09', '2023-09-07 18:18:09', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1699799261208338434, '1699799261137035265', '1397854652581064706', '麻辣水煮鱼', 14800.00, 1, 0, '2023-09-07 22:58:19', '2023-09-07 22:58:19', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1699799261208338435, '1699799261137035265', '1397853890262118402', '鱼香肉丝', 3800.00, 1, 0, '2023-09-07 22:58:19', '2023-09-07 22:58:19', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1699799261208338436, '1699799261137035265', '1397853183287013378', '麻辣兔头', 19800.00, 1, 0, '2023-09-07 22:58:19', '2023-09-07 22:58:19', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1699799342267457539, '1699799342267457538', '1397854652581064706', '麻辣水煮鱼', 14800.00, 1, 0, '2023-09-07 22:58:39', '2023-09-07 22:58:39', 1, 1, 1);
INSERT INTO `setmeal_dish` VALUES (1699799342267457540, '1699799342267457538', '1397854865672679425', '鱼香炒鸡蛋', 2000.00, 1, 0, '2023-09-07 22:58:39', '2023-09-07 22:58:39', 1, 1, 1);
INSERT INTO `setmeal_dish` VALUES (1699799342267457541, '1699799342267457538', '1397853709101740034', '蒜泥白肉', 9800.00, 1, 0, '2023-09-07 22:58:39', '2023-09-07 22:58:39', 1, 1, 1);

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '名称',
  `image` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图片',
  `user_id` bigint(20) NOT NULL COMMENT '主键',
  `dish_id` bigint(20) NULL DEFAULT NULL COMMENT '菜品id',
  `setmeal_id` bigint(20) NULL DEFAULT NULL COMMENT '套餐id',
  `dish_flavor` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '口味',
  `number` int(11) NOT NULL DEFAULT 1 COMMENT '数量',
  `amount` decimal(10, 2) NOT NULL COMMENT '金额',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '购物车' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '性别',
  `id_number` varchar(18) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证号',
  `avatar` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '头像',
  `status` int(11) NULL DEFAULT 0 COMMENT '状态 0:禁用，1:正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1700052775037718530, '张三', '13111111111', NULL, NULL, NULL, 1);
INSERT INTO `user` VALUES (1700058168455557121, NULL, '131111111111', NULL, NULL, NULL, 1);
INSERT INTO `user` VALUES (1700062833465344001, NULL, '13112345678', NULL, NULL, NULL, 1);

SET FOREIGN_KEY_CHECKS = 1;
