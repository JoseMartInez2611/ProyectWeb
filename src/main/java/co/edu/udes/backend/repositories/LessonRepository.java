package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.DayOfWeek;
import co.edu.udes.backend.models.Lesson;
import co.edu.udes.backend.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findBySchedule_DayOfWeekAndClassroom(DayOfWeek dayOfWeek, Room classroom);

    @Query("SELECT l FROM Lesson l WHERE l.classroom = :room")
    List<Lesson> findByClassroom(@Param("room") Room room);

    @Query("""
        SELECT CONCAT(l.schedule.startHour, ' - ', l.schedule.endHour)
        FROM Lesson l
        WHERE l.classroom.id = :id
        """)
    List<String> getSchedulesByRoomIdFromLesson(@Param("id") Long id);
}
