package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.AbsenceJustificationDTO;
import co.edu.udes.backend.mappers.AbsenceJustificationMapper;
import co.edu.udes.backend.models.AbsenceJustification;
import co.edu.udes.backend.models.Attendance;
import co.edu.udes.backend.repositories.AbsenceJustificationRepository;
import co.edu.udes.backend.repositories.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AbsenceJustificationService {

    private final AbsenceJustificationRepository absenceJustificationRepository;
    private final AttendanceRepository attendanceRepository;
    @Autowired
    private AbsenceJustificationMapper absenceJustificationMapper;

    public List<AbsenceJustificationDTO> getAll() {
        List<AbsenceJustification> absenceJustifications = absenceJustificationRepository.findAll();
        return absenceJustificationMapper.toDtoList(absenceJustifications);
    }

    public AbsenceJustificationDTO getById(Long id) {
        return absenceJustificationMapper.toDto(absenceJustificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Absence justification not found with id: " + id)));
    }

    public AbsenceJustificationDTO create(AbsenceJustification absenceJustification) {
        checkIfJustificationExists(absenceJustification.getAttendance().getId());
        verifyAttendanceNotMarkedAsAttended(absenceJustification.getAttendance().getId());
        return absenceJustificationMapper.toDto(
                absenceJustificationRepository.save(absenceJustification)
        );
    }

    public List<AbsenceJustificationDTO> createMultiple(List<AbsenceJustification> absenceJustifications) {
        List<AbsenceJustificationDTO> absenceJustificationDTOs = new ArrayList<>();
        for (AbsenceJustification absenceJustification : absenceJustifications) {
            absenceJustificationDTOs.add(create(absenceJustification));
        }
        return absenceJustificationDTOs;
    }

    public AbsenceJustificationDTO update(Long id, AbsenceJustification absenceJustification) {
        AbsenceJustification existing = absenceJustificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Absence justification not found with id: " + id));

        checkUpdateSameAttendance(existing, absenceJustification);
        verifyAttendanceNotMarkedAsAttended(absenceJustification.getAttendance().getId());

        absenceJustification.setId(id);
        return absenceJustificationMapper.toDto(
                absenceJustificationRepository.save(absenceJustification)
        );
    }

    public void delete(Long id) {
        absenceJustificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Absence justification not found with id: " + id));
        absenceJustificationRepository.deleteById(id);
    }

    private void checkIfJustificationExists(Long attendanceId) {
        boolean exists = absenceJustificationRepository.existsByAttendanceId(attendanceId);
        if (exists){
            throw new RuntimeException("Absence justification already exists for this attendance");
        }
    }

    private void verifyAttendanceNotMarkedAsAttended(Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found with id: " + id));

        if (attendance.isAttended()){
            throw new RuntimeException("Cannot justify an attendance that is already marked as attended");
        }
    }

    private void checkUpdateSameAttendance(AbsenceJustification existing, AbsenceJustification absenceJustification) {
        if (!existing.getAttendance().getId().equals(absenceJustification.getAttendance().getId())) {
            throw new RuntimeException("Cannot change the attendance related to this justification");
        }
    }
}
