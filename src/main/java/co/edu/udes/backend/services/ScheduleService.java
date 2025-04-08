package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.ScheduleDTO;
import co.edu.udes.backend.mappers.ScheduleMapper;
import co.edu.udes.backend.models.Schedule;
import co.edu.udes.backend.repositories.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public List<ScheduleDTO> getAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return ScheduleMapper.INSTANCE.toDtoList(schedules);
    }

    public ScheduleDTO getById(Long id) {
        return ScheduleMapper.INSTANCE.toDto(scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id)));
    }

    public ScheduleDTO create(Schedule schedule) {
        return ScheduleMapper.INSTANCE.toDto(scheduleRepository.save(schedule));
    }

    public ScheduleDTO update(Long id, Schedule schedule) {
        scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id));
        schedule.setId(id);
        return ScheduleMapper.INSTANCE.toDto(scheduleRepository.save(schedule));
    }

    public void delete(Long id) {
        scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id));
        scheduleRepository.deleteById(id);
    }
}
