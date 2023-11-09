INSERT INTO ROLE (roleId, roleType, description, insertDatetime, updateDatetime)
VALUES (unhex(replace(uuid(), '-', '')), 'USER', '일반 유저', now(), now());

INSERT INTO ROLE (roleId, roleType, description, insertDatetime, updateDatetime)
VALUES (unhex(replace(uuid(), '-', '')), 'ADMIN', '운영자', now(), now());

INSERT INTO ROLE (roleId, roleType, description, insertDatetime, updateDatetime)
VALUES (unhex(replace(uuid(), '-', '')), 'TEST', '테스트 계정', now(), now());
