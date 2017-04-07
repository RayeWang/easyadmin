-- 初始化菜单数据
INSERT INTO `menu` VALUES ('1', '系统首页', '/admin/index', null, '2', '1', '0', '0', '2017-03-31 20:16:57', '0', null, '1');
INSERT INTO `menu` VALUES ('2', '修改密码', '/admin/user/updatepass', 'fa-wrench', '2', '0', '0', '0', '2017-04-05 21:33:39', '0', null, '1');
INSERT INTO `menu` VALUES ('3', '系统配置', '12', 'fa-wrench', '0', '1', '0', '0', '2017-03-31 20:16:43', '0', '2017-04-05 20:30:53', '1');
INSERT INTO `menu` VALUES ('4', '菜单配置', '/admin/menu', 'fa-list', '0', '1', '3', '0', '2017-03-31 20:16:45', '0', '2017-04-05 20:31:10', '1');
INSERT INTO `menu` VALUES ('5', '角色管理', '/admin/role', null, '0', '2', '3', '0', '2017-03-31 20:16:48', '0', null, '1');
INSERT INTO `menu` VALUES ('6', '角色权限', '/admin/role/menu', null, '2', '0', '3', '0', '2017-03-31 20:16:52', '0', null, '1');
INSERT INTO `menu` VALUES ('7', '用户管理', '/admin/user', null, '1', '2', '3', '0', '2017-03-31 20:16:54', '0', null, '1');
INSERT INTO `menu` VALUES ('8', '新增菜单', '/admin/menu/edit', null, '2', '0', '4', '0', '2017-03-31 20:17:01', '0', null, '1');
INSERT INTO `menu` VALUES ('9', '删除菜单', '/admin/menu/delete', null, '2', '0', '4', '0', '2017-03-31 20:17:04', '0', null, '1');
INSERT INTO `menu` VALUES ('10', '编辑角色', '/admin/role/edit', null, '2', '0', '5', '0', '2017-03-31 20:17:06', '0', null, '1');
INSERT INTO `menu` VALUES ('11', '删除角色', '/admin/role/delete', null, '2', '0', '5', '0', '2017-03-31 20:17:07', '0', null, '1');
INSERT INTO `menu` VALUES ('12', '角色资源管理', '/admin/role/menu', null, '2', '0', '5', '0', '2017-03-31 20:17:08', '0', null, '1');
INSERT INTO `menu` VALUES ('13', '编辑用户', '/admin/user/edit', null, '2', '0', '7', '0', '2017-03-31 20:17:09', '0', null, '1');
INSERT INTO `menu` VALUES ('14', '删除用户', '/admin/user/delete', null, '2', '0', '7', '0', '2017-03-31 20:17:10', '0', null, '1');
INSERT INTO `menu` VALUES ('15', '用户角色管理', '/admin/user/role', null, '2', '0', '7', '0', '2017-03-31 20:17:12', '0', null, '1');
INSERT INTO `menu` VALUES ('16', '菜单配置', '', null, '2', '0', '7', '20', '2017-04-02 11:38:28', '0', null, '1');
-- 初始化用户数据
INSERT INTO `admin_user` VALUES ('-1', 'root', '863DFABFFFB800C9CBDBC16760D07735', 'admin@raye.wang', '2017-04-07 22:23:15', '0', '1', '1', '2017-04-07 22:23:15');
INSERT INTO `admin_user` VALUES ('1', 'test', 'C4CA4238A0B923820DCC509A6F75849B', 'test@raye.wang', '2017-04-05 23:18:58', '20', '1', '1', '2017-04-05 22:55:17');
-- 初始化权限
INSERT INTO `role` VALUES ('-1', '默认权限', '2017-03-18 17:35:42', '0', '2017-04-05 21:32:16', null, '用户默认拥有的权限，无需授权给用户');
-- 初始化角色权限
INSERT INTO `role_menu` VALUES ('-1', '1', '1', '0', '2017-04-06 23:18:24');
INSERT INTO `role_menu` VALUES ('-1', '2', '1', '0', '2017-04-06 23:18:24');