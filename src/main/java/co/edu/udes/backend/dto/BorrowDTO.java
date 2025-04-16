package co.edu.udes.backend.dto;

import co.edu.udes.backend.models.Employee;
import co.edu.udes.backend.models.inheritance.ProfileU;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BorrowDTO {
    private Long id;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private String duration;
    private long resourceId;
    private long petitionerId;
}