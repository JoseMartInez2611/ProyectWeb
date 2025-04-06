package co.edu.udes.backend.mapper;

import co.edu.udes.backend.dto.CommunicationDTO;
import co.edu.udes.backend.dto.MessageDTO;
import co.edu.udes.backend.dto.UserDTO;
import co.edu.udes.backend.models.Message;
import co.edu.udes.backend.models.inheritance.User;
import org.springframework.stereotype.Component;

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
                .subject(message.getSubject())
                .sender(userMapper.toDTO(message.getSender()));

        // Map inherited properties from Communication
        CommunicationDTO communicationDTO = communicationMapper.toDTO(message);
        builder.id(communicationDTO.getId())
                .receiver(communicationDTO.getReceiver())
                .sentDate(communicationDTO.getSentDate())
                .content(communicationDTO.getContent())
                .read(communicationDTO.isRead());

        return builder.build();
    }

    public Message toEntity(MessageDTO messageDTO) {
        if (messageDTO == null) {
            return null;
        }
        Message.MessageBuilder builder = Message.builder()
                .subject(messageDTO.getSubject())
                .sender(userMapper.toEntity(messageDTO.getSender()));

        // Map inherited properties to Communication
        Message message = (Message) communicationMapper.toEntity(messageDTO);
        builder.id(message.getId()) // Ensure ID is set
                .receiver(message.getReceiver())
                .sentDate(message.getSentDate())
                .content(message.getContent())
                .read(message.isRead());

        return builder.build();
    }
}