package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    List<Borrow> findAllByResource_Id(Long id);

    @Query("SELECT b.resource.category FROM Borrow b WHERE b.id = :id")
    String findCategoryById(@Param("id") Long id);

}
