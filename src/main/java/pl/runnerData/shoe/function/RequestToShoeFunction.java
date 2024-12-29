package pl.runnerData.shoe.function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import pl.runnerData.runner.entity.Runner;
import pl.runnerData.runner.repository.RunnerRepository;
import pl.runnerData.runner.service.DefaultRunnerService;
import pl.runnerData.shoe.dto.PutShoeRequest;
import pl.runnerData.shoe.entity.Shoe;

import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class RequestToShoeFunction implements BiFunction<UUID, PutShoeRequest, Shoe> {
    private final RunnerRepository runnerRepository;

    @Autowired
    public RequestToShoeFunction(RunnerRepository runnerRepository) {
        this.runnerRepository = runnerRepository;
    }

    @Override
    public Shoe apply(UUID uuid, PutShoeRequest request) {


        return Shoe.builder()
                .shoe_id(UUID.randomUUID())
                .brandName(request.getBrandName())
                .model(request.getModel())
                .color(request.getColor())
                .size(request.getSize())
                .owner(runnerRepository.findById(uuid).get())
                .build();

    }
}
