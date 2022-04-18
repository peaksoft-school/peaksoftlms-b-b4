create table if not exists users
(
    id                         bigint  not null
    primary key,
    email                      varchar(255),
    is_account_non_expired     boolean not null,
    is_account_non_locked      boolean not null,
    is_credentials_non_expired boolean not null,
    is_enabled                 boolean not null,
    password                   varchar(255),
    role                       varchar(255)
    );

create table if not exists course
(
    id            bigint not null
        primary key,
    course_name   varchar(255),
    date_of_start date,
    description   varchar(255),
    image         varchar(255)
);

create table if not exists groups
(
    id            bigint not null
    primary key,
    date_of_start date,
    description   varchar(255),
    group_name    varchar(255),
    imagine       varchar(255)
    );
create table if not exists teachers
(
    id             bigint not null
    primary key,
    last_name      varchar(255),
    name           varchar(255),
    phone_number   varchar(255),
    specialization varchar(255),
    user_id        bigint
    constraint fkb8dct7w2j1vl1r2bpstw5isc0
    references users
    );

create table if not exists student
(
    id           bigint not null
    primary key,
    email        varchar(255),
    last_name    varchar(255),
    phone_number varchar(255),
    role         integer,
    student_name varchar(255),
    study_format varchar(255),
    group_id     bigint
    constraint fksflcrdigyrhbqi27vvioiw53q
    references groups
    );



create table courses_students
(
    course_id  bigint not null
        constraint fkowxu7s93rqg1g4ahpl1u0jq22
            references course,
    student_id bigint not null
        constraint fk76usiy9lxmty1q3rgiqblqe41
            references student
);

create table groups_courses
(
    group_id   bigint not null
        constraint fkb5nvrwxye8n0ct77q8s3war1x
            references groups,
    courses_id bigint not null
        constraint fknp0t2cwlk0se28uk5f9gj6nkj
            references course
);


create table if not exists course_teacher
(
    course_id  bigint not null
    constraint fkrna6ik293g84mo3rslnkk7m1a
    references course,
    teacher_id bigint not null
    constraint fkjoytng93eni3erly1divd3xla
    references teachers
);














