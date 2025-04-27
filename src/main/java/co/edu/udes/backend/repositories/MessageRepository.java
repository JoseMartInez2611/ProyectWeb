package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m JOIN m.receivers r WHERE m.sender.id = :senderId AND r.id = :receiverId")
    List<Message> findBySenderIdAndReceiverId(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);}
