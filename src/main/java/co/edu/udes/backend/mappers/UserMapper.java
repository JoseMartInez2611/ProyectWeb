package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.inheritanceDTO.UserDTO;
import co.edu.udes.backend.models.inheritance.ProfileU;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private BorrowMapper borrowMapper;
    private MessageMapper messageMapper;
    private ReportMapper reportMapper;
    private CommunicationMapper communicationMapper;

    public UserDTO toDTO(ProfileU profileU) {
        return UserDTO.builder()
                .id(profileU.getId())
                .firstName(profileU.getFirstName())
                .lastName(profileU.getLastName())
                .phone(profileU.getPhone())
                .email(profileU.getEmail())
                .userName(profileU.getUserName())
                .password(profileU.getPassword())

                .message(messageMapper.toDTOList(profileU.getMessage()))
                .report(reportMapper.toDTOList(profileU.getReport()))
                .borrow(borrowMapper.toDTOList(profileU.getBorrow()))
                .communication(communicationMapper.toDTOList(profileU.getCommunication()))
                .build();

    }

    public ProfileU toEntity(UserDTO userDTO) {
        return ProfileU.builder()
                .id(userDTO.getId())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .phone(userDTO.getPhone())
                .email(userDTO.getEmail())
                .userName(userDTO.getUserName())
                .password(userDTO.getPassword())

                .message(messageMapper.toEntityList(userDTO.getMessage()))
                .report(reportMapper.toEntityList(userDTO.getReport()))
                .borrow(borrowMapper.toEntityList(userDTO.getBorrow()))
                .communication(communicationMapper.toEntityList(userDTO.getCommunication()))
                .build();

    }

    public List<ProfileU> toEntityList(List<UserDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }

        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<UserDTO> toDTOList(List<ProfileU> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::toDTO)
                .toList();
    }
}
