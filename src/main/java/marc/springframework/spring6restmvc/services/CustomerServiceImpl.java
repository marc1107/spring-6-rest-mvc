package marc.springframework.spring6restmvc.services;

import lombok.extern.slf4j.Slf4j;
import marc.springframework.spring6restmvc.model.Customer;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService
{
    private final Map<UUID, Customer> customerMap;

    public CustomerServiceImpl()
    {
        this.customerMap = new HashMap<>();

        Customer customer1 = Customer.builder()
                .id(UUID.randomUUID())
                .firstName("Marc")
                .lastName("Bohner")
                .build();

        Customer customer2 = Customer.builder()
                .id(UUID.randomUUID())
                .firstName("John")
                .lastName("Doe")
                .build();

        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
    }

    @Override
    public List<Customer> listCustomers()
    {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer getCustomerById(UUID id)
    {
        log.debug("Get Customer Id - in service. Id: " + id.toString());
        return customerMap.get(id);
    }
}
