package co.edu.udes.backend.dto.inheritanceDTO;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Data
@SuperBuilder
public class CommunicationDTO {
    private Long id;
    private List<UserDTO> receiver;
    private LocalDate sentDate;
    private String content;
    private boolean read;
}