package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.DayOfWeekDTO;
import co.edu.udes.backend.mappers.DayOfWeekMapper;
import co.edu.udes.backend.models.DayOfWeek;
import co.edu.udes.backend.repositories.DayOfWeekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DayOfWeekService {

    private final DayOfWeekRepository dayOfWeekRepository;

    public List<DayOfWeekDTO> getAll() {
        List<DayOfWeek> dayOfWeeks = dayOfWeekRepository.findAll();
        return DayOfWeekMapper.INSTANCE.toDtoList(dayOfWeeks);
    }

    public DayOfWeekDTO getById(Long id) {
        return DayOfWeekMapper.INSTANCE.toDto(dayOfWeekRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Day of week not found with id: " + id)));
    }

    public DayOfWeekDTO create(DayOfWeek dayOfWeek) {
        return DayOfWeekMapper.INSTANCE.toDto(dayOfWeekRepository.save(dayOfWeek));
    }

    public DayOfWeekDTO update(Long id, DayOfWeek dayOfWeek) {
        dayOfWeekRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
        dayOfWeek.setId(id);
        return DayOfWeekMapper.INSTANCE.toDto(dayOfWeekRepository.save(dayOfWeek));
    }

    public void delete(Long id) {
        dayOfWeekRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Day of week not found with id: " + id));
        dayOfWeekRepository.deleteById(id);
    }
}
