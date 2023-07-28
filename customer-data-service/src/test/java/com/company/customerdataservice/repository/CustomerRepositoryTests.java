package com.company.customerdataservice.repository;

import com.company.customerdataservice.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerRepositoryTests {
        @Autowired
        CustomerRepository customerRepository;

        @BeforeEach
        public void setUp() throws Exception {
            customerRepository.deleteAll();
        }

        @Test
        public void shouldAddArtist() {

            //Arrange...
            Customer customer = new Customer();
            customer.setFirstName("John");
            customer.setLastName("Doe");
            customer.setAddress1("10 Green St");
            customer.setAddress2("#2");
            customer.setCity("San Francisco");
            customer.setState("California");
            customer.setPostalCode(94123);
            customer.setCountry("USA");
            customer.setPhone("415-555-0089");
            customer.setEmail("johnDoe@me.com");
            customer.setCompany("Netflix");


            //Act...
            customer = customerRepository.save(customer);

            Optional<Customer> customer1 = customerRepository.findById(customer.getId());

            //Assert...
            assertEquals(customer1.get(), customer);
        }

        @Test
        public void shouldGetCustomerById() {

            //Arrange...
            // Need to create a Label and an Artist first
            Customer customer = new Customer();
            customer.setFirstName("John");
            customer.setLastName("Doe");
            customer.setAddress1("10 Green St");
            customer.setAddress2("#2");
            customer.setCity("San Francisco");
            customer.setState("California");
            customer.setPostalCode(94123);
            customer.setCountry("USA");
            customer.setPhone("415-555-0089");
            customer.setEmail("johnDoe@me.com");
            customer.setCompany("Netflix");
            customer = customerRepository.save(customer);

            Customer customer2 = new Customer();
            customer2.setFirstName("Janet");
            customer2.setLastName("Johnson");
            customer2.setAddress1("8080 Road Ave");
            customer2.setAddress2("");
            customer2.setCity("Verbena");
            customer2.setState("Alabama");
            customer2.setPostalCode(36091);
            customer2.setCountry("USA");
            customer2.setPhone("205-555-8899");
            customer2.setEmail("JanetJ@gmail.com");
            customer2.setCompany("Google");
            customer2 = customerRepository.save(customer2);

            //Act...
            Optional<Customer> foundArtist = customerRepository.findById(customer.getId());

            //Assert...
            assertEquals(foundArtist.get(), customer);
        }

        @Test
        public void shouldUpdateCustomer() {

            //Arrange...
            Customer customer = new Customer();
            customer.setFirstName("John");
            customer.setLastName("Doe");
            customer.setAddress1("10 Green St");
            customer.setAddress2("#2");
            customer.setCity("San Francisco");
            customer.setState("California");
            customer.setPostalCode(94123);
            customer.setCountry("USA");
            customer.setPhone("415-555-0089");
            customer.setEmail("johnDoe@me.com");
            customer.setCompany("Netflix");

            customer = customerRepository.save(customer);

            customer.setFirstName("Janet");
            customer.setLastName("Johnson");
            customer.setAddress1("8080 Road Ave");
            customer.setAddress2("");
            customer.setCity("Verbena");
            customer.setState("Alabama");
            customer.setPostalCode(36091);
            customer.setCountry("USA");
            customer.setPhone("205-555-8899");
            customer.setEmail("JanetJ@gmail.com");
            customer.setCompany("Google");

            customer = customerRepository.save(customer);

            //Act...
            Optional<Customer> customer1 = customerRepository.findById(customer.getId());

            //Assert...
            assertEquals(customer1.get(), customer);
        }

        @Test
        public void shouldGetAllCustomersFromState() {

            //Arrange...
            Customer customer = new Customer();
            customer.setFirstName("John");
            customer.setLastName("Doe");
            customer.setAddress1("10 Green St");
            customer.setAddress2("#2");
            customer.setCity("San Francisco");
            customer.setState("California");
            customer.setPostalCode(94123);
            customer.setCountry("USA");
            customer.setPhone("415-555-0089");
            customer.setEmail("johnDoe@me.com");
            customer.setCompany("Netflix");

            customer = customerRepository.save(customer);

            Customer customer2 = new Customer();
            customer2.setFirstName("Janet");
            customer2.setLastName("Johnson");
            customer2.setAddress1("8080 Road Ave");
            customer2.setAddress2("");
            customer2.setCity("Verbena");
            customer2.setState("Alabama");
            customer2.setPostalCode(36091);
            customer2.setCountry("USA");
            customer2.setPhone("205-555-8899");
            customer2.setEmail("JanetJ@gmail.com");
            customer.setCompany("Google");

            customer2 = customerRepository.save(customer2);

            //Act...
            List<Customer> aList = customerRepository.findByState("California");

            //Assert...
            assertEquals(aList.size(), 1);
        }

        @Test
        public void shouldDeleteArtistById() {

            //Arrange...
            Customer customer = new Customer();
            customer.setFirstName("John");
            customer.setLastName("Doe");
            customer.setAddress1("10 Green St");
            customer.setAddress2("#2");
            customer.setCity("San Francisco");
            customer.setState("California");
            customer.setPostalCode(94123);
            customer.setCountry("USA");
            customer.setPhone("415-555-0089");
            customer.setEmail("johnDoe@me.com");
            customer.setCompany("Netflix");

            customer = customerRepository.save(customer);

            Optional<Customer> customer1 = customerRepository.findById(customer.getId());

            assertEquals(customer1.get(), customer);

            //Act...
            customerRepository.deleteById(customer.getId());

            customer1 = customerRepository.findById(customer.getId());

            //Assert...
            assertFalse(customer1.isPresent());
        }
}
