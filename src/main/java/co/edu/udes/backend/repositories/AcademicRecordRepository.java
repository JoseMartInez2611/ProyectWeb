package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.AcademicRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AcademicRecordRepository extends JpaRepository<AcademicRecord, Long> {
    Optional<AcademicRecord> findByStudentId(Long studentId);
}
