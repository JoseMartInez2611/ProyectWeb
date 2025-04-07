package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Student;
import co.edu.udes.backend.repositories.StudentRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/api/v1/")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // get all employees
    @GetMapping("/student")
    public List<Student> getAllStudents(){

        return studentRepository.findAll();
    }

    // create employee rest api
    @PostMapping("/student")
    public Student createStudent(@RequestBody Student student) {

        return studentRepository.save(student);
    }

    //get students by id
    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));
        return ResponseEntity.ok(student);
    }


    // update employee rest api

    @PutMapping("/student/{id}")
    public ResponseEntity<Student> updatedStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));

        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setPhone(studentDetails.getPhone());
        student.setUserName(studentDetails.getUserName());
        student.setEmail(studentDetails.getEmail());
        student.setPassword(student.getPassword());
        student.setAddress(studentDetails.getAddress());
        student.setSemester(student.getSemester());
        student.setCode(studentDetails.getCode());
        student.setDateBirth(studentDetails.getDateBirth());
        student.setCareer(studentDetails.getCareer());



        Student updatedStudent = studentRepository.save(student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable Long id){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));

        studentRepository.delete(student);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
