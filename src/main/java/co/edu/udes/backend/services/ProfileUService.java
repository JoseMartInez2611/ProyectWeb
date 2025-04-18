package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.inheritanceDTO.ProfileUDTO;
import co.edu.udes.backend.mappers.ProfileUMapper;
import co.edu.udes.backend.models.inheritance.ProfileU;
import co.edu.udes.backend.repositories.ProfileURepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileUService {

    private final ProfileURepository userRepository;
    @Autowired
    private ProfileUMapper profileUMapper;

    public List<ProfileUDTO> getAll() {
        List<ProfileU> profilesU = userRepository.findAll();
        return profileUMapper.toDtoList(profilesU);
    }

    public List<ProfileU> createMultiple(List<ProfileU> users) {

        return userRepository.saveAll(users);
    }

    public ProfileUDTO getById(Long id) {
        return profileUMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id)));
    }

    public ProfileUDTO create(ProfileU user) {
        return profileUMapper.toDto(userRepository.save(user));

    }

    public ProfileUDTO update(Long id, ProfileU user) {
        userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        user.setId(id);
        return profileUMapper.toDto(userRepository.save(user));
    }

    public void delete(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        userRepository.deleteById(id);
    }

}
