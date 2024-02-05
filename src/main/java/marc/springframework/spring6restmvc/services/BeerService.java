package marc.springframework.spring6restmvc.services;

import marc.springframework.spring6restmvc.model.Beer;

import java.util.UUID;

public interface BeerService
{
    Beer getBeerById(UUID id);
}
