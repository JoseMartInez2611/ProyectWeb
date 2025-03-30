package co.edu.udes.backend.models.inheritance;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Comunication {

    private List<User> reciver;
    private Date sentDate;
    private String content;
    private boolean read;

    public void markAsRead() {
        this.read = true;
    }

}
