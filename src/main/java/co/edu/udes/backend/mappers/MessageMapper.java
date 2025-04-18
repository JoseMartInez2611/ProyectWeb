package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.MessageDTO;
import co.edu.udes.backend.models.Message;
import co.edu.udes.backend.models.inheritance.ProfileU;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {ProfileUMapper.class}
)

public interface MessageMapper {
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    @Mapping(source = "senderId", target = "sender.id")
    @Mapping(source = "receiverIds", target = "receivers", qualifiedByName = "mapIdsToProfiles")
    Message toEntity(MessageDTO message);
    List<Message> toEntityList(List<MessageDTO> messages);

    @Mapping(source = "sender.id", target = "senderId")
    @Mapping(source = "receivers", target = "receiverIds", qualifiedByName = "mapProfilesToIds")
    MessageDTO toDto(Message message);
    List<MessageDTO> toDtoList(List<Message> messages);

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