package co.edu.udes.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Entity
@Table (name = "borrow")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class Borrow {
    private Date borrowDate, returnDate;
    private String duration;
    private AcademicResource resource;
    private Employee lender;

}
