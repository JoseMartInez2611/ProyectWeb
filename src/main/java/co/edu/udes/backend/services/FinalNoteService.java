package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.FinalNoteDTO;
import co.edu.udes.backend.mappers.FinalNoteMapper;
import co.edu.udes.backend.models.AcademicRecord;
import co.edu.udes.backend.models.FinalNote;
import co.edu.udes.backend.models.Group;
import co.edu.udes.backend.models.Student;
import co.edu.udes.backend.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FinalNoteService {

    private final FinalNoteRepository finalNoteRepository;
    private final LessonRepository lessonRepository;
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final AcademicRecordRepository academicRecordRepository;
    @Autowired
    private FinalNoteMapper finalNoteMapper;

    public List<FinalNoteDTO> getAll() {
        List<FinalNote> finalNotes = finalNoteRepository.findAll();
        return finalNoteMapper.toDtoList(finalNotes);
    }

    public FinalNoteDTO getById(Long id) {
        return finalNoteMapper.toDto(finalNoteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Final note not found with id: " + id)));
    }

    public FinalNoteDTO create(FinalNote finalNote) {
        return finalNoteMapper.toDto(finalNoteRepository.save(finalNote));
    }

    public List<FinalNoteDTO> createMultiple(List<FinalNote> finalNotes) {
        return finalNoteMapper.toDtoList(
                finalNoteRepository.saveAll(finalNotes)
        );
    }

    public FinalNoteDTO update(Long id, FinalNote finalNote) {
        finalNoteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Final note not found with id: " + id));
        finalNote.setId(id);
        return finalNoteMapper.toDto(finalNoteRepository.save(finalNote));
    }

    public void delete(Long id) {
        finalNoteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Final note not found with id: " + id));
        finalNoteRepository.deleteById(id);
    }


    public float getLessonFinalNote(long idStudent, long idGroup) {

        Student student  = studentRepository.findById(idStudent)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + idStudent));

        Group group = groupRepository.findById(idGroup)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + idGroup));

        AcademicRecord academicRecord = academicRecordRepository.findByStudentId(idStudent)
                .orElseThrow(() -> new RuntimeException("Academic record not found for student with id: " + idStudent));


        FinalNote finalNote = finalNoteRepository.findByAcademicRecordIdAndGroupId(academicRecord.getId(), idGroup);
        return finalNote.getNote();
    }




}
