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

    public List<ActivityDTO> getAll() {
        List<Activity> activities = activityRepository.findAll();
        return ActivityMapper.INSTANCE.toDtoList(activities);
    }

    public ActivityDTO getById(Long id) {
        return ActivityMapper.INSTANCE.toDto(activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found with id: " + id)));
    }

    public ActivityDTO create(Activity activity) {
        return ActivityMapper.INSTANCE.toDto(activityRepository.save(activity));
    }

    public ActivityDTO update(Long id, Activity activity) {
        activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found with id: " + id));
        activity.setId(id);
        return ActivityMapper.INSTANCE.toDto(activityRepository.save(activity));
    }

    public void delete(Long id) {
        activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found with id: " + id));
        activityRepository.deleteById(id);
    }


}
