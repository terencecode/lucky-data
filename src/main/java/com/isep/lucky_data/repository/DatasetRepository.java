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

    List<Dataset> findAllByOrderByUploadedAtAsc();
    List<Dataset> findAllByOrderByUploadedAtDesc();
    List<Dataset> findAllByOrderByTitleAsc();
    List<Dataset> findAllByOrderByTitleDesc();
    List<Dataset> findAllByOrderByDownloadsAsc();
    List<Dataset> findAllByOrderByDownloadsDesc();

    Optional<Dataset> findById(Long datasetId);
}
