CREATE DATABASE i18n_test;

USE i18n_test;

CREATE TABLE message(
	messageId varchar(20) NOT NULL,
	locale varchar(20),
	content varchar(50),
	PRIMARY KEY(messageid,locale)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;



INSERT INTO message VALUES('1000','en_US','Hello');
INSERT INTO message VALUES('1000','zh_CN','你好');
INSERT INTO message VALUES('1000','zh','你好');
