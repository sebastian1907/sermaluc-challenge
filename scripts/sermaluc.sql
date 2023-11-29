
drop table if exists person cascade;

drop table if exists person_phones cascade;

drop table if exists phone cascade;

drop sequence if exists phone_seq;

create sequence phone_seq start with 1 increment by 50;

create table person (active boolean, created timestamp(6), last_login timestamp(6), modified timestamp(6), email varchar(255) unique, id varchar(255) not null, name varchar(255), password varchar(255), token varchar(255), primary key (id));

create table person_phones (phones_phone_id bigint not null unique, user_id varchar(255) not null);

create table phone (phone_id bigint not null, country_code varchar(255), cyty_code varchar(255), number varchar(255), primary key (phone_id));

alter table if exists person_phones add constraint FKe2qs2se0haw8pgnkscycqfrnu foreign key (phones_phone_id) references phone;

alter table if exists person_phones add constraint FKjc355rx6w1whg687iu4qf2l9g foreign key (user_id) references person;