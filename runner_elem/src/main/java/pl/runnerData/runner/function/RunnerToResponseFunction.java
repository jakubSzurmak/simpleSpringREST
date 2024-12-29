package pl.runnerData.runner.function;

import org.springframework.stereotype.Component;
import pl.runnerData.runner.dto.GetRunnerResponse;
import pl.runnerData.runner.entity.Runner;

import java.util.function.Function;

@Component
public class RunnerToResponseFunction implements Function <Runner, GetRunnerResponse>{

    @Override
    public GetRunnerResponse apply(Runner entity) {

        return GetRunnerResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .birthDate(entity.getBirthDate())
                .build();
    }
}
