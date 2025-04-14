package co.edu.udes.backend.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "answer_documents")

public class AnswerDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String fileName;

    @Column(name = "file_path", columnDefinition = "VARCHAR(500)", nullable = false)
    private String filePath;

    @ManyToOne(targetEntity = Activity.class)
    @JoinColumn(name = "activity_id", nullable = false, columnDefinition = "BIGINT")
    private Activity activity;
}
