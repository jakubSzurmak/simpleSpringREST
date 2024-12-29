package pl.runnerData.runner.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.runnerData.runner.dto.GetRunnerResponse;
import pl.runnerData.runner.dto.GetRunnersResponse;
import pl.runnerData.runner.dto.PutRunnerRequest;
import pl.runnerData.shoe.dto.GetShoesResponse;

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

    @GetMapping("api/runners/{id}/shoes")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetShoesResponse findRunnerShoes(
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


