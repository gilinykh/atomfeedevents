--liquibase example sql

--changeset author:1
create table example(
    id varchar(100) PRIMARY KEY
);

create schema atomfeed;