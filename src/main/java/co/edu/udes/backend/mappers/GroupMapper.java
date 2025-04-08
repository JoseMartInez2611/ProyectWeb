package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.GroupDTO;
import co.edu.udes.backend.models.Group;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    Group toEntity(GroupDTO group);
    List<Group> toEntityList(List<GroupDTO> groups);

    GroupDTO toDto(Group group);
    List<GroupDTO> toDtoList(List<Group> groups);
}
