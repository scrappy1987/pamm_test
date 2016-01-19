# Users schema

# --- !Ups

ALTER TABLE project DROP COLUMN owner_user_id;
ALTER TABLE project DROP COLUMN sessionstatus;


# --- !Downs