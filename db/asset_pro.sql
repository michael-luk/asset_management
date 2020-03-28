# Host: localhost  (Version: 5.1.62-community)
# Date: 2020-03-29 00:58:23
# Generator: MySQL-Front 5.3  (Build 4.120)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "admin"
#

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `descriptions` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `last_login_ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `last_login_ip_area` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user_role_enum` int(11) DEFAULT NULL,
  `comment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "admin"
#

INSERT INTO `admin` VALUES (1,'admin_1','e10adc3949ba59abbe56e057f20f883e','0',NULL,'2020-03-29 00:38:01','0',NULL,'0',0,0,'0'),(2,'admin_2','e10adc3949ba59abbe56e057f20f883e','1',NULL,'2020-03-29 00:38:01','1',NULL,'1',0,1,'1'),(3,'admin_3','e10adc3949ba59abbe56e057f20f883e','2',NULL,'2020-03-29 00:38:01','2',NULL,'2',0,2,'2');

#
# Structure for table "admin_journal"
#

DROP TABLE IF EXISTS `admin_journal`;
CREATE TABLE `admin_journal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `actor` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `actor_level` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "admin_journal"
#


#
# Structure for table "asset_card"
#

DROP TABLE IF EXISTS `asset_card`;
CREATE TABLE `asset_card` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `images` varchar(400) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `comment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "asset_card"
#

INSERT INTO `asset_card` VALUES (1,'assetCard_1','0',NULL,'2020-03-29 00:38:01','0'),(2,'assetCard_2','1',NULL,'2020-03-29 00:38:01','1'),(3,'assetCard_3','2',NULL,'2020-03-29 00:38:01','2');

#
# Structure for table "asset_handle"
#

DROP TABLE IF EXISTS `asset_handle`;
CREATE TABLE `asset_handle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `comment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "asset_handle"
#

INSERT INTO `asset_handle` VALUES (1,'assetHandle_1',0,NULL,'2020-03-29 00:38:01','0'),(2,'assetHandle_2',1,NULL,'2020-03-29 00:38:01','1'),(3,'assetHandle_3',2,NULL,'2020-03-29 00:38:01','2');

#
# Structure for table "asset_type"
#

DROP TABLE IF EXISTS `asset_type`;
CREATE TABLE `asset_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `comment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "asset_type"
#

INSERT INTO `asset_type` VALUES (1,'assetType_1',NULL,'2020-03-29 00:38:01','0'),(2,'assetType_2',NULL,'2020-03-29 00:38:01','1'),(3,'assetType_3',NULL,'2020-03-29 00:38:01','2');

#
# Structure for table "auth"
#

DROP TABLE IF EXISTS `auth`;
CREATE TABLE `auth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_default` tinyint(1) DEFAULT '0',
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `comment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "auth"
#

INSERT INTO `auth` VALUES (1,1,'auth_1','0',NULL,'2020-03-29 00:38:01','0'),(2,0,'auth_2','1',NULL,'2020-03-29 00:38:01','1'),(3,1,'auth_3','2',NULL,'2020-03-29 00:38:01','2');

#
# Structure for table "barcode"
#

DROP TABLE IF EXISTS `barcode`;
CREATE TABLE `barcode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `images` varchar(400) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `comment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "barcode"
#

INSERT INTO `barcode` VALUES (1,'barcode_1','0',NULL,'2020-03-29 00:38:01','0'),(2,'barcode_2','1',NULL,'2020-03-29 00:38:01','1'),(3,'barcode_3','2',NULL,'2020-03-29 00:38:01','2');

#
# Structure for table "config"
#

DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `type_enum` int(11) NOT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `comment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "config"
#

INSERT INTO `config` VALUES (1,'is.prod','no',3,NULL,'2020-03-29 00:38:01','是否生产模式(yes/no)'),(2,'admin.login','no',3,NULL,'2020-03-29 00:38:01','是否启用管理员权限(yes/no), 若不启用则后台不需要管理员登陆'),(3,'websocket','no',3,NULL,'2020-03-29 00:38:01','是否启用即时通讯'),(4,'app.name','资产管理系统',0,NULL,'2020-03-29 00:38:01','网站名称'),(5,'weixin.appid','wx',0,NULL,'2020-03-29 00:38:01','微信公众号appid'),(6,'weixin.secret','b6781a019a6143f243514dae493',0,NULL,'2020-03-29 00:38:01','微信公众号secret'),(7,'weixin.token','Test',0,NULL,'2020-03-29 00:38:01','微信公众号token'),(8,'weixin.aes','QYnBBbbQhdjPAJmgDDPFyxd2lLW3z3RTxab',0,NULL,'2020-03-29 00:38:01','微信公众号aes'),(9,'sms.server','sms.todaynic.com',0,NULL,'2020-03-29 00:38:01','第三方短信验证码服务器'),(10,'sms.port','20002',0,NULL,'2020-03-29 00:38:01','第三方短信验证码端口'),(11,'sms.uid','ms114',0,NULL,'2020-03-29 00:38:01','第三方短信验证码用户名'),(12,'sms.psw','mtod',0,NULL,'2020-03-29 00:38:01','第三方短信验证码密码'),(13,'protocol','http',0,NULL,'2020-03-29 00:38:01','协议(http或https)'),(14,'domain.name','localhost:9000',0,NULL,'2020-03-29 00:38:01','域名 (若本地则localhost:9000, 带端口号), 不带http头'),(15,'company.name','广西科技有限公司',0,NULL,'2020-03-29 00:38:01','公司名称'),(16,'user.timeout.minute','300',1,NULL,'2020-03-29 00:38:01','用户登陆过期时间(分钟), 超过此时间需重新登陆'),(17,'admin.timeout.minute','300',1,NULL,'2020-03-29 00:38:01','管理员登陆过期时间(分钟), 超过此时间需重新登陆'),(18,'forget.password.email.timeout.minute','30',1,NULL,'2020-03-29 00:38:01','重置密码邮件验证的过期时间(分钟), 超过此时间需要重新申请'),(19,'email.send.protect.second','20',1,NULL,'2020-03-29 00:38:01','邮件发送频率保护时间(秒)'),(20,'pic.thumb.size','200',2,NULL,'2020-03-29 00:38:01','图片缩略图尺寸(如200)'),(21,'pic.mid.thumb.size','500',2,NULL,'2020-03-29 00:38:01','图片中等缩略图尺寸(如500)');

#
# Structure for table "depart"
#

DROP TABLE IF EXISTS `depart`;
CREATE TABLE `depart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_default` tinyint(1) DEFAULT '0',
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `comment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "depart"
#

INSERT INTO `depart` VALUES (1,1,'depart_1','0',NULL,'2020-03-29 00:38:01','0'),(2,0,'depart_2','1',NULL,'2020-03-29 00:38:01','1'),(3,1,'depart_3','2',NULL,'2020-03-29 00:38:01','2');

#
# Structure for table "dict"
#

DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `comment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "dict"
#

INSERT INTO `dict` VALUES (1,'dict_1','0',NULL,'2020-03-29 00:38:01','0'),(2,'dict_2','1',NULL,'2020-03-29 00:38:01','1'),(3,'dict_3','2',NULL,'2020-03-29 00:38:01','2');

#
# Structure for table "info"
#

DROP TABLE IF EXISTS `info`;
CREATE TABLE `info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `classify` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `english_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `visible` tinyint(1) DEFAULT '0',
  `status` int(11) DEFAULT NULL,
  `images` longtext COLLATE utf8_unicode_ci,
  `small_images` longtext COLLATE utf8_unicode_ci,
  `last_update_time` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `description1` longtext COLLATE utf8_unicode_ci,
  `description2` longtext COLLATE utf8_unicode_ci,
  `comment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "info"
#


#
# Structure for table "location"
#

DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `comment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "location"
#

INSERT INTO `location` VALUES (1,'location_1',NULL,'2020-03-29 00:38:01','0'),(2,'location_2',NULL,'2020-03-29 00:38:01','1'),(3,'location_3',NULL,'2020-03-29 00:38:01','2');

#
# Structure for table "material"
#

DROP TABLE IF EXISTS `material`;
CREATE TABLE `material` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `quantity` bigint(20) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `comment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "material"
#

