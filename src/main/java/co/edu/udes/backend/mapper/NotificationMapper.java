package co.edu.udes.backend.mapper;

import co.edu.udes.backend.dto.CommunicationDTO;
import co.edu.udes.backend.dto.NotificationDTO;
import co.edu.udes.backend.models.Notification;
import co.edu.udes.backend.models.inheritance.Communication;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NotificationMapper {

    private final CommunicationMapper communicationMapper;

    public NotificationMapper(CommunicationMapper communicationMapper) {
        this.communicationMapper = communicationMapper;
    }

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
        Notification notification = new Notification();
        notification.setId(notificationDTO.getId());
        notification.setReceiver(userMapper.toEntityList(notificationDTO.getReceiver()));
        notification.setSentDate(notificationDTO.getSentDate());
        notification.setContent(notificationDTO.getContent());
        notification.setRead(notificationDTO.isRead());
        notification.setType(notificationDTO.getType());
        return notification;
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