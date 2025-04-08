package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.NotificationDTO;
import co.edu.udes.backend.models.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NotificationMapper {

    private UserMapper userMapper;

    public NotificationDTO toDTO(Notification notification) {
        if (notification == null) {
            return null;
        }

        return NotificationDTO.builder()
                .id(notification.getId())
                .receiver(userMapper.toDTOList(notification.getReceiver()))
                .sentDate(notification.getSentDate())
                .content(notification.getContent())
                .read(notification.isRead())
                .type(notification.getType())
                .build();
    }

    public Notification toEntity(NotificationDTO notificationDTO) {
        if (notificationDTO == null) {
            return null;
        }
        return Notification.builder()
                .id(notificationDTO.getId())
                .receiver(userMapper.toEntityList(notificationDTO.getReceiver()))
                .sentDate(notificationDTO.getSentDate())
                .content(notificationDTO.getContent())
                .read(notificationDTO.isRead())
                .type(notificationDTO.getType())
                .build();
    }

    public List<NotificationDTO> toDTOList(List<Notification> notifications) {
        if (notifications == null) {
            return null;
        }
        return notifications.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Notification> toEntityList(List<NotificationDTO> notificationDTOs) {
        if (notificationDTOs == null) {
            return null;
        }
        return notificationDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}