/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : 127.0.0.1:3306
Source Database       : jsp_petmall

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-01-31 12:18:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `cover` varchar(45) DEFAULT NULL,
  `image1` varchar(45) DEFAULT NULL,
  `image2` varchar(45) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `intro` varchar(300) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_type_id_idx` (`type_id`),
  CONSTRAINT `fk_type_id` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=194 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('181', '泰迪', '/picture/1572099738355.jpg', '/picture/1572099738356.jpg', '/picture/1572099738356.jpg', '1000', '是一个可爱的小狗', '1', '1');
INSERT INTO `goods` VALUES ('182', '藏獒', '/picture/1572100040028.jpg', '/picture/1572100040029.jpg', '/picture/1572100040029.jpg', '15000', '凶猛巨兽', '4', '1');
INSERT INTO `goods` VALUES ('183', '兔子', '/picture/1572100084526.jpg', '/picture/1572100084527.jpg', '/picture/1572100084528.png', '60', '可爱', '20', '3');
INSERT INTO `goods` VALUES ('184', '蜥蜴', '/picture/1572100133092.jpg', '/picture/1572100133092.jpg', '/picture/1572100133093.jpg', '3000', '挺硬的，不错！', '5', '3');
INSERT INTO `goods` VALUES ('185', '马', '/picture/1572100197486.jpg', '/picture/1572100197487.jpg', '/picture/1572100197488.jpg', '150000', '汗血宝马！日行千里不是梦！', '1', '4');
INSERT INTO `goods` VALUES ('186', '老虎', '/picture/1572100263546.jpg', '/picture/1572100263547.jpg', '/picture/1572100263548.jpg', '2000000', '为中东土豪特供', '2', '4');
INSERT INTO `goods` VALUES ('187', '波斯猫', '/picture/1572100298743.jpg', '/picture/1572100298743.jpg', '/picture/1572100298744.jpg', '8000', '高贵！', '3', '2');
INSERT INTO `goods` VALUES ('188', '无毛猫', '/picture/1572100372252.jpg', '/picture/1572100372253.jpg', '/picture/1572100372253.jpg', '12000', '洁癖猫毛过敏者！！必要', '4', '2');
INSERT INTO `goods` VALUES ('189', '胡萝卜', '/picture/1572100519248.jpg', '/picture/1572100519249.jpg', '/picture/1572100519249.jpg', '5', '兔子的最爱', '100', '5');
INSERT INTO `goods` VALUES ('190', '猫罐头、猫粮', '/picture/1572100570001.jpg', '/picture/1572100570001.jpg', '/picture/1572100570001.jpg', '20', '好吃不贵', '100', '5');
INSERT INTO `goods` VALUES ('191', '马鞍', '/picture/1572100619889.jpg', '/picture/1572100619889.jpg', '/picture/1572100619890.jpg', '200', '带上马鞍骑马对身体好！', '12', '5');
INSERT INTO `goods` VALUES ('192', '牵引绳', '/picture/1572100669669.jpg', '/picture/1572100669669.jpg', '/picture/1572100669669.jpg', '13', '防止走丢神器', '30', '5');
INSERT INTO `goods` VALUES ('193', '猫窝', '/picture/1572100716813.jpg', '/picture/1572100716813.jpg', '/picture/1572100716813.jpg', '20', '给猫猫一个温暖的家', '20', '5');

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `total` float DEFAULT NULL,
  `amount` int(6) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `paytype` tinyint(1) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `datetime` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_id_idx` (`user_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('49', '1000', '1', '2', '3', '张三', '12345678901', '江苏省南京市', '2020-01-31 09:58:07', '33');

-- ----------------------------
-- Table structure for `orderitem`
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` float DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `goods_id` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_id_idx` (`order_id`),
  KEY `fk_orderitem_goods_id_idx` (`goods_id`),
  CONSTRAINT `fk_order_id` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_orderitem_goods_id` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('45', '1000', '1', '181', '49');

-- ----------------------------
-- Table structure for `recommend`
-- ----------------------------
DROP TABLE IF EXISTS `recommend`;
CREATE TABLE `recommend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` tinyint(1) DEFAULT NULL,
  `goods_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_goods_id_idx` (`goods_id`),
  CONSTRAINT `fk_goods_id` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recommend
-- ----------------------------
INSERT INTO `recommend` VALUES ('29', '1', '181');
INSERT INTO `recommend` VALUES ('30', '2', '181');
INSERT INTO `recommend` VALUES ('31', '3', '181');
INSERT INTO `recommend` VALUES ('32', '2', '183');
INSERT INTO `recommend` VALUES ('33', '3', '183');
INSERT INTO `recommend` VALUES ('34', '1', '183');
INSERT INTO `recommend` VALUES ('35', '1', '184');
INSERT INTO `recommend` VALUES ('36', '3', '184');
INSERT INTO `recommend` VALUES ('37', '2', '185');
INSERT INTO `recommend` VALUES ('38', '1', '185');

-- ----------------------------
-- Table structure for `type`
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('1', '宠物狗系列');
INSERT INTO `type` VALUES ('2', '宠物猫系列');
INSERT INTO `type` VALUES ('3', '爬行类宠物');
INSERT INTO `type` VALUES ('4', '奇珍异兽');
INSERT INTO `type` VALUES ('5', '宠物用品');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `isadmin` bit(1) DEFAULT NULL,
  `isvalidate` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('6', 'admin', '1347845688@qq.com', 'admin', '魏志林', '13022502404', '北京市西城区', '', '');
INSERT INTO `user` VALUES ('24', '王磊', '1357845688@qq.com', 'wl1997', '王磊', '13022502404', '内蒙古自治区呼和浩特市赛罕区大学西路内蒙古大学', '', '');
INSERT INTO `user` VALUES ('25', '1', '1', '1', '魏志林', '13022502404', '内蒙古自治区呼和浩特市赛罕区大学西路内蒙古大学', null, null);
INSERT INTO `user` VALUES ('32', 'xiaoli', '12456@qq.com', '123456', '可爱精灵', '13022502404', '可爱精灵大厦', '', '');
INSERT INTO `user` VALUES ('33', 'user', 'test@126.com', '123456', '张三', '12345678901', '江苏省南京市', '', '');
