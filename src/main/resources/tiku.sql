
SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', '', '', '0', '', '1');
INSERT INTO `sys_menu` VALUES ('2', '1', '菜单管理', 'sys/menu.html', 'sys:menu:list,sys:menu:select,sys:menu:perms,sys:menu:info,sys:menu:save,sys:menu:update,sys:menu:delete', '1', '', '3');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'sys/role.html', 'sys:role:list,sys:role:select,sys:role:info,sys:role:save,sys:role:update,sys:role:delete', '1', '', '2');
INSERT INTO `sys_menu` VALUES ('4', '1', '用户管理', 'sys/user.html', 'sys:user:info,sys:user:save,sys:user:update,sys:user:delete', '1', '', '1');
INSERT INTO `sys_menu` VALUES ('5', '0', '内容管理', '', '', '0', '', '1');
INSERT INTO `sys_menu` VALUES ('6', '5', '考试类型', 'tiku/subject/list.html', 'tiku:subject:list,tiku:subject:create,tiku:subject:edit,tiku:subject:delete', '1', '', '1');
INSERT INTO `sys_menu` VALUES ('7', '5', '科目管理', 'tiku/course/list.html', 'tiku:course:save,tiku:course:update,tiku:course:delete,tiku:course:list', '1', '', '2');
INSERT INTO `sys_menu` VALUES ('8', '5', '菜单管理', 'tiku/menu/list.html', 'tiku:menu:list,tiku:menu:save,tiku:menu:update,tiku:menu:delete', '1', '', '3');
INSERT INTO `sys_menu` VALUES ('9', '5', '试卷管理', 'tiku/paper/list.html', 'tiku:paper:list,tiku:paper:save,tiku:paper:update,tiku:paper:delete', '1', '', '4');
INSERT INTO `sys_menu` VALUES ('11', '5', '试题管理', 'tiku/question/list.html', 'tiku:question:list,tiku:question:create,tiku:question:edit,tiku:question:delete', '1', '', '5');

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
INSERT INTO `sys_user` VALUES ('1', 'admin', '7681b79275c1f3e5c82b579f5fe6fa9974fca815b6780bbb65fa34b532ebf16c', '223', '123', '1', '1514256410', '1523502537');

