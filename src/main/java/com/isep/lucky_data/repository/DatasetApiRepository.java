package com.isep.lucky_data.repository;

import com.isep.lucky_data.model.DatasetApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatasetApiRepository extends JpaRepository<DatasetApi, Long> {
    void deleteById(Long datasetApiId);
}
