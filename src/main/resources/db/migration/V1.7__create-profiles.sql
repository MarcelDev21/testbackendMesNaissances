create table profiles(
     id int auto_increment primary key,
     firstname varchar(30),
     lastname varchar(30),
     civility varchar(30),
     email varchar(30),
     password varchar(30),
     phone varchar(30),
     address_id int,
     creation datetime default current_timestamp,
     constraint fk_profiles_addresses foreign key(address_id) references address(id)
 )