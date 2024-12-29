package pl.runnerData.runner.function;

import org.springframework.stereotype.Component;
import pl.runnerData.runner.dto.PutRunnerRequest;
import pl.runnerData.runner.entity.Runner;

import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class RequestToRunnerFunction implements BiFunction <UUID, PutRunnerRequest, Runner>{
    @Override
    public Runner apply(UUID id, PutRunnerRequest request) {
        return Runner.builder()
                .id(id)
                .name(request.getName())
                .surname(request.getSurname())
                .birthDate(request.getBirthDate())
                .build();
    }
}
