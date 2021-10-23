
/* 系统菜单表 */
create table if not exists sys_menu
(
    menu_id     bigint auto_increment comment '菜单ID' primary key,
    role_id     bigint(20)               not null comment '菜单所属角色',
    menu_name   varchar(50)              not null comment '菜单名称',
    parent_id   bigint       default 0   null comment '父菜单ID',
    order_num   int(4)       default 0   null comment '显示顺序',
    url         varchar(200) default '#' null comment '请求地址',
    menu_type   char         default ''  null comment '菜单类型（M目录 C菜单）',
    visible     char         default '0' null comment '菜单状态（0显示 1隐藏）',
    icon        varchar(100) default '#' null comment '菜单图标',
    create_by   varchar(64)  default ''  null comment '创建者',
    create_time datetime                 null comment '创建时间',
    update_by   varchar(64)  default ''  null comment '更新者',
    update_time datetime                 null comment '更新时间',
    remark      varchar(500) default ''  null comment '备注'
)engine=innodb default charset=utf8
    comment '菜单权限表';

INSERT INTO `sys_menu` VALUES (1, 1, '系统管理', 0, 1, '#', '1', '0', 'fa fa-gear', 'admin', '2021-09-18 11:01:43', 'admin', '2021-09-18 11:01:50', '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, 1, '系统监控', 0, 2, '#', '1', '0', 'fa fa-video-camera', 'admin', '2021-09-18 11:03:37', 'admin', '2021-09-18 11:03:42', '系统监控目录');
INSERT INTO `sys_menu` VALUES (100, 1, '菜单管理', 1, 1, 'system/menu/menu.html', '2', '0', 'fa fa-th-list', 'admin', '2021-09-18 11:07:38', 'admin', '2021-09-18 11:07:42', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (200, 1, '在线用户', 2, 1, 'monitor/online/online.html', '2', '0', 'fa fa-user-circle', 'admin', '2021-09-22 09:34:00', 'admin', '2021-09-22 09:34:06', '在线用户菜单');

-- ----------------------------
-- 2、用户信息表
-- ----------------------------
drop table if exists sys_user;
create table sys_user (
      user_id           bigint(20)      not null auto_increment    comment '用户ID',
      dept_id           bigint(20)      default null               comment '部门ID',
      user_name         varchar(30)     not null                   comment '用户账号',
      nick_name         varchar(30)     not null                   comment '用户昵称',
      user_type         varchar(2)      default '00'               comment '用户类型（00系统用户）',
      email             varchar(50)     default ''                 comment '用户邮箱',
      phonenumber       varchar(11)     default ''                 comment '手机号码',
      sex               char(1)         default '0'                comment '用户性别（0男 1女 2未知）',
      avatar            varchar(100)    default ''                 comment '头像地址',
      password          varchar(100)    default ''                 comment '密码',
      status            char(1)         default '0'                comment '帐号状态（0正常 1停用）',
      del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
      login_ip          varchar(128)    default ''                 comment '最后登录IP',
      login_date        datetime                                   comment '最后登录时间',
      create_by         varchar(64)     default ''                 comment '创建者',
      create_time       datetime                                   comment '创建时间',
      update_by         varchar(64)     default ''                 comment '更新者',
      update_time       datetime                                   comment '更新时间',
      remark            varchar(500)    default null               comment '备注',
      primary key (user_id)
) engine=innodb auto_increment=100 comment = '用户信息表';

-- ----------------------------
-- 角色信息表
-- ----------------------------
drop table if exists sys_role;
create table sys_role (
    role_id           bigint(20)      not null auto_increment    comment '角色ID',
    role_name         varchar(30)     not null                   comment '角色名称',
    role_key          varchar(100)    not null                   comment '角色权限字符串',
    role_sort         int(4)          not null                   comment '显示顺序',
    data_scope        char(1)         default '1'                comment '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    status            char(1)         not null                   comment '角色状态（0正常 1停用）',
    del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
    create_by         varchar(64)     default ''                 comment '创建者',
    create_time       datetime                                   comment '创建时间',
    update_by         varchar(64)     default ''                 comment '更新者',
    update_time       datetime                                   comment '更新时间',
    remark            varchar(500)    default null               comment '备注',
    primary key (role_id)
) engine=innodb auto_increment=100 default charset=utf8 comment = '角色信息表';


-- ----------------------------
-- 初始化-角色信息表数据
-- ----------------------------
insert into sys_role values('1', '超级管理员', 'admin',  1, 1, '0', '0', 'admin', sysdate(), '', null, '超级管理员');
insert into sys_role values('2', '普通角色',   'common', 2, 2, '0', '0', 'admin', sysdate(), '', null, '普通角色');

    -- ----------------------------
-- 14、系统访问记录
-- ----------------------------
drop table if exists sys_logininfo;
create table sys_logininfo (
  info_id        bigint(20)     not null auto_increment   comment '访问ID',
  login_name     varchar(50)    default ''                comment '登录账号',
  ipaddr         varchar(128)   default ''                comment '登录IP地址',
  login_location varchar(255)   default ''                comment '登录地点',
  browser        varchar(50)    default ''                comment '浏览器类型',
  os             varchar(50)    default ''                comment '操作系统',
  status         char(1)        default '0'               comment '登录状态（0成功 1失败）',
  msg            varchar(255)   default ''                comment '提示消息',
  login_time     datetime                                 comment '访问时间',
  primary key (info_id)
) engine=innodb auto_increment=100  default charset=utf8 comment = '系统访问记录';


-- ----------------------------
-- 15、在线用户记录
-- ----------------------------
drop table if exists sys_user_online;
create table sys_user_online (
  sessionId         varchar(50)   default ''                comment '用户会话id',
  login_name        varchar(50)   default ''                comment '登录账号',
  dept_name         varchar(50)   default ''                comment '部门名称',
  ipaddr            varchar(128)  default ''                comment '登录IP地址',
  login_location    varchar(255)  default ''                comment '登录地点',
  browser           varchar(50)   default ''                comment '浏览器类型',
  os                varchar(50)   default ''                comment '操作系统',
  status            varchar(10)   default ''                comment '在线状态on_line在线off_line离线',
  start_timestamp   datetime                                comment 'session创建时间',
  last_access_time  datetime                                comment 'session最后访问时间',
  expire_time       int(5)        default 0                 comment '超时时间，单位为分钟',
  primary key (sessionId)
) engine=innodb default charset=utf8 comment = '在线用户记录';

