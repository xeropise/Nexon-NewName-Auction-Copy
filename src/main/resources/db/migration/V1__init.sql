CREATE TABLE `USER`
(
    userId         binary(16) not null primary key,
    account        varchar(255) not null,
    password       varchar(255) not null,
    email          varchar(255) not null,
    insertDatetime datetime(6) not null,
    updateDatetime datetime(6) not null
);

create unique index u_USER_account on USER (account);
create unique index u_USER_email on USER (email);

CREATE TABLE `USER_ROLE`
(
    userRoleId     binary(16) not null primary key,
    userId         binary(16) not null,
    roleId         binary(16) not null,
    insertDatetime datetime(6) not null,
    updateDatetime datetime(6) not null
);

create unique index u_USER_ROLE_userId_roleId on USER_ROLE (userId, roleId);

CREATE TABLE `ROLE`
(
    roleId         binary(16) not null primary key,
    roleType       varchar(255) not null,
    description    varchar(1023),
    insertDatetime datetime(6) not null,
    updateDatetime datetime(6) not null
);


