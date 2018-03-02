SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `uid` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL DEFAULT '' COMMENT 'email',
  `gender` char(1) NOT NULL DEFAULT '0' COMMENT '性别',
  `group_id` mediumint(8) NOT NULL DEFAULT '1' COMMENT '会员等级',
  `icon` varchar(255) NOT NULL DEFAULT '' COMMENT '头像',
  `last_login_ip` varchar(20) NOT NULL,
  `last_login_time` bigint(20) NOT NULL DEFAULT '0',
  `oicq` varchar(20) NOT NULL,
  `password` varchar(64) NOT NULL DEFAULT '',
  `register_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '注册IP',
  `reg_time` bigint(20) NOT NULL,
  `signature` varchar(64) NOT NULL DEFAULT '',
  `source` varchar(20) NOT NULL,
  `status` char(1) NOT NULL,
  `user_name` varchar(100) NOT NULL,
  `group_name` varchar(255) NOT NULL DEFAULT '',
  `coin` bigint(8) NOT NULL,
  `expire_time` bigint(10) NOT NULL DEFAULT '0' COMMENT '到期时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for member_deposit
-- ----------------------------
DROP TABLE IF EXISTS `member_deposit`;
CREATE TABLE `member_deposit` (
  `uid` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `balance` int(8) NOT NULL,
  `create_time` bigint(20) NOT NULL,
  `credit` int(8) NOT NULL,
  `debit` int(8) NOT NULL,
  `member_id` mediumint(8) NOT NULL,
  `type` char(1) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for member_group
-- ----------------------------
DROP TABLE IF EXISTS `member_group`;
CREATE TABLE `member_group` (
  `uid` mediumint(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户组id',
  `group_name` varchar(20) NOT NULL DEFAULT '' COMMENT '用户组名称',
  `group_memo` varchar(512) NOT NULL DEFAULT '' COMMENT '用户组简介',
  `present_coin` bigint(12) NOT NULL DEFAULT '0' COMMENT '购买赠送金币',
  `need_money` int(8) NOT NULL DEFAULT '0' COMMENT '购买需要的钱',
  `expire_day` int(8) NOT NULL DEFAULT '0' COMMENT '到期天数',
  `icon` varchar(128) NOT NULL DEFAULT 'none.gif' COMMENT '图标',
  `sort` int(8) NOT NULL DEFAULT '0' COMMENT '排序码',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 0：正常 1：限制登录',
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 普通会员，1 收费会员',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='成员等级表';

-- ----------------------------
-- Records of member_group
-- ----------------------------
INSERT INTO `member_group` VALUES ('1', '普通会员', '限制观看', '2', '10000', '0', 'none.gif', '1', '0', '0');
INSERT INTO `member_group` VALUES ('2', '一月超级会员', '一月无限制观看', '100', '30', '30', 'none.gif', '2', '0', '1');
INSERT INTO `member_group` VALUES ('3', '一季度超级会员', '一季度无限制观看', '100', '79', '120', 'none.gif', '3', '0', '1');
INSERT INTO `member_group` VALUES ('4', '半年超级会员', '半年无限制观看', '500', '149', '180', 'none.gif', '4', '0', '1');
INSERT INTO `member_group` VALUES ('5', '一年超级会员', '一年无限制观看', '1000', '199', '360', 'none.gif', '5', '0', '1');

-- ----------------------------
-- Table structure for menu_inf
-- ----------------------------
DROP TABLE IF EXISTS `menu_inf`;
CREATE TABLE `menu_inf` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_nm` varchar(255) NOT NULL DEFAULT '',
  `menu_url` varchar(255) NOT NULL DEFAULT '' COMMENT '菜单URL',
  `menu_parent` bigint(255) NOT NULL,
  `menu_sort` varchar(10) NOT NULL DEFAULT '' COMMENT '排序',
  `row_crt_usr` varchar(255) NOT NULL DEFAULT '',
  `row_crt_ts` varchar(255) NOT NULL DEFAULT '',
  `rec_upd_usr` varchar(255) NOT NULL DEFAULT '',
  `rec_upd_ts` varchar(255) NOT NULL DEFAULT '',
  `menu_tp` int(255) NOT NULL DEFAULT '0',
  `style` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu_inf
