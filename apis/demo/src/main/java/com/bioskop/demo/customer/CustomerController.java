package com.bioskop.demo.customer;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.bioskop.demo.DemoResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CustomerController {
  @Autowired
  CustomerRepository customerRepository;

  @GetMapping(path = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Customer>> findAll() {
    return ResponseEntity.ok(customerRepository.findAll());
  }

  @PostMapping("/customer")
  public Customer createNote(@Valid @RequestBody Customer customer) {
    return customerRepository.save(customer);
  }

  @PutMapping("/customer")
  public Customer updateCustomer(@PathVariable(value = "id") Long customerId,
      @Valid @RequestBody Customer customerBody) {
    Customer customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new DemoResourceNotFoundException("Customer", "id", customerId));

    return customerRepository.save(customer);
  }

  @GetMapping("/customer/{id}")
  public Optional<Customer> getNoteById(@PathVariable(value = "id") Long noteId) {
    return customerRepository.findById(noteId);
  }

  @DeleteMapping("/customer/{id}")
  public ResponseEntity<Customer> deleteNote(@PathVariable(value = "id") Long customerId) {
    Customer customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new DemoResourceNotFoundException("Customer", "id", customerId));

    customerRepository.delete(customer);

    return ResponseEntity.ok().build();
  }
}