package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.ActivityDTO;
import co.edu.udes.backend.mappers.ActivityMapper;
import co.edu.udes.backend.models.Activity;
import co.edu.udes.backend.models.Group;
import co.edu.udes.backend.models.QualificationCategory;
import co.edu.udes.backend.repositories.ActivityRepository;
import co.edu.udes.backend.repositories.GroupRepository;
import co.edu.udes.backend.repositories.QualificationCategoryRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final GroupRepository groupRepository;
    private final QualificationCategoryRepository qualificationCategoryRepository;
    @Autowired
    private ActivityMapper activityMapper;

    public List<ActivityDTO> getAll() {
        List<Activity> activities = activityRepository.findAll();
        return activityMapper.toDtoList(activities);
    }

    public ActivityDTO getById(Long id) {
        return activityMapper.toDto(activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found with id: " + id)));
    }

    public ActivityDTO create(Activity activity) {
        return activityMapper.toDto(activityRepository.save(activity));
    }

    public List<ActivityDTO> createMultiple(List<Activity> users) {
        return activityMapper.toDtoList(
                activityRepository.saveAll(users)
        );
    }

    public ActivityDTO update(Long id, Activity activity) {
        activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found with id: " + id));
        activity.setId(id);
        return activityMapper.toDto(activityRepository.save(activity));
    }

    public void delete(Long id) {
        activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found with id: " + id));
        activityRepository.deleteById(id);
    }

    //modulos

    /**
     * Crea una nueva actividad y la asocia a un grupo.
     *
     * @param activity La entidad Activity a crear. Debe contener la información del grupo asociado (a través de su ID).
     * @return El ActivityDTO de la actividad creada.
     * @throws ResourceNotFoundException Si no se encuentra el grupo con el ID proporcionado en la actividad.
     */
    public ActivityDTO createActivity(Activity activity) {
        // Buscar la categoría asociada por el ID
        QualificationCategory qualificationCategory = qualificationCategoryRepository.findById(activity.getQualificationCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Qualification category not found with id: " + activity.getQualificationCategory().getId()));

        // Establecer la relación entre la actividad y el grupo
        activity.setQualificationCategory(qualificationCategory);

        // Guardar la nueva actividad
        Activity savedActivity = activityRepository.save(activity);
        return activityMapper.toDto(savedActivity);
    }

    /**
     * Crea múltiples actividades y las asocia a sus respectivos grupos.
     *
     * @param activities Una lista de entidades Activity a crear. Cada actividad debe contener la información del grupo asociado.
     * @return Una lista de ActivityDTO de las actividades creadas.
     */
    public List<ActivityDTO> createMultipleActivities(List<Activity> activities) {
        List<ActivityDTO> createdActivities = new ArrayList<>();
        for (Activity activity : activities) {
            createdActivities.add(createActivity(activity));
        }
        return createdActivities;
    }





}
