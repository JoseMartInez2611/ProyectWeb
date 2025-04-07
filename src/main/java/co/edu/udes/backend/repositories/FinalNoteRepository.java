package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.FinalNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinalNoteRepository extends JpaRepository<FinalNote, Long> {
}
