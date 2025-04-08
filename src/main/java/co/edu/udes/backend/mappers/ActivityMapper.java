package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.ActivityDTO;
import co.edu.udes.backend.models.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ActivityMapper {
    ActivityMapper INSTANCE = Mappers.getMapper(ActivityMapper.class);

    Activity toEntity(ActivityDTO activity);
    List<Activity> toEntityList(List<ActivityDTO> activities);

    ActivityDTO toDto(Activity activity);
    List<ActivityDTO> toDtoList(List<Activity> activities);
}
