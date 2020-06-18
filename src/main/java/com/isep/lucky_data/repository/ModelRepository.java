package com.isep.lucky_data.repository;

import com.isep.lucky_data.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> findAll();

    List<Model> findAllByOrderByUploadedAtAsc();
    List<Model> findAllByOrderByUploadedAtDesc();
    List<Model> findAllByOrderByTitleAsc();
    List<Model> findAllByOrderByTitleDesc();
    List<Model> findAllByOrderByDownloadsAsc();
    List<Model> findAllByOrderByDownloadsDesc();

    Optional<Model> findById(Long modelId);

    void deleteById(Long modelId);
}
