package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.GroupDTO;
import co.edu.udes.backend.mappers.GroupMapper;
import co.edu.udes.backend.models.Group;
import co.edu.udes.backend.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupMapper groupMapper;

    // get all groups
    @GetMapping
    public ResponseEntity<List<GroupDTO>> getAll(){
        return ResponseEntity.ok(groupService.getAll());
    }

    // create group
    @PostMapping
    public ResponseEntity<?> create(@RequestBody GroupDTO dto){
        try{
            Group entities = groupMapper.toEntity(dto);
            return ResponseEntity.ok(groupService.create(entities));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending" + e.getMessage());
        }
    }

    // get group by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try{
            return ResponseEntity.ok().body(groupService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // update group
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody GroupDTO dto){
        try{
            Group group = groupMapper.toEntity(dto);
            return ResponseEntity.ok(groupService.update(id, group));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    // delete group
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            groupService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<?> getStudentsByGroupId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(groupService.getStudentsByGroupId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/lessons/{id}")
    public ResponseEntity<?> getLessonsByGroupId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(groupService.getLessonsByGroupId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
