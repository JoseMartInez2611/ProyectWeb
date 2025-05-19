package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, Long> {

    @Query("SELECT q FROM Qualification q WHERE q.student.id = :studentId")
    List<Qualification> findAllByStudentId(@Param("studentId") Long studentId);

    List<Qualification>
    findByStudentIdAndEvaluationQualificationCategoryGroupId(
            Long studentId,
            Long groupId
    );

    @Query("""
        SELECT q
        FROM Qualification q
        JOIN q.evaluation e
        JOIN e.qualificationCategory qc
        WHERE q.student.id = :studentId
          AND qc.group.id = :groupId
          AND qc.academicSubperiod.id = :subPeriodId
    """)
    List<Qualification> findAllByStudentAndGroupIdAndCut(
            @Param("studentId") Long studentId,
            @Param("groupId") Long groupId,
            @Param("subPeriodId") Long subPeriodId
    );


    @Query("SELECT AVG(q.qualification) FROM Qualification q WHERE q.student.id = :studentId")
    Double findAverageScoreByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT DISTINCT CONCAT(p.firstName,' ',p.lastName) AS fullName\n" +
            "FROM Qualification q\n" +
            "JOIN q.student s\n" +
            "JOIN ProfileU p ON s.id = p.id\n" +
            "WHERE s.id = :studentId\n")
    String findFullNameByStudentId(@Param("studentId") Long studentId);

    @Query("""
        SELECT q
        FROM Qualification q
        JOIN q.evaluation e
        JOIN e.qualificationCategory qc
        WHERE qc.group.id = :groupId
    """)
    List<Qualification> findAllByGroupId(@Param("groupId") Long groupId);



    @Query("""
        SELECT COALESCE(SUM(qc.percentage), 0.0)
        FROM QualificationCategory qc
        JOIN Evaluation e ON e.qualificationCategory = qc
        JOIN Qualification q ON q.evaluation = e
        WHERE q.student.id = :studentId
          AND qc.group.id = :groupId
          AND qc.academicSubperiod.id = :subPeriodId
    """)
    double getPercentagesUsed(
            @Param("studentId") Long studentId,
            @Param("groupId") Long groupId,
            @Param("subPeriodId") Long subPeriodId
    );

}


