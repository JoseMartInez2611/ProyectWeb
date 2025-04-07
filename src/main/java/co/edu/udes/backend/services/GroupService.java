package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.GroupDTO;
import co.edu.udes.backend.mappers.GroupMapper;
import co.edu.udes.backend.repositories.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    public List<GroupDTO> getAll() {
        return groupRepository.findAll().stream()
                .map(groupMapper::toDTO)
                .toList();
    }

    public GroupDTO getById(Long id) {
        return groupMapper.toDTO(groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id)));
    }

    public GroupDTO create(GroupDTO dto) {
        return groupMapper.toDTO(groupRepository.save(groupMapper.toEntity(dto)));
    }

    public GroupDTO update(Long id, GroupDTO dto) {
        groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));
        dto.setId(id);
        return groupMapper.toDTO(groupRepository.save(groupMapper.toEntity(dto)));
    }

    public void delete(Long id) {
        groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));
        groupRepository.deleteById(id);
    }
}
