package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.NotificationDTO;
import co.edu.udes.backend.models.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    Notification toEntity(NotificationDTO notification);
    List<Notification> toEntityList(List<NotificationDTO>  notifications);

    NotificationDTO toDto(Notification notification);
    List<NotificationDTO> toDtoList(List<Notification> notifications);
}