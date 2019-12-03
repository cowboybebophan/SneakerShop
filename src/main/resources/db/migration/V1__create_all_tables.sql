DROP TABLE IF EXISTS customer CASCADE;
DROP TABLE IF EXISTS sneaker_order CASCADE;
DROP TABLE IF EXISTS product CASCADE;

CREATE TABLE customer (
    /*id             INTEGER NOT NULL default next val */
   id                SERIAL NOT NULL,
   name              VARCHAR(30) not null unique,
   email             VARCHAR(50),
   password          VARCHAR(20),
   address           VARCHAR(300)
);
ALTER TABLE customer ADD CONSTRAINT customer_pk PRIMARY KEY ( id );

CREATE TABLE sneaker_order (
    /*id             INTEGER NOT NULL default next val */
    id               SERIAL NOT NULL,
    order_date       date default current_date,
    customer_id      INTEGER,
    product_id       INTEGER,
    payment          VARCHAR(30)
);
ALTER TABLE sneaker_order ADD CONSTRAINT sneaker_order_pk PRIMARY KEY ( id );

CREATE TABLE product (
    /*id             INTEGER NOT NULL default next val */
    id               SERIAL NOT NULL,
    name             VARCHAR(50) not null unique,
    description      VARCHAR(100),
    price            NUMERIC(10,2),
    stock            INTEGER
);
ALTER TABLE product ADD CONSTRAINT product_pk PRIMARY KEY ( id );

ALTER TABLE sneaker_order
    ADD CONSTRAINT orders_customer_fk FOREIGN KEY ( customer_id )
        REFERENCES customer( id );

ALTER TABLE sneaker_order
    ADD CONSTRAINT orders_product_fk FOREIGN KEY ( product_id )
        REFERENCES product( id );



