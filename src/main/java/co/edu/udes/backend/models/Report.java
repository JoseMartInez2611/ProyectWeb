package co.edu.udes.backend.models;


import co.edu.udes.backend.models.inheritance.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.File;
import java.util.Date;
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

public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "report_type")
    private String reportType;

    @Column(name = "content")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "generation_date")
    private Date generationDate;

    @ManyToOne
    @JoinColumn(name = "requesting_user_id")
    private User requestingUser;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "report_id")
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
