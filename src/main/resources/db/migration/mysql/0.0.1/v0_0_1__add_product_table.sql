create table if not exists product (
    id bigint(10) auto_increment not null primary key,
    code varchar(15) not null,
    description varchar(25) default null,
    measurement_unit varchar(15) default null,
    unique key uk_code (code)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;