package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.NotificationDTO;
import co.edu.udes.backend.mappers.NotificationMapper;
import co.edu.udes.backend.models.Notification;
import co.edu.udes.backend.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    @Autowired
    private NotificationMapper notificationMapper;

    public List<NotificationDTO> getAll() {
        List<Notification> notifications = notificationRepository.findAll();
        return notificationMapper.toDtoList(notifications);
    }

    public NotificationDTO getById(Long id) {
        return notificationMapper.toDto(notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id)));
    }

    public NotificationDTO create(Notification notification) {
        return notificationMapper.toDto(notificationRepository.save(notification));

    }

    public NotificationDTO update(Long id, Notification notification) {
        notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));
        notification.setId(id);
        return notificationMapper.toDto(notificationRepository.save(notification));
    }

    public void delete(Long id) {
        notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));
        notificationRepository.deleteById(id);
    }
}
