package marc.springframework.spring6restmvc.services;

import lombok.extern.slf4j.Slf4j;
import marc.springframework.spring6restmvc.model.Customer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
                .version(1)
                .customerName("Marc Bohner")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer customer2 = Customer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName("John Doe")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
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

    @Override
    public Customer saveNewCustomer(Customer customer)
    {
        Customer savedCustomer = Customer.builder()
                .id(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .version(1)
                .customerName(customer.getCustomerName())
                .build();

        customerMap.put(savedCustomer.getId(), savedCustomer);

        return savedCustomer;
    }

    @Override
    public void updateCustomerById(UUID customerId, Customer customer)
    {
        Customer existing = customerMap.get(customerId);

        existing.setCustomerName(customer.getCustomerName());
        existing.setLastModifiedDate(LocalDateTime.now());

        customerMap.put(existing.getId(), existing);
    }
}
