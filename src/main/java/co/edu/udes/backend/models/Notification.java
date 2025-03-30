package co.edu.udes.backend.models;

import co.edu.udes.backend.models.inheritance.Communication;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comunication")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString


public class Notification extends Communication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "type")
    private String type;

    public void send() {
       // send notificación
    }
}
