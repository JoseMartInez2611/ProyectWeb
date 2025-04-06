package co.edu.udes.backend.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Builder;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class NotificationDTO extends CommunicationDTO {
    private String type;
}