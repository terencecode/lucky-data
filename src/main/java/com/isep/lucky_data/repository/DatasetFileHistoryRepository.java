package com.isep.lucky_data.repository;

import com.isep.lucky_data.model.DatasetFile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.isep.lucky_data.model.DatasetFileHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

@Repository
public interface DatasetFileHistoryRepository extends JpaRepository<DatasetFileHistory, Long> {

    @Query("SELECT max(d.date) FROM DatasetFileHistory d WHERE d.datasetFile = :datasetFile")
    Optional<Date> findDateMaxByDatasetFileHistory(@Param("datasetFile") DatasetFile datasetFile);
}
