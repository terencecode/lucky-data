CREATE TABLE application_user (
     id bigserial NOT NULL CONSTRAINT application_user_pkey PRIMARY KEY,
     first_name varchar(255) NOT NULL,
     last_name varchar(255) NOT NULL,
     email varchar(255) NOT NULL CONSTRAINT application_user_email_unique UNIQUE,
     password varchar(100) NOT NULL,
     department_id bigint NOT NULL
);

CREATE TABLE dataset (
    id bigserial NOT NULL CONSTRAINT dataset_pkey PRIMARY KEY,
    title varchar(255) NOT NULL CONSTRAINT dataset_unique_title UNIQUE,
    description varchar(5000) NOT NULL,
    source varchar(255) NOT NULL,
    uploaded_at timestamp without time zone NOT NULL,
    date timestamp without time zone NOT NULL,
    start_date timestamp without time zone,
    end_date timestamp without time zone,
    latitude real,
    longitude real,
    tag varchar(255),
    downloads bigint NOT NULL
);

CREATE TABLE dataset_consultation (
    department_id bigint NOT NULL,
    dataset_id bigint NOT NULL,
    consultations bigint NOT NULL
);

CREATE TABLE dataset_file (
    id bigserial NOT NULL CONSTRAINT dataset_file_pkey PRIMARY KEY,
    name varchar(255) NOT NULL,
    type varchar(255) NOT NULL,
    data oid NOT NULL,
    dataset_id bigint NOT NULL CONSTRAINT dataset_file_unique_dataset_id UNIQUE
);

CREATE TABLE department (
    id bigserial NOT NULL CONSTRAINT department_pkey PRIMARY KEY,
    name varchar(255) NOT NULL CONSTRAINT department_unique_name UNIQUE
);

CREATE TABLE roles (
    id bigserial NOT NULL CONSTRAINT roles_pkey PRIMARY KEY,
    role integer NOT NULL CONSTRAINT roles_unique_role UNIQUE
);

CREATE TABLE user_roles (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);

ALTER TABLE application_user
    ADD CONSTRAINT department_foreign_key FOREIGN KEY (department_id) REFERENCES department(id);

ALTER TABLE dataset_file
    ADD CONSTRAINT dataset_foreign_key FOREIGN KEY (dataset_id) REFERENCES dataset(id);

ALTER TABLE dataset_consultation
    ADD CONSTRAINT dataset_consultation_pkey PRIMARY KEY (dataset_id, department_id),
    ADD CONSTRAINT dataset_foreign_key FOREIGN KEY (dataset_id) REFERENCES dataset(id),
    ADD CONSTRAINT department_foreign_key FOREIGN KEY (department_id) REFERENCES department(id);

ALTER TABLE user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id),
    ADD CONSTRAINT role_foreign_key FOREIGN KEY (role_id) REFERENCES roles(id),
    ADD CONSTRAINT user_foreign_key FOREIGN KEY (user_id) REFERENCES application_user(id);