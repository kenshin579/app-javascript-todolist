DROP TABLE account;
CREATE TABLE account
(
  userid varchar (20) PRIMARY KEY NOT NULL,
  passwd varchar(20) NOT NULL,
  name varchar (20) NOT NULL,
  email varchar (200) NOT NULL
);

INSERT INTO account VALUES ('ykoh', '1234', 'Frank', 'ykoh@hancom.com');
INSERT INTO account VALUES ('thkang', '1234', 'Kialog', 'thkang@hancom.com');

drop table todo;
CREATE TABLE jtl.todo
(
  userid varchar (20) PRIMARY KEY NOT NULL,
  todoContent varchar (200) NOT NULL
);
ALTER TABLE jtl.todo ADD CONSTRAINT unique_userid UNIQUE (userid);

INSERT INTO todo VALUES ('ykoh', '["todo1", "todo2", "todo3"]');

