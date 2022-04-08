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
    open_id   varchar(40) default ' ' not null,
    user_name varchar(20) default ' ' not null,
    secret    varchar(60) default ' ' not null,
    user_type varchar(4)  default ' ' not null,
    primary key pk_user_name (open_id)
);

call proc_dropTable('activity_user');
create table activity_user
(
    act_id  varchar(40) default ' ' not null,
    open_id varchar(40) default ' ' not null,
    primary key pk_user_name (act_id, open_id)
);

call proc_dropTable('activity_info');
create table activity_info
(
    act_id       varchar(40)    default ' ' not null,
    act_name     varchar(40)    default ' ' not null,
    start_time   numeric(14, 0) default 0   not null,
    end_time     numeric(14, 0) default 0   not null,
    act_desc     varchar(255)   default ' ' not null,
    min_user_num int(10)        default 0   not null,
    max_user_num int(10)        default 0   not null,
    primary key pk_user_name (act_id)
);

commit ;