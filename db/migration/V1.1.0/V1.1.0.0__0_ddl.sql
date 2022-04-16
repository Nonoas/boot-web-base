-- 定义存储过程：当表字段存在时执行
drop procedure if exists proc_ifColumnExist;
delimiter $$
create procedure proc_ifColumnExist(tableName varchar(50), colName varchar(50), ExeSql varchar(2000)) comment '当表字段存在时执行'
begin
    declare cnt int default 0;
    select count(*) into cnt from information_schema.columns where table_name = tableName and column_name = colName and table_schema = database();
    set @SQL = ExeSql;
    if (cnt > 0) then prepare stmt from @SQL; execute stmt; end if;
end $$

-- 定义存储过程：添加字段，当表字段不存在时执行
drop procedure if exists proc_addColumn $$
delimiter $$
create procedure proc_addColumn(tableName varchar(50), colName varchar(50), ExeSql varchar(2000)) comment '添加字段，当表字段不存在时执行'
begin
    declare cnt int default 0;
    select count(*) into cnt from information_schema.columns where table_name = tableName and column_name = colName and table_schema = database();
    set @SQL = ExeSql;
    if (cnt = 0) then prepare stmt from @SQL; execute stmt; end if;
end $$

-- 定义存储过程：删除表格
drop procedure if exists proc_dropTable$$
delimiter $$
create procedure proc_dropTable(tableName varchar(50)) comment '表格存在时，删除表格'
begin
    declare cnt int default 0;
    select count(*) into cnt from information_schema.tables where table_name = tableName and table_schema = database();
    set @SQL = concat('drop table ', tableName);
    if (cnt > 0) then prepare stmt from @SQL; execute stmt; end if;
end $$
delimiter ;

-- 登录信息表
call proc_dropTable('login_info');
create table login_info
(
    user_id   varchar(30) default ' '      not null,
    user_name varchar(20) default ' '      not null,
    password  varchar(60) default ' ' not null,
    status    int(1)      default 0        not null,
    role      varchar(4)  default ' '      not null,
    primary key pk_user_name (user_id)
);

-- 系统配置表
call proc_dropTable('options');
create table options
(
    id           int(11)        not null auto_increment comment '主键',
    create_time  numeric(13, 0) not null default 0,
    update_time  numeric(13, 0) not null default 0,
    option_key   varchar(100)   not null ,
    type         int(11)        not null default 0,
    option_value longtext       not null,
    primary key (id) using btree
);

-- 菜单表
call proc_dropTable('menus');
create table menus
(
    id          int(11)        not null auto_increment,
    create_time numeric(13, 0) null default null,
    update_time numeric(13, 0) null default null,
    icon        varchar(50)    null default null,
    name        varchar(50)    not null,
    parent_id   int(11)        null default 0,
    priority    int(11)        null default 0,
    target      varchar(20)    null default '_self',
    team        varchar(255)   null default null,
    url         varchar(1023)  not null,
    primary key (id) using btree,
    index menus_parent_id (parent_id) using btree,
    index menus_name (name) using btree
);

-- 分类表
call proc_dropTable('categories');
create table categories
(
    id          int(11)       not null auto_increment,
    create_time numeric(13,0)  null default null,
    update_time numeric(13,0)  null default null,
    description varchar(100)  null default null,
    name        varchar(255)  not null,
    parent_id   int(11)       null default 0,
    password    varchar(255)  null default null,
    thumbnail   varchar(1023) null default null,
    priority    int(11)       null default 0,
    primary key (id) using btree,
    index categories_name (name) using btree,
    index categories_parent_id (parent_id) using btree
);

-- 文章表
call proc_dropTable('posts');
create table posts
(
    type             int(11)        not null default 0,
    id               int(11)        not null auto_increment,
    create_time      numeric(13, 0) null     default null,
    update_time      numeric(13, 0) null     default null,
    disallow_comment bit(1)         null     default b'0',
    edit_time        numeric(13, 0) null     default null,
    editor_type      int(11)        null     default 0,
    format_content   longtext       null,
    original_content longtext       null,
    status           int(11)        null     default 1,
    summary          longtext       null comment '摘要',
    template         varchar(255)   null     default null,
    thumbnail        varchar(1023)  null     default null comment '文章头部图',
    title            varchar(255)   not null,
    top_priority     int(11)        null     default 0,
    url              varchar(255)   null     default null,
    word_count       bigint(20)     null     default 0,
    version          int(11)        null     default 1,
    primary key (id) using btree,
    index posts_type_status (type, status) using btree,
    index posts_create_time (create_time) using btree
);


-- 文章分离映射
call proc_dropTable('post_categories');
create table post_categories
(
    id          int(11)        not null auto_increment,
    create_time numeric(13, 0) null default null,
    update_time numeric(13, 0) null default null,
    category_id int(11)        null default null,
    post_id     int(11)        null default null,
    primary key (id) using btree,
    index post_categories_post_id (post_id) using btree,
    index post_categories_category_id (category_id) using btree
);

-- 图片表
call proc_dropTable('photos');
create table photos
(
    id          int(11)        not null auto_increment,
    create_time numeric(13, 0) null     default null,
    update_time numeric(13, 0) null     default null,
    description varchar(255)   null     default null,
    location    varchar(255)   null     default null,
    name        varchar(255)   not null,
    take_time   numeric(13, 0) null     default null,
    team        varchar(255)   null     default null,
    thumbnail   varchar(1023)  null     default null,
    url         varchar(1023)  not null,
    primary key (id) using btree,
    index photos_team (team) using btree,
    index photos_create_time (create_time) using btree
);

-- 附近表，用于存放上传的文件信息
call proc_dropTable('attachments');
create table attachments
(
    id          int(11)        not null auto_increment,
    create_time numeric(13, 0) null default null,
    update_time numeric(13, 0) null default null,
    file_key    varchar(2047)  null default null,
    height      int(11)        null default 0,
    media_type  varchar(127)   not null,
    name        varchar(255)   not null,
    path        varchar(1023)  not null,
    size        bigint(20)     not null,
    suffix      varchar(50)    null default null,
    thumb_path  varchar(1023)  null default null,
    type        int(11)        null default 0,
    width       int(11)        null default 0,
    primary key (id) using btree,
    index attachments_media_type (media_type) using btree,
    index attachments_create_time (create_time) using btree
);

commit ;