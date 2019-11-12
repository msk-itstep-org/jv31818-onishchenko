
CREATE table controllers (
    id int(20) not null auto_increment primary key,
    safeKey varchar(255) not null unique ,
    hostname varchar(255) not null,
    owner int(20) not null references users(id),
    name varchar(255) null);

CREATE table objects (
    id int(20) not null auto_increment primary key,
    name varchar(255) not null,
    owner int(20) not null references users(id),
    description varchar(255) null);