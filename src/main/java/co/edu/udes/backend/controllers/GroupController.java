package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.GroupDTO;
import co.edu.udes.backend.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    // get all groups
    @GetMapping
    public ResponseEntity<List<GroupDTO>> getAll(){
        return ResponseEntity.ok(groupService.getAll());
    }

    // create group
    @PostMapping
    public ResponseEntity<GroupDTO> create(@RequestBody GroupDTO dto){
        return ResponseEntity.ok(groupService.create(dto));
    }

    // get group by id
    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(groupService.getById(id));
    }

    // update group
    @PutMapping("/{id}")
    public ResponseEntity<GroupDTO> update(@PathVariable Long id, @RequestBody GroupDTO dto){
        return ResponseEntity.ok(groupService.update(id, dto));
    }

    // delete group
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id){
        groupService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
