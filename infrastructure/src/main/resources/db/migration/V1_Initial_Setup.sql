create table if not exists credit_request (
    id bigserial not null,
    amount numeric(38,2) not null,
    created_at timestamp(6) with time zone,
    currency varchar(255) not null,
    installments integer not null,
    mail varchar(255) not null,
    total_amount numeric(38,2) not null,
    uuid varchar(255) not null,
    primary key (id)
);

create table if not exists installment_entity (
    id bigserial not null,
    amount numeric(38,2) not null,
    interest numeric(38,2) not null,
    number integer not null,
    rate numeric(38,2) not null,
    credit_request_id bigint,
    primary key (id)
);

alter table if exists installment_entity
add constraint FKda7a984n5y3x1c17a82k2ltwp
foreign key (credit_request_id)
references credit_request;