package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.ScheduleDTO;
import co.edu.udes.backend.mappers.ScheduleMapper;
import co.edu.udes.backend.repositories.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    public List<ScheduleDTO> getAll() {
        return scheduleRepository.findAll().stream()
                .map(scheduleMapper::toDTO)
                .toList();
    }

    public ScheduleDTO getById(Long id) {
        return scheduleMapper.toDTO(scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id)));
    }

    public ScheduleDTO create(ScheduleDTO dto) {
        return scheduleMapper.toDTO(scheduleRepository.save(scheduleMapper.toEntity(dto)));
    }

    public ScheduleDTO update(Long id, ScheduleDTO dto) {
        scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id));
        dto.setId(id);
        return scheduleMapper.toDTO(scheduleRepository.save(scheduleMapper.toEntity(dto)));
    }

    public void delete(Long id) {
        scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id));
        scheduleRepository.deleteById(id);
    }
}
