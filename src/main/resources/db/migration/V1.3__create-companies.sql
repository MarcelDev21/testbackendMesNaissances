create table companies(
    id int auto_increment primary key,
    name varchar(30),
    description varchar(30),
    address_id int,
    creation datetime default current_timestamp,
    constraint fk_companies_addresses foreign key(address_id) references address(id)
)