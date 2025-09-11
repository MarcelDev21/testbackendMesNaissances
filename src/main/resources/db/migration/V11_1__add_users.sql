insert into profiles(firstname, lastname, password, email, active, roles_id)
values
('Agent', 'chillo.tech', '$2a$10$YseC4R80guA1QpZvKIecl.BBqxvWCNoeoWHtK4CWS0U.9I1CCbIvi', 'agent@chilloh.tech', true, (select id from roles where name = 'Agent')),
('Administrator', 'chillo.tech', '$2a$10$YseC4R80guA1QpZvKIecl.BBqxvWCNoeoWHtK4CWS0U.9I1CCbIvi', 'Administrator@chilloh.tech', true, (select id from roles where name = 'Administrator'));