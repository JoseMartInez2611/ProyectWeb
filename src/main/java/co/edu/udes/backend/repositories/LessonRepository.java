package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.DayOfWeek;
import co.edu.udes.backend.models.Lesson;
import co.edu.udes.backend.models.Room;
import co.edu.udes.backend.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findBySchedule_DayOfWeekAndClassroom(DayOfWeek dayOfWeek, Room classroom);

    List<Lesson> findByGroupIdIn(List<Long> groupIds);

    @Query("SELECT l FROM Lesson l WHERE l.group.id = :groupId")
    List<Lesson> findByGroupId(@Param("groupId") Long groupId);

    @Query("SELECT l.schedule.id FROM Lesson l WHERE l.group.teacher.id = :teacherId")
    List<Long> findScheduleIdsByTeacherId(@Param("teacherId") Long teacherId);

    @Query("SELECT l.schedule.id FROM Lesson l WHERE l.group.id = :groupId")
    List<Long> findScheduleIdsByGroupId(@Param("groupId") Long groupId);

    @Query("SELECT l FROM Lesson l WHERE l.classroom = :room")
    List<Lesson> findByClassroom(@Param("room") Room room);

    @Query("""
        SELECT CONCAT(l.schedule.startHour, ' - ', l.schedule.endHour)
        FROM Lesson l
        WHERE l.classroom.id = :id
        """)
    List<String> getSchedulesByRoomIdFromLesson(@Param("id") Long id);

    @Query("SELECT DISTINCT l.schedule FROM Lesson l WHERE l.group.teacher.id = :teacherId")
    List<Schedule> findSchedulesByTeacherId(@Param("teacherId") Long teacherId);

    @Query("SELECT l.id, " +
            "       s.startHour, s.endHour, dw.day, " + // Traemos el atributo 'day' de DayOfWeek
            "       r.number, r.building, r.floor, " +
            "       g.id, c.name " +
            "FROM Lesson l " +
            "JOIN l.schedule s " +
            "JOIN s.dayOfWeek dw " + // Unimos con la entidad DayOfWeek para obtener el atributo 'day'
            "JOIN l.classroom r " +
            "JOIN l.group g " +
            "JOIN g.course c " +
            "WHERE g.teacher.id = :teacherId")
    List<Object[]> findCustomLessonsDetailsByTeacherId(@Param("teacherId") Long teacherId);

    @Query("SELECT c.name FROM Course c WHERE c.id = :id")
    String getNameByIdCourse(@Param("id") Long id);

}
