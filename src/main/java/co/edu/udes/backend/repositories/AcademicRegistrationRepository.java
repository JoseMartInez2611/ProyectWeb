package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.AcademicRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicRegistrationRepository extends JpaRepository<AcademicRegistration, Long> {
    List<AcademicRegistration> findByStudentId(Long studentId);
    boolean existsByStudentIdAndGroupId(Long studentId, Long groupId);
}
