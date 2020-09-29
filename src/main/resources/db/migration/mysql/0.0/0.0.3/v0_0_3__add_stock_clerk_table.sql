create table if not exists stock_clerk (
    id bigint(10) auto_increment not null primary key,
    registry_number varchar(5) not null,
    first_name varchar(12) not null,
    last_name varchar(18) not null,
    unique key uk_registry_number (registry_number)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
