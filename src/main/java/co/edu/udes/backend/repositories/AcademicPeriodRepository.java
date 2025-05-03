package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.AcademicPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademicPeriodRepository extends JpaRepository<AcademicPeriod, Long> {
    AcademicPeriod findByAcademicYearAndPeriod(int academicYear, char period);
}
