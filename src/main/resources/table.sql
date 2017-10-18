-- create table;

CREATE TABLE `app_info` (
  `appid` varchar(20) NOT NULL COMMENT '主键app的ID',
  `score` int(2) DEFAULT NULL COMMENT '评分',
  `title` varchar(30) DEFAULT NULL COMMENT '标题',
  `url` varchar(255) DEFAULT NULL COMMENT '详情地址',
  `thumbnail_url` varchar(255) DEFAULT NULL COMMENT '下载地址',
  `intro` varchar(255) DEFAULT NULL COMMENT '说明',
  `developer` varchar(255) DEFAULT NULL COMMENT '开发者',
  `top5App` varchar(110) DEFAULT NULL COMMENT 'top 5 app',
  PRIMARY KEY (`appid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='app_info表';