INSERT INTO `material` VALUES (1,'material_1','0',0,0.00,0,NULL,'2020-03-29 00:38:01','0'),(2,'material_2','1',100,1.00,1,NULL,'2020-03-29 00:38:01','1'),(3,'material_3','2',200,2.00,2,NULL,'2020-03-29 00:38:01','2');

#
# Structure for table "depart_material"
#

DROP TABLE IF EXISTS `depart_material`;
CREATE TABLE `depart_material` (
  `depart_id` bigint(20) NOT NULL,
  `material_id` bigint(20) NOT NULL,
  PRIMARY KEY (`depart_id`,`material_id`),
  KEY `fk_depart_material_material_02` (`material_id`),
  CONSTRAINT `fk_depart_material_material_02` FOREIGN KEY (`material_id`) REFERENCES `material` (`id`),
  CONSTRAINT `fk_depart_material_depart_01` FOREIGN KEY (`depart_id`) REFERENCES `depart` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "depart_material"
#


#
# Structure for table "material_depart"
#

DROP TABLE IF EXISTS `material_depart`;
CREATE TABLE `material_depart` (
  `material_id` bigint(20) NOT NULL,
  `depart_id` bigint(20) NOT NULL,
  PRIMARY KEY (`material_id`,`depart_id`),
  KEY `fk_material_depart_depart_02` (`depart_id`),
  CONSTRAINT `fk_material_depart_depart_02` FOREIGN KEY (`depart_id`) REFERENCES `depart` (`id`),
  CONSTRAINT `fk_material_depart_material_01` FOREIGN KEY (`material_id`) REFERENCES `material` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "material_depart"
#


#
# Structure for table "member"
#

DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_default` tinyint(1) DEFAULT '0',
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `comment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "member"
#

INSERT INTO `member` VALUES (1,1,'member_1','0',NULL,'2020-03-29 00:38:01','0'),(2,0,'member_2','1',NULL,'2020-03-29 00:38:01','1'),(3,1,'member_3','2',NULL,'2020-03-29 00:38:01','2');

#
# Structure for table "play_evolutions"
#

DROP TABLE IF EXISTS `play_evolutions`;
CREATE TABLE `play_evolutions` (
  `id` int(11) NOT NULL,
  `hash` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `applied_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `apply_script` text COLLATE utf8_unicode_ci,
  `revert_script` text COLLATE utf8_unicode_ci,
  `state` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_problem` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "play_evolutions"
#

INSERT INTO `play_evolutions` VALUES (1,'36f4666a3af357037070ba352cebf1e614bbc274','2020-03-29 00:37:59','create table admin (\nid                        bigint auto_increment not null,\nname                      varchar(255) not null,\npassword                  varchar(255) not null,\ndescriptions              varchar(255),\nlast_update_time          datetime,\ncreated_at                datetime,\nlast_login_ip             varchar(255),\nlast_login_time           datetime,\nlast_login_ip_area        varchar(255),\nstatus                    integer,\nuser_role_enum            integer,\ncomment                   varchar(255),\nconstraint pk_admin primary key (id))\n;\n\ncreate table admin_journal (\nid                        bigint auto_increment not null,\nname                      varchar(255) not null,\nactor                     varchar(255),\nactor_level               varchar(255),\ncreated_at                datetime,\nconstraint pk_admin_journal primary key (id))\n;\n\ncreate table asset_card (\nid                        bigint auto_increment not null,\nname                      varchar(255) not null,\nimages                    varchar(400),\nlast_update_time          datetime,\ncreated_at                datetime,\ncomment                   varchar(255),\nconstraint pk_asset_card primary key (id))\n;\n\ncreate table asset_handle (\nid                        bigint auto_increment not null,\nname                      varchar(255) not null,\nstatus                    integer,\nlast_update_time          datetime,\ncreated_at                datetime,\ncomment                   varchar(255),\nconstraint pk_asset_handle primary key (id))\n;\n\ncreate table asset_type (\nid                        bigint auto_increment not null,\nname                      varchar(255) not null,\nlast_update_time          datetime,\ncreated_at                datetime,\ncomment                   varchar(255),\nconstraint pk_asset_type primary key (id))\n;\n\ncreate table asset_use (\nid                        bigint auto_increment not null,\nname                      varchar(255) not null,\ncontent                   varchar(255),\nstatus                    integer,\nlast_update_time          datetime,\ncreated_at                datetime,\ncomment                   varchar(255),\nref_user_id               bigint,\nuser_id                   bigint,\nconstraint pk_asset_use primary key (id))\n;\n\ncreate table auth (\nid                        bigint auto_increment not null,\nis_default                tinyint(1) default 0,\nname                      varchar(255) not null,\ncode                      varchar(255),\nlast_update_time          datetime,\ncreated_at                datetime,\ncomment                   varchar(255),\nconstraint pk_auth primary key (id))\n;\n\ncreate table barcode (\nid                        bigint auto_increment not null,\nname                      varchar(255) not null,\nimages                    varchar(400),\nlast_update_time          datetime,\ncreated_at                datetime,\ncomment                   varchar(255),\nconstraint pk_barcode primary key (id))\n;\n\ncreate table config (\nid                        bigint auto_increment not null,\nname                      varchar(255) not null,\ncontent                   varchar(255) not null,\ntype_enum                 integer not null,\nlast_update_time          datetime,\ncreated_at                datetime,\ncomment                   varchar(255),\nconstraint pk_config primary key (id))\n;\n\ncreate table depart (\nid                        bigint auto_increment not null,\nis_default                tinyint(1) default 0,\nname                      varchar(255) not null,\ncode                      varchar(255),\nlast_update_time          datetime,\ncreated_at                datetime,\ncomment                   varchar(255),\nconstraint pk_depart primary key (id))\n;\n\ncreate table dict (\nid                        bigint auto_increment not null,\nname                      varchar(255) not null,\ncode                      varchar(255),\nlast_update_time          datetime,\ncreated_at                datetime,\ncomment                   varchar(255),\nconstraint pk_dict primary key (id))\n;\n\ncreate table fix_record (\nid                        bigint auto_increment not null,\nname                      varchar(255) not null,\ncontent                   varchar(255),\nstatus                    integer,\nlast_update_time          datetime,\ncreated_at                datetime,\ncomment                   varchar(255),\nref_user_id               bigint,\nuser_id                   bigint,\nconstraint pk_fix_record primary key (id))\n;\n\ncreate table fix_request (\nid                        bigint auto_increment not null,\nname                      varchar(255) not null,\ncontent                   varchar(255),\nstatus                    integer,\nlast_update_time          datetime,\ncreated_at                datetime,\ncomment                   varchar(255),\nref_user_id               bigint,\nuser_id                   bigint,\nconstraint pk_fix_request primary key (id))\n;\n\ncreate table info (\nid                        bigint auto_increment not null,\nname                      varchar(255) not null,\nclassify                  varchar(255),\nenglish_name              varchar(255),\nphone                     varchar(255),\nurl                       varchar(255),\nvisible                   tinyint(1) default 0,\nstatus                    integer,\nimages                    longtext,\nsmall_images              longtext,\nlast_update_time          datetime,\ncreated_at                datetime,\ndescription1              longtext,\ndescription2              longtext,\ncomment                   varchar(255),\nconstraint pk_info primary key (id))\n;\n\ncreate table location (\nid                        bigint auto_increment not null,\nname                      varchar(255) not null,\nlast_update_time          datetime,\ncreated_at                datetime,\ncomment                   varchar(255),\nconstraint pk_location primary key (id))\n;\n\ncreate table material (\nid                        bigint auto_increment not null,\nname                      varchar(255) not null,\ncontent                   varchar(255),\nquantity                  bigint,\nprice                     Decimal(10,2),\nstatus                    integer,\nlast_update_time          datetime,\ncreated_at                datetime,\ncomment                   varchar(255),\nconstraint pk_material primary key (id))\n;\n\ncreate table member (\nid                        bigint auto_increment not null,\nis_default                tinyint(1) default 0,\nname                      varchar(255) not null,\ncode                      varchar(255),\nlast_update_time          datetime,\ncreated_at                datetime,\ncomment                   varchar(255),\nconstraint pk_member primary key (id))\n;\n\ncreate table purchase (\nid                        bigint auto_increment not null,\nname                      varchar(255) not null,\ncontent                   varchar(255),\nlast_update_time          datetime,\ncreated_at                datetime,\ncomment                   varchar(255),\nref_material_id           bigint,\nmaterial_id               bigint,\nref_user_id               bigint,\nuser_id                   bigint,\nconstraint pk_purchase primary key (id))\n;\n\ncreate table sms_info (\nid                        bigint auto_increment not null,\nname                      varchar(255),\nphone                     varchar(255),\ncheck_code                varchar(255),\nsend_xml                  longtext,\nreturn_table              varchar(255),\nreceive_xml               longtext,\ncode                      varchar(255),\nreturn_msg                varchar(255),\nlast_update_time          datetime,\ncreated_at                datetime,\ncomment                   varchar(255),\nconstraint pk_sms_info primary key (id))\n;\n\ncreate table use_request (\nid                        bigint auto_increment not null,\nname                      varchar(255) not null,\ncontent                   varchar(255),\nstatus                    integer,\nlast_update_time          datetime,\ncreated_at                datetime,\ncomment                   varchar(255),\nref_material_id           bigint,\nmaterial_id               bigint,\nref_user_id               bigint,\nuser_id                   bigint,\nconstraint pk_use_request primary key (id))\n;\n\ncreate table user (\nid                        bigint auto_increment not null,\nname                      varchar(255),\nlogin_name                varchar(255) not null,\npassword                  varchar(255) not null,\nhead_image                varchar(255),\nthird_id                  varchar(255),\nemail                     varchar(255),\nis_email_verified         tinyint(1) default 0,\nemail_key                 varchar(255),\nemail_over_time           datetime,\nimages                    varchar(400),\nsex_enum                  integer,\nphone                     varchar(255),\ncard_number               varchar(255),\ncountry                   varchar(255),\nprovince                  varchar(255),\ncity                      varchar(255),\nzone                      varchar(255),\naddress                   varchar(255),\nlast_update_time          datetime,\ncreated_at                datetime,\nlast_login_ip             varchar(255),\nlast_login_time           datetime,\nlast_login_ip_area        varchar(255),\nstatus                    integer,\ncomment                   varchar(2000),\nconstraint pk_user primary key (id))\n;\n\n\ncreate table auth_user (\nauth_id                        bigint not null,\nuser_id                        bigint not null,\nconstraint pk_auth_user primary key (auth_id, user_id))\n;\n\ncreate table depart_user (\ndepart_id                      bigint not null,\nuser_id                        bigint not null,\nconstraint pk_depart_user primary key (depart_id, user_id))\n;\n\ncreate table depart_material (\ndepart_id                      bigint not null,\nmaterial_id                    bigint not null,\nconstraint pk_depart_material primary key (depart_id, material_id))\n;\n\ncreate table material_depart (\nmaterial_id                    bigint not null,\ndepart_id                      bigint not null,\nconstraint pk_material_depart primary key (material_id, depart_id))\n;\n\ncreate table member_user (\nmember_id                      bigint not null,\nuser_id                        bigint not null,\nconstraint pk_member_user primary key (member_id, user_id))\n;\n\ncreate table user_depart (\nuser_id                        bigint not null,\ndepart_id                      bigint not null,\nconstraint pk_user_depart primary key (user_id, depart_id))\n;\n\ncreate table user_member (\nuser_id                        bigint not null,\nmember_id                      bigint not null,\nconstraint pk_user_member primary key (user_id, member_id))\n;\n\ncreate table user_auth (\nuser_id                        bigint not null,\nauth_id                        bigint not null,\nconstraint pk_user_auth primary key (user_id, auth_id))\n;\nalter table asset_use add constraint fk_asset_use_user_1 foreign key (user_id) references user (id) on delete restrict on update restrict;\ncreate index ix_asset_use_user_1 on asset_use (user_id);\nalter table fix_record add constraint fk_fix_record_user_2 foreign key (user_id) references user (id) on delete restrict on update restrict;\ncreate index ix_fix_record_user_2 on fix_record (user_id);\nalter table fix_request add constraint fk_fix_request_user_3 foreign key (user_id) references user (id) on delete restrict on update restrict;\ncreate index ix_fix_request_user_3 on fix_request (user_id);\nalter table purchase add constraint fk_purchase_material_4 foreign key (material_id) references material (id) on delete restrict on update restrict;\ncreate index ix_purchase_material_4 on purchase (material_id);\nalter table purchase add constraint fk_purchase_user_5 foreign key (user_id) references user (id) on delete restrict on update restrict;\ncreate index ix_purchase_user_5 on purchase (user_id);\nalter table use_request add constraint fk_use_request_material_6 foreign key (material_id) references material (id) on delete restrict on update restrict;\ncreate index ix_use_request_material_6 on use_request (material_id);\nalter table use_request add constraint fk_use_request_user_7 foreign key (user_id) references user (id) on delete restrict on update restrict;\ncreate index ix_use_request_user_7 on use_request (user_id);\n\n\n\nalter table auth_user add constraint fk_auth_user_auth_01 foreign key (auth_id) references auth (id) on delete restrict on update restrict;\n\nalter table auth_user add constraint fk_auth_user_user_02 foreign key (user_id) references user (id) on delete restrict on update restrict;\n\nalter table depart_user add constraint fk_depart_user_depart_01 foreign key (depart_id) references depart (id) on delete restrict on update restrict;\n\nalter table depart_user add constraint fk_depart_user_user_02 foreign key (user_id) references user (id) on delete restrict on update restrict;\n\nalter table depart_material add constraint fk_depart_material_depart_01 foreign key (depart_id) references depart (id) on delete restrict on update restrict;\n\nalter table depart_material add constraint fk_depart_material_material_02 foreign key (material_id) references material (id) on delete restrict on update restrict;\n\nalter table material_depart add constraint fk_material_depart_material_01 foreign key (material_id) references material (id) on delete restrict on update restrict;\n\nalter table material_depart add constraint fk_material_depart_depart_02 foreign key (depart_id) references depart (id) on delete restrict on update restrict;\n\nalter table member_user add constraint fk_member_user_member_01 foreign key (member_id) references member (id) on delete restrict on update restrict;\n\nalter table member_user add constraint fk_member_user_user_02 foreign key (user_id) references user (id) on delete restrict on update restrict;\n\nalter table user_depart add constraint fk_user_depart_user_01 foreign key (user_id) references user (id) on delete restrict on update restrict;\n\nalter table user_depart add constraint fk_user_depart_depart_02 foreign key (depart_id) references depart (id) on delete restrict on update restrict;\n\nalter table user_member add constraint fk_user_member_user_01 foreign key (user_id) references user (id) on delete restrict on update restrict;\n\nalter table user_member add constraint fk_user_member_member_02 foreign key (member_id) references member (id) on delete restrict on update restrict;\n\nalter table user_auth add constraint fk_user_auth_user_01 foreign key (user_id) references user (id) on delete restrict on update restrict;\n\nalter table user_auth add constraint fk_user_auth_auth_02 foreign key (auth_id) references auth (id) on delete restrict on update restrict;','SET FOREIGN_KEY_CHECKS=0;\n\ndrop table admin;\n\ndrop table admin_journal;\n\ndrop table asset_card;\n\ndrop table asset_handle;\n\ndrop table asset_type;\n\ndrop table asset_use;\n\ndrop table auth;\n\ndrop table auth_user;\n\ndrop table barcode;\n\ndrop table config;\n\ndrop table depart;\n\ndrop table depart_user;\n\ndrop table depart_material;\n\ndrop table dict;\n\ndrop table fix_record;\n\ndrop table fix_request;\n\ndrop table info;\n\ndrop table location;\n\ndrop table material;\n\ndrop table material_depart;\n\ndrop table member;\n\ndrop table member_user;\n\ndrop table purchase;\n\ndrop table sms_info;\n\ndrop table use_request;\n\ndrop table user;\n\ndrop table user_depart;\n\ndrop table user_member;\n\ndrop table user_auth;\n\nSET FOREIGN_KEY_CHECKS=1;','applied','');

#
# Structure for table "sms_info"
#

DROP TABLE IF EXISTS `sms_info`;
CREATE TABLE `sms_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `check_code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `send_xml` longtext COLLATE utf8_unicode_ci,
  `return_table` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `receive_xml` longtext COLLATE utf8_unicode_ci,
  `code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `return_msg` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `comment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "sms_info"
#


#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `login_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `head_image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `third_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_email_verified` tinyint(1) DEFAULT '0',
  `email_key` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email_over_time` datetime DEFAULT NULL,
  `images` varchar(400) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sex_enum` int(11) DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `card_number` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `country` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `province` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `city` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `zone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `last_login_ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `last_login_ip_area` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `comment` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1,'user_1','0','e10adc3949ba59abbe56e057f20f883e','0','0','0',1,'0',NULL,'0',0,'0','0','0','0','0','0','0',NULL,'2020-03-29 00:38:01','0',NULL,'0',0,'0'),(2,'user_2','1','e10adc3949ba59abbe56e057f20f883e','1','1','1',0,'1',NULL,'1',1,'1','1','1','1','1','1','1',NULL,'2020-03-29 00:38:01','1',NULL,'1',0,'1'),(3,'user_3','2','e10adc3949ba59abbe56e057f20f883e','2','2','2',1,'2',NULL,'2',2,'2','2','2','2','2','2','2',NULL,'2020-03-29 00:38:01','2',NULL,'2',0,'2');

#
# Structure for table "use_request"
#

DROP TABLE IF EXISTS `use_request`;
CREATE TABLE `use_request` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `comment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ref_material_id` bigint(20) DEFAULT NULL,
  `material_id` bigint(20) DEFAULT NULL,
  `ref_user_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_use_request_material_6` (`material_id`),
  KEY `ix_use_request_user_7` (`user_id`),
  CONSTRAINT `fk_use_request_material_6` FOREIGN KEY (`material_id`) REFERENCES `material` (`id`),
  CONSTRAINT `fk_use_request_user_7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "use_request"
#

INSERT INTO `use_request` VALUES (1,'useRequest_1','0',0,NULL,'2020-03-29 00:38:01','0',NULL,NULL,NULL,NULL),(2,'useRequest_2','1',1,NULL,'2020-03-29 00:38:01','1',NULL,NULL,NULL,NULL),(3,'useRequest_3','2',2,NULL,'2020-03-29 00:38:01','2',NULL,NULL,NULL,NULL);

#
# Structure for table "purchase"
#

DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `comment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ref_material_id` bigint(20) DEFAULT NULL,
  `material_id` bigint(20) DEFAULT NULL,
  `ref_user_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_purchase_material_4` (`material_id`),
  KEY `ix_purchase_user_5` (`user_id`),
  CONSTRAINT `fk_purchase_material_4` FOREIGN KEY (`material_id`) REFERENCES `material` (`id`),
  CONSTRAINT `fk_purchase_user_5` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "purchase"
#

INSERT INTO `purchase` VALUES (1,'purchase_1','0',NULL,'2020-03-29 00:38:01','0',NULL,NULL,NULL,NULL),(2,'purchase_2','1',NULL,'2020-03-29 00:38:01','1',NULL,NULL,NULL,NULL),(3,'purchase_3','2',NULL,'2020-03-29 00:38:01','2',NULL,NULL,NULL,NULL);

#
# Structure for table "member_user"
#

DROP TABLE IF EXISTS `member_user`;
CREATE TABLE `member_user` (
  `member_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`member_id`,`user_id`),
  KEY `fk_member_user_user_02` (`user_id`),
  CONSTRAINT `fk_member_user_user_02` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_member_user_member_01` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "member_user"
#


#
# Structure for table "fix_request"
#

DROP TABLE IF EXISTS `fix_request`;
CREATE TABLE `fix_request` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `comment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ref_user_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_fix_request_user_3` (`user_id`),
  CONSTRAINT `fk_fix_request_user_3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "fix_request"
#

INSERT INTO `fix_request` VALUES (1,'fixRequest_1','0',0,NULL,'2020-03-29 00:38:01','0',NULL,NULL),(2,'fixRequest_2','1',1,NULL,'2020-03-29 00:38:01','1',NULL,NULL),(3,'fixRequest_3','2',2,NULL,'2020-03-29 00:38:01','2',NULL,NULL);

#
# Structure for table "fix_record"
#

DROP TABLE IF EXISTS `fix_record`;
CREATE TABLE `fix_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `comment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ref_user_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_fix_record_user_2` (`user_id`),
  CONSTRAINT `fk_fix_record_user_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "fix_record"
#

INSERT INTO `fix_record` VALUES (1,'fixRecord_1','0',0,NULL,'2020-03-29 00:38:01','0',NULL,NULL),(2,'fixRecord_2','1',1,NULL,'2020-03-29 00:38:01','1',NULL,NULL),(3,'fixRecord_3','2',2,NULL,'2020-03-29 00:38:01','2',NULL,NULL);

#
# Structure for table "depart_user"
#

DROP TABLE IF EXISTS `depart_user`;
CREATE TABLE `depart_user` (
  `depart_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`depart_id`,`user_id`),
  KEY `fk_depart_user_user_02` (`user_id`),
  CONSTRAINT `fk_depart_user_user_02` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_depart_user_depart_01` FOREIGN KEY (`depart_id`) REFERENCES `depart` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "depart_user"
#


#
# Structure for table "auth_user"
#

DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user` (
  `auth_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`auth_id`,`user_id`),
  KEY `fk_auth_user_user_02` (`user_id`),
  CONSTRAINT `fk_auth_user_user_02` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_auth_user_auth_01` FOREIGN KEY (`auth_id`) REFERENCES `auth` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "auth_user"
#


#
# Structure for table "asset_use"
#

DROP TABLE IF EXISTS `asset_use`;
CREATE TABLE `asset_use` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `comment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ref_user_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_asset_use_user_1` (`user_id`),
  CONSTRAINT `fk_asset_use_user_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "asset_use"
#

INSERT INTO `asset_use` VALUES (1,'assetUse_1','0',0,NULL,'2020-03-29 00:38:01','0',NULL,NULL),(2,'assetUse_2','1',1,NULL,'2020-03-29 00:38:01','1',NULL,NULL),(3,'assetUse_3','2',2,NULL,'2020-03-29 00:38:01','2',NULL,NULL);

#
# Structure for table "user_auth"
#

DROP TABLE IF EXISTS `user_auth`;
CREATE TABLE `user_auth` (
  `user_id` bigint(20) NOT NULL,
  `auth_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`auth_id`),
  KEY `fk_user_auth_auth_02` (`auth_id`),
  CONSTRAINT `fk_user_auth_auth_02` FOREIGN KEY (`auth_id`) REFERENCES `auth` (`id`),
  CONSTRAINT `fk_user_auth_user_01` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "user_auth"
#


#
# Structure for table "user_depart"
#

DROP TABLE IF EXISTS `user_depart`;
CREATE TABLE `user_depart` (
  `user_id` bigint(20) NOT NULL,
  `depart_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`depart_id`),
  KEY `fk_user_depart_depart_02` (`depart_id`),
  CONSTRAINT `fk_user_depart_depart_02` FOREIGN KEY (`depart_id`) REFERENCES `depart` (`id`),
  CONSTRAINT `fk_user_depart_user_01` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "user_depart"
#


#
# Structure for table "user_member"
#

DROP TABLE IF EXISTS `user_member`;
CREATE TABLE `user_member` (
  `user_id` bigint(20) NOT NULL,
  `member_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`member_id`),
  KEY `fk_user_member_member_02` (`member_id`),
  CONSTRAINT `fk_user_member_member_02` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  CONSTRAINT `fk_user_member_user_01` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "user_member"
#

