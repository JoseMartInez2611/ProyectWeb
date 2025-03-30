package co.edu.udes.backend.models;


import co.edu.udes.backend.models.inheritance.Comunication;
import co.edu.udes.backend.models.inheritance.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table (name = "message")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Message extends Comunication {
    private String subject;
    private User sender;

    public void send() {
        // send Message
    }
}
