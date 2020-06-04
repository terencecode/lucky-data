ALTER TABLE dataset
    ADD COLUMN dataset_file_id bigint;

UPDATE dataset
SET dataset_file_id = dataset_file.id
FROM dataset_file
WHERE dataset.id = dataset_file.dataset_id;

ALTER TABLE dataset
    ALTER COLUMN dataset_file_id SET NOT NULL,
    ADD CONSTRAINT dataset_file_foreign_key FOREIGN KEY (dataset_file_id) REFERENCES dataset_file(id);

ALTER TABLE dataset_file
    DROP CONSTRAINT dataset_file_unique_dataset_id,
    DROP CONSTRAINT dataset_foreign_key,
    DROP COLUMN dataset_id;
