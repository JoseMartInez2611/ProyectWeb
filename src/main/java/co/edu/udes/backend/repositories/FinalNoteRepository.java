package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.FinalNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinalNoteRepository extends JpaRepository<FinalNote, Long> {

    @Query("SELECT fn.note FROM FinalNote fn WHERE fn.academicRecord.id = :idAcademicRecord")
    Double getNoteByAcademicRecordId(@Param("idAcademicRecord") Long idAcademicRecord);

    @Query("""
        SELECT DISTINCT CONCAT(p.firstName, ' ', p.lastName)
        FROM FinalNote fn
        JOIN fn.academicRecord ar
        JOIN ar.student s
        JOIN ProfileU p ON s.id = p.id
        WHERE ar.id = :academicRecordId
    """)
    String findStudentFullNameByAcademicRecordId(@Param("academicRecordId") Long academicRecordId);

    @Query("SELECT AVG(fn.note) FROM FinalNote fn WHERE fn.group.id = :groupId")
    Double getGroupAverage(@Param("groupId") Long groupId);

    @Query("SELECT DISTINCT c.name " +
            "FROM FinalNote fn " +
            "JOIN fn.group g " +
            "JOIN g.course c " +
            "WHERE g.id = :groupId")
    String findCourseNameByGroupId(@Param("groupId") Long groupId);

    FinalNote findByAcademicRecordIdAndGroupId(Long academicRecordId, Long groupId);
}
