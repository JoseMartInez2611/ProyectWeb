package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.NotificationDTO;
import co.edu.udes.backend.models.Notification;
import co.edu.udes.backend.models.inheritance.ProfileU;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    @Mapping(source = "receiverIds", target = "receivers", qualifiedByName = "mapIdsToProfiles")
    Notification toEntity(NotificationDTO notification);
    List<Notification> toEntityList(List<NotificationDTO>  notifications);

    @Mapping(source = "receivers", target = "receiverIds", qualifiedByName = "mapProfilesToIds")
    NotificationDTO toDto(Notification notification);
    List<NotificationDTO> toDtoList(List<Notification> notifications);

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