package co.edu.udes.backend.models;

import co.edu.udes.backend.models.inheritance.Comunication;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "comunication")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString


public class Notification extends Comunication {
    private String type;

    public void send() {
       // send notificación
    }
}
