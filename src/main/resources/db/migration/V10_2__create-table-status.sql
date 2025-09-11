create table status(
    id int auto_increment primary key,
    name varchar(30),
    description varchar(30),
    creation datetime default current_timestamp
);


INSERT INTO status(`name`)
VALUES
("NEW"),
("ON_GOING"),
("VALIDATED"),
("REJECTED");
