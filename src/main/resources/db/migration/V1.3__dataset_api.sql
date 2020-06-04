CREATE TABLE dataset_api (
    id bigserial NOT NULL CONSTRAINT dataset_api_pkey PRIMARY KEY,
    url varchar(1000) NOT NULL,
    token_name varchar(255) NOT NULL,
    token_value varchar(1000) NOT NULL,
    method varchar(255) NOT NULL,
    body varchar(10000),
    form_data varchar(10000),
    query_params varchar(10000),
    path_params varchar(10000),
    content_type varchar(500) NOT NULL,
    downloaded_at timestamp without time zone NOT NULL,
    validity bigint
);

ALTER TABLE dataset
    ADD COLUMN dataset_api_id bigint,
    ADD CONSTRAINT dataset_api_foreign_key FOREIGN KEY (dataset_api_id) REFERENCES dataset_api(id);
