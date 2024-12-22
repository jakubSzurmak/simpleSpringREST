package pl.runnerData.shoe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.runnerData.runner.entity.Runner;
import pl.runnerData.shoe.entity.Shoe;
import pl.runnerData.shoe.repository.ShoeRepository;

import java.util.List;
import java.util.UUID;

@Service
public class DefaultShoeService {
    private final ShoeRepository shoeRepository;
    //private final RunnerRepository runnerRepository;

    @Autowired
    //public DefaultShoeService(ShoeRepository shoeRepository, RunnerRepository runnerRepository) {
    public DefaultShoeService(ShoeRepository shoeRepository, ShoeRepository runnerRepository) {
        this.shoeRepository = shoeRepository;
    }

    public List<Shoe> findAll(){
        return shoeRepository.findAll();
    }

    public List<Shoe> findAllByOwner(Runner owner){
        return shoeRepository.findAllByOwner(owner);
    }

    public void create(Shoe shoe) {
        shoeRepository.save(shoe);
    }

    public void delete(UUID id){
        shoeRepository.findById(id).ifPresent(shoeRepository::delete);
    }

}
