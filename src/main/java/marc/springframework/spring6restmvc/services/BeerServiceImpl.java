package marc.springframework.spring6restmvc.services;

import lombok.extern.slf4j.Slf4j;
import marc.springframework.spring6restmvc.model.BeerDTO;
import marc.springframework.spring6restmvc.model.BeerStyle;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService
{
    private final Map<UUID, BeerDTO> beerMap;

    public BeerServiceImpl()
    {
        this.beerMap = new HashMap<>();

        BeerDTO beer1 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("123456")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        BeerDTO beer2 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Crank")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356222")
                .price(new BigDecimal("11.99"))
                .quantityOnHand(392)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        BeerDTO beer3 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Sunshine City")
                .beerStyle(BeerStyle.IPA)
                .upc("123456")
                .price(new BigDecimal("13.99"))
                .quantityOnHand(144)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        beerMap.put(beer1.getId(), beer1);
        beerMap.put(beer2.getId(), beer2);
        beerMap.put(beer3.getId(), beer3);
    }

    @Override
    public Optional<BeerDTO> patchBeerById(UUID beerId, BeerDTO beer)
    {
        BeerDTO existing = beerMap.get(beerId);

        if (beer.getBeerName() != null) existing.setBeerName(beer.getBeerName());

        if (beer.getBeerStyle() != null) existing.setBeerStyle(beer.getBeerStyle());

        if (beer.getUpc() != null) existing.setUpc(beer.getUpc());

        if (beer.getPrice() != null) existing.setPrice(beer.getPrice());

        if (beer.getQuantityOnHand() != null) existing.setQuantityOnHand(beer.getQuantityOnHand());

        existing.setUpdateDate(LocalDateTime.now());

        return Optional.of(existing);
    }

    @Override
    public Boolean deleteById(UUID beerId)
    {
        beerMap.remove(beerId);

        return true;
    }

    @Override
    public Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer)
    {
        BeerDTO existing = beerMap.get(beerId);

        existing.setBeerName(beer.getBeerName());
        existing.setBeerStyle(beer.getBeerStyle());
        existing.setUpc(beer.getUpc());
        existing.setPrice(beer.getPrice());
        existing.setQuantityOnHand(beer.getQuantityOnHand());
        existing.setUpdateDate(LocalDateTime.now());

        return Optional.of(existing);
    }

    @Override
    public List<BeerDTO> listBeers()
    {
        return new ArrayList<>(beerMap.values());
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id)
    {
        log.debug("Get Beer Id - in service. Id: " + id.toString());

        return Optional.of(beerMap.get(id));
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer)
    {
        BeerDTO savedBeer = BeerDTO.builder()
                .id(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .version(1)
                .beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle())
                .upc(beer.getUpc())
                .price(beer.getPrice())
                .quantityOnHand(beer.getQuantityOnHand())
                .build();

        beerMap.put(savedBeer.getId(), savedBeer);

        return savedBeer;
    }
}
