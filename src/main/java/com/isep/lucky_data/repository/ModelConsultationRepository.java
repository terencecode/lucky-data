package com.isep.lucky_data.repository;

import com.isep.lucky_data.model.ModelConsultation;
import com.isep.lucky_data.model.ModelConsultationKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelConsultationRepository extends JpaRepository<ModelConsultation, ModelConsultationKey> {
    List<ModelConsultation> findAll();

    @Override
    Optional<ModelConsultation> findById(ModelConsultationKey modelConsultationKey);
}
