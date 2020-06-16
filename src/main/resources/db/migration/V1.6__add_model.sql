CREATE TABLE model (
    id bigserial NOT NULL CONSTRAINT model_pkey PRIMARY KEY,
    title varchar(255) NOT NULL CONSTRAINT model_unique_title UNIQUE,
    description varchar(5000) NOT NULL,
    source varchar(255) NOT NULL,
    uploaded_at timestamp without time zone NOT NULL,
    tag varchar(255),
    downloads bigint NOT NULL,
    model_file_id bigint NOT NULL
);

CREATE TABLE model_consultation (
    department_id bigint NOT NULL,
    model_id bigint NOT NULL,
    consultations bigint NOT NULL
);

CREATE TABLE model_file (
    id bigserial NOT NULL CONSTRAINT model_file_pkey PRIMARY KEY,
    name varchar(255) NOT NULL,
    type varchar(255) NOT NULL,
    data oid NOT NULL
);

ALTER TABLE model
    ADD CONSTRAINT model_file_foreign_key FOREIGN KEY (model_file_id) REFERENCES model_file(id);

ALTER TABLE model_consultation
    ADD CONSTRAINT model_consultation_pkey PRIMARY KEY (model_id, department_id),
    ADD CONSTRAINT model_foreign_key FOREIGN KEY (model_id) REFERENCES model(id),
    ADD CONSTRAINT department_foreign_key FOREIGN KEY (department_id) REFERENCES department(id);