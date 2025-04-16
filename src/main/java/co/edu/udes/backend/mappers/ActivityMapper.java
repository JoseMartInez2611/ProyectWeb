package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.ActivityDTO;
import co.edu.udes.backend.models.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AnswerDocumentMapper.class, GroupMapper.class})
public interface ActivityMapper {
    ActivityMapper INSTANCE = Mappers.getMapper(ActivityMapper.class);

    @Mapping(source = "groupId", target = "group.id")
    Activity toEntity(ActivityDTO activity);
    List<Activity> toEntityList(List<ActivityDTO> activities);

    @Mapping(source = "group.id", target = "groupId")
    ActivityDTO toDto(Activity activity);
    List<ActivityDTO> toDtoList(List<Activity> activities);
}
