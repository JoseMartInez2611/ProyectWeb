package co.edu.udes.backend.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "question")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@Builder

public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "question", nullable = false, columnDefinition = "VARCHAR(1024)")
    String question;

    @Column(name = "question_type", nullable = false, columnDefinition = "VARCHAR(255)")
    String questionType;

    @Column(name = "answer", nullable = false, columnDefinition = "VARCHAR(1024)")
    String answer;

    @ManyToOne(targetEntity = Exam.class)
    @JoinColumn(name = "id_exam",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    Exam exam;
}
