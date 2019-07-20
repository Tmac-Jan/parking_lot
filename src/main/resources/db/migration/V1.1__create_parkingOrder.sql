create table parking_order (
id  integer not null  IDENTITY(1,1),
car_number varchar(255),
create_time timestamp,
parking_lot_name varchar(255),
update_time timestamp, primary key (id));