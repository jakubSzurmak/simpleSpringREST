package pl.runnerData.shoe.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import pl.runnerData.runner.entity.Runner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import java.text.DecimalFormat;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shoes")
public class Shoe implements Serializable {
    @Id
    private UUID shoe_id;

    @Column(name = "brand_name")
    private String brandName;

    private String model;
    private String color;
    private double size;

    @ManyToOne
    @JoinColumn(name = "id")
    private Runner owner;

    private static final DecimalFormat df = new DecimalFormat("0.0");

    private boolean compareNatural(Object test){
        Shoe newShoe = (Shoe) test;
        return ((Objects.equals(newShoe.brandName, this.brandName))
                &&(Objects.equals(newShoe.model, this.model))
                &&(Objects.equals(newShoe.color, this.color))
                &&(newShoe.size == this.size));
    }

    private boolean compareHash(Object test){
        return this.hashCode() == test.hashCode();
    }

    @Override
    public String toString(){
        return shoe_id + " " + model + " " + color + " " + df.format(size) + "\n" + owner.getPID();
    }
}
