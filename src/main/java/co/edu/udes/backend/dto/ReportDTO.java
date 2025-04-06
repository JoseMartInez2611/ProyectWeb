package co.edu.udes.backend.dto;

import lombok.Data;
import lombok.Builder;

import java.io.File;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class ReportDTO {
    private Long id;
    private String reportType;
    private String content;
    private Date generationDate;
    private UserDTO requestingUserId;
    private List<File> file;
}