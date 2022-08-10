drop database if exists to_do_list;

create database to_do_list;

use to_do_list;

create table user
(
    userid   int(11) primary key auto_increment,
    username varchar(255) not null,
    password varchar(255) not null,
    email    varchar(255),
    flag     boolean default true comment '逻辑删除键'
);

create table fans
(
    relation_id int(11) primary key auto_increment,
    userid      int(11) not null,
    fan_id      int(11) not null,
    flag        boolean default true comment '逻辑删除键',
    foreign key (userid) references user (userid),
    foreign key (fan_id) references user (userid),
    unique (userid, fan_id)
);

create table task
(
    task_id     int(11) primary key auto_increment,
    userid      int(11)      not null,
    task_name   varchar(255) not null,
    description text comment '备注',
    deadline    int(11)      comment '小时',
    finish      boolean default false,
    flag        boolean default true comment '逻辑删除键',
    foreign key (userid) references user (userid)
);

create table blog
(
    blog_id   int(11) primary key auto_increment,
    userid    int(11)    not null,
    context   mediumtext not null,
    post_time int(11)    not null comment '发送时间戳,1970-1-1至今的小时数',
    flag      boolean default true comment '逻辑删除键',
    foreign key (userid) references user (userid)
);