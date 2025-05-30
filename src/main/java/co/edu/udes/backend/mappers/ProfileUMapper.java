package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.inheritanceDTO.ProfileUDTO;
import co.edu.udes.backend.models.inheritance.ProfileU;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {MessageMapper.class,
                ReportMapper.class,
                BorrowMapper.class,
                CommunicationMapper.class
        }
)public interface ProfileUMapper {
    ProfileUMapper INSTANCE = Mappers.getMapper(ProfileUMapper.class);

    ProfileU toEntity(ProfileUDTO profileU);
    List<ProfileU> toEntityList(List<ProfileUDTO> profilesU);

    ProfileUDTO toDto(ProfileU profileU);
    List<ProfileUDTO> toDtoList(List<ProfileU> profilesU);
}
