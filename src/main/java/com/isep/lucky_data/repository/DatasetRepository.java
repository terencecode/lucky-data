package com.isep.lucky_data.repository;

import com.isep.lucky_data.model.Dataset;
import com.isep.lucky_data.model.DatasetFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DatasetRepository extends JpaRepository<Dataset, Long> {
    List<Dataset> findAll();

    Optional<Dataset> findById(Long datasetId);
}
