package marc.springframework.spring6restmvc.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marc.springframework.spring6restmvc.model.Beer;
import marc.springframework.spring6restmvc.services.BeerService;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Controller
public class BeerController
{
    private final BeerService beerService;

    public Beer getBeerById(UUID id)
    {
        log.debug("Get Beer by Id - in controller");

        return beerService.getBeerById(id);
    }
}
