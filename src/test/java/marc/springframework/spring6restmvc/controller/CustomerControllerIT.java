package marc.springframework.spring6restmvc.controller;

import marc.springframework.spring6restmvc.model.CustomerDTO;
import marc.springframework.spring6restmvc.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CustomerControllerIT
{
    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;

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