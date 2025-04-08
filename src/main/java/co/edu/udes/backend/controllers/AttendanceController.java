package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.AttendanceDTO;
import co.edu.udes.backend.mappers.AttendanceMapper;
import co.edu.udes.backend.models.Attendance;
import co.edu.udes.backend.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origin = "http://localhost")
@RestController
@RequestMapping("/api/v1/attendances")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // get all attendances
    @GetMapping
    public ResponseEntity<List<AttendanceDTO>> getAllAttendances() {
        return ResponseEntity.ok(attendanceService.getAll());
    }

    // create attendance rest api
    @PostMapping
    public ResponseEntity<?> create(@RequestBody AttendanceDTO dto) {
        try{
            Attendance attendance = AttendanceMapper.INSTANCE.toEntity(dto);
            return ResponseEntity.ok(attendanceService.create(attendance));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    // get attendance by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(attendanceService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // update attendance rest api
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AttendanceDTO dto) {
        try{
            Attendance attendance = AttendanceMapper.INSTANCE.toEntity(dto);
            return ResponseEntity.ok(attendanceService.update(id, attendance));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    // delete attendance rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            attendanceService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
