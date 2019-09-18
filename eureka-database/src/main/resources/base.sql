create table dynamic_property(
	id int(11)  PRIMARY key auto_increment,
	path VARCHAR(30) not null,
	service_id varchar(20) default null,
  url varchar(30) default null,
  strip_prefix TINYINT(1) not null,
  retryable TINYINT(1) not null,
  enabled TINYINT(1) not null,
  description varchar(30) default null
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='动态路由配置';
alter table dynamicPro  auto_increment = 1000000