# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table notification_event (
  id                        bigint not null,
  description               varchar(255),
  constraint pk_notification_event primary key (id))
;

create table project (
  id                        bigint not null,
  description               varchar(255),
  session_id                integer,
  user_group_id             bigint,
  constraint pk_project primary key (id))
;

create table session (
  id                        integer not null,
  topic                     varchar(255),
  description               varchar(255),
  admin                     varchar(255),
  constraint pk_session primary key (id))
;

create table task (
  id                        bigint not null,
  description               varchar(255),
  user_id                   bigint,
  project_id                bigint,
  constraint pk_task primary key (id))
;

create table user (
  id                        bigint not null,
  email                     varchar(255),
  password                  varchar(255),
  session_id                integer,
  user_group_id             bigint,
  task_id                   bigint,
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id))
;

create table user_group (
  id                        bigint not null,
  group_name                varchar(255),
  group_description         varchar(255),
  admin_id                  bigint,
  session_id                integer,
  project_id                bigint,
  constraint pk_user_group primary key (id))
;


create table notification_event_user (
  notification_event_id          bigint not null,
  user_id                        bigint not null,
  constraint pk_notification_event_user primary key (notification_event_id, user_id))
;

create table notification_event_user_group (
  notification_event_id          bigint not null,
  user_group_id                  bigint not null,
  constraint pk_notification_event_user_group primary key (notification_event_id, user_group_id))
;

create table notification_event_session (
  notification_event_id          bigint not null,
  session_id                     integer not null,
  constraint pk_notification_event_session primary key (notification_event_id, session_id))
;

create table notification_event_project (
  notification_event_id          bigint not null,
  project_id                     bigint not null,
  constraint pk_notification_event_project primary key (notification_event_id, project_id))
;
create sequence notification_event_seq;

create sequence project_seq;

create sequence session_seq;

create sequence task_seq;

create sequence user_seq;

create sequence user_group_seq;

alter table project add constraint fk_project_session_1 foreign key (session_id) references session (id) on delete restrict on update restrict;
create index ix_project_session_1 on project (session_id);
alter table project add constraint fk_project_userGroup_2 foreign key (user_group_id) references user_group (id) on delete restrict on update restrict;
create index ix_project_userGroup_2 on project (user_group_id);
alter table task add constraint fk_task_user_3 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_task_user_3 on task (user_id);
alter table task add constraint fk_task_project_4 foreign key (project_id) references project (id) on delete restrict on update restrict;
create index ix_task_project_4 on task (project_id);
alter table user add constraint fk_user_session_5 foreign key (session_id) references session (id) on delete restrict on update restrict;
create index ix_user_session_5 on user (session_id);
alter table user add constraint fk_user_userGroup_6 foreign key (user_group_id) references user_group (id) on delete restrict on update restrict;
create index ix_user_userGroup_6 on user (user_group_id);
alter table user add constraint fk_user_task_7 foreign key (task_id) references task (id) on delete restrict on update restrict;
create index ix_user_task_7 on user (task_id);
alter table user_group add constraint fk_user_group_admin_8 foreign key (admin_id) references user (id) on delete restrict on update restrict;
create index ix_user_group_admin_8 on user_group (admin_id);
alter table user_group add constraint fk_user_group_session_9 foreign key (session_id) references session (id) on delete restrict on update restrict;
create index ix_user_group_session_9 on user_group (session_id);
alter table user_group add constraint fk_user_group_project_10 foreign key (project_id) references project (id) on delete restrict on update restrict;
create index ix_user_group_project_10 on user_group (project_id);



alter table notification_event_user add constraint fk_notification_event_user_no_01 foreign key (notification_event_id) references notification_event (id) on delete restrict on update restrict;

alter table notification_event_user add constraint fk_notification_event_user_us_02 foreign key (user_id) references user (id) on delete restrict on update restrict;

alter table notification_event_user_group add constraint fk_notification_event_user_gr_01 foreign key (notification_event_id) references notification_event (id) on delete restrict on update restrict;

alter table notification_event_user_group add constraint fk_notification_event_user_gr_02 foreign key (user_group_id) references user_group (id) on delete restrict on update restrict;

alter table notification_event_session add constraint fk_notification_event_session_01 foreign key (notification_event_id) references notification_event (id) on delete restrict on update restrict;

alter table notification_event_session add constraint fk_notification_event_session_02 foreign key (session_id) references session (id) on delete restrict on update restrict;

alter table notification_event_project add constraint fk_notification_event_project_01 foreign key (notification_event_id) references notification_event (id) on delete restrict on update restrict;

alter table notification_event_project add constraint fk_notification_event_project_02 foreign key (project_id) references project (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists notification_event;

drop table if exists notification_event_user;

drop table if exists notification_event_user_group;

drop table if exists notification_event_session;

drop table if exists notification_event_project;

drop table if exists project;

drop table if exists session;

drop table if exists task;

drop table if exists user;

drop table if exists user_group;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists notification_event_seq;

drop sequence if exists project_seq;

drop sequence if exists session_seq;

drop sequence if exists task_seq;

drop sequence if exists user_seq;

drop sequence if exists user_group_seq;

