package pl.runnerData.shoe.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


import java.util.List;
import java.util.UUID;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetShoesResponse {

    @Getter
    @Setter
    @SuperBuilder
    public static class Shoe{
        private UUID id;
        private String brandName;
        private String model;
        private double size;
        private UUID ownerId;
    }

    @Singular
    private List<Shoe> shoes;
}
