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

    public List<GroupDTO> getAll() {
        List<Group> groups = groupRepository.findAll();
        return GroupMapper.INSTANCE.toDtoList(groups);
    }

    public GroupDTO getById(Long id) {
        return GroupMapper.INSTANCE.toDto(groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id)));
    }

    public GroupDTO create(Group group) {
        return GroupMapper.INSTANCE.toDto(groupRepository.save(group));
    }

    public GroupDTO update(Long id, Group group) {
        groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));
        group.setId(id);
        return GroupMapper.INSTANCE.toDto(groupRepository.save(group));
    }

    public void delete(Long id) {
        groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));
        groupRepository.deleteById(id);
    }
}
