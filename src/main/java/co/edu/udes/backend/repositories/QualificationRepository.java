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

    @Query("SELECT q FROM Qualification q " +
            "JOIN q.evaluation e " +
            "JOIN e.qualificationCategories qc " +
            "WHERE q.student.id = :studentId AND qc.group.id = :groupId")
    List<Qualification> findAllByStudentAndGroupId(@Param("studentId") Long studentId, @Param("groupId") Long groupId);

    @Query("SELECT q FROM Qualification q " +
            "JOIN q.evaluation e " +
            "JOIN e.qualificationCategories qc " +
            "WHERE q.student.id = :studentId AND qc.group.id = :groupId AND qc.academicSubperiod.id = :idAcademicSubperiod")
    List<Qualification> findAllByStudentAndGroupIdAndCut(
            @Param("studentId") Long studentId,
            @Param("groupId") Long groupId,
            @Param("idAcademicSubperiod") Long idAcademicSubperiod
    );


    @Query("SELECT AVG(q.qualification) FROM Qualification q WHERE q.student.id = :studentId")
    Double findAverageScoreByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT DISTINCT CONCAT(p.firstName,' ',p.lastName) AS fullName\n" +
            "FROM Qualification q\n" +
            "JOIN q.student s\n" +
            "JOIN ProfileU p ON s.id = p.id\n" +
            "WHERE s.id = :studentId\n")
    String findFullNameByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT q FROM Qualification q WHERE q.group.id = :idGroup")
    List<Qualification> findAllByGroupId(Long idGroup);
}