-- ----------------------------
INSERT INTO `menu_inf` VALUES ('1', '首页', '../index.html', '0', '1', 'admin', '2147483647', 'admin', '2147483647', '1', '1');
INSERT INTO `menu_inf` VALUES ('2', '亚洲', '../video/list?menu=2', '0', '2', 'admin', '2147483647', 'admin', '2147483647', '2', '1');
INSERT INTO `menu_inf` VALUES ('3', '欧美', '../video/list?menu=3', '0', '3', 'admin', '2147483647', 'admin', '2147483647', '3', '1');
INSERT INTO `menu_inf` VALUES ('4', '自拍', '../video/list?menu=4', '0', '4', 'admin', '2147483647', 'admin', '2147483647', '4', '1');
INSERT INTO `menu_inf` VALUES ('5', '偷拍', '../video/list?menu=5', '0', '5', 'admin', '2147483647', 'admin', '2147483647', '5', '1');
INSERT INTO `menu_inf` VALUES ('6', '门事件', '../video/list?menu=6', '0', '6', 'admin', '2147483647', 'admin', '2147483647', '6', '1');
INSERT INTO `menu_inf` VALUES ('7', '福利', '../video/list?menu=7', '0', '7', 'admin', '2147483647', 'admin', '2147483647', '7', '1');
INSERT INTO `menu_inf` VALUES ('8', '三级', '../video/list?menu=8', '0', '8', 'admin', '2147483647', 'admin', '2147483647', '8', '1');
INSERT INTO `menu_inf` VALUES ('9', '巨乳', '../video/list?menu=9', '0', '9', 'admin', '2147483647', 'admin', '2147483647', '9', '1');
INSERT INTO `menu_inf` VALUES ('10', '巨臀', '../video/list?menu=10', '0', '10', 'admin', '2147483647', 'admin', '2147483647', '10', '1');

