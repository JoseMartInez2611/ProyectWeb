package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.QualificationCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QualificationCategoryRepository extends JpaRepository<QualificationCategory, Long> {
    List<QualificationCategory> findByGroupId(Long groupId);

    @Query("SELECT q FROM QualificationCategory q WHERE q.academicSubperiod.id = :idAcademicSubperiod AND q.group.id = :idGroup")
    List<QualificationCategory> getAllByAcademicSubperiodIdAndGroupId(Long idAcademicSubperiod, Long idGroup);

    @Query("""
    SELECT COALESCE(SUM(qc.percentage), 0.0)
    FROM QualificationCategory qc
    JOIN Evaluation e ON e.category.id = qc.id
    JOIN Qualification q ON q.evaluation.id = e.id
    WHERE q.student.id = :idStudent
    AND qc.group.id = :idGroup
    AND qc.academicSubperiod.id = :idAcademicSubperiod
""")
    double getPercentagesUsed(@Param("idStudent") Long idStudent,
                              @Param("idGroup") Long idGroup,
                              @Param("idAcademicSubperiod") Long idAcademicSubperiod
    );

}
