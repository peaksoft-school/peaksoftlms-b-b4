create table student
(
    id bigint not null
        constraint student_pkey
            primary key,
    last_name    varchar(255),
    phone_number varchar(255),
    student_name varchar(255),
    study_format varchar(255),


);

create table teachers
(
    id bigint not null
        constraint teachers_pkey
            primary key,
    last_name      varchar(255),
    phone_number   varchar(255),
    specialization varchar(255),
    teacher_name   varchar(255),


);

create table users
(
    id                         bigint  not null
        constraint users_pkey
            primary key,
    email                      varchar(255),
    is_account_non_expired     boolean not null,
    is_account_non_locked      boolean not null,
    is_credentials_non_expired boolean not null,
    is_enabled                 boolean not null,
    password                   varchar(255),
    role                       varchar(255)
);


create table groups
(
    id            bigint not null
        constraint groups_pkey
            primary key,
    date_of_start date,
    description   varchar(255),
    group_name    varchar(255),
    imagine       varchar(255)
);

create table admin
(
    id         bigint not null
        constraint admin_pkey
            primary key,
    first_name varchar(255),
    last_name  varchar(255),

);