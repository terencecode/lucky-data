INSERT INTO roles (role) VALUES (0) ON CONFLICT DO NOTHING;
INSERT INTO roles (role) VALUES (1) ON CONFLICT DO NOTHING;
INSERT INTO roles (role) VALUES (2) ON CONFLICT DO NOTHING;

INSERT INTO application_user (first_name, last_name, email, password, department_id) VALUES ('John', 'Doe', 'admin@lcl.fr', '$2a$10$/zyiyVs8xECEDUqdqpZKI.8S.bHh2OvkjOYMHsmTO4CVrB9pAsrMa', (SELECT id FROM department WHERE name='Data-science'));
INSERT INTO user_roles (user_id, role_id) VALUES ((SELECT id FROM application_user WHERE email = 'admin@lcl.fr'), (SELECT id FROM roles WHERE role=2));