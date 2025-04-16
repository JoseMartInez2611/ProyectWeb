package co.edu.udes.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinalNoteDTO {

    private long id;
    private long academicRecordId;
    private long groupId;
    private float note;
}
