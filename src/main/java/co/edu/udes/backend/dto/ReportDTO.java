package co.edu.udes.backend.dto;

import co.edu.udes.backend.dto.inheritanceDTO.ProfileUDTO;
import lombok.Data;
import lombok.Builder;

import java.time.LocalDate;

@Data
@Builder
public class ReportDTO {
    private Long id;
    private String reportType;
    private String content;
    private LocalDate generationDate;
    private ProfileUDTO requestingUserId;
}