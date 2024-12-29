package pl.runnerData.runner.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
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

    @Override
    public String toString(){
        return id.toString() + " "
                + name + " " + surname
                + " " + birthDate.toString()
                + "\n";
    }
}
