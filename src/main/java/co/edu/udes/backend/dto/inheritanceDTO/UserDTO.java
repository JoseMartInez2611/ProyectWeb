package co.edu.udes.backend.dto.inheritanceDTO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@FieldDefaults(level= AccessLevel.PRIVATE)
public class UserDTO {

    long id;
    String firstName;
    String lastName;
    String phone;
    String email;
    String userName;
    String password;
    MessageDTO message;
    ReporteDTO report;
    BorrowdDTO borrow;
    CommunicationDTO communication;


}
