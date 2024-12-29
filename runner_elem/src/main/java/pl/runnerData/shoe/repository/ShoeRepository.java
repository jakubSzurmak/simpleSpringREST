package pl.runnerData.shoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.runnerData.shoe.entity.Shoe;

import java.util.List;
import java.util.UUID;

@Repository
public interface ShoeRepository extends JpaRepository<Shoe, UUID> {
    List<Shoe> findAllByOwnerId(UUID ownerId);
}
