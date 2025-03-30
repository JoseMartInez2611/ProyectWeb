package co.edu.udes.backend.models;

import co.edu.udes.backend.models.inheritance.Comunication;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Notification extends Comunication {
    private String type;

    public void send() {
       // send notificación
    }
}
