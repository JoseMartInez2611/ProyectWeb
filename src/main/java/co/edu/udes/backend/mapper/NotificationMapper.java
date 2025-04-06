package co.edu.udes.backend.mapper;

import co.edu.udes.backend.dto.CommunicationDTO;
import co.edu.udes.backend.dto.NotificationDTO;
import co.edu.udes.backend.models.Notification;
import org.springframework.stereotype.Component;

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
        NotificationDTO.NotificationDTOBuilder builder = NotificationDTO.builder()
                .type(notification.getType());

        CommunicationDTO communicationDTO = communicationMapper.toDTO(notification);
        builder.id(communicationDTO.getId())
                .receiver(communicationDTO.getReceiver())
                .sentDate(communicationDTO.getSentDate())
                .content(communicationDTO.getContent())
                .read(communicationDTO.isRead());

        return builder.build();
    }

    public Notification toEntity(NotificationDTO notificationDTO) {
        if (notificationDTO == null) {
            return null;
        }
        Notification.NotificationBuilder builder = Notification.builder()
                .type(notificationDTO.getType());

        Notification notification = (Notification) communicationMapper.toEntity(notificationDTO);
        builder.id(notification.getId())
                .receiver(notification.getReceiver())
                .sentDate(notification.getSentDate())
                .content(notification.getContent())
                .read(notification.isRead());

        return builder.build();
    }
}