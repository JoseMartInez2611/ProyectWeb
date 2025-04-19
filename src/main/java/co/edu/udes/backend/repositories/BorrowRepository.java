package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    List<Borrow> findAllByResource_Id(Long id);

}
