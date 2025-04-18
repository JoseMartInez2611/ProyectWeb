package co.edu.udes.backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class QualificationDTO {

    private long id;
    private long studentId;
    private float qualification;
    private long evaluationId;

}
