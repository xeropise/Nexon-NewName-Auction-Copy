CREATE TABLE `USER`
(
    userId         binary(16) not null primary key,
    account        varchar(255) not null,
    password       varchar(255) not null,
    email          varchar(255) not null,
    insertDatetime datetime(6) not null,
    updateDatetime datetime(6) not null
);

ALTER TABLE USER
    ADD UNIQUE KEY u_user_account (account);
ALTER TABLE USER
    ADD UNIQUE KEY u_user_email (email);

CREATE TABLE `USER_ROLE`
(
    userRoleId     binary(16) not null primary key,
    userId         binary(16) not null,
    roleType       varchar(255) not null,
    insertDatetime datetime(6) not null,
    updateDatetime datetime(6) not null
);

ALTER TABLE USER_ROLE
    ADD UNIQUE KEY u_user_role_userId_roleType (userId, roleType);
