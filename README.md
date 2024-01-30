# Project-Management-Boot
Boot and React application to manage projects for a User


<!-- Table Structure  -->

create database dev;

create table dev.users(
user_id int(11) not null auto_increment,
username varchar(100) not null unique,
password varchar(200) not null,
roles varchar(500) not null, -- multiple roles seperated by commas
enabled boolean default true,
email_id varchar(100) not null,
primary key(user_id)
);

create table dev.projects(
project_id int(11) not null auto_increment,
title varchar(100) not null unique,
description varchar(1000) not null,
due_date date not null,
created_by varchar(100) not null,
created_at date not null,
last_updated_by varchar(100) not null,
last_updated_at datetime not null,
completed boolean not null default false,
primary key(project_id)
);

create table dev.tasks(
task_id int(11) not null auto_increment,
project_id int(11) not null,
title varchar(1000) not null,
created_by varchar(100) not null,
created_at date not null,
last_updated_by varchar(100) not null,
last_updated_at datetime not null,
done boolean not null default false,
primary key(task_id),
foreign key(project_id) references dev.projects(project_id)
);