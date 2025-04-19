package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.ScheduleDTO;
import co.edu.udes.backend.mappers.ScheduleMapper;
import co.edu.udes.backend.models.Schedule;
import co.edu.udes.backend.repositories.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    @Autowired
    private ScheduleMapper scheduleMapper;

    public List<ScheduleDTO> getAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return scheduleMapper.toDtoList(schedules);
    }

    public ScheduleDTO getById(Long id) {
        return scheduleMapper.toDto(scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id)));
    }

    public ScheduleDTO create(Schedule schedule) {
        validateUniqueSchedule(schedule, null);
        validateDuration(schedule);
        return scheduleMapper.toDto(scheduleRepository.save(schedule));
    }

    public List<ScheduleDTO> createMultiple(List<Schedule> schedules) {
        List<ScheduleDTO> newSchedules = new ArrayList<>();
        for (Schedule schedule : schedules) {
            newSchedules.add(create(schedule));
        }
        return newSchedules;
    }

    public ScheduleDTO update(Long id, Schedule schedule) {
        Schedule existing = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id));

        validateUniqueSchedule(schedule, id);
        validateDuration(schedule);

        existing.setDayOfWeek(schedule.getDayOfWeek());
        existing.setStartHour(schedule.getStartHour());
        existing.setEndHour(schedule.getEndHour());

        return scheduleMapper.toDto(scheduleRepository.save(existing));
    }

    public void delete(Long id) {
        scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id));
        scheduleRepository.deleteById(id);
    }

    private void validateDuration(Schedule schedule) {
        if (schedule.getEndHour().isBefore(schedule.getStartHour().plusHours(2))) {
            throw new RuntimeException("End hour must be at least 2 hours after start hour.");
        }
    }

    private void validateUniqueSchedule(Schedule schedule, Long excludeId) {
        List<Schedule> duplicates = scheduleRepository.findByDayOfWeekAndStartHourAndEndHour(
                schedule.getDayOfWeek(),
                schedule.getStartHour(),
                schedule.getEndHour()
        );

        Schedule existingSchedule = duplicates.stream()
                .filter(s -> excludeId == null || !s.getId().equals(excludeId))
                .findFirst()
                .orElse(null);

        if (existingSchedule != null) {
            throw new RuntimeException(
                    "A schedule with the same day and hours already exists (ID: " + existingSchedule.getId() + ")."
            );
        }
    }
}
