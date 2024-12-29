package pl.runnerData.shoe.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.runnerData.shoe.controller.api.ShoeController;
import pl.runnerData.shoe.dto.GetShoeResponse;
import pl.runnerData.shoe.dto.GetShoesResponse;
import pl.runnerData.shoe.dto.PutShoeRequest;
import pl.runnerData.shoe.function.RequestToShoeFunction;
import pl.runnerData.shoe.function.ShoeToResponseFunction;
import pl.runnerData.shoe.function.ShoesToResponseFunction;
import pl.runnerData.shoe.service.DefaultShoeService;

import java.util.UUID;

@RestController
public class ShoeDefaultController implements ShoeController {
    private final DefaultShoeService service;
    private final ShoeToResponseFunction shoeToResponse;
    private final ShoesToResponseFunction shoesToResponse;
    private final RequestToShoeFunction requestToShoe;

    @Autowired
    public ShoeDefaultController(
            DefaultShoeService service,
            ShoeToResponseFunction shoeToResponse,
            ShoesToResponseFunction shoesToResponse,
            RequestToShoeFunction requestToShoe){
        this.service = service;
        this.shoeToResponse = shoeToResponse;
        this.shoesToResponse = shoesToResponse;
        this.requestToShoe = requestToShoe;
    }


    @Override
    public GetShoesResponse getShoes() {
        return shoesToResponse.apply(service.findAll()) ;
    }

    @Override
    public GetShoeResponse getShoe(UUID id) {
        return service.findById(id).map(shoeToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public GetShoesResponse findRunnerShoes(UUID id) {
        return shoesToResponse.apply(service.findAllByOwnerId(id));
    }

    @Override
    public void putShoe(UUID ownerId, PutShoeRequest request) {
        service.create(requestToShoe.apply(ownerId, request));
    }

    @Override
    public void deleteShoe(UUID id) {
        service.findById(id)
                .ifPresentOrElse(
                        shoe -> service.delete(id),
                        ()->{
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        }
                );
    }
}
