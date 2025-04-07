package co.edu.udes.backend.dto;


import co.edu.udes.backend.dto.inheritanceDTO.CommunicationDTO;
import co.edu.udes.backend.dto.inheritanceDTO.UserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class MessageDTO extends CommunicationDTO {
    private String subject;
    private UserDTO sender;
}