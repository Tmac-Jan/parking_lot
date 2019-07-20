alter table parking_order add column parking_lot_id integer;
alter table parking_order add constraint FK701du5mj20ogq7fyhccisnj foreign key (parking_lot_id) references parking_lot;