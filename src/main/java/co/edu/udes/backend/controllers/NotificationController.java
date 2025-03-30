package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Notification;
import co.edu.udes.backend.repositories.NotificationRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping ("/api/v1/notifications") // Cambiado a /api/v1/notifications para mejor semántica
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    // get all notifications
    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // create notification rest api
    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationRepository.save(notification);
    }

    // get notification by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not exist with id :" + id));
        return ResponseEntity.ok(notification);
    }

    // update notification rest api
    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateNotification(@PathVariable Long id, @RequestBody Notification notificationDetails) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not exist with id :" + id));

        notification.setType(notificationDetails.getType());
        // Asumiendo que quieres actualizar los campos de Communication también
        notification.setReceiver(notificationDetails.getReceiver());
        notification.setSentDate(notificationDetails.getSentDate());
        notification.setContent(notificationDetails.getContent());
        notification.setRead(notificationDetails.isRead());

        Notification updatedNotification = notificationRepository.save(notification);
        return ResponseEntity.ok(updatedNotification);
    }

    // delete notification rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteNotification(@PathVariable Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not exist with id :" + id));

        notificationRepository.delete(notification);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}