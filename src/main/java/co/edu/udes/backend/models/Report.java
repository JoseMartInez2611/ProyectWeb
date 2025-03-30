package co.edu.udes.backend.models;


import co.edu.udes.backend.models.inheritance.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.File;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table (name = "report")
public class Report {

    private String reportType, content;
    private Date generationDate;
    private User requestingUser;
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
