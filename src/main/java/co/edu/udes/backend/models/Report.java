package co.edu.udes.backend.models;


import co.edu.udes.backend.models.inheritance.ProfileU;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table (name = "report")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder

public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report_type", nullable = false, columnDefinition = "varchar(255)")
    private String reportType;

    @Column(name = "content", nullable = false, columnDefinition = "varchar(255)")
    private String content;

    @Column(name = "generation_date", nullable = false, columnDefinition = "DATE")
    private LocalDate generationDate;

    @ManyToOne
    @JoinColumn(name = "requesting_user_id", nullable = false, columnDefinition = "BIGINT")
    private ProfileU requestingProfileU;

}
