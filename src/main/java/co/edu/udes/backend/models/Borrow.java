package co.edu.udes.backend.models;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Borrow {
    private Date borrowDate, returnDate;
    private String duration;
    private AcademicResource resource;
    private Employee lender;

}
