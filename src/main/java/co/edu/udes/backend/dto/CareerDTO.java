package co.edu.udes.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CareerDTO {

    private long id;
    private String name;
    private String description;
    private String code;
    private int duration;
    private String modality;
    private String type;
}
