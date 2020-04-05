package com.isep.lucky_data.repository;

import com.isep.lucky_data.model.DatasetConsultation;
import com.isep.lucky_data.model.DatasetConsultationKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatasetConsultationRepository extends JpaRepository<DatasetConsultation, DatasetConsultationKey> {
    List<DatasetConsultation> findAll();
}