-- ----------------------------
-- Table structure for menu_video_relation
-- ----------------------------
DROP TABLE IF EXISTS `menu_video_relation`;
CREATE TABLE `menu_video_relation` (
  `mvr_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_id` bigint(20) NOT NULL DEFAULT '0',
  `vid` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`mvr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=370 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `uid` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `sn` varchar(32) NOT NULL DEFAULT '' COMMENT '编码',
  `group_id` mediumint(8) NOT NULL DEFAULT '0' COMMENT '会员等级',
  `group_name` varchar(20) NOT NULL DEFAULT '' COMMENT '会员等级',
  `amount` int(11) NOT NULL DEFAULT '0' COMMENT '交易金额',
  `payment_status` char(1) NOT NULL DEFAULT '0' COMMENT '付款状态',
  `payment_time` bigint(20) DEFAULT NULL COMMENT '付款时间',
  `payment_type` varchar(20) NOT NULL DEFAULT '0' COMMENT '付款类型',
  `member_id` mediumint(8) NOT NULL DEFAULT '0' COMMENT '会员类型',
  `create_time` bigint(8) NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for plugin_config
-- ----------------------------
DROP TABLE IF EXISTS `plugin_config`;
CREATE TABLE `plugin_config` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` bigint(20) NOT NULL,
  `modify_date` bigint(20) NOT NULL,
  `version` varchar(20) NOT NULL,
  `orders` int(11) DEFAULT NULL,
  `attributes` longtext,
  `is_enabled` char(1) NOT NULL,
  `plugin_id` varchar(255) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of plugin_config
-- ----------------------------
INSERT INTO `plugin_config` VALUES ('1', '1509938065', '1', '1.0.0.3', '1', '{\"merchantAcc\":\"210001110100250\",\"fee\":\"1\",\"platformId\":\"MerchTest\",\"feeType\":\"scale\",\"key\":\"22c41d776c24deddca95b1709a88f04b\"}', '1', 'mobaopayPaymentPlugin');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL DEFAULT '',
  `url` varchar(255) NOT NULL DEFAULT '',
  `perms` varchar(255) NOT NULL DEFAULT '',
  `type` int(10) NOT NULL DEFAULT '0',
  `icon` varchar(255) NOT NULL DEFAULT '',
  `order_num` int(11) NOT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', '', '', '0', '', '1');
INSERT INTO `sys_menu` VALUES ('2', '1', '菜单管理', 'sys/menu.html', 'sys:menu:list,sys:menu:select,sys:menu:perms,sys:menu:info,sys:menu:save,sys:menu:update,sys:menu:delete', '1', '', '3');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'sys/role.html', 'sys:role:list,sys:role:select,sys:role:info,sys:role:save,sys:role:update,sys:role:delete', '1', '', '2');
INSERT INTO `sys_menu` VALUES ('4', '1', '用户管理', 'sys/user.html', 'sys:user:info,sys:user:save,sys:user:update,sys:user:delete', '1', '', '1');
INSERT INTO `sys_menu` VALUES ('5', '0', '视频管理', '', '', '0', '', '1');
INSERT INTO `sys_menu` VALUES ('6', '5', '视频列表', 'video/video.html', 'video:topVideoList,video:info,video:save,video:update,video:delete,video:create,video:create,video:recommend', '1', '', '1');
INSERT INTO `sys_menu` VALUES ('7', '5', '最热视频', 'video/topVideo.html', 'video:topVideoList,video:info,video:save,video:update,video:delete,video:create,video:create', '1', '', '2');
INSERT INTO `sys_menu` VALUES ('8', '5', '推荐视频', 'video/recommendsVideo.html', 'video:topVideoList,video:info,video:save,video:update,video:delete,video:create,video:create', '1', '', '3');
INSERT INTO `sys_menu` VALUES ('9', '5', '最新视频', 'video/newsVideo.html', 'video:topVideoList,video:info,video:save,video:update,video:delete,video:create,video:create', '1', '', '4');
INSERT INTO `sys_menu` VALUES ('10', '0', '会员管理', '', '', '0', '', '3');
INSERT INTO `sys_menu` VALUES ('11', '10', '会员等级', 'member/group.html', 'memberGroup:save,memberGroup:update,memberGroup:info', '1', '', '1');
INSERT INTO `sys_menu` VALUES ('12', '10', '会员列表', 'member/member.html', '', '1', '', '2');
INSERT INTO `sys_menu` VALUES ('13', '1', '支付插件', './payment/paymentConfigList.html', 'sys:pluginConfig:settings', '1', '', '4');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL DEFAULT '',
  `remark` varchar(255) NOT NULL DEFAULT '',
  `create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', '备注', '1509607713');
INSERT INTO `sys_role` VALUES ('3', '视频管理员', '视频', '1509607713');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` mediumint(9) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` mediumint(9) NOT NULL,
  `menu_id` mediumint(9) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('14', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('15', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('16', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('17', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('18', '1', '5');
INSERT INTO `sys_role_menu` VALUES ('19', '1', '6');
INSERT INTO `sys_role_menu` VALUES ('20', '1', '7');
INSERT INTO `sys_role_menu` VALUES ('21', '1', '8');
INSERT INTO `sys_role_menu` VALUES ('22', '1', '9');
INSERT INTO `sys_role_menu` VALUES ('23', '1', '10');
INSERT INTO `sys_role_menu` VALUES ('24', '1', '11');
INSERT INTO `sys_role_menu` VALUES ('25', '1', '12');
INSERT INTO `sys_role_menu` VALUES ('26', '1', '13');
INSERT INTO `sys_role_menu` VALUES ('27', '2', '5');
INSERT INTO `sys_role_menu` VALUES ('28', '2', '6');
INSERT INTO `sys_role_menu` VALUES ('29', '2', '7');
INSERT INTO `sys_role_menu` VALUES ('30', '2', '8');
INSERT INTO `sys_role_menu` VALUES ('31', '2', '9');
INSERT INTO `sys_role_menu` VALUES ('32', '3', '5');
INSERT INTO `sys_role_menu` VALUES ('33', '3', '6');
INSERT INTO `sys_role_menu` VALUES ('34', '3', '7');
INSERT INTO `sys_role_menu` VALUES ('35', '3', '8');
INSERT INTO `sys_role_menu` VALUES ('36', '3', '9');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL DEFAULT '',
  `password` varchar(64) NOT NULL DEFAULT '',
  `email` varchar(200) NOT NULL DEFAULT '',
  `mobile` varchar(30) NOT NULL DEFAULT '',
  `status` char(1) NOT NULL,
  `create_time` int(11) NOT NULL DEFAULT '0',
  `last_login_time` int(11) NOT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'TanMin', '7681b79275c1f3e5c82b579f5fe6fa9974fca815b6780bbb65fa34b532ebf16c', '223', '123', '1', '1514256410', '1514259410');

-- ----------------------------
-- Table structure for videos
-- ----------------------------
DROP TABLE IF EXISTS `videos`;
CREATE TABLE `videos` (
  `vid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '视频ID',
  `video_name` varchar(255) DEFAULT '' COMMENT '电影名称',
  `tag` varchar(32) DEFAULT '无' COMMENT '标签 OL 亚洲 欧美等',
  `class_nm` varchar(32) DEFAULT '电影' COMMENT '电影 电视剧 福利等',
  `region` varchar(32) DEFAULT '无' COMMENT '地区 大陆 香港 日韩 欧美',
  `play_url` text COMMENT '播放地址',
  `down_url` text COMMENT '下载地址',
  `pics` varchar(255) DEFAULT '' COMMENT '缩略图 多个以,隔开',
  `play_time` int(10) DEFAULT '0' COMMENT '播放时间',
  `director` varchar(255) DEFAULT '' COMMENT '导演',
  `performer` varchar(255) DEFAULT '' COMMENT '演员',
  `source` varchar(255) DEFAULT '' COMMENT '来源',
  `year` int(4) DEFAULT '2017' COMMENT '年代',
  `coin` int(10) DEFAULT '1' COMMENT '需要金币',
  `language` varchar(32) DEFAULT '' COMMENT '语言',
  `keywords` varchar(255) DEFAULT '' COMMENT '关键字',
  `introduction` varchar(512) DEFAULT '' COMMENT '简介',
  `description` text COMMENT '描述',
  `sort` int(8) NOT NULL DEFAULT '0' COMMENT '排序码',
  `create_user` int(12) NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_time` int(10) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `update_user` int(12) NOT NULL DEFAULT '0' COMMENT '更新人',
  `update_time` int(10) NOT NULL DEFAULT '0' COMMENT '更新时间',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 0：正常 1：下架',
  PRIMARY KEY (`vid`)
) ENGINE=InnoDB AUTO_INCREMENT=926015983192113153 DEFAULT CHARSET=utf8 COMMENT='视频表';