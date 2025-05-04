package co.edu.udes.backend.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDTO {
    private String userName;
    private String password;
}
