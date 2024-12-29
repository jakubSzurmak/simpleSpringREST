package pl.runnerData.runner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.runnerData.runner.entity.Runner;

import java.util.UUID;

@Repository
public interface RunnerRepository extends JpaRepository<Runner, UUID> { }
