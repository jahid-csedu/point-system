CREATE TABLE payment_methods (
  id BIGINT PRIMARY KEY,
  name VARCHAR(20) NOT NULL,
  point_rate DECIMAL(5,2) NOT NULL,
  additional_info VARCHAR(100)
);

CREATE TABLE payments (
  id BIGINT PRIMARY KEY,
  customer_id BIGINT NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  price_modifier DECIMAL(10,2) NOT NULL,
  final_price DECIMAL(10,2) NOT NULL,
  points DECIMAL(10,2) NOT NULL,
  purchase_date TIMESTAMP,
  payment_method_id BIGINT NOT NULL,
  FOREIGN KEY (payment_method_id) REFERENCES payment_methods(id)
);

CREATE TABLE card_details (
  id BIGINT PRIMARY KEY,
  card_number VARCHAR(4) NOT NULL,
  payment_id BIGINT NOT NULL,
  FOREIGN KEY (payment_id) REFERENCES payments(id)
);

CREATE TABLE bank_details (
  id BIGINT PRIMARY KEY,
  bank_name VARCHAR(100) NOT NULL,
  account_number VARCHAR(20) NOT NULL,
  payment_id BIGINT NOT NULL,
  FOREIGN KEY (payment_id) REFERENCES payments(id)
);

CREATE TABLE cheque_details (
  id BIGINT PRIMARY KEY,
  bank_name VARCHAR(100) NOT NULL,
  cheque_number VARCHAR(20) NOT NULL,
  payment_id BIGINT NOT NULL,
  FOREIGN KEY (payment_id) REFERENCES payments(id)
);

CREATE TABLE courier_services (
  id BIGINT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  permitted BOOLEAN DEFAULT TRUE
);

CREATE SEQUENCE bank_details_seq;
CREATE SEQUENCE card_details_seq;
CREATE SEQUENCE cheque_details_seq;
CREATE SEQUENCE courier_services_seq;
CREATE SEQUENCE payment_seq;
CREATE SEQUENCE payment_methods_seq;
