create database app_db;
create user app_user;
alter user app_user with password 'app_password';
alter database app_db OWNER TO app_user;

\c app_db;

CREATE SCHEMA app_schema;

CREATE TABLE IF NOT EXISTS app_schema.IMAGES
(
    ID         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    NAME       VARCHAR(255) NOT NULL,
    IMAGE_DATA TEXT         NOT NULL
);

GRANT CREATE ON SCHEMA app_schema TO app_user;
GRANT USAGE ON SCHEMA app_schema TO app_user;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA app_schema TO app_user;

\q