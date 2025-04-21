package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    boolean existsByLessonIdAndStudentIdAndDate(Long lessonId, Long studentId, LocalDate date);
}
