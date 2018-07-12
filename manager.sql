-- 后台管理相关sql语句
-- 创建菜单表
DROP TABLE IF EXISTS menu;
CREATE TABLE IF NOT EXISTS menu(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
	`name` VARCHAR(32) NOT NULL COMMENT '菜单名称',
	url VARCHAR(500) COMMENT '网址',
	icon VARCHAR(20) COMMENT '显示的图标',
	menutype ENUM('0','1','2') NOT NULL DEFAULT '0' COMMENT '类型，0 菜单，1 连接网址,2 隐藏连接',
	display INT NOT NULL DEFAULT 1 COMMENT '显示排序',
	parentid INT NOT NULL DEFAULT 0 COMMENT '父级的id，引用本表id字段',
	creator INT NOT NULL DEFAULT 0 COMMENT '创建者id，0为超级管理员',
	createtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	updateuser INT  COMMENT '更新者id',
	updatetime TIMESTAMP NULL COMMENT '更新时间',
	flag ENUM('0','1') NOT NULL DEFAULT '1' COMMENT '是否启用，0 禁用，1启用'
)ENGINE=InnoDB;
-- 后台管理用户表
DROP TABLE IF EXISTS admin_user;
CREATE TABLE IF NOT EXISTS admin_user(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '用户表主键',
  tenantid INT NOT NULL COMMENT '租户id，0为系统用户',
  name VARCHAR(20) NOT NULL COMMENT '用户名',
  psw VARCHAR(32) NOT NULL COMMENT '用户密码MD5加密',
  email VARCHAR(32) NOT NULL COMMENT '用户邮箱',
  creator INT NOT NULL COMMENT '创建人，0为初始化',
	createtime TIMESTAMP NOT NULL COMMENT '创建时间',
  flag INT(1) NOT NULL DEFAULT 1 COMMENT '用户状态，1启用，0禁用',
  logintime TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录时间',
  updateuser INT  COMMENT '更新者id',
	updatetime TIMESTAMP NULL COMMENT '更新时间'
)ENGINE=InnoDB;
-- 租户表
DROP TABLE IF EXISTS tenant;
CREATE TABLE IF NOT EXISTS tenant(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '租户主键id',
  tenantname VARCHAR(32) NOT NULL COMMENT '租户名称',
  account VARCHAR(32) NOT NULL COMMENT '租户联系人',
  phone VARCHAR(12) NOT NULL COMMENT '租户手机号',
  begintime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '租户有效期开始时间',
  endtime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '租户有效期结束时间',
  flag INT NOT NULL DEFAULT 1 COMMENT '租户状态，0  未启用  1 启用  与时间共同控制&&'
);
-- 创建角色表
DROP TABLE IF EXISTS role;
CREATE TABLE IF NOT EXISTS role(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '角色表主键',
  name VARCHAR(20) NOT NULL COMMENT '角色名称',
  createtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  creator INT DEFAULT 0 COMMENT '用户id，0为角色，有关联时则为用户的单独权限',
  description VARCHAR(200) COMMENT '角色描述',
  updateuser INT  COMMENT '更新者id',
	updatetime TIMESTAMP NULL COMMENT '更新时间'
)ENGINE=InnoDB;
-- 创建用户与角色关联表
DROP TABLE IF EXISTS user_role;
CREATE TABLE IF NOT EXISTS user_role(
 userid INT NOT NULL COMMENT '用户id',
 roleid INT NOT NULL COMMENT '角色id',
 creator INT NOT NULL COMMENT '创建人，0为初始化',
 createtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
)ENGINE=InnoDB;
ALTER TABLE user_role add constraint PK01_user_role primary key (userid,roleid);
-- 创建角色与菜单（资源的关联表）
DROP TABLE IF EXISTS role_menu;
CREATE TABLE IF NOT EXISTS role_menu(
 roleid INT NOT NULL COMMENT '角色id',
 menuid INT NOT NULL COMMENT '菜单id',
 flag INT(1) NOT NULL DEFAULT 1 COMMENT '1为有权限，0为没有权限（防止以后会出现角色有权限但是个人没有权限的情况）',
 creator INT NOT NULL COMMENT '创建人，0为初始化',
 createtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
)ENGINE=InnoDB;
ALTER TABLE role_menu add constraint PK01_role_menu primary key (menuid,roleid);


