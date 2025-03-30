package co.edu.udes.backend.models;


import co.edu.udes.backend.models.inheritance.Comunication;
import co.edu.udes.backend.models.inheritance.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table (name = "message")
public class Message extends Comunication {
    private String subject;
    private User sender;

    public void send() {
        // send Message
    }
}
