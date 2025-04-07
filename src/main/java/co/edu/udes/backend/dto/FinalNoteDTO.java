package co.edu.udes.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FinalNoteDTO {

    private long id;
    private AcademicRecordDTO academicRecord;
    private GroupDTO group;
    private float note;
}
