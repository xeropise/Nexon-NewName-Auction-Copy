CREATE TABLE `USER`
(
    userId                binary(16)  not null primary key,
    userName              varchar(255) not null,
    password              varchar(255) not null,
    email                 varchar(255) not null,
    insertDatetime        datetime(6) not null,
    updateDatetime        datetime(6) not null
) DEFAULT CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI;
