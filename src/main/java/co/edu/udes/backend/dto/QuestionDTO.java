package co.edu.udes.backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class QuestionDTO {

    private long id;
    private String question;
    private String questionType;
    private String answer;
    private long examId;
}
