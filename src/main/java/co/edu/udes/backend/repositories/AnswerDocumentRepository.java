package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.AnswerDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerDocumentRepository extends JpaRepository<AnswerDocument, Long> {
}
