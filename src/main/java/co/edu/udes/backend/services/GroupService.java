package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.GroupDTO;
import co.edu.udes.backend.mappers.GroupMapper;
import co.edu.udes.backend.models.Group;
import co.edu.udes.backend.repositories.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private GroupMapper groupMapper;

    public List<GroupDTO> getAll() {
        List<Group> groups = groupRepository.findAll();
        return groupMapper.toDtoList(groups);
    }

    public GroupDTO getById(Long id) {
        return groupMapper.toDto(groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id)));
    }

    public GroupDTO create(Group group) {
        return groupMapper.toDto(groupRepository.save(group));
    }

    public GroupDTO update(Long id, Group group) {
        groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));
        group.setId(id);
        return groupMapper.toDto(groupRepository.save(group));
    }

    public void delete(Long id) {
        groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));
        groupRepository.deleteById(id);
    }
}
