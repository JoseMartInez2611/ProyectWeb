package co.edu.udes.backend.models;


import co.edu.udes.backend.models.inheritance.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
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
