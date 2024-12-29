package pl.runnerData.runner.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutRunnerRequest {
    private String name;
    private String surname;
    private LocalDate birthDate;

}
