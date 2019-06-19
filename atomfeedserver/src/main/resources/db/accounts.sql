--liquibase accounts sql

--changeset author:1
create table accounts(
    id varchar(100) PRIMARY KEY
);

create schema atomfeed;