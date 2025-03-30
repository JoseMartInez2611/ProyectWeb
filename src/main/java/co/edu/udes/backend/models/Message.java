package co.edu.udes.backend.models;


import co.edu.udes.backend.models.inheritance.Comunication;
import co.edu.udes.backend.models.inheritance.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Message extends Comunication {
    private String subject;
    private User sender;

    public void send() {
        // send Message
    }
}
