# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table project (
  id                        bigint not null,
  description               varchar(255),
  session                   integer,
  user_group                integer,
  constraint pk_project primary key (id))
;

create table session (
  id                        integer auto_increment not null,
  topic                     varchar(255),
  description               varchar(255),
  admin                     varchar(255),
  constraint pk_session primary key (id))
;

create table user (
  id                        bigint not null,
  email                     varchar(255),
  password                  varchar(255),
  session_id                integer,
  user_group                integer,
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id))
;

create table user_group (
  id                        bigint not null,
  group_description         varchar(255),
  group_name                varchar(255),
  admin                     varchar(255),
  session_id                integer,
  constraint pk_user_group primary key (id))
;

create sequence project_seq;

create sequence user_seq;

create sequence user_group_seq;

alter table user add constraint fk_user_session_1 foreign key (session_id) references session (id) on delete restrict on update restrict;
create index ix_user_session_1 on user (session_id);
alter table user_group add constraint fk_user_group_session_2 foreign key (session_id) references session (id) on delete restrict on update restrict;
create index ix_user_group_session_2 on user_group (session_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists project;

drop table if exists session;

drop table if exists user;

drop table if exists user_group;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists project_seq;

drop sequence if exists user_seq;

drop sequence if exists user_group_seq;

