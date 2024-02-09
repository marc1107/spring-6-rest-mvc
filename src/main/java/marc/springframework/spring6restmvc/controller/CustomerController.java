package marc.springframework.spring6restmvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marc.springframework.spring6restmvc.model.Customer;
import marc.springframework.spring6restmvc.services.CustomerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController
{
    private final CustomerService customerService;

    @PatchMapping("/{customerId}")
    public ResponseEntity handlePatch(@PathVariable UUID customerId, @RequestBody Customer customer)
    {
        customerService.patchCustomerById(customerId, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity deleteById(@PathVariable UUID customerId)
    {
        customerService.deleteById(customerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity updateById(@PathVariable UUID customerId, @RequestBody Customer customer)
    {
        customerService.updateCustomerById(customerId, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity handlePost(@RequestBody Customer customer)
    {
        Customer savedCustomer = customerService.saveNewCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + savedCustomer.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> listCustomers()
    {
        return customerService.listCustomers();
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable UUID customerId)
    {
        log.debug("Get Customer by Id - in controller");

        return customerService.getCustomerById(customerId);
    }
}
