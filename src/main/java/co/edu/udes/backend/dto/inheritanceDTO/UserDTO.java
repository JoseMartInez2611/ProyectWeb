package co.edu.udes.backend.dto.inheritanceDTO;

import co.edu.udes.backend.dto.BorrowDTO;
import co.edu.udes.backend.dto.MessageDTO;
import co.edu.udes.backend.dto.ReportDTO;
import lombok.AccessLevel;
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
    ReportDTO report;
    BorrowDTO borrow;
    CommunicationDTO communication;


}
