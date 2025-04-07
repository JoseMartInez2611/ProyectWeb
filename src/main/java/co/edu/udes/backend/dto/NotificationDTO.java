package co.edu.udes.backend.dto;

import co.edu.udes.backend.dto.inheritanceDTO.CommunicationDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class NotificationDTO extends CommunicationDTO {
    private String type;
}