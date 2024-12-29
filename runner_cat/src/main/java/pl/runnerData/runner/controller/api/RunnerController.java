package pl.runnerData.runner.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.runnerData.runner.dto.GetRunnerResponse;
import pl.runnerData.runner.dto.GetRunnersResponse;
import pl.runnerData.runner.dto.PutRunnerRequest;

import java.util.UUID;

public interface RunnerController {

    @GetMapping("api/runners")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetRunnersResponse getRunners();


    @GetMapping("api/runners/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetRunnerResponse getRunner(
            @PathVariable("id")
            UUID id
    );

    @PostMapping("api/runners/")
    @ResponseStatus(HttpStatus.CREATED)
    void postRunner(
            @RequestBody
            PutRunnerRequest request
    );

    @PutMapping("api/runners/{id}")
    @ResponseStatus(HttpStatus.OK)
    void putRunner(
            @PathVariable("id")
            UUID id,
            @RequestBody
            PutRunnerRequest request
    );


    @DeleteMapping("api/runners/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteRunner(
            @PathVariable("id")
            UUID id
    );

}


