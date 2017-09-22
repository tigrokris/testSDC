DROP TABLE IF EXISTS user_roles ;
DROP TABLE IF EXISTS users ;
DROP SEQUENCE IF EXISTS global_seq ;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name VARCHAR(255),
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON USERS ( email );

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY ( user_id ) REFERENCES USERS ( id ) ON DELETE CASCADE
);
