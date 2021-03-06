package com.isep.lucky_data.repository;

import com.isep.lucky_data.model.Dataset;
import com.isep.lucky_data.model.DatasetConsultation;
import com.isep.lucky_data.model.DatasetConsultationKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface DatasetConsultationRepository extends JpaRepository<DatasetConsultation, DatasetConsultationKey> {
    List<DatasetConsultation> findAll();

    @Override
    Optional<DatasetConsultation> findById(DatasetConsultationKey datasetConsultationKey);

    @Override
    void delete(DatasetConsultation datasetConsultation);

    void deleteAllByDataset(Dataset dataset);
}
