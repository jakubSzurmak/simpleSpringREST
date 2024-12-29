package pl.runnerData.shoe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.runnerData.shoe.entity.Shoe;
import pl.runnerData.shoe.repository.ShoeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultShoeService {
    private final ShoeRepository shoeRepository;


    @Autowired
    public DefaultShoeService(ShoeRepository shoeRepository) {
        this.shoeRepository = shoeRepository;
    }

    public List<Shoe> findAll() {
        return shoeRepository.findAll();
    }


    public Optional<Shoe> findById(UUID id){
        return shoeRepository.findById(id);
    }

    public void create(Shoe shoe) {
        shoeRepository.save(shoe);
    }

    public void delete(UUID id){
        shoeRepository.findById(id).ifPresent(shoeRepository::delete);
    }

}
