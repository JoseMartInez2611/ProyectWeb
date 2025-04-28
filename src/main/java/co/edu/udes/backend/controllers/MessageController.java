package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.MessageDTO;
import co.edu.udes.backend.mappers.MessageMapper;
import co.edu.udes.backend.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
public class MessageController {

    @Autowired
    private final MessageService messageService;

    @Autowired
    private final MessageMapper messageMapper;

    @GetMapping
    public ResponseEntity<List<MessageDTO>> getAll() {
        return ResponseEntity.ok(messageService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(messageService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<MessageDTO> create(@RequestBody MessageDTO messageDTO) {
        try {
            MessageDTO createdMessage = messageService.create(messageDTO);
            return new ResponseEntity<>(createdMessage, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // O un mensaje de error más específico
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageDTO> update(@PathVariable Long id, @RequestBody MessageDTO messageDTO) {
        try {
            MessageDTO updatedMessage = messageService.update(id, messageDTO);
            return ResponseEntity.ok(updatedMessage);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // O badRequest si la data es inválida
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            messageService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // modulos

    // Endpoint para obtener los mensajes recibidos por un usuario
    /*
     * Este endpoint permite obtener todos los mensajes que han sido enviados
     * a un usuario específico, identificado por su ID.
     *
     * @param userId El ID del usuario del cual se quieren obtener los mensajes recibidos.
     * @return ResponseEntity con una lista de MessageDTO si el usuario existe,
     * o un estado NOT_FOUND (404) si el usuario no se encuentra.
     */
    @GetMapping("/received/user/{userId}")
    public ResponseEntity<List<MessageDTO>> getReceivedMessagesByUser(@PathVariable Long userId) {
        try {
            List<MessageDTO> receivedMessages = messageService.getReceivedByUserId(userId);
            return ResponseEntity.ok(receivedMessages);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // O un mensaje de error más específico
        }
    }

    // Endpoint para obtener los mensajes enviados por un usuario
    /*
     * Este endpoint permite obtener todos los mensajes que han sido enviados
     * por un usuario específico, identificado por su ID.
     *
     * @param userId El ID del usuario del cual se quieren obtener los mensajes enviados.
     * @return ResponseEntity con una lista de MessageDTO si el usuario existe,
     * o un estado NOT_FOUND (404) si el usuario no se encuentra.
     */
    @GetMapping("/sent/user/{userId}")
    public ResponseEntity<List<MessageDTO>> getSentMessagesByUser(@PathVariable Long userId) {
        try {
            List<MessageDTO> sentMessages = messageService.getSentByUserId(userId);
            return ResponseEntity.ok(sentMessages);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // O un mensaje de error más específico
        }
    }

    // Endpoint para obtener la conversación entre dos usuarios
    @GetMapping("sender/{id}/receiver/{receiverId}")
    public ResponseEntity<?> getConversation(@PathVariable Long id, @PathVariable Long receiverId) {
        try{
            return ResponseEntity.ok(messageService.getConversation(id, receiverId));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}