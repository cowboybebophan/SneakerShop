alter table sneaker_order add column order_date date default current_date ;
alter table sneaker_order drop column date ;