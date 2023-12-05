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

CREATE TABLE `BID`
(
    bidId            binary(16) not null primary key,
    characterItemId  binary(16) not null,
    startingPrice    int not null,
    registrationTime datetime(6) not null,
    endTime          datetime(6) not null,
    userId           binary(16) not null,
    insertDatetime   datetime(6) not null,
    updateDatetime   datetime(6) not null
);

CREATE TABLE `BID_DETAIL`
(
    bidDetailId    binary(16) not null primary key,
    bidMoney       int not null,
    bidId          binary(16) not null,
    insertDatetime datetime(6) not null,
    updateDatetime datetime(6) not null
);

CREATE TABLE `USER_CHARACTER`
(
    userCharacterId    binary(16) not null primary key,
    name               varchar(255) not null,
    userId             binary(16) not null,
    insertDatetime     datetime(6) not null,
    updateDatetime     datetime(6) not null
);

create index USER_CHARACTER_userId on USER_CHARACTER (userId);

CREATE TABLE `CHARACTER_ITEM`
(
    characterItemId binary(16) not null primary key,
    userCharacterId binary(16) not null,
    itemId          binary(16) not null,
    count           int,
    onBidding       tinyint(1) not null,
    insertDatetime  datetime(6) not null,
    updateDatetime  datetime(6) not null
);

CREATE TABLE `ITEM`
(
    itemId         binary(16) not null primary key,
    name           varchar(255) not null,
    isConsumable   tinyint(1) not null,
    insertDatetime datetime(6) not null,
    updateDatetime datetime(6) not null
);

