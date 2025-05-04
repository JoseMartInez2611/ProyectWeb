package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Group;
import co.edu.udes.backend.models.QualificationCategory;
import co.edu.udes.backend.models.inheritance.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findByQualificationCategory(QualificationCategory qualificationCategory);
}
