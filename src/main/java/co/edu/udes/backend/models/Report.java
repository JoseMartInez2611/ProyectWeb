package co.edu.udes.backend.models;


import co.edu.udes.backend.models.inheritance.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table (name = "report")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder

public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "report_type", nullable = false, columnDefinition = "varchar(255)")
    private String reportType;

    @Column(name = "content", nullable = false, columnDefinition = "varchar(255)")
    private String content;

    @Column(name = "generation_date", nullable = false, columnDefinition = "DATE")
    private LocalDate generationDate;

    @ManyToOne
    @JoinColumn(name = "requesting_user_id", nullable = false, columnDefinition = "varchar(255)")
    private User requestingUser;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "report_id", nullable = false, columnDefinition = "varchar(255)")
    private List<File> files;

    public void generateReport() {
    }
    public void viewReport() {
    }
    public void saveReport() {
    }
    public void editReport() {
    }
    public void deleteReport() {
    }
}
