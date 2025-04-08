package co.edu.udes.backend.services;


import co.edu.udes.backend.dto.AcademicResourceDTO;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import co.edu.udes.backend.mappers.AcademicResourceMapper;
import co.edu.udes.backend.models.AcademicResource;
import co.edu.udes.backend.repositories.AcademicResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademicResourceService {

    private final AcademicResourceRepository academicResourceRepository;
    private AcademicResourceMapper academicResourceMapper;

    public List<AcademicResourceDTO> getAll() {
        List<AcademicResource> academicResources = academicResourceRepository.findAll();
        return academicResourceMapper.toDtoList(academicResources);
    }

    public AcademicResourceDTO getById(Long id) {
        return academicResourceMapper.toDto(academicResourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Academic resource not found with id: " + id)));
    }

    public AcademicResourceDTO create(AcademicResource academicResource) {
        return academicResourceMapper.toDto(academicResourceRepository.save(academicResource));
    }

    public AcademicResourceDTO update(Long id, AcademicResource academicResource) {
        academicResourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Academic resource not found with id: " + id));
        academicResource.setId(id);
        return academicResourceMapper.toDto(academicResourceRepository.save(academicResource));
    }

    public void delete(Long id) {
        academicResourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Academic resource not found with id: " + id));
        academicResourceRepository.deleteById(id);
    }
}