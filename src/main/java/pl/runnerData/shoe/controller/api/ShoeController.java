package pl.runnerData.shoe.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
    @ResponseStatus
    @ResponseBody
    GetShoeResponse getShoe(
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
