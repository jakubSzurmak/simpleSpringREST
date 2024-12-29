package pl.runnerData.runner.event.repository.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pl.runnerData.runner.entity.Runner;
import pl.runnerData.runner.event.repository.api.RunnerEventRepository;

import java.util.UUID;

@Repository
public class RunnerEventRestRepository implements RunnerEventRepository {
    private final RestTemplate restTemplate;

    @Autowired
    public RunnerEventRestRepository(RestTemplate template) {
        this.restTemplate = template;
    }

    @Override
    public void delete(UUID id){
        restTemplate.delete("/api/runners/{id}", id);
    }

    public void createUpdate(Runner entity) {
        restTemplate.put("/api/runners/{id}", entity, entity.getId());
        System.out.println("Runner entity: " + entity.getId());
    }
}
