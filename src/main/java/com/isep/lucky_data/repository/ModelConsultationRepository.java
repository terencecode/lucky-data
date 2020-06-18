package com.isep.lucky_data.repository;

import com.isep.lucky_data.model.ModelConsultation;
import com.isep.lucky_data.model.ModelConsultationKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelConsultationRepository extends JpaRepository<ModelConsultation, ModelConsultationKey> {
    List<ModelConsultation> findAll();

    @Override
    Optional<ModelConsultation> findById(ModelConsultationKey modelConsultationKey);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM model_consultation u WHERE u.model_id = ?1", nativeQuery = true)
    void deleteConsultation(Long modeltConsultationId);
}
