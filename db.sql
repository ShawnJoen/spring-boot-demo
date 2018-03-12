#DROP table `t_city`;
CREATE TABLE `t_city` (
`id` int UNSIGNED NOT NULL COMMENT '' AUTO_INCREMENT,
`province_id` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '城市编号',
`city_name` varchar(30) NOT NULL DEFAULT '' COMMENT '城市名称',
`description` varchar(100) NOT NULL DEFAULT '' COMMENT '城市描述',
PRIMARY KEY (`id`),
KEY `i1` (`province_id`),
KEY `i2` (`city_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='城市表';
INSERT INTO `t_city` (`id`, `province_id`, `city_name`, `description`) VALUES (1, 0, '上海', '一线城市');
INSERT INTO `t_city` (`id`, `province_id`, `city_name`, `description`) VALUES (2, 0, '北京', '一线城市');
INSERT INTO `t_city` (`id`, `province_id`, `city_name`, `description`) VALUES (3, 0, '南京', '二线城市');
INSERT INTO `t_city` (`id`, `province_id`, `city_name`, `description`) VALUES (4, 1, '杨浦区', '区');
INSERT INTO `t_city` (`id`, `province_id`, `city_name`, `description`) VALUES (5, 1, '闵行区', '区');

#DROP table `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(60) NOT NULL,
  `pw` varchar(120) NOT NULL,
  `otp_hash` varchar(120) NOT NULL,
  `active` varchar(1) NOT NULL,
  `role` varchar(10) DEFAULT NULL,
  `reg_date` datetime NOT NULL,
  PRIMARY KEY `i1` (`id`),
  UNIQUE KEY `u1` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

#DROP table `t_finger_print`;
CREATE TABLE `t_finger_print` (
  `user_id` int(11) NOT NULL,
  `hash_key` varchar(255) NOT NULL,
  PRIMARY KEY `i1`(`user_id`),
  UNIQUE KEY `u1` (`hash_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




