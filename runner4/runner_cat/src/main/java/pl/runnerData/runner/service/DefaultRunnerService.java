package pl.runnerData.runner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.runnerData.runner.entity.Runner;
import pl.runnerData.runner.event.repository.api.RunnerEventRepository;
import pl.runnerData.runner.repository.RunnerBackupRepository;
import pl.runnerData.runner.repository.RunnerRepository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultRunnerService {
    private final RunnerRepository runnerRepository;
    private final RunnerEventRepository eventRepository;
    private final RunnerBackupRepository runnerBackupRepository;

    @Autowired
    public DefaultRunnerService(RunnerRepository runnerRepository,
                                RunnerEventRepository eventRepository,
                                RunnerBackupRepository runnerBackupRepository) {
        this.runnerRepository = runnerRepository;
        this.eventRepository = eventRepository;
        this.runnerBackupRepository = runnerBackupRepository;
    }
    public List<Runner> findAll(){
        return runnerRepository.findAll();
    }

    public Optional<Runner> findById(UUID id){
        return runnerRepository.findById(id);
    }


    public void create(Runner runner){
        runnerRepository.save(runner);
        runnerBackupRepository.insert(runner);
        eventRepository.createUpdate(runner);
    }

    public void delete(UUID id){
        runnerRepository.findById(id).ifPresent(runnerRepository::delete);
        eventRepository.delete(id);
    }
}


