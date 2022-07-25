create database to_do_list;

create table users
(
    id       int(10) primary key auto_increment,
    username varchar(255) not null,
    password varchar(255) not null,
    email    varchar(255),
    is_flag  boolean comment '逻辑删除键'
);

create table task
(
    id          int(10) primary key auto_increment,
    userid      int(10)      not null,
    task_name   varchar(255) not null,
    description text comment '备注',
    deadline    datetime     not null,
    priority    int(1) check ( value in (1, 2, 3)) comment '越小优先级越高',
    is_finish   boolean default false,
    flag        boolean comment '逻辑删除键',
    foreign key (userid) references users (id)
);

create table diary
(
    id        int(10) primary key auto_increment,
    userid    int(10)   not null,
    task_id   int(10)   not null,
    context   text      not null,
    post_time timestamp not null comment '发送时间戳',
    flag      boolean comment '逻辑删除键',
    foreign key (userid) references users (id),
    foreign key (task_id) references task (id)
);