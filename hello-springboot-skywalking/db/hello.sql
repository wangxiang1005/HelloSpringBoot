-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
    `id` int(11) NOT NULL,
    `title` varchar(200) NOT NULL COMMENT '商品名字',
    `price` int(11) NOT NULL,
    `count` int(11) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('1', '华为P40', '8000', '10');
INSERT INTO `item` VALUES ('2', '荣耀30S', '3500', '100');


-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
     `id` int(11) NOT NULL AUTO_INCREMENT,
     `item_id` int(11) NOT NULL COMMENT '商品ID',
     `count` int(11) NOT NULL COMMENT '购买数量',
     PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;