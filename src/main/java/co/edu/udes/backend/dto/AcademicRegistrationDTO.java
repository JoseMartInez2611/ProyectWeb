package co.edu.udes.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AcademicRegistrationDTO {

    private Long id;
    private long studentId;
    public long groupId;
    private long academicPeriodId;
}
