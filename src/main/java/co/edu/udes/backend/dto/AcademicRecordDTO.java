package co.edu.udes.backend.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AcademicRecordDTO {

    private long id;
    private float academicAverage;
    private StudentDTO student;
    private List<FinalNoteDTO> finalNotes;
}
