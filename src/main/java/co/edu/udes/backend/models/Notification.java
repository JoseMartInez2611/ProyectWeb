package co.edu.udes.backend.models;

import co.edu.udes.backend.models.inheritance.Comunication;
import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Notification extends Comunication {
    private String type;

    public void send() {
       // send notificación
    }
}
