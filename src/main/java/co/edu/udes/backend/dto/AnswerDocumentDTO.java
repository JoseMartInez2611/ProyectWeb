package co.edu.udes.backend.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDocumentDTO {

    long id;
    String fileName;
    String filePath;
    ActivityDTO activity;
}
