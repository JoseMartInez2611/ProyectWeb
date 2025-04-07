package co.edu.udes.backend.dto;

import co.edu.udes.backend.models.Employee;
import lombok.Data;
import lombok.Builder;

import java.time.LocalDate;

@Data
@Builder
public class BorrowDTO {
    private Long id;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private String duration;
    private AcademicResourceDTO resource;
    private EmployeeDTO lender;
}