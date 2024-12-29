package pl.runnerData.shoe.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetShoeResponse {
    private UUID id;
    private String brandName;
    private String model;
    private String color;
    private double size;

    public static class Runner{
        private UUID id;
        private String name;
        private String surname;
    }

    private UUID ownerId;
}
