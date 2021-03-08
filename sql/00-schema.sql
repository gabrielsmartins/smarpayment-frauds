use smartpaymentfrauddb;

drop table if exists tbl_fraud_items;
drop table if exists tbl_fraud_payment_methods;
drop table if exists tbl_fraud;
drop table if exists tbl_payment_methods;

create table tbl_fraud(
  fraud_id varchar(36) not null default (uuid()),
  order_id bigint not null,
  customer_id char(36) not null,
  created_at datetime not null,
  total_amount decimal(17,2) not null,
  total_discount decimal(17,2) not null,
  constraint PK_tbl_fraud primary key(fraud_id)
);

create table tbl_fraud_items(
 fraud_id varchar(36) not null,
 product_id varchar(36) not null,
 item_quantity bigint not null,
 item_amount decimal(17,2) not null,
 constraint PK_tbl_fraud_items primary key(fraud_id, product_id),
 constraint FK_tbl_fraud_tbl_fraud_items foreign key (fraud_id) references tbl_fraud(fraud_id)
);

create table tbl_payment_methods (
  payment_method_id int not null,
  payment_method_description varchar(256) not null,
  constraint PK_tbl_payment_methods primary key(payment_method_id)
);

create table tbl_fraud_payment_methods(
   fraud_id varchar(36) not null,
   payment_method_id int not null,
   payment_method_amount decimal(17,2) not null,
   constraint PK_tbl_fraud_payment_methods primary key(fraud_id, payment_method_id),
   constraint FK_tbl_fraud_payment_methods_tbl_fraud_items foreign key (fraud_id) references tbl_fraud(fraud_id),
   constraint FK_tbl_fraud_payment_methods_tbl_payment_methods foreign key (payment_method_id) references tbl_payment_methods(payment_method_id)
);
