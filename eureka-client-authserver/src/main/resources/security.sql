
-- 角色
create table role(
    id bigint primary key  auto_increment,
    name varchar(50),
    descn varchar(200)
);

-- 用户
create table user(
    id bigint PRIMARY key auto_increment,
    username varchar(50),
    password varchar(50),
    status integer,
    descn varchar(200)
);

-- 用户角色连接表
create table user_role(
    user_id bigint,
    role_id bigint
);
alter table user_role add constraint pk_user_role primary key(user_id, role_id);
alter table user_role add constraint fk_user_role_user foreign key(user_id) references user(id);
alter table user_role add constraint fk_user_role_role foreign key(role_id) references role(id);