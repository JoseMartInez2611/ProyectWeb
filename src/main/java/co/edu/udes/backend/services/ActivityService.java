package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.ActivityDTO;
import co.edu.udes.backend.mappers.ActivityMapper;
import co.edu.udes.backend.models.Activity;
import co.edu.udes.backend.repositories.ActivityRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;

    public List<ActivityDTO> getAll() {
        return activityRepository.findAll().stream()
                .map(activityMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ActivityDTO getById(Long id) {
        Activity entity = activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        return activityMapper.toDTO(entity);
    }

    public ActivityDTO create(ActivityDTO dto) {
        Activity entity = activityMapper.toEntity(dto);
        return activityMapper.toDTO(activityRepository.save(entity));
    }

    public ActivityDTO update(Long id, ActivityDTO dto) {
        activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        dto.setId(id);
        Activity updated = activityRepository.save(activityMapper.toEntity(dto));
        return activityMapper.toDTO(updated);
    }

    public void delete(Long id) {
        Activity entity = activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        activityRepository.delete(entity);
    }


}
