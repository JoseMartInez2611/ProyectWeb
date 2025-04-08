package co.edu.udes.backend.dto;


import co.edu.udes.backend.dto.inheritanceDTO.CommunicationDTO;
import co.edu.udes.backend.dto.inheritanceDTO.ProfileUDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO extends CommunicationDTO {
    private String subject;
    private ProfileUDTO sender;
}