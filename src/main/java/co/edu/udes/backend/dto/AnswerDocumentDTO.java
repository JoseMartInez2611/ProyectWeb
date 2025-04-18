package co.edu.udes.backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AnswerDocumentDTO {

    private long id;
    private String fileName;
    private String filePath;
    private long activityId;
}
