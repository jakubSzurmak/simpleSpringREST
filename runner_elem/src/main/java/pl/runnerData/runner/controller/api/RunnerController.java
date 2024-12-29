package pl.runnerData.runner.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

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

    @PutMapping("api/runners/{id}")
    @ResponseStatus(HttpStatus.CREATED)
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


