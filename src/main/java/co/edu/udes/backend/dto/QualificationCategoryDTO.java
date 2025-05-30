package co.edu.udes.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QualificationCategoryDTO {
    private long id;
    private String name;
    private int percentage;
    private long groupId;
    private long academicSubperiodId;
}