-- ----------------------------
-- Table structure for tiku_ad
-- ----------------------------
DROP TABLE IF EXISTS `tiku_ad`;
CREATE TABLE `tiku_ad` (
  `uid` bigint(20) NOT NULL COMMENT 'ID_',
  `business_key` varchar(20) NOT NULL DEFAULT '' COMMENT '业务键',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '标题',
  `ad_info` text NOT NULL COMMENT '路径',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tiku_ad
-- ----------------------------
INSERT INTO `tiku_ad` VALUES ('1', 'indexBanner', '首页轮播', '[{bgimage:\"https://edu-wenku.bdimg.com/v1/na/NAZXJ/%E9%A2%98%E5%BA%93%E9%A6%96%E9%A1%B5banner-1510132588487.jpg\",type:1,index:2,href:\"http://localhost\"},{bgimage:\"https://edu-wenku.bdimg.com/v1/pc/hushuang01/1920x350-1493812791774.jpg\",type:1,index:1,href:\"http://localhost\"},{bgimage:\"https://edu-wenku.bdimg.com/v1/na/NAZXJ/%E9%A2%98%E5%BA%93%E9%A6%96%E9%A1%B5banner-1510132588487.jpg\",type:1,index:3,href:\"http://localhost\"}]');
INSERT INTO `tiku_ad` VALUES ('2', 'subject_13', '高考轮播', '[{bgimage:\"https://edu-wenku.bdimg.com/v1/na/NAZXJ/%E9%A2%98%E5%BA%93%E9%A6%96%E9%A1%B5banner-1510132588487.jpg\",type:1,index:2,href:\"http://localhost\"},{bgimage:\"https://edu-wenku.bdimg.com/v1/pc/hushuang01/1920x350-1493812791774.jpg\",type:1,index:1,href:\"http://localhost\"}]');

-- ----------------------------
-- Table structure for tiku_area
-- ----------------------------
DROP TABLE IF EXISTS `tiku_area`;
CREATE TABLE `tiku_area` (
  `uid` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '地区',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tiku_area
-- ----------------------------
INSERT INTO `tiku_area` VALUES ('1', '北京');
INSERT INTO `tiku_area` VALUES ('2', '上海');
INSERT INTO `tiku_area` VALUES ('3', '重庆');
INSERT INTO `tiku_area` VALUES ('4', '天津');
INSERT INTO `tiku_area` VALUES ('5', '山东');
INSERT INTO `tiku_area` VALUES ('6', '河南');
INSERT INTO `tiku_area` VALUES ('7', '湖北');
INSERT INTO `tiku_area` VALUES ('8', '江苏');
INSERT INTO `tiku_area` VALUES ('9', '浙江');
INSERT INTO `tiku_area` VALUES ('10', '山西');
INSERT INTO `tiku_area` VALUES ('11', '福建');
INSERT INTO `tiku_area` VALUES ('12', '安徽');
INSERT INTO `tiku_area` VALUES ('13', '吉林');
INSERT INTO `tiku_area` VALUES ('14', '内蒙古');
INSERT INTO `tiku_area` VALUES ('15', '宁夏');
INSERT INTO `tiku_area` VALUES ('16', '新疆');
INSERT INTO `tiku_area` VALUES ('17', '广西');
INSERT INTO `tiku_area` VALUES ('18', '辽宁');
INSERT INTO `tiku_area` VALUES ('19', '黑龙江');
INSERT INTO `tiku_area` VALUES ('20', '陕西');
INSERT INTO `tiku_area` VALUES ('21', '江西');
INSERT INTO `tiku_area` VALUES ('22', '广东');
INSERT INTO `tiku_area` VALUES ('23', '湖南');
INSERT INTO `tiku_area` VALUES ('24', '海南');
INSERT INTO `tiku_area` VALUES ('25', '云南');
INSERT INTO `tiku_area` VALUES ('26', '四川');
INSERT INTO `tiku_area` VALUES ('27', '青海');
INSERT INTO `tiku_area` VALUES ('28', '甘肃');
INSERT INTO `tiku_area` VALUES ('29', '河北');
INSERT INTO `tiku_area` VALUES ('30', '西藏');
INSERT INTO `tiku_area` VALUES ('31', '贵州');

-- ----------------------------
-- Table structure for tiku_chapter
-- ----------------------------
DROP TABLE IF EXISTS `tiku_chapter`;
CREATE TABLE `tiku_chapter` (
  `uid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `course_id` bigint(20) NOT NULL COMMENT '科目ID',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父节点(-1:根目录，0:尾目录)',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '章节名称',
  `question_num` int(20) NOT NULL DEFAULT '0' COMMENT '问题数量',
  `point_num` int(20) NOT NULL DEFAULT '0' COMMENT '知识点',
  `orders` int(20) NOT NULL DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tiku_chapter
-- ----------------------------
INSERT INTO `tiku_chapter` VALUES ('68', '2', '-1', '理科数学', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('69', '2', '68', '第一章 集合与常用逻辑用语', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('70', '2', '69', '1 集合的概念及运算', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('71', '2', '70', '1.1 集合的含义', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('72', '2', '70', '1.2 元素与集合关系的判断', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('73', '2', '70', '1.3 集合的确定性、互异性、无序性', '0', '0', '0');

-- ----------------------------
-- Table structure for tiku_course
-- ----------------------------
DROP TABLE IF EXISTS `tiku_course`;
CREATE TABLE `tiku_course` (
  `uid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `subject_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '类型ID',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '科目',
  `orders` int(10) NOT NULL DEFAULT '0' COMMENT '排序',
  `question_num` int(10) NOT NULL DEFAULT '0' COMMENT '题目数量',
  `point_num` int(10) NOT NULL DEFAULT '0' COMMENT '知识点数量',
  `icon` varchar(100) NOT NULL DEFAULT '' COMMENT '图标',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tiku_course
-- ----------------------------
INSERT INTO `tiku_course` VALUES ('1', '6', '综合能力（中级）', '1', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('2', '13', '理科数学', '1', '0', '0', 'icon-like');
INSERT INTO `tiku_course` VALUES ('3', '13', '文科数学', '2', '0', '0', 'icon-wenke');
INSERT INTO `tiku_course` VALUES ('4', '13', '物理', '3', '0', '0', 'icon-wuli');
INSERT INTO `tiku_course` VALUES ('5', '13', '化学', '4', '0', '0', 'icon-huaxue');
INSERT INTO `tiku_course` VALUES ('6', '13', '生物', '5', '0', '0', 'icon-shengwu');
INSERT INTO `tiku_course` VALUES ('7', '13', '政治', '6', '0', '0', 'icon-zhengzhi');
INSERT INTO `tiku_course` VALUES ('8', '13', '历史', '7', '0', '0', 'icon-lishi');
INSERT INTO `tiku_course` VALUES ('9', '13', '地理', '8', '0', '0', 'icon-dili');
INSERT INTO `tiku_course` VALUES ('10', '7', '企业管理知识', '1', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('11', '8', '中学教育心理学', '1', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('12', '9', '社会工作综合能力（初级）', '1', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('13', '10', '建筑工程', '1', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('14', '10', '水利水电工程', '2', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('15', '10', '建设工程项目管理', '3', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('16', '11', '建设工程施工管理', '1', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('17', '12', '考研政治', '1', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('18', '14', '会计基础', '1', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('19', '15', '中级经济法', '1', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('20', '15', '中级会计实务 ', '2', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('21', '15', '中级财务管理', '3', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('22', '16', '会计', '1', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('23', '16', '财务成本管理', '2', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('24', '17', '专业知识与实务', '1', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('25', '17', '中级经济基础', '2', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('26', '18', '经济法基础', '1', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('27', '18', '初级会计实务', '2', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('28', '19', '临床执业医师', '1', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('29', '20', '临床助理医师', '1', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('30', '21', '中药学综合知识与技能', '1', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('31', '21', '中药学专业知识一', '2', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('32', '21', '中药学专业知识二', '3', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('33', '22', '药事管理与法规', '1', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('34', '22', '药学综合知识与技能', '2', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('35', '22', '药学专业知识一', '3', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('36', '22', '药学专业知识二', '4', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('37', '23', '专业实务', '1', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('38', '25', '计算机四级操作系统', '1', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('39', '27', '公安基础知识', '1', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('40', '28', '行测', '1', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('41', '29', '行测', '1', '0', '0', '');

-- ----------------------------
-- Table structure for tiku_member
-- ----------------------------
DROP TABLE IF EXISTS `tiku_member`;
CREATE TABLE `tiku_member` (
  `uid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(100) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码',
  `avatar` varchar(255) NOT NULL DEFAULT '' COMMENT '头像',
  `login_failure_count` int(5) NOT NULL DEFAULT '0' COMMENT '登录错误次数',
  `mobile` varchar(20) NOT NULL DEFAULT '' COMMENT '移动手机号',
  `email` varchar(200) NOT NULL DEFAULT '' COMMENT '邮箱',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tiku_member
-- ----------------------------
INSERT INTO `tiku_member` VALUES ('1', 'tamguo', '14615b0758870dda28961716384d76765422ef9ca6394656eb0c19c280ed46d7', 'https://gss0.bdstatic.com/6LZ1dD3d1sgCo2Kml5_Y_D3/sys/portrait/item/01b9536d696c65746f63616e6479e71e', '0', '15618910786', 'candy@aliyun.com');
INSERT INTO `tiku_member` VALUES ('2', 'tanguo', 'd51a70df396d0d6c0aca3cd4e08883fc2b586e2121ca3e65128180961ae092d7', 'https://gss0.bdstatic.com/6LZ1dD3d1sgCo2Kml5_Y_D3/sys/portrait/item/01b9536d696c65746f63616e6479e71e', '0', '15618910552', 'candy.tam@aliyun.com');
INSERT INTO `tiku_member` VALUES ('3', 'chenfeida', '14615b0758870dda28961716384d76765422ef9ca6394656eb0c19c280ed46d7', 'https://gss0.bdstatic.com/6LZ1dD3d1sgCo2Kml5_Y_D3/sys/portrait/item/01b9536d696c65746f63616e6479e71e', '0', '15641523512', 'smiletocandy@qq.com');
INSERT INTO `tiku_member` VALUES ('4', 'chenhengtong', 'd51a70df396d0d6c0aca3cd4e08883fc2b586e2121ca3e65128180961ae092d7', 'https://gss0.bdstatic.com/6LZ1dD3d1sgCo2Kml5_Y_D3/sys/portrait/item/01b9536d696c65746f63616e6479e71e', '0', '15618915525', '');
INSERT INTO `tiku_member` VALUES ('5', 'wumi', 'd51a70df396d0d6c0aca3cd4e08883fc2b586e2121ca3e65128180961ae092d7', 'https://gss0.bdstatic.com/6LZ1dD3d1sgCo2Kml5_Y_D3/sys/portrait/item/01b9536d696c65746f63616e6479e71e', '0', '15618914542', '');
INSERT INTO `tiku_member` VALUES ('6', 'zengmin', 'd51a70df396d0d6c0aca3cd4e08883fc2b586e2121ca3e65128180961ae092d7', 'https://gss0.bdstatic.com/6LZ1dD3d1sgCo2Kml5_Y_D3/sys/portrait/item/01b9536d696c65746f63616e6479e71e', '0', '15618910554', '');
INSERT INTO `tiku_member` VALUES ('7', 'tg', 'd51a70df396d0d6c0aca3cd4e08883fc2b586e2121ca3e65128180961ae092d7', 'https://gss0.bdstatic.com/6LZ1dD3d1sgCo2Kml5_Y_D3/sys/portrait/item/01b9536d696c65746f63616e6479e71e', '0', '15621223512', '');
INSERT INTO `tiku_member` VALUES ('8', 'chenfd', 'd51a70df396d0d6c0aca3cd4e08883fc2b586e2121ca3e65128180961ae092d7', 'https://gss0.bdstatic.com/6LZ1dD3d1sgCo2Kml5_Y_D3/sys/portrait/item/01b9536d696c65746f63616e6479e71e', '0', '15618910745', '');

-- ----------------------------
-- Table structure for tiku_menu
-- ----------------------------
DROP TABLE IF EXISTS `tiku_menu`;
CREATE TABLE `tiku_menu` (
  `uid` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '菜单名称',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父节点',
  `pinyin` varchar(20) NOT NULL,
  `is_show` char(1) NOT NULL DEFAULT '0' COMMENT '是否显示在头部菜单栏目',
  `orders` char(5) NOT NULL DEFAULT '0' COMMENT '排序',
  `url` varchar(200) NOT NULL DEFAULT '' COMMENT 'URL',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tiku_menu
-- ----------------------------
INSERT INTO `tiku_menu` VALUES ('1', '头部菜单', '0', '', '0', '1', '');
INSERT INTO `tiku_menu` VALUES ('2', '左侧菜单', '0', '', '0', '2', '');
INSERT INTO `tiku_menu` VALUES ('3', '资格考试专区', '0', '', '0', '3', '');
INSERT INTO `tiku_menu` VALUES ('4', '职业资格类', '1', 'zhiye', '1', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('5', '建筑类', '1', 'jianzhu', '1', '2', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('6', '学历类', '1', 'xueli', '1', '3', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('7', '财会类', '1', 'caikuai', '1', '4', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('8', '医药类', '1', 'yiyao', '1', '5', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('9', '社会工作师', '4', '', '1', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('10', '企业法律顾问', '4', '', '1', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('11', '教师资格证', '4', '', '1', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('12', '助理社会工作师', '4', '', '1', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('13', '一级建造师', '5', '', '1', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('14', '二级建造师', '5', '', '1', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('15', '考研', '6', '', '1', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('16', '高考', '6', '', '1', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('17', '会计从业资格', '7', '', '1', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('18', '中级会计师', '7', '', '1', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('19', '注册会计师', '7', '', '1', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('20', '中级经济师', '7', '', '1', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('21', '初级会计师', '7', '', '1', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('22', '临床执业医师', '8', '', '1', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('23', '临床助理医师', '8', '', '1', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('24', '执业中药师', '8', '', '1', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('25', '执业西药师', '8', '', '1', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('26', '护士资格', '8', '', '1', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('27', '计算机类', '1', '', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('28', '计算机四级', '27', '', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('29', '高考', '2', 'gaokao', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('30', '高考', '29', '', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('31', '建筑类', '2', 'jianzhu', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('32', '一级建造师', '31', '', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('33', '二级建造师', '31', '', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('34', '财会类', '2', 'caikuai', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('35', '会计从业资格', '34', '', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('36', '中级会计师', '34', '', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('37', '注册会计师CPA', '34', '', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('38', '中级经济师', '34', '', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('39', '初级会计师', '34', '', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('40', '计算机类', '2', 'jisuanji', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('41', '计算机四级', '40', '', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('42', '公务员', '2', 'gongwuyuan', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('43', '警察招考', '42', '', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('44', '法务干警', '42', '', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('45', '国考', '42', '', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('46', '医药类', '2', 'yiyao', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('47', '临床执业医师', '46', '', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('48', '临床助理医师', '46', '', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('49', '执业中药师', '46', '', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('50', '执业西药师', '46', '', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('51', '护士资格', '46', '', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('52', '其他', '2', 'qita', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('53', '考研', '52', '', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('54', '高考', '52', '', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('55', '财会类', '3', 'kuaiji', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('56', '会计从业资格', '55', '', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('57', '初级会计师', '55', '', '0', '2', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('58', '中级会计师', '55', '', '0', '3', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('59', '注册会计师CPA', '55', '', '0', '4', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('60', '中级经济师', '55', '', '0', '5', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('61', '建筑类', '3', 'jianzhu', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('62', '一级建造师', '61', '', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('63', '二级建造师', '61', '', '0', '2', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('64', '职业资格类', '3', 'zhiye', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('65', '教师资格证', '64', '', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('66', '企业法律顾问', '64', '', '0', '2', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('67', '社会工作师', '64', '', '0', '3', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('68', '助理社会工作师', '64', '', '0', '5', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('69', '公务员', '3', 'yiwei', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('70', '警察招考', '69', '', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('71', '法务干警', '69', '', '0', '2', 'chapter/13/2.html');

-- ----------------------------
-- Table structure for tiku_paper
-- ----------------------------
DROP TABLE IF EXISTS `tiku_paper`;
CREATE TABLE `tiku_paper` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `course_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '科目ID',
  `school_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '学校ID',
  `area_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '地区ID',
  `creater_id` bigint(20) NOT NULL COMMENT '创建人',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '标题',
  `question_info` text NOT NULL COMMENT '题目类型，以逗号分割',
  `type` varchar(10) NOT NULL DEFAULT '0' COMMENT '类型(0:真题试卷,1:模拟试卷,2:押题预测,3:名校精品)',
  `year` varchar(5) NOT NULL DEFAULT '' COMMENT '年份',
  `down_hits` int(10) NOT NULL DEFAULT '0' COMMENT '下载数量',
  `open_hits` int(10) NOT NULL DEFAULT '0' COMMENT '打开数量',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tiku_paper
-- ----------------------------
INSERT INTO `tiku_paper` VALUES ('1', '13', '1', '1', '2', '2018数学试卷 上海', '[{\"uid\":1,\"name\":\"简答题\",\"type\":\"3\",\"title\":\"本大题共15小题，每小题1分，共15分。在每小题给出的4个选项中，有且只有一项是符合题目要求。\"},{\"uid\":2,\"name\":\"多选题\",\"title\":\"书面表达 本大题共15分。\",\"type\":\"2\"}]', '1', '2018', '0', '0');
INSERT INTO `tiku_paper` VALUES ('2', '1', '1', '1', '2', '2017年高考真题 理科数学 (北京卷)', '[{uid:1,name:\"简答题\",type:\"3\",title:\"本大题共15小题，每小题1分，共15分。在每小题给出的4个选项中，有且只有一项是符合题目要求。\"}]', '1', '2017', '2', '100');
INSERT INTO `tiku_paper` VALUES ('3', '1', '1', '1', '2', '理科数学 朝阳区2017年高三第一次模拟考试', '[{uid:1,name:\"简答题\",type:\"3\",title:\"本大题共15小题，每小题1分，共15分。在每小题给出的4个选项中，有且只有一项是符合题目要求。\"}]', '2', '2017', '0', '0');
INSERT INTO `tiku_paper` VALUES ('4', '1', '1', '1', '2', '理科数学 海淀区2017年高三第一次模拟考试', '[{uid:1,name:\"简答题\",type:\"3\",title:\"本大题共15小题，每小题1分，共15分。在每小题给出的4个选项中，有且只有一项是符合题目要求。\"}]', '2', '2017', '0', '0');
INSERT INTO `tiku_paper` VALUES ('5', '1', '2', '1', '2', '理科数学 东城区2017年高三上学期期末考试', '[{uid:1,name:\"简答题\",type:\"3\",title:\"本大题共15小题，每小题1分，共15分。在每小题给出的4个选项中，有且只有一项是符合题目要求。\"}]', '2', '2017', '0', '0');
INSERT INTO `tiku_paper` VALUES ('6', '1', '2', '1', '2', '理科数学 丰台区2017年高三第一次模拟考试', '[{uid:1,name:\"简答题\",type:\"3\",title:\"本大题共15小题，每小题1分，共15分。在每小题给出的4个选项中，有且只有一项是符合题目要求。\"}]', '2', '2017', '0', '0');
INSERT INTO `tiku_paper` VALUES ('7', '1', '2', '1', '2', '理科数学 海淀区2017年高三上学期期末考试', '[{uid:1,name:\"简答题\",type:\"3\",title:\"本大题共15小题，每小题1分，共15分。在每小题给出的4个选项中，有且只有一项是符合题目要求。\"}]', '2', '2017', '0', '0');
INSERT INTO `tiku_paper` VALUES ('10', '9', '3', '8', '0', '2017年高考真题 地理 (江苏卷)', '[{uid:1,name:\"简答题\",type:\"3\",title:\"本大题共15小题，每小题1分，共15分。在每小题给出的4个选项中，有且只有一项是符合题目要求。\"}]', '1', '2017', '0', '0');
INSERT INTO `tiku_paper` VALUES ('11', '9', '3', '24', '0', '2017年高考真题 地理 (海南卷)', '[{uid:1,name:\"简答题\",type:\"3\",title:\"本大题共15小题，每小题1分，共15分。在每小题给出的4个选项中，有且只有一项是符合题目要求。\"}]', '1', '2017', '0', '0');
INSERT INTO `tiku_paper` VALUES ('12', '9', '3', '9', '0', '地理 温州市2017年高三第二次选考', '[{uid:1,name:\"简答题\",type:\"3\",title:\"本大题共15小题，每小题1分，共15分。在每小题给出的4个选项中，有且只有一项是符合题目要求。\"}]', '1', '2017', '0', '0');

-- ----------------------------
-- Table structure for tiku_question
-- ----------------------------
DROP TABLE IF EXISTS `tiku_question`;
CREATE TABLE `tiku_question` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `question_type` char(1) NOT NULL DEFAULT '1' COMMENT '题目类型(1.单选题；2.多选题; 3.解答题)',
  `subject_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '考试类型',
  `chapter_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '章节',
  `paper_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '试卷ID',
  `content` varchar(255) NOT NULL DEFAULT '' COMMENT '题内容',
  `answer` varchar(255) NOT NULL DEFAULT '' COMMENT '答案',
  `analysis` varchar(255) NOT NULL DEFAULT '' COMMENT '解析',
  `fallibility` varchar(255) NOT NULL DEFAULT '' COMMENT '易错点',
  `review_point` varchar(100) NOT NULL DEFAULT '' COMMENT '考察知识点',
  `year` varchar(5) NOT NULL DEFAULT '' COMMENT '年份',
  `score` int(10) NOT NULL DEFAULT '0' COMMENT '分数',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tiku_question
-- ----------------------------
INSERT INTO `tiku_question` VALUES ('1', '3', '2', '33', '1', '<p><span>社会工作专业化是在长期社会服务实践中形成，在实践中被接受的。在专业化过程中，社会工作发展的重要特点包括(  )。</span><br><br><span>                A．专业理论的完善</span><br><span>B．专业方法的发展</span><br><span>C．目标模式的变化</span><br><span>D．工作对象的拓展</span><br><span>E．理论派别的形成</span><br></p>', '<p>见解析。</p>', '<p><span>设</span><img src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/902397dda144ad340d36a726d4a20cf430ad8581.jpg\" data-lazysrc=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/902397dda144ad340d36a726d4a20cf430ad8581.j', '<p>无。</p>', '西方社会工作在不同发展阶段的特点,马克思思想', '2017', '5');
INSERT INTO `tiku_question` VALUES ('2', '3', '2', '33', '1', '<p><span>儿童社会工作的服务对象是所有儿童，包括(  )。</span><br><br><span>                A．以处于各种不同境遇的儿童为对象</span><br><span>B．针对儿童的所有成长阶段</span><br><span>C．面对儿童成长发展中的所有问题</span><br><span>D．由儿童问题研究专家面向接受实验的儿童开展工作</span><br><span>E．要考虑影响儿童发展的儿童自我因素及社会的所有因素</span><br></p>', '<p><span>A,B,C,E</span></p>', '<p><span>[提示] 儿童社会工作是面向儿童的工作，其对象是所有儿童，包括处于各种不同境遇的儿童、儿童的所有成长阶段、儿童成长发展中的所有问题、影响儿童发展的儿童自我因素及社会的所有因素等。因此，选项A、B、C、E为正确答案。</span></p>', '<p>无。</p>', '集合的含义', '2018', '13');
INSERT INTO `tiku_question` VALUES ('3', '1', '2', '33', '1', '<p><span>儿童社会工作的服务对象是所有儿童，包括(  )。</span><br><br><span>                A．以处于各种不同境遇的儿童为对象</span><br><span>B．针对儿童的所有成长阶段</span><br><span>C．面对儿童成长发展中的所有问题</span><br><span>D．由儿童问题研究专家面向接受实验的儿童开展工作</span><br><span>E．要考虑影响儿童发展的儿童自我因素及社会的所有因素</span><br></p>', '<p><span>A,B,C,E</span></p>', '<p><span>设</span><img src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/902397dda144ad340d36a726d4a20cf430ad8581.jpg\" data-lazysrc=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/902397dda144ad340d36a726d4a20cf430ad8581.j', '<p>无。</p>', '西方社会工作在不同发展阶段的特点,马克思思想', '2018', '5');

-- ----------------------------
-- Table structure for tiku_school
-- ----------------------------
DROP TABLE IF EXISTS `tiku_school`;
CREATE TABLE `tiku_school` (
  `uid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `area_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '地区ID',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '学校名称',
  `image` varchar(255) NOT NULL DEFAULT '' COMMENT '图片',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tiku_school
-- ----------------------------
INSERT INTO `tiku_school` VALUES ('1', '1', '北京大学附属中学', 'school-wrap-bg1_22603c9.png');
INSERT INTO `tiku_school` VALUES ('2', '1', '北京市第一零一中学', 'school-wrap-bg2_c1220a1.png');
INSERT INTO `tiku_school` VALUES ('3', '1', '北京市第四中学', 'school-wrap-bg3_9b3e217.png');
INSERT INTO `tiku_school` VALUES ('4', '1', '北京市八一学校', 'school-wrap-bg3_9b3e217.png');
INSERT INTO `tiku_school` VALUES ('5', '1', '北京师范大学第二附属中学', 'school-wrap-bg3_9b3e217.png');
INSERT INTO `tiku_school` VALUES ('6', '1', '东北师范大学附属中学', 'school-wrap-bg3_9b3e217.png');
INSERT INTO `tiku_school` VALUES ('7', '1', '上海中学', 'school-wrap-bg3_9b3e217.png');
INSERT INTO `tiku_school` VALUES ('8', '1', '衡水中学', 'school-wrap-bg3_9b3e217.png');

-- ----------------------------
-- Table structure for tiku_subject
-- ----------------------------
DROP TABLE IF EXISTS `tiku_subject`;
CREATE TABLE `tiku_subject` (
  `uid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '题目名称',
  `course_id` bigint(20) NOT NULL COMMENT '默认科目',
  `course_name` varchar(20) NOT NULL DEFAULT '' COMMENT '科目名称',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tiku_subject
-- ----------------------------
INSERT INTO `tiku_subject` VALUES ('6', '社会工作师', '1', '综合能力（中级）');
INSERT INTO `tiku_subject` VALUES ('7', '企业法律顾问', '10', '企业管理知识');
INSERT INTO `tiku_subject` VALUES ('8', '教师资格证', '11', '中学教育心理学');
INSERT INTO `tiku_subject` VALUES ('9', '助理社会工作师', '12', '社会工作综合能力（初级）');
INSERT INTO `tiku_subject` VALUES ('10', '一级建造师', '13', '建筑工程');
INSERT INTO `tiku_subject` VALUES ('11', '二级建造师', '16', '建设工程施工管理');
INSERT INTO `tiku_subject` VALUES ('12', '考研', '17', '考研政治');
INSERT INTO `tiku_subject` VALUES ('13', '高考', '2', '理科数学');
INSERT INTO `tiku_subject` VALUES ('14', '会计从业资格', '18', '会计基础');
INSERT INTO `tiku_subject` VALUES ('15', '中级会计师', '19', '中级经济法');
INSERT INTO `tiku_subject` VALUES ('16', '注册会计师CPA', '22', '会计');
INSERT INTO `tiku_subject` VALUES ('17', '中级经济师', '24', '专业知识与实务');
INSERT INTO `tiku_subject` VALUES ('18', '初级会计师', '26', '经济法基础');
INSERT INTO `tiku_subject` VALUES ('19', '临床执业医师', '28', '临床执业医师');
INSERT INTO `tiku_subject` VALUES ('20', '临床助理医师', '29', '临床助理医师');
INSERT INTO `tiku_subject` VALUES ('21', '执业中药师', '30', '中药学综合知识与技能');
INSERT INTO `tiku_subject` VALUES ('22', '执业西药师', '33', '药事管理与法规');
INSERT INTO `tiku_subject` VALUES ('23', '护士资格', '37', '专业实务');
INSERT INTO `tiku_subject` VALUES ('25', '计算机四级', '38', '计算机四级操作系统');
INSERT INTO `tiku_subject` VALUES ('27', '警察招考', '39', '公安基础知识');
INSERT INTO `tiku_subject` VALUES ('28', '政法干警', '40', '行测');
INSERT INTO `tiku_subject` VALUES ('29', '国考', '41', '行测');