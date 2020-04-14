package com.isep.lucky_data.repository;

import com.isep.lucky_data.model.Dataset;
import com.isep.lucky_data.model.DatasetFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface DatasetFileRepository extends JpaRepository<DatasetFile, Long> {
    Optional<DatasetFile> findByDataset(Dataset dataset);
}
