INSERT INTO department (id, name) VALUES (1, 'Finances');
INSERT INTO department (id, name) VALUES (2, 'Risques');

INSERT INTO application_user (id, first_name, last_name, email, password, department_id)
VALUES (1, 'William', 'Trojanowski', 'w.tr@hotmail.fr', '$2a$10$kWVP951T14Xm9JrbDropIeyx2Vm1F.9s/7n5ud//AMLjeWxsB5fGS', 1);

INSERT INTO dataset (id, title, description, source, uploaded_at, date, start_date, end_date, downloads)
VALUES (1, 'Démographie - Recensement de la Population par IRIS',
        'Données infracommunales de l''agglomération de Poitiers issues du recensement rénové de la population de l''INSEE – Données construites sur un cycle de 5 ans. Ainsi les données ne sont comparables qu''avec les données proposées 5 ans auparavant. Les thèmes regroupés ici : Population, Famille, Formation, Logement, Activité.',
        'Data Gouv', '2020-04-14 03:07:52.06', '2016-01-01 00:00:00.000', '2012-01-01 00:00:00.000', '2016-01-01 00:00:00.000', 0);

INSERT INTO dataset_file (id, name, type, data, dataset_id)
VALUES (1, 'demographie-recensement-de-la-population-population-par-iris.csv', 'text/csv',
        lo_import('${datasets.path}/demographie-recensement-de-la-population-population-par-iris.csv'), 1);

INSERT INTO dataset_consultation (department_id, dataset_id, consultations) VALUES (1, 1, 0);

INSERT INTO roles (id, role) VALUES (1, 0);
INSERT INTO roles (id, role) VALUES (2, 1);
INSERT INTO roles (id, role) VALUES (3, 2);

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);