-- 计算传入字符串的总length
DELIMITER $$

DROP function IF EXISTS `func_split_TotalLength` $$

CREATE FUNCTION `func_split_TotalLength`

(f_string varchar(1000),f_delimiter varchar(5)) RETURNS int(11)

BEGIN

    return 1+(length(f_string) - length(replace(f_string,f_delimiter,'')));

END$$

DELIMITER;
-- 拆分传入的字符串，返回拆分后的新字符串
DELIMITER $$

DROP function IF EXISTS `func_split` $$

CREATE  FUNCTION `func_split`

(f_string varchar(1000),f_delimiter varchar(5),f_order int) RETURNS varchar(255) CHARSET utf8

BEGIN
        declare result varchar(255) default '';

        set result = reverse(substring_index(reverse(substring_index(f_string,f_delimiter,f_order)),f_delimiter,1));

        return result;

END$$

DELIMITER;
-- 更新角色权限的存储过程
delimiter $$
DROP PROCEDURE IF EXISTS `role_menu_update` ;

CREATE PROCEDURE `role_menu_update`

(IN menuids varchar(3000),IN i_roleid INT,IN userid INT)

BEGIN

-- 拆分结果

DECLARE cnt INT DEFAULT 0;

DECLARE i INT DEFAULT 0;

SET cnt = func_split_TotalLength(menuids,',');
DELETE FROM role_menu WHERE roleid = i_roleid;

WHILE i < cnt

DO

    SET i = i + 1;

    INSERT INTO role_menu(roleid,menuid,creator) VALUES (i_roleid,func_split(menuids,',',i),userid);

END WHILE;

END $$

-- 更新用户角色信息
delimiter $$
DROP PROCEDURE IF EXISTS `user_role_update` ;

CREATE PROCEDURE `user_role_update`

(IN roleids varchar(3000),IN i_userid INT,IN i_creator INT)

BEGIN

-- 拆分结果

DECLARE cnt INT DEFAULT 0;

DECLARE i INT DEFAULT 0;

SET cnt = func_split_TotalLength(roleids,',');
DELETE FROM user_role WHERE userid = i_userid;

WHILE i < cnt

DO

    SET i = i + 1;

    INSERT INTO user_role(userid,roleid,creator) VALUES (i_userid,func_split(roleids,',',i),i_creator);

END WHILE;

END $$

-- 删除菜单的存储过程
DROP PROCEDURE IF EXISTS `delete_menu`;
CREATE  PROCEDURE `delete_menu`(IN `menuid` int)
BEGIN

	DECLARE rowNUM INT DEFAULT 0;
	create temporary table if not exists menu_del_temp -- 不存在则创建临时表
  (
     id INT
  );
	create temporary table if not exists menu_del_temp2 -- 不存在则创建临时表
  (
     id INT
  );
create temporary table if not exists menu_del_temp3 -- 不存在则创建临时表
  (
     id INT
  );
	TRUNCATE TABLE menu_del_temp2;
	TRUNCATE TABLE menu_del_temp; -- 清空临时表
		INSERT INTO menu_del_temp SELECT id FROM  menu where parentid=menuid;
	-- DELETE FROM category WHERE ID IN (SELECT id FROM category_del_temp);
	INSERT INTO menu_del_temp2 SELECT id FROM  menu where parentid IN (SELECT id FROM menu_del_temp);
	SELECT COUNT(id) INTO rowNUM FROM menu_del_temp2;
	WHILE rowNUM > 0 DO
		INSERT INTO menu_del_temp SELECT id FROM menu_del_temp2;
		TRUNCATE TABLE menu_del_temp3;
		INSERT INTO menu_del_temp3 SELECT id FROM menu_del_temp2;
		TRUNCATE TABLE menu_del_temp2;
		INSERT INTO menu_del_temp2 SELECT id FROM  menu where parentid IN (SELECT id FROM menu_del_temp3);
		SELECT COUNT(id) INTO rowNUM FROM menu_del_temp2;
	END WHILE;
	INSERT INTO menu_del_temp(id) values(menuid);
	DELETE FROM menu WHERE id IN (SELECT id FROM menu_del_temp);
	DELETE FROM role_menu WHERE menuid IN (SELECT id FROM menu_del_temp);
END;
