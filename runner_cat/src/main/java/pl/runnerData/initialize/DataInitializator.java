package pl.runnerData.initialize;

import lombok.Getter;
import org.springframework.stereotype.Component;
import pl.runnerData.function.RandomBirthdateGenerator;
import pl.runnerData.runner.entity.Runner;
import pl.runnerData.runner.service.DefaultRunnerService;

import java.util.List;
import java.util.UUID;


@Component
public class DataInitializator {

    static List<String> runner_uuids = List.of(
            "c4804e0f-769e-4ab9-9ebe-0578fb4f00a6",
            "81e1c2a9-7f57-439b-b53d-6db88b071e4e",
            "ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4197"
    );

    static List<String> names = List.of(
            "Jan",
            "Arkadiusz",
            "Tomasz"
    );

    static List<String> surnames = List.of(
            "Kowalski",
            "Tomala",
            "Guzik"
    );

    public static void dataInitializator( DefaultRunnerService defaultRunnerService) {
        for (short i = 0; i < 3; i++) {
            Runner newRunner = Runner.builder()
                    .id(UUID.fromString(runner_uuids.get(i)))
                    .name(names.get(i))
                    .surname(surnames.get(i))
                    .birthDate(RandomBirthdateGenerator.getRandomBirthdate())
                    .build();

            defaultRunnerService.create(newRunner);
        }
    }
}
