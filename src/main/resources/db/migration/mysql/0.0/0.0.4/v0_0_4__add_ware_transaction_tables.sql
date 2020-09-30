create table if not exists ware_transaction (
    id bigint(10) auto_increment not null primary key,
    ware_transaction_type varchar(6) not null,
    description varchar(30) not null,
    transaction_date datetime not null,
    stock_clerk_id bigint(10) not null,
    constraint fk_stock_clerk foreign key (stock_clerk_id) references stock_clerk (id) on update cascade on delete cascade
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

create table if not exists ware_transaction_detail (
    id bigint(10) auto_increment not null primary key,
    ware_transaction_id bigint(10) not null,
    product_id bigint(10) not null,
    shelf_id bigint(10) not null,
    quantity int not null,
    constraint fk_ware_tx foreign key (ware_transaction_id) references ware_transaction (id) on update cascade,
    constraint fk_product foreign key (product_id) references product (id) on update cascade,
    constraint fk_shelf foreign key (shelf_id) references shelf (id) on update cascade
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;