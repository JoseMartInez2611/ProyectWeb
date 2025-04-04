package co.edu.udes.backend.models;


import co.edu.udes.backend.models.inheritance.User;
import co.edu.udes.backend.models.inheritance.communication;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "message")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Message extends communication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "subject")
    private String subject;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    public void send() {
        // send Message
    }
}
