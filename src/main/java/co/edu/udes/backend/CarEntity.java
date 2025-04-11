package co.edu.udes.backend;

import lombok.Builder;

@Builder
public class CarEntity {
    String color;
    int numberOfSeats;
    int maximumSpeed;
}
