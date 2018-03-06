/*
Navicat MySQL Data Transfer

Source Server         : tcz-dev
Source Server Version : 50610
Source Host           : 127.0.0.1:3306
Source Database       : tiku

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2018-03-06 15:46:44
*/

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', '', '', '0', '', '1');
INSERT INTO `sys_menu` VALUES ('2', '1', '菜单管理', 'sys/menu.html', 'sys:menu:list,sys:menu:select,sys:menu:perms,sys:menu:info,sys:menu:save,sys:menu:update,sys:menu:delete', '1', '', '3');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'sys/role.html', 'sys:role:list,sys:role:select,sys:role:info,sys:role:save,sys:role:update,sys:role:delete', '1', '', '2');
INSERT INTO `sys_menu` VALUES ('4', '1', '用户管理', 'sys/user.html', 'sys:user:info,sys:user:save,sys:user:update,sys:user:delete', '1', '', '1');
INSERT INTO `sys_menu` VALUES ('5', '0', '内容管理', '', '', '0', '', '1');
INSERT INTO `sys_menu` VALUES ('6', '5', '题库类型', 'tiku/subject.html', 'video:topVideoList,video:info,video:save,video:update,video:delete,video:create,video:create,video:recommend', '1', '', '1');
INSERT INTO `sys_menu` VALUES ('7', '5', '科目管理', 'tiku/course.html', 'video:topVideoList,video:info,video:save,video:update,video:delete,video:create,video:create', '1', '', '2');
INSERT INTO `sys_menu` VALUES ('8', '5', '章节管理', 'tiku/chapter.html', 'video:topVideoList,video:info,video:save,video:update,video:delete,video:create,video:create', '1', '', '3');
INSERT INTO `sys_menu` VALUES ('9', '5', '问题管理', 'tiku/question.html', 'video:topVideoList,video:info,video:save,video:update,video:delete,video:create,video:create', '1', '', '4');

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
INSERT INTO `sys_user` VALUES ('1', 'TanMin', '7681b79275c1f3e5c82b579f5fe6fa9974fca815b6780bbb65fa34b532ebf16c', '223', '123', '1', '1514256410', '1518157458');

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
  `uid` bigint(20) NOT NULL COMMENT 'ID',
  `course_id` bigint(20) NOT NULL COMMENT '科目ID',
  `parent_id` bigint(20) NOT NULL COMMENT '父节点',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '章节名称',
  `question_num` int(20) NOT NULL COMMENT '问题数量',
  `point_num` int(20) NOT NULL DEFAULT '0' COMMENT '知识点',
  `orders` int(20) NOT NULL DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tiku_chapter
