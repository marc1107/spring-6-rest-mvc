package marc.springframework.spring6restmvc.controller;

import marc.springframework.spring6restmvc.entities.Beer;
import marc.springframework.spring6restmvc.model.BeerDTO;
import marc.springframework.spring6restmvc.repositories.BeerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class BeerControllerIT
{
    @Autowired
    BeerController beerController;

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testBeerIdNotFound()
    {
        assertThrows(NotFoundException.class, () -> beerController.getBeerById(UUID.randomUUID()));
    }

    @Test
    void testGetById()
    {
        Beer beer = beerRepository.findAll().getFirst();

        BeerDTO dto = beerController.getBeerById(beer.getId());

        assertThat(dto).isNotNull();
    }

    @Test
    void testListBeers()
    {
        List<BeerDTO> dtos = beerController.listBeers();

        assertThat(dtos.size()).isEqualTo(3);
    }

    @Rollback
    @Transactional
    @Test
    void testEmptyList()
    {
        beerRepository.deleteAll();
        List<BeerDTO> dtos = beerController.listBeers();

        assertThat(dtos.size()).isEqualTo(0);
    }
}