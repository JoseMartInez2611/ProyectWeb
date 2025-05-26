package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.AcademicRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AcademicRegistrationRepository extends JpaRepository<AcademicRegistration, Long> {
    List<AcademicRegistration> findByStudentId(Long studentId);
    List<AcademicRegistration> findByGroupId(Long groupId);
    List<AcademicRegistration> findByGroupIdAndAcademicPeriodId(Long groupId, Long academicPeriodId);
    List<AcademicRegistration> findByStudentIdAndAcademicPeriodId(Long studentId, Long academicPeriodId);
    boolean existsByStudentIdAndGroupId(Long studentId, Long groupId);
    void deleteByStudentIdAndGroupId(Long studentId, Long groupId);
    Optional<AcademicRegistration> findByStudentIdAndGroupId(Long studentId, Long groupId);
    long countByGroupId(Long groupId);
}
