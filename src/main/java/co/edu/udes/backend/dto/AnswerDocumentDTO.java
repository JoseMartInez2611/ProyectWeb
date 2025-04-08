package co.edu.udes.backend.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerDocumentDTO {

    long id;
    String fileName;
    String filePath;
    ActivityDTO activity;
}
