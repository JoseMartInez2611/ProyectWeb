package co.edu.udes.backend.models.inheritance;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table (name = "comunication")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode


public class Comunication {

    private List<User> reciver;
    private Date sentDate;
    private String content;
    private boolean read = false;

    public void markAsRead() {
        this.read = true;
    }

}
