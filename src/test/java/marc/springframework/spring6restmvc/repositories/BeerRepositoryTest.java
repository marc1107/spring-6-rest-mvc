package marc.springframework.spring6restmvc.repositories;

import jakarta.validation.ConstraintViolationException;
import marc.springframework.spring6restmvc.entities.Beer;
import marc.springframework.spring6restmvc.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class BeerRepositoryTest
{
    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeerTooLong()
    {
        assertThrows(ConstraintViolationException.class, () -> {
            Beer savedBeer = beerRepository.save(Beer.builder()
                    .beerName("My Beer 934576345634295234905326453265230456834895341603q57q356348456786456785434567")
                    .beerStyle(BeerStyle.PALE_ALE)
                    .upc("1234567890")
                    .price(new java.math.BigDecimal("12.95"))
                    .build());

            beerRepository.flush();
        });
    }

    @Test
    void testSaveBeer()
    {
        Beer savedBeer = beerRepository.save(Beer.builder()
                .beerName("My Beer")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("1234567890")
                .price(new java.math.BigDecimal("12.95"))
                .build());

        beerRepository.flush();

        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
    }
}