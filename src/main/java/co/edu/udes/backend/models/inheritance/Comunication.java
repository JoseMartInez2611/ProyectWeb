package co.edu.udes.backend.models.inheritance;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Comunication {

    private List<User> reciver;
    private Date sentDate;
    private String content;
    private boolean read;
    private User sender;

    public void markAsRead() {
        this.read = true;
    }

}
