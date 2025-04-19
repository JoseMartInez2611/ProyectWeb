package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.DayOfWeek;
import co.edu.udes.backend.models.Lesson;
import co.edu.udes.backend.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findBySchedule_DayOfWeekAndClassroom(DayOfWeek dayOfWeek, Room classroom);

}
