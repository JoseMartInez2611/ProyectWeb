package co.edu.udes.backend.mappers;


import co.edu.udes.backend.dto.inheritanceDTO.CommunicationDTO;
import co.edu.udes.backend.models.inheritance.Communication;
import co.edu.udes.backend.models.inheritance.ProfileU;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        uses = {ProfileUMapper.class}
)

public interface CommunicationMapper {
    CommunicationMapper INSTANCE = Mappers.getMapper(CommunicationMapper.class);

    @Mapping(source = "receiverIds", target = "receivers", qualifiedByName = "mapIdsToProfiles")
    Communication toEntity(CommunicationDTO communication);
    List<Communication> toEntityList(List<CommunicationDTO> communications);

    @Mapping(source = "receivers", target = "receiverIds", qualifiedByName = "mapProfilesToIds")
    CommunicationDTO toDto(Communication communication);
    List<CommunicationDTO> toDtoList(List<Communication> communications);

    @Named("mapIdsToProfiles")
    static List<ProfileU> mapIdsToProfiles(List<Long> ids) {
        if (ids == null) return null;
        List<ProfileU> profiles = new ArrayList<>();
        for (Long id : ids) {
            ProfileU profile = new ProfileU();
            profile.setId(id);
            profiles.add(profile);
        }
        return profiles;
    }

    @Named("mapProfilesToIds")
    static List<Long> mapProfilesToIds(List<ProfileU> profiles) {
        if (profiles == null) return null;
        return profiles.stream()
                .map(ProfileU::getId)
                .toList();
    }
}