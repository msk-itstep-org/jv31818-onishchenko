CREATE table users (
    id int(20) not null auto_increment primary key,
    username varchar(255) not null unique ,
    password varchar(255) not null,
    first_name varchar(255) null,
    enabled boolean NOT NULL DEFAULT 1
    );
Create table user_roles (
    id int(20) not null auto_increment primary key,
    user_id int(50) NOT NULL references users(id),
    name_role varchar(45) NOT NULL
);
--insert into users (login,password,name) values ('admin','admin','Admin');