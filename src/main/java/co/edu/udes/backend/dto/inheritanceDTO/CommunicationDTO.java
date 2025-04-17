package co.edu.udes.backend.dto.inheritanceDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CommunicationDTO {
    private long id;
    private List<Long> receiverIds;
    private LocalDate sentDate;
    private String content;
    private boolean read;
}