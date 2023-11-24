SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
     `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
     `username` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户名',
     `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '密码',
     `phone` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '手机号',
     `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户姓名',
     `sex` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户性别',
     `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
     `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;
-- ----------------------------
-- Records of user
-- ----------------------------
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `hello`.`user`(`id`, `username`, `password`, `phone`, `name`, `sex`, `create_time`, `age`) VALUES (1, 'lixiang', '1234567890', '13830567835', '李祥', '男', '2022-11-15 09:19:26', 18);