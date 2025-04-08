package co.edu.udes.backend.dto.inheritanceDTO;

import co.edu.udes.backend.dto.BorrowDTO;
import co.edu.udes.backend.dto.MessageDTO;
import co.edu.udes.backend.dto.ReportDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ProfileUDTO {

    long id;
    String firstName;
    String lastName;
    String phone;
    String email;
    String userName;
    String password;
    List<MessageDTO> message;
    List<ReportDTO> report;
    List<BorrowDTO> borrow;
    List<CommunicationDTO> communication;


}
