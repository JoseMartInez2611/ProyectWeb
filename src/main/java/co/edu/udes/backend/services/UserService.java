package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.inheritanceDTO.UserDTO;
import co.edu.udes.backend.mappers.UserMapper;
import co.edu.udes.backend.models.inheritance.ProfileU;
import co.edu.udes.backend.repositories.UserRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getById(Long id) {
        ProfileU entity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        return userMapper.toDTO(entity);
    }

    public UserDTO create(UserDTO dto) {
        ProfileU entity = userMapper.toEntity(dto);
        return userMapper.toDTO(userRepository.save(entity));
    }

    public UserDTO update(Long id, UserDTO dto) {
        userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        dto.setId(id);
        ProfileU updated = userRepository.save(userMapper.toEntity(dto));
        return userMapper.toDTO(updated);
    }

    public void delete(Long id) {
        ProfileU entity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        userRepository.delete(entity);
    }

}
