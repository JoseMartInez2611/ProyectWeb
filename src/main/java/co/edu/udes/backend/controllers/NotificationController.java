package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.NotificationDTO;
import co.edu.udes.backend.mappers.NotificationMapper;
import co.edu.udes.backend.models.Notification;
import co.edu.udes.backend.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
public class NotificationController {

    @Autowired
    private final NotificationService notificationService;

    @Autowired
    private final NotificationMapper notificationMapper;

    @GetMapping
    public ResponseEntity<List<NotificationDTO>> getAll() {
        return ResponseEntity.ok(notificationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(notificationService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<NotificationDTO> create(@RequestBody NotificationDTO notificationDTO) {
        try {
            NotificationDTO createdNotification = notificationService.create(notificationDTO);
            return new ResponseEntity<>(createdNotification, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // O un mensaje de error más específico
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationDTO> update(@PathVariable Long id, @RequestBody NotificationDTO notificationDTO) {
        try {
            NotificationDTO updatedNotification = notificationService.update(id, notificationDTO);
            return ResponseEntity.ok(updatedNotification);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // O badRequest si la data es inválida
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            notificationService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // modulos

    // Endpoint para obtener las notificaciones recibidas por un usuario
    /*
     * Este endpoint permite obtener todas las notificaciones que han sido enviadas
     * a un usuario específico, identificado por su ID.
     *
     * @param userId El ID del usuario del cual se quieren obtener las notificaciones recibidas.
     * @return ResponseEntity con una lista de NotificationDTO si el usuario existe,
     * o un estado NOT_FOUND (404) si el usuario no se encuentra.
     */
    @GetMapping("/received/user/{userId}")
    public ResponseEntity<List<NotificationDTO>> getReceivedNotificationsByUser(@PathVariable Long userId) {
        try {
            List<NotificationDTO> receivedNotifications = notificationService.getReceivedByUserId(userId);
            return ResponseEntity.ok(receivedNotifications);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // O un mensaje de error más específico
        }
    }
}