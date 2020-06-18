package com.isep.lucky_data.repository;

import com.isep.lucky_data.model.ModelFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ModelFileRepository extends JpaRepository<ModelFile, Long> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM model_file u WHERE u.id = ?1", nativeQuery = true)
    void deleteFile(Long modelFileId);

}
