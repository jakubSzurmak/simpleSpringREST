package pl.runnerData.shoe.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.runnerData.shoe.dto.GetShoeResponse;
import pl.runnerData.shoe.dto.GetShoesResponse;
import pl.runnerData.shoe.dto.PutShoeRequest;

import java.util.UUID;

public interface ShoeController {

    @GetMapping("api/shoes")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetShoesResponse getShoes();

    @GetMapping("api/shoes/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetShoeResponse getShoe(
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

    @PutMapping("api/runners/{id}/shoes/")
    @ResponseStatus(HttpStatus.CREATED)
    void putShoe(
            @PathVariable("id")
            UUID id,
            @RequestBody
            PutShoeRequest request
    );

    @DeleteMapping("api/shoes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteShoe(
            @PathVariable("id")
            UUID id
    );
}
