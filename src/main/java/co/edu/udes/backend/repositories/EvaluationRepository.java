package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Group;
import co.edu.udes.backend.models.inheritance.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    /**
     * Busca todas las evaluaciones asociadas a un grupo específico.
     * @param group El objeto Group para el cual se quieren encontrar las evaluaciones.
     * @return Una lista de objetos Evaluation que pertenecen al grupo especificado.
     */
    List<Evaluation> findByGroup(Group group);
}
