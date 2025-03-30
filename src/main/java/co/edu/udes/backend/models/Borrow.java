package co.edu.udes.backend.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Borrow {
    private Date borrowDate, returnDate;
    private String duration;
    private AcademicResource resource;
    private Employee lender;

}
