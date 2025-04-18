package co.edu.udes.backend.dto.inheritanceDTO;

import co.edu.udes.backend.dto.BorrowDTO;
import co.edu.udes.backend.dto.MessageDTO;
import co.edu.udes.backend.dto.ReportDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

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
    private List<MessageDTO> message;
    private List<ReportDTO> report;
    private List<BorrowDTO> borrow;
    private List<CommunicationDTO> communication;
}
