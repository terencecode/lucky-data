CREATE TABLE dataset_file_history (
    id bigserial NOT NULL CONSTRAINT dataset_file_history_pkey PRIMARY KEY,
    date timestamp without time zone NOT NULL,
    data oid NOT NULL,
    dataset_file_id bigint NOT NULL
);

ALTER TABLE dataset_file_history
    ADD CONSTRAINT dataset_file_foreign_key FOREIGN KEY (dataset_file_id) REFERENCES dataset_file(id);