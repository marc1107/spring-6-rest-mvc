package marc.springframework.spring6restmvc.controller;

import marc.springframework.spring6restmvc.entities.Customer;
import marc.springframework.spring6restmvc.model.CustomerDTO;
import marc.springframework.spring6restmvc.repositories.CustomerRepository;
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
class CustomerControllerIT
{
    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void testCustomerNotFound()
    {
        assertThrows(NotFoundException.class, () -> customerController.getCustomerById(UUID.randomUUID()));
    }

    @Test
    void testGetById()
    {
        Customer customer = customerRepository.findAll().getFirst();

        CustomerDTO dto = customerController.getCustomerById(customer.getId());

        assertThat(dto).isNotNull();
    }

    @Test
    void testListCustomers()
    {
        List<CustomerDTO> dtos = customerController.listCustomers();

        assertThat(dtos.size()).isEqualTo(3);
    }

    @Rollback
    @Transactional
    @Test
    void testEmptyList()
    {
        customerRepository.deleteAll();
        List<CustomerDTO> dtos = customerController.listCustomers();

        assertThat(dtos.size()).isEqualTo(0);
    }
}