package marc.springframework.spring6restmvc.repositories;

import marc.springframework.spring6restmvc.entities.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID>
{
    List<Beer> findAllByBeerNameLikeIgnoreCase(String beerName);
}
