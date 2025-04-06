package co.edu.udes.backend.service;

import co.edu.udes.backend.models.AcademicResource;
import co.edu.udes.backend.repositories.AcademicResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcademicResourceService {

    private final AcademicResourceRepository academicResourceRepository;

    @Autowired
    public AcademicResourceService(AcademicResourceRepository academicResourceRepository) {
        this.academicResourceRepository = academicResourceRepository;
    }

    public List<AcademicResource> getAllAcademicResources() {
        return academicResourceRepository.findAll();
    }

    public Optional<AcademicResource> getAcademicResourceById(Long id) {
        return academicResourceRepository.findById(id);
    }

    public AcademicResource saveAcademicResource(AcademicResource academicResource) {
        return academicResourceRepository.save(academicResource);
    }

    public void deleteAcademicResource(Long id) {
        academicResourceRepository.deleteById(id);
    }

    // Add other business logic methods as needed (e.g., find by category, update availability)
}