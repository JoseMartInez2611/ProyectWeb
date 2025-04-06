package co.edu.udes.backend.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Builder;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class MessageDTO extends CommunicationDTO {
    private String subject;
    private UserDTO sender;
}