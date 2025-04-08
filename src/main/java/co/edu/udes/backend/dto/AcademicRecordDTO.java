package co.edu.udes.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcademicRecordDTO {

    private long id;
    private float academicAverage;
    private StudentDTO student;
    private List<FinalNoteDTO> finalNotes;
}
