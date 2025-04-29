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


    public double getPerformance(long id){
        List<Double> percentage= finalNoteRepository.getPercentagesByAcademicRecordId(id);
        List<Double> note = finalNoteRepository.getNotesByAcademicRecordId(id);
        double performance=0.0;

        for (int i = 0; i < percentage.size(); i++) {
            performance += (note.get(i) * (percentage.get(i) / 100));
        }
        return performance;
    }

    public String getProyectNote(long id) {
        double value = 0.0;
        double minimum = 3.0;

        List<Double> percentage = finalNoteRepository.getPercentagesByAcademicRecordId(id);
        List<Double> note = finalNoteRepository.getNotesByAcademicRecordId(id);


        for (int i = 0; i < percentage.size(); i++) {
            value += (note.get(i) * (percentage.get(i) / 100));
        }

        double usedPercentage = percentage.stream().mapToDouble(Double::doubleValue).sum();
        double remainingPercentage = 100.0 - usedPercentage;

        if (remainingPercentage <= 0) {
            if (value >= minimum) {
                return "You already passed the lesson! You're doing great!";
            } else {
                return "You have completed all the evaluations and didn't reach the minimum grade.";
            }
        }

        double necessaryNote = (minimum - value) * 100 / remainingPercentage;

        if (necessaryNote > 5.0) {
            return "You may need more than 5.0 to pass the lesson";
        } else if (necessaryNote <= 0.0) {
            return "You already passed the lesson! You're awesome";
        } else {
            return String.format("You may need %.2f to pass the lesson", necessaryNote);
        }
    }


    public String getReport(long id){

        double average=getPerformance(id);
        String name= finalNoteRepository.findStudentFullNameByAcademicRecordId(id);
        if (average <= 3.5 ) {
            return "The Student "+name+" has a low academic performance. Performance: "+average;
        }else if (average > 3.5 && average <= 4.5) {
            return"The Student "+name+" has a medium academic performance. Performance: "+average;
        }else if (average > 4.5 && average <= 5.0) {
            return "The Student "+name+" has a good academic performance. Performance: "+average;
        }
        return "The Student Doesn't have a final note";
    }

    public String getLessonFinalNote(long idStudent, long idGroup) {

        Student student  = studentRepository.findById(idStudent)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + idStudent));

        Group group = groupRepository.findById(idGroup)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + idGroup));

        AcademicRecord academicRecord = academicRecordRepository.findByStudentId(idStudent)
                .orElseThrow(() -> new RuntimeException("Academic record not found for student with id: " + idStudent));


        List<FinalNote> finalNotes = finalNoteRepository.findByAcademicRecordIdAndGroupId(academicRecord.getId(), idGroup);

        for (FinalNote note : finalNotes) {
            System.out.println("Notes: "+note.getTitle()+" "+note.getNote());
            if(note.getTitle().equals("final")) {
                return "The Student "+student.getFullName()+" int the Course " + group.getCourse().getName()+" has a final note: "+note.getNote();
            }
        }

        return "The Student Doesn't have a final note";
    }


    public String getGroupPerformance(long id){
        Double average = finalNoteRepository.getGroupAverage(id);
        String name= finalNoteRepository.findCourseNameByGroupId(id);
        if (average <= 3.5 ) {
            return "The Group "+name+" has a low academic performance. Performance: "+average;
        }else if (average > 3.5 && average <= 4.5) {
            return"The Group "+name+" has a medium academic performance. Performance: "+average;
        }else if (average > 4.5 && average <= 5.0) {
            return "The Group "+name+" has a good academic performance. Performance: "+average;
        }
        return "The Group Doesn't have a performance";

    }

}
