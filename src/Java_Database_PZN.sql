use java_database_pzn;

create table customer (
id varchar(100) not null,
name varchar(255) not null,
email varchar(255) not null,
constraint email_unique unique (email),
primary key (id)
) engine = InnoDB;

alter table customer rename customers;

select * from customers c ;

create table admin (
username varchar(100) not null,
password varchar(100) not null,
primary key (username)
)engine = InnoDB;

insert into admin (username, password) values ('admin','admin');

select * from admin a ;

create table comments (
id int not null auto_increment,
email varchar(100),
comment text,
primary key (id)
) engine = InnoDB;

select * from comments c ;

select count(id) from comments c ; 

delete from comments ;

create table sample_time(
id int not null auto_increment,
sample_date date,
sample_time time,
sample_timestamp timestamp,
primary key (id)
) engine = 'InnoDB';

desc sample_time;

select * from sample_time ;

delete from comments ;

select * from comments c ;

select * from comments c where id = 2004;