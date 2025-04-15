package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.ScheduleDTO;
import co.edu.udes.backend.mappers.ScheduleMapper;
import co.edu.udes.backend.models.Schedule;
import co.edu.udes.backend.repositories.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return scheduleMapper.toDto(scheduleRepository.save(schedule));
    }

    public List<ScheduleDTO> createMultiple(List<Schedule> schedules) {
        return scheduleMapper.toDtoList(
                scheduleRepository.saveAll(schedules)
        );
    }

    public ScheduleDTO update(Long id, Schedule schedule) {
        scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id));
        schedule.setId(id);
        return scheduleMapper.toDto(scheduleRepository.save(schedule));
    }

    public void delete(Long id) {
        scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id));
        scheduleRepository.deleteById(id);
    }
}
