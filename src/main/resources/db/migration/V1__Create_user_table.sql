create table USER
(
  ID           INTEGER  auto_increment,
  ACCOUNT_ID   VARCHAR(100),
  NAME         VARCHAR(50),
  TOKEN        CHAR(36),
  GMT_CREATE   BIGINT,
  GMT_MODIFIED BIGINT,
  constraint USER_PK
    primary key (ID)
);
--
-- comment on column USER.ID is '主键';
--
-- comment on column USER.ACCOUNT_ID is '账户ID';
--
-- comment on column USER.NAME is '用户名';
--
-- comment on column USER.GMT_CREATE is '创建时间';
--
-- comment on column USER.GMT_MODIFIED is '更改时间';