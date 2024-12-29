package pl.runnerData.runner.event.repository.api;

import pl.runnerData.runner.entity.Runner;

import java.util.UUID;


public interface RunnerEventRepository {
    void delete(UUID id);
    void createUpdate(Runner entity);
}
