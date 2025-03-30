package co.edu.udes.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table (name = "borrow")
public class Borrow {
    private Date borrowDate, returnDate;
    private String duration;
    private AcademicResource resource;
    private Employee lender;

}
