package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.AttendanceDTO;
import co.edu.udes.backend.mappers.AttendanceMapper;
import co.edu.udes.backend.models.Attendance;
import co.edu.udes.backend.repositories.AttendanceRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public List<AttendanceDTO> getAll() {
        List<Attendance> attendances = attendanceRepository.findAll();
        return AttendanceMapper.INSTANCE.toDtoList(attendances);
    }

    public AttendanceDTO getById(Long id) {
        return AttendanceMapper.INSTANCE.toDto(attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found with id: " + id)));
    }

    public AttendanceDTO create(Attendance attendance) {
        return AttendanceMapper.INSTANCE.toDto(attendanceRepository.save(attendance));
    }

    public AttendanceDTO update(Long id, Attendance attendance) {
        attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found with id: " + id));
        attendance.setId(id);
        return AttendanceMapper.INSTANCE.toDto(attendanceRepository.save(attendance));
    }

    public void delete(Long id) {
        attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found with id: " + id));
        attendanceRepository.deleteById(id);
    }
}
