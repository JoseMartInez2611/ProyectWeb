package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.AcademicResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicResourceRepository extends JpaRepository<AcademicResource, Long> {

    @Query("SELECT ar.availability FROM AcademicResource ar WHERE ar.id = :id")
    Boolean getAvailability(@Param("id") Long id);

    @Query("SELECT ar.name FROM AcademicResource ar WHERE ar.id = :id")
    String getResourceName(@Param("id") Long id);

    @Query("SELECT ar.category FROM AcademicResource ar WHERE ar.id = :id")
    String getCategory(@Param("id") Long id);
}
