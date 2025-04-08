package co.edu.udes.backend.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionDTO {

    long id;
    String question;
    String questionType;
    String answer;
    ExamDTO exam;
}
