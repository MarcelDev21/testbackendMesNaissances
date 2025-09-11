create table activations(
    id int auto_increment primary key,
    code varchar(200),
    creation datetime default current_timestamp,
    desactivation datetime,
    active boolean,
    profiles_id int,
    constraint fk_activations_profiles foreign key(profiles_id) references profiles(id)
);