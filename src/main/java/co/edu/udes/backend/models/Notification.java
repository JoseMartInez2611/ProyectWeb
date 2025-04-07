package co.edu.udes.backend.models;

import co.edu.udes.backend.models.inheritance.Communication;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "notification")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder

public class Notification extends Communication {

    @Column(name = "type", nullable = false, columnDefinition = "varchar(255)")

    private String type;

    public void send() {
       // send notificación
    }
}
