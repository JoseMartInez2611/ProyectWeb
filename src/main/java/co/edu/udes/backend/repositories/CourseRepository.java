package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.careers WHERE c.id = :id")
    Optional<Course> findByIdWithCareers(@Param("id") Long id);
}
