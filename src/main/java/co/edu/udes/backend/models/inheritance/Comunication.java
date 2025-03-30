package co.edu.udes.backend.models.inheritance;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;
import java.util.List;


@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table (name = "comunication")
public class Comunication {

    private List<User> reciver;
    private Date sentDate;
    private String content;
    private boolean read;

    public void markAsRead() {
        this.read = true;
    }

}
