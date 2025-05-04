package co.edu.udes.backend.repositories;


import co.edu.udes.backend.models.inheritance.ProfileU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileURepository extends JpaRepository<ProfileU, Long> {

    Optional<ProfileU> findByUserName(String userName);


}
