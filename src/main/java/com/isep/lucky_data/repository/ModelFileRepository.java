package com.isep.lucky_data.repository;

import com.isep.lucky_data.model.ModelFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelFileRepository extends JpaRepository<ModelFile, Long> {
}
