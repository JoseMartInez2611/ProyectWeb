package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.DayOfWeek;
import co.edu.udes.backend.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByDayOfWeekAndStartHourAndEndHour(
            DayOfWeek dayOfWeek,
            LocalTime startHour,
            LocalTime endHour
    );
}
