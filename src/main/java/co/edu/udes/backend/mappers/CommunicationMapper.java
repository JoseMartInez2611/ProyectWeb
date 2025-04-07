package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.inheritanceDTO.CommunicationDTO;
import co.edu.udes.backend.models.inheritance.Communication;
import java.util.List;
import java.util.stream.Collectors;

public class CommunicationMapper {

    private final UserMapper userMapper = new UserMapper();

    public CommunicationDTO toDTO(Communication communication) {
        if (communication == null) {
            return null;
        }
        return CommunicationDTO.builder()
                .id(communication.getId())
                .receiver(userMapper.toDTOList(communication.getReceiver()))
                .sentDate(communication.getSentDate())
                .content(communication.getContent())
                .read(communication.isRead())
                .build();
    }

    public Communication toEntity(CommunicationDTO communicationDTO) {
        if (communicationDTO == null) {
            return null;
        }
        return Communication.builder()
                .id(communicationDTO.getId())
                .receiver(userMapper.toEntityList(communicationDTO.getReceiver()))
                .sentDate(communicationDTO.getSentDate())
                .content(communicationDTO.getContent())
                .read(communicationDTO.isRead())
                .build();
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