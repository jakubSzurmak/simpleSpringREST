package pl.runnerData.runner.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetRunnerResponse {
    private UUID id;
    private String name;
    private String surname;
    private LocalDate birthDate;
}
