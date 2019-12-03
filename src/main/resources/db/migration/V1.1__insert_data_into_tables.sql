insert into product (name, description, price, stock) values
('Nike Air Jordan 1', 'Designed by Peter Moore, it was first produced for Micheal Jordan in 1984.', 120, 50),
('Adidas Yeezy Boost 350', 'Designed by Kanye West in 2015, it became one of the best selling Adidas sneakers.', 220, 35),
('Nike Air Fear of God 1', 'Designed by Jerry Lorenzo in 2018, it is one of the most creative sneaker.', 400, 10),
('New Balance 990v2', 'Designed by Aime Leon Dore in 2019, it is the perfect combination of fashion and daily use.', 300, 15)
;
commit;

insert into customer (name, email, password, address) values
('Han', 'hanwang@gmail.com', 'wanghan1234', 'Dorchester Apartments in Arlington'),
('Wenjia', 'wenjia@hotmail.com', 'wenjiawenjia123', 'Crystal City in Arlington'),
('Sugar', 'ruichen@gmail.com', 'ruichensugar789', 'Fairfax'),
('Hangbo', 'hangbo@outlook.com', 'hangbozhang543', 'Riverhouse in Pentagon City')
;
commit;

insert into sneaker_order (customer_id, product_id, payment) values
(3, 4, 'Venmo'),
(1, 3, 'Credit Card'),
(1, 4, 'PayPal'),
(2, 1, 'Debit Card'),
(4, 2, 'PayPal'),
(2, 3, 'Venmo'),
(3, 2, 'Credit Card'),
(4, 4, 'PayPal')
;
commit;


