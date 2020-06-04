package com.isep.lucky_data.repository;

import com.isep.lucky_data.model.DatasetFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DatasetFileRepository extends JpaRepository<DatasetFile, Long> {
}
