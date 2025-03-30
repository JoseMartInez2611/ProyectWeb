package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Attendance;
import co.edu.udes.backend.repositories.AttendanceRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin(origin = "http://localhost")
@RestController
@RequestMapping("/api/v1/")
public class AttendanceController {

    @Autowired
    private AttendanceRepository attendanceRepository;

    // get all attendances
    @GetMapping("/attendances")
    public List<Attendance> getAllAttendances() {
        return attendanceRepository.findAll();
    }

    // create attendance rest api
    @PostMapping("/attendances")
    public Attendance createAttendance(@RequestBody Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    // get attendance by id rest api
    @GetMapping("/attendances/{id}")
    public ResponseEntity<Attendance> getAttendanceById(@PathVariable Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not exist with id :" + id));
        return ResponseEntity.ok(attendance);
    }

    // update attendance rest api
    @PutMapping("/attendances/{id}")
    public ResponseEntity<Attendance> updateAttendance(@PathVariable Long id, @RequestBody Attendance attendanceDetails) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not exist with id :" + id));

        attendance.setLesson(attendanceDetails.getLesson());
        attendance.setStudent(attendanceDetails.getStudent());
        attendance.setDate(attendanceDetails.getDate());
        attendance.setAssistance(attendanceDetails.isAssistance());
        attendance.setJustification(attendanceDetails.getJustification());

        Attendance updatedAttendance = attendanceRepository.save(attendance);
        return ResponseEntity.ok(updatedAttendance);
    }

    // delete attendance rest api
    @DeleteMapping("/attendances/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAttendance(@PathVariable Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not exist with id :" + id));

        attendanceRepository.delete(attendance);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
