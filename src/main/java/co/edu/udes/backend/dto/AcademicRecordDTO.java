package co.edu.udes.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AcademicRecordDTO {

    private long id;
    private float academicAverage;
    private long studentId;
    private List<FinalNoteDTO> finalNotes;
}
