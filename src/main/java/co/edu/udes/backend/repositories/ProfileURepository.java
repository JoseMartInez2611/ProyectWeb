package co.edu.udes.backend.repositories;


import co.edu.udes.backend.models.inheritance.ProfileU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileURepository extends JpaRepository<ProfileU, Long> {
}
