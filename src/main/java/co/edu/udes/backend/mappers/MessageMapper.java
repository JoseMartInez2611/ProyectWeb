package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.MessageDTO;
import co.edu.udes.backend.models.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MessageMapper {

    private UserMapper userMapper;


    public MessageDTO toDTO(Message message) {
        if (message == null) {
            return null;
        }
        return MessageDTO.builder()
                .id(message.getId())
                .receiver(userMapper.toDTOList(message.getReceiver()))
                .sentDate(message.getSentDate())
                .content(message.getContent())
                .read(message.isRead())
                .subject(message.getSubject())
                .sender(userMapper.toDTO(message.getSender()))
                .build();
    }

    public Message toEntity(MessageDTO messageDTO) {
        if (messageDTO == null) {
            return null;
        }
        return Message.builder()
                .id(messageDTO.getId())
                .receiver(userMapper.toEntityList(messageDTO.getReceiver()))
                .sentDate(messageDTO.getSentDate())
                .content(messageDTO.getContent())
                .read(messageDTO.isRead())
                .subject(messageDTO.getSubject())
                .sender(userMapper.toEntity(messageDTO.getSender()))
                .build();
    }

    public List<MessageDTO> toDTOList(List<Message> messages) {
        if (messages == null) {
            return null;
        }
        return messages.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Message> toEntityList(List<MessageDTO> messageDTOs) {
        if (messageDTOs == null) {
            return null;
        }
        return messageDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}