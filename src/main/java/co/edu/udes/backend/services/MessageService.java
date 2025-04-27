package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.MessageDTO;
import co.edu.udes.backend.mappers.MessageMapper;
import co.edu.udes.backend.models.Message;
import co.edu.udes.backend.models.inheritance.ProfileU;
import co.edu.udes.backend.repositories.MessageRepository;
import co.edu.udes.backend.repositories.ProfileURepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ProfileURepository profileURepository;

    @Autowired
    private MessageMapper messageMapper;

    public List<MessageDTO> getAll() {
        List<Message> messages = messageRepository.findAll();
        return messageMapper.toDtoList(messages);
    }

    public List<MessageDTO> createMultiple(List<MessageDTO> dtoList) {
        List<Message> messages = dtoList.stream()
                .map(dto -> {
                    Message message = messageMapper.toEntity(dto);
                    // Asegurar que el remitente exista
                    ProfileU sender = profileURepository.findById(dto.getSenderId())
                            .orElseThrow(() -> new RuntimeException("Sender not found with id: " + dto.getSenderId()));
                    message.setSender(sender);
                    // Asegurar que los destinatarios existan (el mapper ya los setea por ID)
                    if (dto.getReceiverIds() != null) {
                        List<ProfileU> receivers = profileURepository.findAllById(dto.getReceiverIds());
                        if (receivers.size() != dto.getReceiverIds().size()) {
                            throw new RuntimeException("One or more receivers not found");
                        }
                        // Los receivers se setean a través del mapper en Communication
                    }
                    return message;
                })
                .collect(Collectors.toList());
        return messageMapper.toDtoList(messageRepository.saveAll(messages));
    }

    public MessageDTO getById(Long id) {
        return messageMapper.toDto(messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found with id: " + id)));
    }

    public MessageDTO create(MessageDTO dto) {
        Message message = messageMapper.toEntity(dto);
        // Asegurar que el remitente exista
        ProfileU sender = profileURepository.findById(dto.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender not found with id: " + dto.getSenderId()));
        message.setSender(sender);
        // Asegurar que los destinatarios existan
        if (dto.getReceiverIds() != null) {
            List<ProfileU> receivers = profileURepository.findAllById(dto.getReceiverIds());
            if (receivers.size() != dto.getReceiverIds().size()) {
                throw new RuntimeException("One or more receivers not found");
            }
            message.setReceivers(receivers); // Establecer los receivers directamente
        }
        return messageMapper.toDto(messageRepository.save(message));
    }

    public MessageDTO update(Long id, MessageDTO dto) {
        Message existingMessage = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found with id: " + id));
        Message updatedMessage = messageMapper.toEntity(dto);
        updatedMessage.setId(id); // Asegurar que el ID se mantenga

        // Asegurar que el remitente exista
        ProfileU sender = profileURepository.findById(dto.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender not found with id: " + dto.getSenderId()));
        updatedMessage.setSender(sender);

        // Asegurar que los destinatarios existan
        if (dto.getReceiverIds() != null) {
            List<ProfileU> receivers = profileURepository.findAllById(dto.getReceiverIds());
            if (receivers.size() != dto.getReceiverIds().size()) {
                throw new RuntimeException("One or more receivers not found");
            }
            updatedMessage.setReceivers(receivers); // Establecer los receivers directamente
        }

        return messageMapper.toDto(messageRepository.save(updatedMessage));
    }

    public void delete(Long id) {
        messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found with id: " + id));
        messageRepository.deleteById(id);
    }

    // modulos

    /**
     * Obtiene todos los mensajes que han sido recibidos por un usuario específico.
     *
     * Este método busca un usuario por su ID y luego filtra su lista de comunicaciones
     * para retornar solo aquellas que son instancias de la entidad Message.
     * Finalmente, mapea estas entidades Message a una lista de MessageDTO.
     *
     * @param userId El ID del usuario del cual se desean obtener los mensajes recibidos.
     * @return Una lista de MessageDTO que representan los mensajes recibidos por el usuario.
     * @throws RuntimeException Si no se encuentra un usuario con el ID proporcionado.
     */
    public List<MessageDTO> getReceivedByUserId(Long userId) {
        ProfileU user = profileURepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        List<Message> receivedMessages = user.getCommunication().stream()
                .filter(message -> message instanceof Message)
                .map(message -> (Message) message)
                .collect(Collectors.toList());
        return messageMapper.toDtoList(receivedMessages);
    }

    /**
     * Obtiene todos los mensajes que han sido enviados por un usuario específico.
     *
     * Este método busca un usuario por su ID y luego retorna la lista de mensajes
     * que tienen a este usuario como remitente, mapeándolos a una lista de MessageDTO.
     *
     * @param userId El ID del usuario del cual se desean obtener los mensajes enviados.
     * @return Una lista de MessageDTO que representan los mensajes enviados por el usuario.
     * @throws RuntimeException Si no se encuentra un usuario con el ID proporcionado.
     */
    public List<MessageDTO> getSentByUserId(Long userId) {
        ProfileU user = profileURepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return messageMapper.toDtoList(user.getMessage());
    }

    /**
     * Obtiene la conversación entre dos usuarios específicos.
     *
     * Este método busca los mensajes enviados y recibidos entre dos usuarios
     * identificados por sus IDs. Los mensajes se combinan y ordenan por fecha de envío.
     *
     * @param senderID   El ID del usuario que envió los mensajes.
     * @param receiverID El ID del usuario que recibió los mensajes.
     * @return Una lista de MessageDTO que representan la conversación entre los dos usuarios.
     * @throws ResourceNotFoundException Si no se encuentra uno de los usuarios con el ID proporcionado.
     */
    public List<MessageDTO> getConversation(Long senderID, Long receiverID) {
        ProfileU sender = profileURepository.findById(senderID)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + senderID));
        ProfileU receiver = profileURepository.findById(receiverID)
                .orElseThrow(() -> new ResourceNotFoundException("Receiver not found with id: " + receiverID));

        List<Message> sentMessages = messageRepository.findBySenderIdAndReceiverId(senderID, receiverID);
        List<Message> receivedMessages = messageRepository.findBySenderIdAndReceiverId(receiverID, senderID);
        sentMessages.addAll(receivedMessages);

        sentMessages.sort(Comparator.comparing(Message::getSentDate));

        return messageMapper.toDtoList(sentMessages);
    }
}
