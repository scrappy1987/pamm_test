# Users schema

# --- !Ups

CREATE TABLE user (
  id                 BIGINT(20)   NOT NULL AUTO_INCREMENT,
  email              VARCHAR(255) NOT NULL UNIQUE,
  forename           VARCHAR(50),
  surname            VARCHAR(50),
  password           VARCHAR(255),
  job_title          VARCHAR(20),
  base_site          VARCHAR(20),
  phone              VARCHAR(20),
  activated          BOOLEAN      NOT NULL DEFAULT FALSE,
  activation_key     VARCHAR(255) NOT NULL,
  activation_date    DATE,
  failed_login_count INTEGER      NOT NULL DEFAULT 0,
  last_access        DATETIME     NOT NULL DEFAULT SYSDATE(),
  role               VARCHAR(10)  NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT ck_user_role CHECK (role IN ('ADMIN', 'USER'))
);

CREATE TABLE project (
  id            BIGINT(20)   NOT NULL AUTO_INCREMENT,
  title         VARCHAR(255) NOT NULL,
  project_code  VARCHAR(20),
  client        VARCHAR(255),
  summary       VARCHAR(255) NOT NULL,
  sessionStatus VARCHAR(50),
  owner_user_id BIGINT(20)   NOT NULL,
  status        VARCHAR(20),
  PRIMARY KEY (id),
  FOREIGN KEY (owner_user_id) REFERENCES user (id)
);

CREATE TABLE project_user (
  project_id BIGINT(20)   NOT NULL,
  user_email VARCHAR(255) NOT NULL,
  user_id    BIGINT(20),
  forename   VARCHAR(50),
  surname    VARCHAR(50),
  role       VARCHAR(20),
  PRIMARY KEY (project_id, user_email),
  FOREIGN KEY (project_id) REFERENCES project (id),
  FOREIGN KEY (user_id) REFERENCES user (id)
);

# --- !Downs

DROP TABLE project_user;
DROP TABLE project;
DROP TABLE user;
