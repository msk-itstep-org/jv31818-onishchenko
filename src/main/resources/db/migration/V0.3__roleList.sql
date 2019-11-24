create table roles(
    id int(20) not null auto_increment primary key,
    name_role varchar(50) not null
);

insert into roles(name_role) values ('ADMIN');
insert into roles(name_role) values ('USER');

insert into users (username,password,first_name,enabled) values ('admin','admin','Admin','1');

insert into user_roles(user_id,name_role) values ('1','ADMIN');
insert into user_roles(user_id,name_role) values ('1','USER');
