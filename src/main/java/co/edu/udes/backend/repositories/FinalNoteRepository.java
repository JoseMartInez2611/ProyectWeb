package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.FinalNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinalNoteRepository extends JpaRepository<FinalNote, Long> {

    @Query("SELECT fn.percentage FROM FinalNote fn WHERE fn.academicRecord.id = :idAcademicRecord")
    List<Double> getPercentagesByAcademicRecordId(@Param("idAcademicRecord") Long idAcademicRecord);

    @Query("SELECT fn.note FROM FinalNote fn WHERE fn.academicRecord.id = :idAcademicRecord")
    List<Double> getNotesByAcademicRecordId(@Param("idAcademicRecord") Long idAcademicRecord);

    @Query("""
        SELECT DISTINCT CONCAT(p.firstName, ' ', p.lastName)
        FROM FinalNote fn
        JOIN fn.academicRecord ar
        JOIN ar.student s
        JOIN ProfileU p ON s.id = p.id
        WHERE ar.id = :academicRecordId
    """)
    String findStudentFullNameByAcademicRecordId(@Param("academicRecordId") Long academicRecordId);


}
