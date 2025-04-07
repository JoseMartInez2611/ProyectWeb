package co.edu.udes.backend.mapper;

import co.edu.udes.backend.dto.CommunicationDTO;
import co.edu.udes.backend.dto.UserDTO;
import co.edu.udes.backend.models.inheritance.Communication;
import co.edu.udes.backend.models.inheritance.User;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommunicationMapper {

    private final UserMapper userMapper;

    public CommunicationMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public CommunicationDTO toDTO(Communication communication) {
        if (communication == null) {
            return null;
        }
        return CommunicationDTO.builder()
                .id(communication.getId())
                .receiver(mapUserListToDTOList(communication.getReceiver()))
                .sentDate(communication.getSentDate())
                .content(communication.getContent())
                .read(communication.isRead())
                .build();
    }

    public Communication toEntity(CommunicationDTO communicationDTO) {
        if (communicationDTO == null) {
            return null;
        }
        Communication communication = Communication.builder()
                .id(communicationDTO.getId())
                .receiver(mapDTOListToUserList(communicationDTO.getReceiver()))
                .sentDate(communicationDTO.getSentDate())
                .content(communicationDTO.getContent())
                .read(communicationDTO.isRead())
                .build();
        return communication;
    }

    private List<UserDTO> mapUserListToDTOList(List<User> users) {
        if (users == null) {
            return null;
        }
        return users.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    private List<User> mapDTOListToUserList(List<UserDTO> userDTOs) {
        if (userDTOs == null) {
            return null;
        }
        return userDTOs.stream()
                .map(userMapper::toEntity)
                .collect(Collectors.toList());
    }

    public List<CommunicationDTO> toDTOList(List<Communication> communications) {
        if (communications == null) {
            return null;
        }
        return communications.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Communication> toEntityList(List<CommunicationDTO> communicationDTOs) {
        if (communicationDTOs == null) {
            return null;
        }
        return communicationDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}