INSERT INTO ROLE (roleId, roleType, description, insertDatetime, updateDatetime)
VALUES (unhex(replace('5d5b0368-b3f5-4ce9-b2d2-b2b336434d65', '-', '')), 'USER', '일반 유저', now(), now());

INSERT INTO ROLE (roleId, roleType, description, insertDatetime, updateDatetime)
VALUES (unhex(replace('7988ffd4-4a53-407d-bd5f-8bdf1010d397', '-', '')), 'ADMIN', '운영자', now(), now());

INSERT INTO ROLE (roleId, roleType, description, insertDatetime, updateDatetime)
VALUES (unhex(replace('5317f18d-788b-485b-9916-b6a861cbb42e', '-', '')), 'TEST', '테스트 계정', now(), now());
