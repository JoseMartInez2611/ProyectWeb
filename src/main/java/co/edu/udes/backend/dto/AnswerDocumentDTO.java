package co.edu.udes.backend.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerDocumentDTO {

    long id;
    String fileName;
    String filePath;
    ActivityDTO activity;
}
