package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    @Query("SELECT a.availability FROM Borrow b JOIN b.resource a WHERE a.id = :id")
    Boolean getAvailability(@Param("id") Long id);

    @Query("SELECT a.name FROM Borrow b JOIN b.resource a WHERE a.id = :id")
    String getResouceName(@Param("id") Long id);

}