-- ----------------------------
INSERT INTO `tiku_chapter` VALUES ('1', '1', '0', '第一章 社会工作概述', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('2', '1', '0', '第二章 社会工作价值观与专业伦理', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('3', '1', '0', '第三章 人类行为与社会环境', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('4', '1', '0', '第四章 社会工作理论', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('5', '1', '0', '第五章 个案工作方法', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('6', '1', '0', '第六章 小组工作方法', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('7', '1', '0', '第七章 社区工作方法', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('8', '1', '0', '第八章 社会工作行政', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('9', '1', '0', '第九章 社会工作督导', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('10', '1', '0', '第十章 社会工作研究', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('11', '1', '1', '社会工作的含义、目标与功能', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('12', '1', '1', '社会工作的发展', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('13', '1', '1', '社会工作的构成要素', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('14', '1', '1', '社会工作者的主要角色', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('15', '1', '1', '社会工作领域', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('16', '2', '0', '第一章 集合与常用逻辑用语', '0', '1', '1');
INSERT INTO `tiku_chapter` VALUES ('17', '2', '0', '第二章 函数的概念与基本初等函数', '0', '1', '2');
INSERT INTO `tiku_chapter` VALUES ('18', '2', '0', '第三章 导数及其应用', '0', '1', '3');
INSERT INTO `tiku_chapter` VALUES ('19', '2', '0', '第四章 三角函数与三角恒等变换', '9', '1', '4');
INSERT INTO `tiku_chapter` VALUES ('20', '2', '0', '第五章 平面向量', '0', '1', '5');
INSERT INTO `tiku_chapter` VALUES ('21', '2', '0', '第六章 数列', '0', '1', '6');
INSERT INTO `tiku_chapter` VALUES ('22', '2', '0', '第七章 不等式', '0', '2', '7');
INSERT INTO `tiku_chapter` VALUES ('23', '2', '0', '第八章 立体几何与空间向量', '10', '2', '8');
INSERT INTO `tiku_chapter` VALUES ('24', '2', '0', '第九章 直线和圆的方程', '1', '2', '9');
INSERT INTO `tiku_chapter` VALUES ('25', '2', '0', '第十章 圆锥曲线与方程', '0', '2', '10');
INSERT INTO `tiku_chapter` VALUES ('26', '2', '0', '第十一章 计数原理', '0', '2', '11');
INSERT INTO `tiku_chapter` VALUES ('27', '2', '0', '第十二章 概率与统计', '3', '2', '12');
INSERT INTO `tiku_chapter` VALUES ('28', '2', '0', '第十三章 算法初步', '0', '3', '13');
INSERT INTO `tiku_chapter` VALUES ('29', '2', '0', '第十四章 数系的扩充和复数的引入', '4', '3', '14');
INSERT INTO `tiku_chapter` VALUES ('30', '2', '0', '第十五章 推理与证明', '5', '3', '15');
INSERT INTO `tiku_chapter` VALUES ('31', '2', '0', '第十六章 选修部分', '0', '3', '16');
INSERT INTO `tiku_chapter` VALUES ('32', '2', '16', '1 集合的概念及运算', '2', '1', '17');
INSERT INTO `tiku_chapter` VALUES ('33', '2', '32', '1.1 集合的含义', '2', '1', '1');
INSERT INTO `tiku_chapter` VALUES ('34', '2', '32', '1.2 元素与集合关系的判断', '2', '1', '2');

-- ----------------------------
-- Table structure for tiku_course
-- ----------------------------
DROP TABLE IF EXISTS `tiku_course`;
CREATE TABLE `tiku_course` (
  `uid` bigint(20) NOT NULL,
  `subject_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '类型ID',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '科目',
  `orders` int(10) NOT NULL DEFAULT '0' COMMENT '排序',
  `question_num` int(10) NOT NULL DEFAULT '0' COMMENT '题目数量',
  `point_num` int(10) NOT NULL DEFAULT '0' COMMENT '知识点数量',
  `icon` varchar(100) NOT NULL DEFAULT '' COMMENT '图标',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tiku_course
-- ----------------------------
INSERT INTO `tiku_course` VALUES ('1', '1', '综合能力（中级）', '1', '0', '0', '');
INSERT INTO `tiku_course` VALUES ('2', '13', '理科数学', '1', '0', '0', 'icon-like');
INSERT INTO `tiku_course` VALUES ('3', '13', '文科数学', '2', '0', '0', 'icon-wenke');
INSERT INTO `tiku_course` VALUES ('4', '13', '物理', '3', '0', '0', 'icon-wuli');
INSERT INTO `tiku_course` VALUES ('5', '13', '化学', '4', '0', '0', 'icon-huaxue');
INSERT INTO `tiku_course` VALUES ('6', '13', '生物', '5', '0', '0', 'icon-shengwu');
INSERT INTO `tiku_course` VALUES ('7', '13', '政治', '6', '0', '0', 'icon-zhengzhi');
INSERT INTO `tiku_course` VALUES ('8', '13', '历史', '7', '0', '0', 'icon-lishi');
INSERT INTO `tiku_course` VALUES ('9', '13', '地理', '8', '0', '0', 'icon-dili');

-- ----------------------------
-- Table structure for tiku_menu
-- ----------------------------
DROP TABLE IF EXISTS `tiku_menu`;
CREATE TABLE `tiku_menu` (
  `uid` bigint(20) NOT NULL,
  `subject_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '业务ID',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '菜单名称',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父节点',
  `pinyin` varchar(20) NOT NULL,
  `is_show` char(1) NOT NULL DEFAULT '0' COMMENT '是否显示在头部菜单栏目',
  `orders` char(5) NOT NULL DEFAULT '0' COMMENT '排序',
  `type` char(1) NOT NULL DEFAULT '0' COMMENT '菜单类型（0:头部菜单,1:左侧菜单）',
  `url` varchar(200) NOT NULL DEFAULT '' COMMENT 'URL',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tiku_menu
-- ----------------------------
INSERT INTO `tiku_menu` VALUES ('1', '1', '职业资格类', '0', 'zhiye', '1', '1', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('2', '2', '建筑类', '0', 'jianzhu', '1', '2', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('3', '3', '学历类', '0', 'xueli', '1', '3', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('4', '4', '财会类', '0', 'caikuai', '1', '4', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('5', '5', '医药类', '0', 'yiyao', '1', '5', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('6', '6', '社会工作师', '1', '', '1', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('7', '7', '企业法律顾问', '1', '', '1', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('8', '8', '教师资格证', '1', '', '1', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('9', '9', '助理社会工作师', '1', '', '1', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('10', '10', '一级建造师', '2', '', '1', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('11', '11', '二级建造师', '2', '', '1', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('12', '12', '考研', '3', '', '1', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('13', '13', '高考', '3', '', '1', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('14', '14', '会计从业资格', '4', '', '1', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('15', '15', '中级会计师', '4', '', '1', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('16', '16', '注册会计师', '4', '', '1', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('17', '17', '中级经济师', '4', '', '1', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('18', '18', '初级会计师', '4', '', '1', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('19', '19', '临床执业医师', '5', '', '1', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('20', '20', '临床助理医师', '5', '', '1', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('21', '21', '执业中药师', '5', '', '1', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('22', '22', '执业西药师', '5', '', '1', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('23', '23', '护士资格', '5', '', '1', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('24', '24', '计算机类', '0', '', '0', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('25', '25', '计算机四级', '24', '', '0', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('26', '13', '高考', '0', 'gaokao', '0', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('27', '13', '高考', '26', '', '0', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('28', '2', '建筑类', '0', 'jianzhu', '0', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('29', '10', '一级建造师', '28', '', '0', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('30', '11', '二级建造师', '28', '', '0', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('31', '4', '财会类', '0', 'caikuai', '0', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('32', '14', '会计从业资格', '31', '', '0', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('33', '15', '中级会计师', '31', '', '0', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('34', '26', '注册会计师CPA', '31', '', '0', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('35', '17', '中级经济师', '31', '', '0', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('36', '18', '初级会计师', '31', '', '0', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('37', '24', '计算机类', '0', 'jisuanji', '0', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('38', '25', '计算机四级', '37', '', '0', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('39', '0', '公务员', '0', 'gongwuyuan', '0', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('40', '0', '警察招考', '39', '', '0', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('41', '0', '法务干警', '39', '', '0', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('42', '0', '国考', '39', '', '0', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('43', '0', '医药类', '0', 'yiyao', '0', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('44', '0', '临床执业医师', '43', '', '0', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('45', '0', '临床助理医师', '43', '', '0', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('46', '0', '执业中药师', '43', '', '0', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('47', '0', '执业西药师', '43', '', '0', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('48', '0', '护士资格', '43', '', '0', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('49', '0', '其他', '0', 'qita', '0', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('50', '0', '考研', '49', '', '0', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('51', '0', '高考', '49', '', '0', '0', '1', 'chapter/13/2.html');

-- ----------------------------
-- Table structure for tiku_paper
-- ----------------------------
DROP TABLE IF EXISTS `tiku_paper`;
CREATE TABLE `tiku_paper` (
  `uid` bigint(20) NOT NULL DEFAULT '0' COMMENT 'ID',
  `course_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '科目ID',
  `school_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '学校ID',
  `area_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '地区ID',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '标题',
  `question_info` text NOT NULL COMMENT '题目类型，以逗号分割',
  `type` char(1) NOT NULL DEFAULT '0' COMMENT '类型(0:真题试卷,1:模拟试卷,2:押题预测,3:名校精品)',
  `year` varchar(5) NOT NULL DEFAULT '' COMMENT '年份',
  `down_hits` int(10) NOT NULL DEFAULT '0' COMMENT '下载数量',
  `open_hits` int(10) NOT NULL DEFAULT '0' COMMENT '打开数量',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tiku_paper
-- ----------------------------
INSERT INTO `tiku_paper` VALUES ('1', '1', '1', '1', '2017年高考真题 理科数学 (北京卷)', '[{name:\"简答题\",type:\"3\",title:\"本大题共15小题，每小题1分，共15分。在每小题给出的4个选项中，有且只有一项是符合题目要求。\"},{name:\"单选题\",type:\"1\",title:\"本大题共15小题，每小题1分，共15分。在每小题给出的4个选项中，有且只有一项是符合题目要求。\"}]', '1', '2017', '2', '100');
INSERT INTO `tiku_paper` VALUES ('2', '1', '1', '1', '理科数学 朝阳区2017年高三第一次模拟考试', '', '2', '2017', '0', '0');
INSERT INTO `tiku_paper` VALUES ('3', '1', '1', '1', '理科数学 海淀区2017年高三第一次模拟考试', '', '2', '2017', '0', '0');
INSERT INTO `tiku_paper` VALUES ('4', '1', '2', '1', '理科数学 东城区2017年高三上学期期末考试', '', '2', '2017', '0', '0');
INSERT INTO `tiku_paper` VALUES ('5', '1', '2', '1', '理科数学 丰台区2017年高三第一次模拟考试', '', '2', '2017', '0', '0');
INSERT INTO `tiku_paper` VALUES ('6', '1', '2', '1', '理科数学 海淀区2017年高三上学期期末考试', '', '2', '2017', '0', '0');
INSERT INTO `tiku_paper` VALUES ('7', '3', '3', '1', '2017年高考真题 文科数学 (北京卷)', '', '1', '2017', '0', '0');
INSERT INTO `tiku_paper` VALUES ('8', '5', '3', '1', '化学 海淀区2017年高三第一次模拟考试', '', '2', '2017', '0', '0');
INSERT INTO `tiku_paper` VALUES ('9', '9', '3', '8', '2017年高考真题 地理 (江苏卷)', '', '1', '2017', '0', '0');
INSERT INTO `tiku_paper` VALUES ('10', '9', '3', '24', '2017年高考真题 地理 (海南卷)', '', '1', '2017', '0', '0');
INSERT INTO `tiku_paper` VALUES ('11', '9', '3', '9', '地理 温州市2017年高三第二次选考', '', '1', '2017', '0', '0');

-- ----------------------------
-- Table structure for tiku_question
-- ----------------------------
DROP TABLE IF EXISTS `tiku_question`;
CREATE TABLE `tiku_question` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `question_type` char(1) NOT NULL DEFAULT '1' COMMENT '题目类型(1.单选题；2.多选题; 3.解答题)',
  `subject_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '题类型',
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

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
INSERT INTO `tiku_school` VALUES ('9', '1', '南京外国语学校', 'school-wrap-bg3_9b3e217.png');
INSERT INTO `tiku_school` VALUES ('10', '1', '成都市第七中学', 'school-wrap-bg3_9b3e217.png');

-- ----------------------------
-- Table structure for tiku_subject
-- ----------------------------
DROP TABLE IF EXISTS `tiku_subject`;
CREATE TABLE `tiku_subject` (
  `uid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '题目名称',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tiku_subject
-- ----------------------------
INSERT INTO `tiku_subject` VALUES ('1', '职业资格类');
INSERT INTO `tiku_subject` VALUES ('2', '建筑类');
INSERT INTO `tiku_subject` VALUES ('3', '学历类');
INSERT INTO `tiku_subject` VALUES ('4', '财会类');
INSERT INTO `tiku_subject` VALUES ('5', '医药类');
INSERT INTO `tiku_subject` VALUES ('6', '社会工作师');
INSERT INTO `tiku_subject` VALUES ('7', '企业法律顾问');
INSERT INTO `tiku_subject` VALUES ('8', '教师资格证');
INSERT INTO `tiku_subject` VALUES ('9', '助理社会工作师');
INSERT INTO `tiku_subject` VALUES ('10', '一级建造师');
INSERT INTO `tiku_subject` VALUES ('11', '二级建造师');
INSERT INTO `tiku_subject` VALUES ('12', '考研');
INSERT INTO `tiku_subject` VALUES ('13', '高考');
INSERT INTO `tiku_subject` VALUES ('14', '会计从业资格');
INSERT INTO `tiku_subject` VALUES ('15', '中级会计师');
INSERT INTO `tiku_subject` VALUES ('16', '注册会计师');
INSERT INTO `tiku_subject` VALUES ('17', '中级经济师');
INSERT INTO `tiku_subject` VALUES ('18', '初级会计师');
INSERT INTO `tiku_subject` VALUES ('19', '临床执业医师');
INSERT INTO `tiku_subject` VALUES ('20', '临床助理医师');
INSERT INTO `tiku_subject` VALUES ('21', '执业中药师');
INSERT INTO `tiku_subject` VALUES ('22', '执业西药师');
INSERT INTO `tiku_subject` VALUES ('23', '护士资格');
INSERT INTO `tiku_subject` VALUES ('24', '计算机类');
INSERT INTO `tiku_subject` VALUES ('25', '计算机四级');
INSERT INTO `tiku_subject` VALUES ('26', '公务员');
INSERT INTO `tiku_subject` VALUES ('27', '警察招考');
INSERT INTO `tiku_subject` VALUES ('28', '政法干警');
INSERT INTO `tiku_subject` VALUES ('29', '国考');
