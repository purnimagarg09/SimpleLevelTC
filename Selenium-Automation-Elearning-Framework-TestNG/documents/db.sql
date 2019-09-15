create table login(
username varchar(50) not null,
password varchar(50));


insert into login values("admin", "admin@123"); 
insert into login values("admin", "admin@123"); 

create table users(
firstname varchar(50) not null,
lastname varchar(50) not null,
email varchar(50) not null,
phone varchar(20) not null,
login varchar(20) not null,
password varchar(20) not null,
profile varchar(20));

insert into users values("valid","user","valid.user@gmail.com","900900900","validuser","password","Trainer");