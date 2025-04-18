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

    @Query("SELECT AVG(q.qualification) FROM Qualification q WHERE q.student.id = :studentId")
    Double findAverageScoreByStudentId(@Param("studentId") Long studentId);


}


