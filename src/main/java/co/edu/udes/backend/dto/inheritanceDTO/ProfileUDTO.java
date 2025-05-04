package co.edu.udes.backend.dto.inheritanceDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProfileUDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String userName;
    private String password;
    private boolean enable;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;

}
