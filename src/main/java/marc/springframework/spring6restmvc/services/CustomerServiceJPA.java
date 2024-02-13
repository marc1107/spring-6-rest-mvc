package marc.springframework.spring6restmvc.services;

import lombok.RequiredArgsConstructor;
import marc.springframework.spring6restmvc.mappers.CustomerMapper;
import marc.springframework.spring6restmvc.model.CustomerDTO;
import marc.springframework.spring6restmvc.repositories.CustomerRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class CustomerServiceJPA implements CustomerService
{
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> listCustomers()
    {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id)
    {
        return Optional.ofNullable(customerMapper.customerToCustomerDto(customerRepository.findById(id)
                .orElse(null)));
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customer)
    {
        return customerMapper.customerToCustomerDto(customerRepository.save(customerMapper.customerDtoToCustomer(customer)));
    }

    @Override
    public void updateCustomerById(UUID customerId, CustomerDTO customer)
    {
        customerRepository.findById(customerId).ifPresent(foundCustomer ->
        {
            foundCustomer.setName(customer.getName());
            customerRepository.save(foundCustomer);
        });
    }

    @Override
    public void deleteById(UUID customerId)
    {

    }

    @Override
    public void patchCustomerById(UUID customerId, CustomerDTO customer)
    {

    }
}
