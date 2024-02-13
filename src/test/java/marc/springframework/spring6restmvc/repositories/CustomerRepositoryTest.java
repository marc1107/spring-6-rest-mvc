package marc.springframework.spring6restmvc.repositories;

import marc.springframework.spring6restmvc.entities.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CustomerRepositoryTest
{
    @Autowired
    CustomerRepository customerRepository;

    @Test
    void testSaveCustomer()
    {
        Customer savedCustomer = customerRepository.save(Customer.builder().name("Marc").build());

        assertThat(savedCustomer).isNotNull();
        assertThat(savedCustomer.getId()).isNotNull();
    }
}