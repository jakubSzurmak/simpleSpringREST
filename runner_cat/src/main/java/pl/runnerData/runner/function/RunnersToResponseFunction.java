package pl.runnerData.runner.function;

import org.springframework.stereotype.Component;
import pl.runnerData.runner.dto.GetRunnersResponse;
import pl.runnerData.runner.entity.Runner;

import java.util.List;
import java.util.function.Function;

@Component
public class RunnersToResponseFunction implements Function<List<Runner>, GetRunnersResponse> {
    @Override
    public GetRunnersResponse apply(List<Runner> entities) {
        return GetRunnersResponse.builder()
                .runners(entities.stream()
                        .map(runner -> GetRunnersResponse.Runner.builder()
                                .id(runner.getId())
                                .name(runner.getName())
                                .surname(runner.getSurname())
                                .birthDate(runner.getBirthDate())
                                .build())
                        .toList())
                .build();
    }
}
