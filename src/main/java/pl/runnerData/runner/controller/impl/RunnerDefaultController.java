package pl.runnerData.runner.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.runnerData.runner.function.RequestToRunnerFunction;
import pl.runnerData.runner.controller.api.RunnerController;
import pl.runnerData.runner.dto.GetRunnerResponse;
import pl.runnerData.runner.dto.GetRunnersResponse;
import pl.runnerData.runner.dto.PutRunnerRequest;
import pl.runnerData.runner.function.RunnerToResponseFunction;
import pl.runnerData.runner.function.RunnersToResponseFunction;
import pl.runnerData.shoe.function.ShoesToResponseFunction;
import pl.runnerData.runner.service.DefaultRunnerService;
import pl.runnerData.shoe.dto.GetShoesResponse;

import java.util.UUID;

@RestController
public class RunnerDefaultController implements RunnerController {

    private final DefaultRunnerService service;

    private final RunnerToResponseFunction runnerToResponse;

    private final RunnersToResponseFunction runnersToResponse;

    private final RequestToRunnerFunction requestToRunner;

    private final ShoesToResponseFunction shoesToResponseFunction;

    @Autowired
    public RunnerDefaultController(
            DefaultRunnerService service,
            RunnerToResponseFunction runnerToResponse,
            RunnersToResponseFunction runnersToResponse,
            RequestToRunnerFunction requestToRunner,
            ShoesToResponseFunction shoesToResponse
                                   ){
        this.service = service;
        this.runnerToResponse = runnerToResponse;
        this.runnersToResponse = runnersToResponse;
        this.requestToRunner = requestToRunner;
        this.shoesToResponseFunction = shoesToResponse;
    }

    @Override
    public GetRunnersResponse getRunners() {
        return runnersToResponse.apply(service.findAll());
    }


    @Override
    public GetRunnerResponse getRunner(UUID id) {
        return service.findById(id).map(runnerToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public GetShoesResponse findRunnerShoes(UUID id) {
        return shoesToResponseFunction.apply(service.findById(id).get().getShoes());
    }

    @Override
    public void putRunner(UUID id, PutRunnerRequest request) {
        service.create(requestToRunner.apply(id, request));
    }

    @Override
    public void deleteRunner(UUID id) {
        service.findById(id)
                .ifPresentOrElse(
                        runner -> service.delete(id),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        }
                );
    }
}