package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.MessageDTO;
import co.edu.udes.backend.mappers.MessageMapper;
import co.edu.udes.backend.models.Message;
import co.edu.udes.backend.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private MessageMapper messageMapper;

    public List<MessageDTO> getAll() {
        List<Message> messages = messageRepository.findAll();
        return messageMapper.toDtoList(messages);
    }

    public MessageDTO getById(Long id) {
        return messageMapper.toDto(messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found with id: " + id)));
    }

    public MessageDTO create(Message message) {
        return messageMapper.toDto(messageRepository.save(message));

    }

    public MessageDTO update(Long id, Message message) {
        messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found with id: " + id));
        message.setId(id);
        return messageMapper.toDto(messageRepository.save(message));
    }

    public void delete(Long id) {
        messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found with id: " + id));
        messageRepository.deleteById(id);
    }
}
