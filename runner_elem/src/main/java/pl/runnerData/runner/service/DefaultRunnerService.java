package pl.runnerData.runner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.runnerData.runner.entity.Runner;
import pl.runnerData.runner.repository.RunnerRepository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultRunnerService {
    private final RunnerRepository runnerRepository;

    @Autowired
    public DefaultRunnerService(RunnerRepository runnerRepository) {
        this.runnerRepository = runnerRepository;
    }

    public List<Runner> findAll(){
        return runnerRepository.findAll();
    }

    public Optional<Runner> findById(UUID id){
        return runnerRepository.findById(id);
    }

    public void create(Runner runner){
        runnerRepository.save(runner);
    }

    public void delete(UUID id){
        runnerRepository.findById(id).ifPresent(runnerRepository::delete);
    }
}


