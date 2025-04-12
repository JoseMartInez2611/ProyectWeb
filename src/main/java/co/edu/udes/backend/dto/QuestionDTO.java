package co.edu.udes.backend.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {

    long id;
    String question;
    String questionType;
    String answer;
    ExamDTO exam;
}
