package marc.springframework.spring6restmvc.services;

import lombok.RequiredArgsConstructor;
import marc.springframework.spring6restmvc.entities.Beer;
import marc.springframework.spring6restmvc.mappers.BeerMapper;
import marc.springframework.spring6restmvc.model.BeerDTO;
import marc.springframework.spring6restmvc.repositories.BeerRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class BeerServiceJPA implements BeerService
{
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public List<BeerDTO> listBeers(String beerName)
    {
        List<Beer> beerList;

        if(StringUtils.hasText(beerName))
        {
            beerList = listBeersByName(beerName);
        }
        else
        {
            beerList = beerRepository.findAll();
        }

        return beerList.stream()
                .map(beerMapper::beerToBeerDto)
                .collect(Collectors.toList());
    }

    public List<Beer> listBeersByName(String beerName)
    {
        return beerRepository.findAllByBeerNameLikeIgnoreCase("%" + beerName + "%");
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id)
    {
        return Optional.ofNullable(beerMapper.beerToBeerDto(beerRepository.findById(id)
                .orElse(null)));
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer)
    {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beer)));
    }

    @Override
    public Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer)
    {
        AtomicReference<Optional<BeerDTO>> atomicReference = new AtomicReference<>();

        beerRepository.findById(beerId).ifPresentOrElse(foundBeer ->
        {
            foundBeer.setBeerName(beer.getBeerName());
            foundBeer.setBeerStyle(beer.getBeerStyle());
            foundBeer.setPrice(beer.getPrice());
            foundBeer.setUpc(beer.getUpc());
            atomicReference.set(Optional.of(beerMapper
                    .beerToBeerDto(beerRepository.save(foundBeer))));
        }, () -> atomicReference.set(Optional.empty()));

        return atomicReference.get();
    }

    @Override
    public Boolean deleteById(UUID beerId)
    {
        if (beerRepository.existsById(beerId))
        {
            beerRepository.deleteById(beerId);
            return true;
        }
        return false;
    }

    @Override
    public Optional<BeerDTO> patchBeerById(UUID beerId, BeerDTO beer)
    {
        AtomicReference<Optional<BeerDTO>> atomicReference = new AtomicReference<>();

        beerRepository.findById(beerId).ifPresentOrElse(foundBeer ->
        {
            if (StringUtils.hasText(beer.getBeerName()))
                foundBeer.setBeerName(beer.getBeerName());
            if (beer.getBeerStyle() != null)
                foundBeer.setBeerStyle(beer.getBeerStyle());
            if (StringUtils.hasText(beer.getUpc()))
                foundBeer.setUpc(beer.getUpc());
            if (beer.getPrice() != null)
                foundBeer.setPrice(beer.getPrice());
            if (beer.getQuantityOnHand() != null)
                foundBeer.setQuantityOnHand(beer.getQuantityOnHand());
            atomicReference.set(Optional.of(beerMapper
                    .beerToBeerDto(beerRepository.save(foundBeer))));
        }, () -> atomicReference.set(Optional.empty()));

        return atomicReference.get();
    }
}
