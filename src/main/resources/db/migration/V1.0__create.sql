 create table parking_lot (id integer IDENTITY(1,1) not null, capacity integer check (capacity >= 0), location varchar(255), name varchar(255), primary key (id));
 alter table parking_lot drop constraint if exists UK_74jy8sn4gxqu3enbbn27ic9yp;
 alter table parking_lot add constraint UK_74jy8sn4gxqu3enbbn27ic9yp unique (name);
 create sequence hibernate_sequence start with 1 increment by 1;