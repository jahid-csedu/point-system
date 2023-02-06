insert into payment_methods(id, name, point_rate, additional_info)
values
(1, 'CASH', 0.05, ''),
(2, 'CASH_ON_DELIVERY', 0.05, 'COURIER'),
(3, 'VISA', 0.03, 'CARD'),
(4, 'MASTERCARD', 0.03, 'CARD'),
(5, 'AMEX', 0.02, 'CARD'),
(6, 'JCB', 0.05, 'CARD'),
(7, 'LINE PAY', 0.01, ''),
(8, 'PAYPAY', 0.01, ''),
(9, 'POINTS', 0, ''),
(10, 'GRAB PAY', 0.01, ''),
(11, 'BANK TRANSFER', 0, 'BANK'),
(12, 'CHEQUE', 0, 'CHEQUE')

insert into courier_services(id, name)
values
(1, 'YAMATO'),
(2, 'SAGAWA');
