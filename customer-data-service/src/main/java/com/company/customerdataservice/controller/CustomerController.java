package com.company.customerdataservice.controller;

import com.company.customerdataservice.repository.CustomerRepository;
import com.company.customerdataservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {
        @Autowired
        CustomerRepository repo;

    //    Find a customer record by id.
        @GetMapping("/customers/{id}")
        @ResponseStatus(HttpStatus.OK)
        public Customer getCustomerById(@PathVariable int id) {

            Optional<Customer> returnVal = repo.findById(id);
            if (returnVal.isPresent()) {
                return returnVal.get();
            } else {
                return null;
            }
        }

    //    Find customer records by state.
        @GetMapping("/customer/state/{state}")
        @ResponseStatus(HttpStatus.OK)
        public List<Customer> getCustomerByState(@PathVariable String state) {

            return repo.findByState(state);
        }
    //    Create a new customer record.
        @PostMapping("/customer")
        @ResponseStatus(HttpStatus.CREATED)
        public Customer addCustomer(@RequestBody Customer customer) {
            return repo.save(customer);
        }


    //    Update an existing customer record.
        @PutMapping("/customer/update/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void updateCustomer(@RequestBody Customer customer) {
            repo.save(customer);
        }


    //    Delete an existing customer record.
        @DeleteMapping("/customer/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteCustomer(@PathVariable int id) {
            repo.deleteById(id);
        }
}
