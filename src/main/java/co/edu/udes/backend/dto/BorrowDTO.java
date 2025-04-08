package co.edu.udes.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowDTO {
    private Long id;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private String duration;
    private AcademicResourceDTO resource;
    private EmployeeDTO lender;
}