package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.AcademicResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicResourceRepository extends JpaRepository<AcademicResource, Long> {

    Boolean getAvailability(Long id);

    String getResouceName(Long id);
}
