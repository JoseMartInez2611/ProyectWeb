package co.edu.udes.backend.models;


import co.edu.udes.backend.models.inheritance.Comunication;
import co.edu.udes.backend.models.inheritance.User;
import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Message extends Comunication {
    private String subject;
    private User sender;

    public void send() {
        // send Message
    }
}
