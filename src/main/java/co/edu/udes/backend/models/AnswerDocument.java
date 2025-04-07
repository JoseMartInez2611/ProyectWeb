package co.edu.udes.backend.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "answer_documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String fileName;

    @Column(name = "file_path", columnDefinition = "VARCHAR(500)", nullable = false)
    private String filePath;

    @ManyToOne(targetEntity = Activity.class)
    private Activity activity;
}
