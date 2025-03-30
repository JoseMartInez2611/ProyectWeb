package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Group;
import co.edu.udes.backend.repositories.GroupRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/api/v1/")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    // get all groups
    @GetMapping("/groups")
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    // create group rest api
    @PostMapping("/groups")
    public Group createGroup(@RequestBody Group group) {
        return groupRepository.save(group);
    }

    // get group by id rest api
    @GetMapping("/groups/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Group not exist with id :" + id));
        return ResponseEntity.ok(group);
    }

    // update group rest api
    @PutMapping("/groups/{id}")
    public ResponseEntity<Group> updateGroup(@PathVariable Long id, @RequestBody Group groupDetails) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Group not exist with id :" + id));

        group.setCourse(groupDetails.getCourse());
        group.setTeacher(groupDetails.getTeacher());
        group.setAcademicPeriod(groupDetails.getAcademicPeriod());

        Group updatedGroup = groupRepository.save(group);
        return ResponseEntity.ok(updatedGroup);
    }

    // delete group rest api
    @DeleteMapping("/groups/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteGroup(@PathVariable Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Group not exist with id :" + id));

        groupRepository.delete(group);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
