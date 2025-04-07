package co.edu.udes.backend.mapper;

import co.edu.udes.backend.dto.CommunicationDTO;
import co.edu.udes.backend.dto.MessageDTO;
import co.edu.udes.backend.dto.UserDTO;
import co.edu.udes.backend.models.Message;
import co.edu.udes.backend.models.inheritance.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageMapper {

    private final UserMapper userMapper;
    private final CommunicationMapper communicationMapper;

    public MessageMapper(UserMapper userMapper, CommunicationMapper communicationMapper) {
        this.userMapper = userMapper;
        this.communicationMapper = communicationMapper;
    }

    public MessageDTO toDTO(Message message) {
        if (message == null) {
            return null;
        }
        MessageDTO.MessageDTOBuilder builder = MessageDTO.builder()
                .id(message.getId())
                .receiver(userMapper.toDTOList(message.getReceiver()))
                .sentDate(message.getSentDate())
                .content(message.getContent())
                .read(message.isRead())
                .subject(message.getSubject())
                .sender(userMapper.toDTO(message.getSender()));

        return builder.build();
    }

    public Message toEntity(MessageDTO messageDTO) {
        if (messageDTO == null) {
            return null;
        }
        Message.MessageBuilder builder = Message.builder()
                .id(messageDTO.getId())
                .receiver(userMapper.toEntityList(messageDTO.getReceiver()))
                .sentDate(messageDTO.getSentDate())
                .content(messageDTO.getContent())
                .read(messageDTO.isRead())
                .subject(messageDTO.getSubject())
                .sender(userMapper.toEntity(messageDTO.getSender()));

        return builder.build();
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