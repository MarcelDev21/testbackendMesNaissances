
create table roles(
    id int auto_increment primary key,
    name varchar(30),
    description varchar(30),
    creation datetime default current_timestamp
);

insert into roles(name)
values('Agent'),('Administrator'), ('Public');


create table permissions(
    id int auto_increment primary key,
    name varchar(30),
    description varchar(30),
    creation datetime default current_timestamp
);


insert into permissions(name)
values
('Agent_Create'),
('Agent_Read'),
('Agent_Update'),
('Agent_Delete'),


('Administrator_Create'),
('Administrator_Read'),
('Administrator_Update'),
('Administrator_Delete'),


('Public_Create'),
('Public_Read'),
('Public_Update'),
('Public_Delete'),


('Profile_Create'),
('Profile_Read'),
('Profile_Update'),
('Profile_Delete'),

('Declaration_Create'),
('Declaration_Read'),
('Declaration_Update'),
('Declaration_Delete'),

('Request_Create'),
('Request_Read'),
('Request_Update'),
('Request_Delete');

create table roles_permissions(
    id int auto_increment primary key,
    roles_id int,
    permissions_id int,
    constraint fk_roles_permissions_permissions foreign key(permissions_id) references permissions(id),
    constraint fk_roles_permissions_roles foreign key(roles_id) references roles(id),
    constraint unique_role_permission unique (roles_id, permissions_id)
);


insert into roles_permissions(roles_id, permissions_id)
values
((select id from roles where name ='Administrator'), (select id from permissions where name = 'Agent_Create')),
((select id from roles where name ='Administrator'), (select id from permissions where name = 'Agent_Read')),
((select id from roles where name ='Administrator'), (select id from permissions where name = 'Agent_Update')),
((select id from roles where name ='Administrator'), (select id from permissions where name = 'Agent_Delete')),


((select id from roles where name ='Administrator'), (select id from permissions where name = 'Administrator_Create')),
((select id from roles where name ='Administrator'), (select id from permissions where name = 'Administrator_Read')),
((select id from roles where name ='Administrator'), (select id from permissions where name = 'Administrator_Update')),
((select id from roles where name ='Administrator'), (select id from permissions where name = 'Administrator_Delete')),


((select id from roles where name ='Administrator'), (select id from permissions where name = 'Public_Read')),
((select id from roles where name ='Administrator'), (select id from permissions where name = 'Public_Update')),
((select id from roles where name ='Administrator'), (select id from permissions where name = 'Public_Delete')),

((select id from roles where name ='Administrator'), (select id from permissions where name = 'Profile_Read')),
((select id from roles where name ='Administrator'), (select id from permissions where name = 'Profile_Update')),
((select id from roles where name ='Administrator'), (select id from permissions where name = 'Profile_Delete')),


((select id from roles where name ='Administrator'), (select id from permissions where name = 'Declaration_Create')),
((select id from roles where name ='Administrator'), (select id from permissions where name = 'Declaration_Read')),
((select id from roles where name ='Administrator'), (select id from permissions where name = 'Declaration_Update')),
((select id from roles where name ='Administrator'), (select id from permissions where name = 'Declaration_Delete')),


((select id from roles where name ='Administrator'), (select id from permissions where name = 'Request_Create')),
((select id from roles where name ='Administrator'), (select id from permissions where name = 'Request_Read')),
((select id from roles where name ='Administrator'), (select id from permissions where name = 'Request_Update')),
((select id from roles where name ='Administrator'), (select id from permissions where name = 'Request_Delete')),


((select id from roles where name ='Agent'), (select id from permissions where name = 'Agent_Create')),
((select id from roles where name ='Agent'), (select id from permissions where name = 'Agent_Read')),
((select id from roles where name ='Agent'), (select id from permissions where name = 'Agent_Update')),
((select id from roles where name ='Agent'), (select id from permissions where name = 'Agent_Delete')),


((select id from roles where name ='Agent'), (select id from permissions where name = 'Public_Read')),
((select id from roles where name ='Agent'), (select id from permissions where name = 'Public_Update')),
((select id from roles where name ='Agent'), (select id from permissions where name = 'Public_Delete')),

((select id from roles where name ='Agent'), (select id from permissions where name = 'Profile_Read')),
((select id from roles where name ='Agent'), (select id from permissions where name = 'Profile_Update')),
((select id from roles where name ='Agent'), (select id from permissions where name = 'Profile_Delete')),


((select id from roles where name ='Agent'), (select id from permissions where name = 'Declaration_Create')),
((select id from roles where name ='Agent'), (select id from permissions where name = 'Declaration_Read')),
((select id from roles where name ='Agent'), (select id from permissions where name = 'Declaration_Update')),
((select id from roles where name ='Agent'), (select id from permissions where name = 'Declaration_Delete')),


((select id from roles where name ='Agent'), (select id from permissions where name = 'Request_Create')),
((select id from roles where name ='Agent'), (select id from permissions where name = 'Request_Read')),
((select id from roles where name ='Agent'), (select id from permissions where name = 'Request_Update')),
((select id from roles where name ='Agent'), (select id from permissions where name = 'Request_Delete')),




((select id from roles where name ='Public'), (select id from permissions where name = 'Profile_Read')),
((select id from roles where name ='Public'), (select id from permissions where name = 'Profile_Update')),
((select id from roles where name ='Public'), (select id from permissions where name = 'Profile_Delete')),


((select id from roles where name ='Public'), (select id from permissions where name = 'Declaration_Create')),
((select id from roles where name ='Public'), (select id from permissions where name = 'Declaration_Read')),
((select id from roles where name ='Public'), (select id from permissions where name = 'Declaration_Update')),
((select id from roles where name ='Public'), (select id from permissions where name = 'Declaration_Delete')),


((select id from roles where name ='Public'), (select id from permissions where name = 'Request_Create')),
((select id from roles where name ='Public'), (select id from permissions where name = 'Request_Read')),
((select id from roles where name ='Public'), (select id from permissions where name = 'Request_Update')),
((select id from roles where name ='Public'), (select id from permissions where name = 'Request_Delete'));


