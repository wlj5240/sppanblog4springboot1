/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.56-MariaDB : Database - SPPanBlog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `tb_blog` */

DROP TABLE IF EXISTS `tb_blog`;

CREATE TABLE `tb_blog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` longtext,
  `create_at` datetime DEFAULT NULL,
  `featured` int(11) DEFAULT NULL,
  `privacy` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `summary` varchar(500) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `views` int(11) NOT NULL DEFAULT '0',
  `author_id` bigint(20) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK26qshdwihej4t9niu5e4lmgxv` (`author_id`),
  KEY `FKjawve4qwb5wvb0jkvpv6rs20k` (`category_id`),
  CONSTRAINT `FKjawve4qwb5wvb0jkvpv6rs20k` FOREIGN KEY (`category_id`) REFERENCES `tb_category` (`id`),
  CONSTRAINT `FK26qshdwihej4t9niu5e4lmgxv` FOREIGN KEY (`author_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tb_blog` */

insert  into `tb_blog`(`id`,`content`,`create_at`,`featured`,`privacy`,`status`,`summary`,`tags`,`title`,`views`,`author_id`,`category_id`) values (1,'<p>风吹沙 蝶恋花 千古佳话<br><em>似水中月 情迷着镜中花</em><br>竹篱笆 木琵琶 拱桥月下<br><em>谁在弹唱 思念远方牵挂</em><br>那年仲夏 你背上行囊离开家<br><em>古道旁 我欲语泪先下</em><br>庙里求签 我哭诉青梅等竹马<br><em>求 菩萨保佑我俩</em><br>不停的猜 猜 猜 又卜了一卦<br><em>吉凶祸福 还是担惊受怕</em><br>对你的爱 爱 爱 望断了天涯<br><em>造化弄人 缘分阴错阳差</em></p>','2018-03-27 11:17:13',0,0,0,'那年仲夏，你背上行囊离开家','other','那年仲夏',1,1,1);

/*Table structure for table `tb_category` */

DROP TABLE IF EXISTS `tb_category`;

CREATE TABLE `tb_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `count` int(11) NOT NULL DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `tb_category` */

insert  into `tb_category`(`id`,`count`,`name`,`status`) values (1,1,'原创',0);
insert  into `tb_category`(`id`,`count`,`name`,`status`) values (2,0,'转载',0);
insert  into `tb_category`(`id`,`count`,`name`,`status`) values (3,0,'杂记',0);

/*Table structure for table `tb_login_log` */

DROP TABLE IF EXISTS `tb_login_log`;

CREATE TABLE `tb_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) DEFAULT NULL,
  `login_at` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7vcmphcy2rt1aatmheivnk31r` (`user_id`),
  CONSTRAINT `FK7vcmphcy2rt1aatmheivnk31r` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tb_login_log` */

insert  into `tb_login_log`(`id`,`ip`,`login_at`,`user_id`) values (1,'182.150.40.60','2018-03-26 18:59:45',1);
insert  into `tb_login_log`(`id`,`ip`,`login_at`,`user_id`) values (2,'182.150.40.60','2018-03-27 11:03:58',1);

/*Table structure for table `tb_options` */

DROP TABLE IF EXISTS `tb_options`;

CREATE TABLE `tb_options` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `option_key` varchar(255) DEFAULT NULL,
  `option_value` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `tb_options` */

insert  into `tb_options`(`id`,`description`,`option_key`,`option_value`) values (1,NULL,'siteIcp','蜀ICP备17013510号');
insert  into `tb_options`(`id`,`description`,`option_key`,`option_value`) values (2,NULL,'siteAboutMe','<p>风吹沙 蝶恋花 千古佳话<br>似水中月 情迷着镜中花<br>竹篱笆 木琵琶 拱桥月下<br>谁在弹唱 思念远方牵挂<br>那年仲夏 你背上行囊离开家<br>古道旁 我欲语泪先下<br>庙里求签 我哭诉青梅等竹马<br>求 菩萨保佑我俩<br>不停的猜 猜 猜 又卜了一卦<br>吉凶祸福 还是担惊受怕<br>对你的爱 爱 爱 望断了天涯<br>造化弄人 缘分阴错阳差</p>');
insert  into `tb_options`(`id`,`description`,`option_key`,`option_value`) values (3,NULL,'siteDescription','那年仲夏，你背上行囊离开家。');
insert  into `tb_options`(`id`,`description`,`option_key`,`option_value`) values (4,NULL,'siteBanner','http://www.hollischuang.com/wp-content/uploads/2015/04/xihu_meitu_2.jpg,http://www.hollischuang.com/wp-content/uploads/2015/04/songhuajiang_meitu_4.jpg');
insert  into `tb_options`(`id`,`description`,`option_key`,`option_value`) values (5,NULL,'siteNotice','请注意，老夫要发布公告了。');
insert  into `tb_options`(`id`,`description`,`option_key`,`option_value`) values (6,NULL,'siteName','SPPanBlog');

/*Table structure for table `tb_session` */

DROP TABLE IF EXISTS `tb_session`;

CREATE TABLE `tb_session` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `expire_at` bigint(20) DEFAULT NULL,
  `session_id` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK31riknm358i4amq9cqvye97r8` (`user_id`),
  CONSTRAINT `FK31riknm358i4amq9cqvye97r8` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tb_session` */

insert  into `tb_session`(`id`,`expire_at`,`session_id`,`user_id`) values (2,1522127038085,'a76245e4a79b43f8be6e8ba21bda7766',1);

/*Table structure for table `tb_tag` */

DROP TABLE IF EXISTS `tb_tag`;

CREATE TABLE `tb_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `count` int(11) NOT NULL DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tb_tag` */

insert  into `tb_tag`(`id`,`count`,`name`,`status`) values (1,1,'other',0);

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`avatar`,`create_at`,`description`,`ip`,`nick_name`,`password`,`salt`,`status`,`user_name`) values (1,'https://gitee.com/uploads/78/559378_whoismy8023.png?1482721399','2017-01-24 09:41:30','这是描述吧','127.0.0.1','SPPan','8AKL0L5IF60DUJ91CATBFDTCN','zmxyyZJkE-N6JjRhujp6U8l4Yu7vuQDZ',0,'whoismy8023@163.com');

/*Table structure for table `tb_youlian` */

DROP TABLE IF EXISTS `tb_youlian`;

CREATE TABLE `tb_youlian` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `title` varchar(255) DEFAULT NULL,
  `url` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_youlian` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
