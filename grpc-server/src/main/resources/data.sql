insert into payment_methods(id, name, point_rate, additional_info)
values
(payment_methods_seq.nextVal(), 'CASH', 0.05, ''),
(payment_methods_seq.nextVal(), 'CASH_ON_DELIVERY', 0.05, 'COURIER'),
(payment_methods_seq.nextVal(), 'VISA', 0.03, 'CARD'),
(payment_methods_seq.nextVal(), 'MASTERCARD', 0.03, 'CARD'),
(payment_methods_seq.nextVal(), 'AMEX', 0.02, 'CARD'),
(payment_methods_seq.nextVal(), 'JCB', 0.05, 'CARD'),
(payment_methods_seq.nextVal(), 'LINE PAY', 0.01, ''),
(payment_methods_seq.nextVal(), 'PAYPAY', 0.01, ''),
(payment_methods_seq.nextVal(), 'POINTS', 0, ''),
(payment_methods_seq.nextVal(), 'GRAB PAY', 0.01, ''),
(payment_methods_seq.nextVal(), 'BANK TRANSFER', 0, 'BANK'),
(payment_methods_seq.nextVal(), 'CHEQUE', 0, 'CHEQUE')