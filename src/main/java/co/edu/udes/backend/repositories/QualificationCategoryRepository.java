package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.QualificationCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QualificationCategoryRepository extends JpaRepository<QualificationCategory, Long> {
    List<QualificationCategory> findByGroupId(Long groupId);
}
