package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.DayOfWeekDTO;
import co.edu.udes.backend.mappers.DayOfWeekMapper;
import co.edu.udes.backend.repositories.DayOfWeekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DayOfWeekService {

    private final DayOfWeekRepository dayOfWeekRepository;
    private final DayOfWeekMapper dayOfWeekMapper = new DayOfWeekMapper();

    public List<DayOfWeekDTO> getAll() {
        return dayOfWeekRepository.findAll().stream()
                .map(dayOfWeekMapper::toDTO)
                .toList();
    }

    public DayOfWeekDTO getById(Long id) {
        return dayOfWeekMapper.toDTO(dayOfWeekRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Day of week not found with id: " + id)));
    }

    public DayOfWeekDTO create(DayOfWeekDTO dto) {
        return dayOfWeekMapper.toDTO(dayOfWeekRepository.save(dayOfWeekMapper.toEntity(dto)));
    }

    public DayOfWeekDTO update(Long id, DayOfWeekDTO dto) {
        dayOfWeekRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Day of week not found with id: " + id));
        dto.setId(id);
        return dayOfWeekMapper.toDTO(dayOfWeekRepository.save(dayOfWeekMapper.toEntity(dto)));
    }

    public void delete(Long id) {
        dayOfWeekRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Day of week not found with id: " + id));
        dayOfWeekRepository.deleteById(id);
    }
}
