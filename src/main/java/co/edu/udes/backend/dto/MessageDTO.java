package co.edu.udes.backend.dto;


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