--liquibase formatted sql

--changeset reservation:1

create table contact (id bigint not null auto_increment, email varchar(30) not null, telephone_number varchar(30) not null, primary key (id));

create table passenger (id bigint not null auto_increment, birthday date not null, nationality varchar(3) not null, reservation_id bigint, document_number varchar(10) not null, document_type varchar(10) not null, first_name varchar(10) not null, last_name varchar(10) not null, primary key (id));
create table reservation (id bigint not null auto_increment, reservation_id bigint, itinerary_id varchar(50) not null, search_id varchar(50) not null, primary key (id));

alter table reservation add constraint UKpdrap500f6gas6d2sjnjdsfi7 unique (reservation_id);
alter table passenger add constraint FKp4qdewtk73iwqerswqtqs0g1q foreign key (reservation_id) references reservation (id);
alter table reservation add constraint FK3qqqfyk9v0yjrb4ntjv30lnb0 foreign key (reservation_id) references contact (id);
