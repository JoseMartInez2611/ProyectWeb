package co.edu.udes.backend.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "question")

public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question", nullable = false, columnDefinition = "VARCHAR(1024)")
    private String question;

    @Column(name = "question_type", nullable = false, columnDefinition = "VARCHAR(255)")
    private String questionType;

    @Column(name = "answer", nullable = false, columnDefinition = "VARCHAR(1024)")
    private String answer;

    @ManyToOne(targetEntity = Exam.class)
    @JoinColumn(name = "id_exam",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private Exam exam;
}
