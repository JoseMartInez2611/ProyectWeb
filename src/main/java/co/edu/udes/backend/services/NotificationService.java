package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.NotificationDTO;
import co.edu.udes.backend.mappers.NotificationMapper;
import co.edu.udes.backend.models.Notification;
import co.edu.udes.backend.models.inheritance.ProfileU;
import co.edu.udes.backend.repositories.NotificationRepository;
import co.edu.udes.backend.repositories.ProfileURepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final ProfileURepository profileURepository;
    @Autowired
    private NotificationMapper notificationMapper;

    public List<NotificationDTO> getAll() {
        List<Notification> notifications = notificationRepository.findAll();
        return notificationMapper.toDtoList(notifications);
    }

    public List<NotificationDTO> createMultiple(List<NotificationDTO> dtoList) {
        List<Notification> notifications = dtoList.stream()
                .map(dto -> {
                    Notification notification = notificationMapper.toEntity(dto);
                    // Asegurar que los destinatarios existan
                    if (dto.getReceiverIds() != null) {
                        List<ProfileU> receivers = profileURepository.findAllById(dto.getReceiverIds());
                        if (receivers.size() != dto.getReceiverIds().size()) {
                            throw new RuntimeException("One or more receivers not found");
                        }
                        notification.setReceivers(receivers); // Establecer los receivers directamente
                    }
                    return notification;
                })
                .collect(Collectors.toList());
        return notificationMapper.toDtoList(notificationRepository.saveAll(notifications));
    }

    public NotificationDTO getById(Long id) {
        return notificationMapper.toDto(notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id)));
    }

    public NotificationDTO create(NotificationDTO dto) {
        Notification notification = notificationMapper.toEntity(dto);
        // Asegurar que los destinatarios existan
        if (dto.getReceiverIds() != null) {
            List<ProfileU> receivers = profileURepository.findAllById(dto.getReceiverIds());
            if (receivers.size() != dto.getReceiverIds().size()) {
                throw new RuntimeException("One or more receivers not found");
            }
            notification.setReceivers(receivers); // Establecer los receivers directamente
        }
        return notificationMapper.toDto(notificationRepository.save(notification));
    }

    public NotificationDTO update(Long id, NotificationDTO dto) {
        Notification existingNotification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));
        Notification updatedNotification = notificationMapper.toEntity(dto);
        updatedNotification.setId(id); // Asegurar que el ID se mantenga

        // Asegurar que los destinatarios existan
        if (dto.getReceiverIds() != null) {
            List<ProfileU> receivers = profileURepository.findAllById(dto.getReceiverIds());
            if (receivers.size() != dto.getReceiverIds().size()) {
                throw new RuntimeException("One or more receivers not found");
            }
            updatedNotification.setReceivers(receivers); // Establecer los receivers directamente
        }

        return notificationMapper.toDto(notificationRepository.save(updatedNotification));
    }

    public void delete(Long id) {
        notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));
        notificationRepository.deleteById(id);
    }

    //modulos

    /**
     * Obtiene todas las notificaciones recibidas por un usuario específico.
     *
     * Este método busca un usuario por su ID y luego filtra su lista de comunicaciones
     * para retornar solo aquellas que son instancias de la entidad Notification.
     * Finalmente, mapea estas entidades Notification a una lista de NotificationDTO.
     *
     * @param userId El ID del usuario del cual se desean obtener las notificaciones recibidas.
     * @return Una lista de NotificationDTO que representan las notificaciones recibidas por el usuario.
     * @throws RuntimeException Si no se encuentra un usuario con el ID proporcionado.
     */
    public List<NotificationDTO> getReceivedByUserId(Long userId) {
        ProfileU user = profileURepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        List<Notification> receivedNotifications = user.getCommunication().stream()
                .filter(notification -> notification instanceof Notification)
                .map(notification -> (Notification) notification)
                .collect(Collectors.toList());
        return notificationMapper.toDtoList(receivedNotifications);
    }

}
