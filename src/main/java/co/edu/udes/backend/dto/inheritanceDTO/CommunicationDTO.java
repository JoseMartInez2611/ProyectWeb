package co.edu.udes.backend.dto.inheritanceDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CommunicationDTO {
    private Long id;
    private List<ProfileUDTO> receiver;
    private LocalDate sentDate;
    private String content;
    private boolean read;
}