package pl.runnerData.shoe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
public class ShoeDTO implements Serializable, Comparable<ShoeDTO> {
    private String brandName;
    private String model;
    private String color;
    private double size;
    private UUID ownerID;

    @Override
    public int compareTo(ShoeDTO o) {
        return model.compareTo(o.model);
    }

    private boolean compareNatural(Object test){
        ShoeDTO newShoe = (ShoeDTO) test;
        return ((Objects.equals(newShoe.brandName, this.brandName))
                &&(Objects.equals(newShoe.model, this.model))
                &&(Objects.equals(newShoe.color, this.color))
                &&(newShoe.size == this.size)
                && (Objects.equals(newShoe.ownerID, this.ownerID)));
    }

    private boolean compareHash(Object test){
        return this.hashCode() == test.hashCode();
    }
}
