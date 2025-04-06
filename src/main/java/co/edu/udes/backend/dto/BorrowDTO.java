package co.edu.udes.backend.dto;

import co.edu.udes.backend.models.Employee;
import lombok.Data;
import lombok.Builder;

import java.util.Date;

@Data
@Builder
public class BorrowDTO {
    private Long id;
    private Date borrowDate;
    private Date returnDate;
    private String duration;
    private AcademicResourceDTO resource;
    private EmployeeDTO lender;
}