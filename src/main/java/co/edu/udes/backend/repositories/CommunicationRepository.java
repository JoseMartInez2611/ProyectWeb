package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.inheritance.Communication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunicationRepository extends JpaRepository<Communication, Long> {
}
