package pl.runnerData.runner.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.runnerData.shoe.entity.Shoe;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "runners")
public class Runner implements Serializable {
    @Id
    private UUID id;
    private String name;
    private String surname;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @OneToMany(mappedBy ="owner" ,cascade=CascadeType.REMOVE)
    @Setter(AccessLevel.PUBLIC)
    List<Shoe> shoes;

    public Collection<Shoe> getShoes() {
        return shoes;
    }

    private boolean compareNatural(Object test){
        Runner newRunner = (Runner) test;
        return ((newRunner.id == this.id)
                &&(Objects.equals(newRunner.name, this.name))
                &&(Objects.equals(newRunner.surname, this.surname))
                &&(Objects.equals(newRunner.birthDate, this.birthDate))
                &&(Objects.equals(newRunner.shoes, this.shoes)));
    }

    private boolean compareHash(Object test){
        return this.hashCode() == test.hashCode();
    }

    private String shoeListToString(){
        StringBuilder output = new StringBuilder();
        for(Shoe shoe : this.shoes){
            output.append(shoe.toString());
        }
        return output.toString();
    }

    public String getPID(){
        return id.toString() + " " + name + " " + surname;
    }

    @Override
    public String toString(){
        return id.toString() + " "
                + name + " " + surname
                + " " + birthDate.toString()
                + "\n";
    }

}
