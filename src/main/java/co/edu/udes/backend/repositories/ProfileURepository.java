package co.edu.udes.backend.repositories;


import co.edu.udes.backend.models.RoleEnum;
import co.edu.udes.backend.models.inheritance.ProfileU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileURepository extends JpaRepository<ProfileU, Long> {

    Optional<ProfileU> findByUserName(String userName);


    @Query("SELECT p.role.roleEnum FROM ProfileU p WHERE p.id = :id")
    RoleEnum getRoleNameByProfileUId(@Param("id") Long id);

    @Query("SELECT CONCAT(p.firstName, ' ', p.lastName) FROM ProfileU p WHERE p.userName = :userName")
    String getFullNameByUserName(String userName);
}
