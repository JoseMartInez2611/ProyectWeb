package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.MessageDTO;
import co.edu.udes.backend.models.Message;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring"
)

public interface MessageMapper {
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    Message toEntity(MessageDTO message);
    List<Message> toEntityList(List<MessageDTO> messages);

    MessageDTO toDto(Message message);
    List<MessageDTO> toDtoList(List<Message> messages);
}