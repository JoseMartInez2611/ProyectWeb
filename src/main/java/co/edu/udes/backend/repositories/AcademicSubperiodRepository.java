package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.AcademicSubperiod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicSubperiodRepository extends JpaRepository<AcademicSubperiod, Long> {
}
