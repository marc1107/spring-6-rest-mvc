package marc.springframework.spring6restmvc.services;

import lombok.extern.slf4j.Slf4j;
import marc.springframework.spring6restmvc.model.CustomerDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService
{
    private final Map<UUID, CustomerDTO> customerMap;

    public CustomerServiceImpl()
    {
        this.customerMap = new HashMap<>();

        CustomerDTO customer1 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .name("Marc Bohner")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        CustomerDTO customer2 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .name("John Doe")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
    }

    @Override
    public List<CustomerDTO> listCustomers()
    {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id)
    {
        log.debug("Get Customer Id - in service. Id: " + id.toString());
        return Optional.of(customerMap.get(id));
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customer)
    {
        CustomerDTO savedCustomer = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .version(1)
                .name(customer.getName())
                .build();

        customerMap.put(savedCustomer.getId(), savedCustomer);

        return savedCustomer;
    }

    @Override
    public void updateCustomerById(UUID customerId, CustomerDTO customer)
    {
        CustomerDTO existing = customerMap.get(customerId);

        existing.setName(customer.getName());
        existing.setUpdateDate(LocalDateTime.now());

        customerMap.put(existing.getId(), existing);
    }

    @Override
    public void deleteById(UUID customerId)
    {
        customerMap.remove(customerId);
    }

    @Override
    public void patchCustomerById(UUID customerId, CustomerDTO customer)
    {
        CustomerDTO existing = customerMap.get(customerId);

        if (customer.getName() != null) existing.setName(customer.getName());

        existing.setUpdateDate(LocalDateTime.now());

        customerMap.put(existing.getId(), existing);
    }
}
