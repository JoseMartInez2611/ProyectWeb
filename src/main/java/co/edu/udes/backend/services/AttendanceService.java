package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.AttendanceDTO;
import co.edu.udes.backend.mappers.AttendanceMapper;
import co.edu.udes.backend.repositories.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;

    public List<AttendanceDTO> getAll() {
        return attendanceRepository.findAll().stream()
                .map(attendanceMapper::toDTO)
                .toList();
    }

    public AttendanceDTO getById(Long id) {
        return attendanceMapper.toDTO(attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found with id: " + id)));
    }

    public AttendanceDTO create(AttendanceDTO dto) {
        return attendanceMapper.toDTO(attendanceRepository.save(attendanceMapper.toEntity(dto)));
    }

    public AttendanceDTO update(Long id, AttendanceDTO dto) {
        attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found with id: " + id));
        dto.setId(id);
        return attendanceMapper.toDTO(attendanceRepository.save(attendanceMapper.toEntity(dto)));
    }

    public void delete(Long id) {
        attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found with id: " + id));
        attendanceRepository.deleteById(id);
    }
}
