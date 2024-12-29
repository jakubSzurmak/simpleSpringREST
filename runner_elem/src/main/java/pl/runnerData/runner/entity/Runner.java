package pl.runnerData.runner.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import pl.runnerData.shoe.entity.Shoe;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
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
    private List<Shoe> shoes;


    @Override
    public String toString(){
        return id.toString() + " " + name + " " + surname + " " + birthDate;
    }

}
