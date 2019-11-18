drop table users;
drop table user_roles;
drop table roles;
create table roles (id int(20) auto_increment PRIMARY KEY, role varchar(100) unique);
create table users (
 id int(20) not null auto_increment PRIMARY KEY,
    username varchar(255) not null unique ,
    password varchar(255) not null,
    first_name varchar(255) null,
    enabled boolean NOT NULL DEFAULT 1
);
create table user_roles (id_user int(20) not null references users(id) , id_role int(20) not null references roles(id) , PRIMARY KEY user_role_pk (id_user,id_role));


insert into roles(role) values ('ADMIN');
insert into roles(role) values ('USER');

insert into users (username,password,first_name,enabled) values ('admin','admin','Admin','1');

insert into user_roles(id_user,id_role) values ('1','1');
insert into user_roles(id_user,id_role) values ('1','2');