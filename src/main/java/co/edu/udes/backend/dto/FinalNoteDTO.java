package co.edu.udes.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinalNoteDTO {

    private long id;
    private AcademicRecordDTO academicRecord;
    private GroupDTO group;
    private float note;
}
