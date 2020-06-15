package com.isep.lucky_data.repository;

import com.isep.lucky_data.model.DatasetFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface DatasetFileRepository extends JpaRepository<DatasetFile, Long> {
    void deleteById(Long datasetFileId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM dataset_file u WHERE u.id = ?1", nativeQuery = true)
    void deleteFile(Long datasetFileId);
}
