package co.edu.udes.backend.dto;

import lombok.Data;
import lombok.Builder;
import co.edu.udes.backend.dto.UserDTO;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class CommunicationDTO {
    private Long id;
    private List<UserDTO> receiver;
    private Date sentDate;
    private String content;
    private boolean read;
}