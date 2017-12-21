/*
SQLyog Community v12.5.0 (64 bit)
MySQL - 5.6.38-log : Database - killagram
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`killagram` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `killagram`;

/*Table structure for table `과일류` */

DROP TABLE IF EXISTS `과일류`;

CREATE TABLE `과일류` (
  `이름` varchar(20) NOT NULL,
  `기준` varchar(20) DEFAULT NULL,
  `칼로리` int(10) DEFAULT NULL,
  PRIMARY KEY (`이름`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `과일류` */

insert  into `과일류`(`이름`,`기준`,`칼로리`) values 
('감','1개',70),
('귤','1개',38),
('딸기','1개',5),
('레몬','1조각',45),
('멜론','1쪽',38),
('바나나','1개',93),
('방울토마토','4개',8),
('배','1개',156),
('복숭아','1개',91),
('사과','1개',98),
('수박','1조각',62),
('오렌지','1개',131),
('자몽','1개',95),
('참외','1개',62),
('키위','1개',54),
('토마토','1개',35),
('파인애플','1조각',23),
('포도','1송이',200);

/*Table structure for table `구이/튀김/전류` */

DROP TABLE IF EXISTS `구이/튀김/전류`;

CREATE TABLE `구이/튀김/전류` (
  `이름` varchar(20) NOT NULL,
  `기준` varchar(20) DEFAULT NULL,
  `칼로리` int(10) DEFAULT NULL,
  PRIMARY KEY (`이름`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `구이/튀김/전류` */

insert  into `구이/튀김/전류`(`이름`,`기준`,`칼로리`) values 
('갈비구이','1접시',220),
('갈치구이','1토막',100),
('감자전','1접시',150),
('감자튀김','1접시',125),
('계란말이','1접시',125),
('계란후라이','1접시',100),
('고등어구이','1토막',150),
('굴비','1토막',200),
('김구이','1접시',25),
('김치전','1접시',150),
('꽁치구이','1토막',175),
('녹두빈대떡','1접시',200),
('녹두전','1접시',200),
('닭다리튀김','1개',175),
('더덕구이','1접시',75),
('돼지갈비구이','1접시',225),
('돼지불고기','1접시',200),
('두부무침','1접시',200),
('맛탕','1접시',75),
('북어양념구이','1접시',125),
('삼겹살','1접시',200),
('삼치','1토막',125),
('새우튀김','1개',150),
('소갈비구이','1접시',225),
('소등심구이','1접시',175),
('소불고기','1접시',50),
('야채튀김','1접시',200),
('오징어양념구이','1접시',125),
('장어구이','1토막',225),
('조기구이','1토막',100),
('쥐포','1접시',50),
('파전','1접시',200),
('해물파전','1접시',175),
('호박전','1접시',100);

/*Table structure for table `국/찌개류` */

DROP TABLE IF EXISTS `국/찌개류`;

CREATE TABLE `국/찌개류` (
  `이름` varchar(20) NOT NULL,
  `기준` varchar(20) DEFAULT NULL,
  `칼로리` int(10) DEFAULT NULL,
  PRIMARY KEY (`이름`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `국/찌개류` */

insert  into `국/찌개류`(`이름`,`기준`,`칼로리`) values 
('갈비탕','1인분',630),
('감자탕','1인분',180),
('계란국','1인분',55),
('김치찌개','1인분',157),
('꽃게탕','1인분',138),
('닭곰탕','1인분',250),
('된장국','1인분',140),
('된장찌개','1인분',160),
('떡국','1인분',420),
('만두국','1인분',400),
('미역국','1인분',95),
('배추국','1인분',70),
('부대찌개','1인분',256),
('북어국','1인분',172),
('비지찌개','1인분',130),
('삼계탕','1인분',630),
('설렁탕','1인분',240),
('쇠고기무국','1인분',110),
('순두부찌개','1인분',240),
('알탕','1인분',184),
('어묵국','1인분',60),
('오이냉국','1인분',40),
('육개장','1인분',240),
('청국장','1인분',160),
('콩나물국','1인분',60),
('해물탕','1인분',200);

/*Table structure for table `김치/장아찌/젓갈류` */

DROP TABLE IF EXISTS `김치/장아찌/젓갈류`;

CREATE TABLE `김치/장아찌/젓갈류` (
  `이름` varchar(20) NOT NULL,
  `기준` varchar(20) DEFAULT NULL,
  `칼로리` int(10) DEFAULT NULL,
  PRIMARY KEY (`이름`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `김치/장아찌/젓갈류` */

insert  into `김치/장아찌/젓갈류`(`이름`,`기준`,`칼로리`) values 
('게장','1종지',78),
('깍두기','1접시',25),
('나박김치','1접시',9),
('단무지','1접시',2),
('동치미','1접시',22),
('명란젓','1종지',18),
('배추김치','1접시',11),
('새우젓','1종지',8),
('어리굴젓','1종지',13),
('열무김치','1접시',25),
('오이소배기','1접시',25),
('오이지','1접시',11),
('오이피클','1접시',21),
('오징어젓','1종지',12),
('총각김치','1접시',25);

/*Table structure for table `밥/죽/떡류` */

DROP TABLE IF EXISTS `밥/죽/떡류`;

CREATE TABLE `밥/죽/떡류` (
  `이름` varchar(20) NOT NULL,
  `기준` varchar(20) DEFAULT NULL,
  `칼로리` int(10) DEFAULT NULL,
  PRIMARY KEY (`이름`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `밥/죽/떡류` */

insert  into `밥/죽/떡류`(`이름`,`기준`,`칼로리`) values 
('검정콩밥','1공기',340),
('경단','5개',240),
('고구마','1개',250),
('닭죽','1인분',670),
('백설기','1쪽',234),
('보리밥','1공기',290),
('볶음밥','1인분',750),
('비빔밥','1인분',525),
('송편','5개',215),
('시루떡','1조각',205),
('씨리얼','1공기',350),
('야채죽','1인분',215),
('약식','1쪽',130),
('오므라이스','1인분',680),
('오징어덮밥','1인분',550),
('인절미','6개',450),
('자장밥','1인분',370),
('잣죽','1인분',275),
('절편','5개',220),
('현미밥','1공기',321),
('흰쌀밥','1공기',300),
('흰죽','1공기',150);

/*Table structure for table `사용자` */

DROP TABLE IF EXISTS `사용자`;

CREATE TABLE `사용자` (
  `id` varchar(12) NOT NULL,
  `password` varchar(12) NOT NULL,
  `name` varchar(12) NOT NULL,
  `age` int(11) NOT NULL,
  `gender` varchar(12) NOT NULL,
  `height` float NOT NULL,
  `weight` float NOT NULL,
  `goal_weight` float NOT NULL,
  PRIMARY KEY (`id`,`password`,`name`,`age`,`gender`,`height`,`weight`,`goal_weight`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `사용자` */

insert  into `사용자`(`id`,`password`,`name`,`age`,`gender`,`height`,`weight`,`goal_weight`) values 
('sun910715','014015','황보선',27,'남',173,70,65),
('tester','1234','테스터',23,'M',180,80,75);

/*Table structure for table `양식` */

DROP TABLE IF EXISTS `양식`;

CREATE TABLE `양식` (
  `이름` varchar(20) NOT NULL,
  `기준` varchar(20) DEFAULT NULL,
  `칼로리` int(10) DEFAULT NULL,
  PRIMARY KEY (`이름`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `양식` */

insert  into `양식`(`이름`,`기준`,`칼로리`) values 
('감자샐러드','1인분',183),
('그라탕','1인분',414),
('돈까스정식','1인분',980),
('라자냐','1인분',390),
('멕시칸샐러드','1인분',139),
('생선까스 정식','1인분',880),
('소고기스프','1인분',63),
('스크램블에그','1인분',107),
('안심스테이크','1인분',860),
('야채샐러드','1인분',100),
('야채오믈렛','1인분',140),
('오므라이스','1인분',680),
('치킨도리아','1인분',979),
('카레라이스','1인분',580),
('케이준샐러드','1인분',154),
('크림스프','1인분',72),
('피자','1조각',250),
('해물스파게티','1인분',630),
('햄버거스테이크 정식','1인분',900);

/*Table structure for table `음료` */

DROP TABLE IF EXISTS `음료`;

CREATE TABLE `음료` (
  `이름` varchar(20) NOT NULL,
  `기준` varchar(20) DEFAULT NULL,
  `칼로리` int(10) DEFAULT NULL,
  PRIMARY KEY (`이름`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `음료` */

insert  into `음료`(`이름`,`기준`,`칼로리`) values 
('녹차','1잔',17),
('두유','1컵',130),
('레몬차','1잔',59),
('밀크커피','1잔',42),
('블랙커피','1잔',5),
('사이다','1캔',100),
('수정과','1컵',200),
('식혜','1컵',208),
('쌍화차','1잔',57),
('오렌지주스','1컵',100),
('우유','1컵',120),
('율무차','1잔',78),
('커피우유','1컵',126),
('콜라','1캔',89),
('홍차','1잔',4);

/*Table structure for table `일식/중식` */

DROP TABLE IF EXISTS `일식/중식`;

CREATE TABLE `일식/중식` (
  `이름` varchar(20) NOT NULL,
  `기준` varchar(20) DEFAULT NULL,
  `칼로리` int(10) DEFAULT NULL,
  PRIMARY KEY (`이름`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `일식/중식` */

insert  into `일식/중식`(`이름`,`기준`,`칼로리`) values 
('군만두','1인분',325),
('김초밥','1인분',500),
('깐풍기','1인분',330),
('날치알초밥','1개',53),
('마파두부','1인분',130),
('메밀소바','1인분',450),
('볶음밥','1인분',540),
('새우초밥','1개',56),
('어묵','1인분',100),
('오징어초밥','1개',45),
('우동','1인분',470),
('울면','1인분',520),
('유부초밥','1인분',570),
('자장면','1인분',537),
('잡채밥','1인분',550),
('짬뽕','1인분',510),
('탕수육','1인분',340),
('튀김우동','1인분',577),
('회덮밥','1인분',520);

/*Table structure for table `일품요리/분식` */

DROP TABLE IF EXISTS `일품요리/분식`;

CREATE TABLE `일품요리/분식` (
  `이름` varchar(20) NOT NULL,
  `기준` varchar(20) DEFAULT NULL,
  `칼로리` int(20) DEFAULT NULL,
  PRIMARY KEY (`이름`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `일품요리/분식` */

insert  into `일품요리/분식`(`이름`,`기준`,`칼로리`) values 
('고기만두','1인분',270),
('골뱅이무침','1접시',100),
('김말이','1개',130),
('김밥','1줄',475),
('김치볶음밥','1인분',450),
('돌솥밥','1인분',375),
('떡볶이','1인분',480),
('라면','1봉지',526),
('라볶이','1접시',525),
('물냉면','1인분',450),
('비빔국수','1인분',535),
('비빔냉면','1인분',500),
('비빔밥','1인분',525),
('사발면','1인분',465),
('쇠고기덮밥','1인분',450),
('순대','1인분',520),
('쫄면','1인분',295),
('칼국수','1인분',500),
('콩국수','1인분',515),
('콩나물덮밥','1인분',400),
('해물덮밥','1인분',425);

/*Table structure for table `조림/볶음류` */

DROP TABLE IF EXISTS `조림/볶음류`;

CREATE TABLE `조림/볶음류` (
  `이름` varchar(20) NOT NULL,
  `기준` varchar(20) DEFAULT NULL,
  `칼로리` int(10) DEFAULT NULL,
  PRIMARY KEY (`이름`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `조림/볶음류` */

insert  into `조림/볶음류`(`이름`,`기준`,`칼로리`) values 
('갈치조림','1접시',125),
('감자조림','1접시',75),
('고등어조림','1접시',200),
('김치볶음','1접시',100),
('깻잎조림','1접시',75),
('낙지볶음','1접시',125),
('닭도리탕','1접시',250),
('도라지나물','1접시',100),
('마늘쫑조림','1접시',75),
('마파두부','1접시',125),
('멸치볶음','1접시',100),
('미역줄기볶음','1접시',75),
('버섯볶음','1접시',50),
('소시지야채볶음','1접시',175),
('쇠고기장조림','1접시',125),
('오징어볶음','1접시',150),
('우엉조림','1접시',100),
('잡채','1접시',175),
('콩자반','1접시',100),
('호박볶음','1접시',50);

/*Table structure for table `주류` */

DROP TABLE IF EXISTS `주류`;

CREATE TABLE `주류` (
  `이름` varchar(20) DEFAULT NULL,
  `기준` varchar(20) DEFAULT NULL,
  `칼로리` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `주류` */

insert  into `주류`(`이름`,`기준`,`칼로리`) values 
('소주','1잔',71),
('막걸리','1잔',110),
('위스키','1잔',140),
('맥주','1잔',86);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
