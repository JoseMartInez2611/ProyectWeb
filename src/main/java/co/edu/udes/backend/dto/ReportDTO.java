package co.edu.udes.backend.dto;

import lombok.Data;
import lombok.Builder;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ReportDTO {
    private Long id;
    private String reportType;
    private String content;
    private LocalDate generationDate;
    private UserDTO requestingUserId;
    private List<File> file;
}