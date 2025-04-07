package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.AbsenceJustification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenceJustificationRepository extends JpaRepository<AbsenceJustification, Long> {
    // Define any custom query methods if needed
}
