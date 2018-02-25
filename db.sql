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

