package co.edu.udes.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BorrowDTO {
    private long id;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private String duration;
    private long resourceId;
    private long petitionerId;
}