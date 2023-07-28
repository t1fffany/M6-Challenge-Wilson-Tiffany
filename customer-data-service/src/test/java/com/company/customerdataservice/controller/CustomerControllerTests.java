package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(CustomerController.class)
public class CustomerControllerTests {

    @MockBean
    private CustomerRepository customerRepository;
    @Autowired
    private MockMvc mockMvc;


    // Testing get /customer/state/{state}
    @Test
    public void testByState() throws Exception {
        // ARRANGE
        mockMvc.perform(
                        get("/customer/state/California")   // Perform the GET request
                                .contentType(MediaType.APPLICATION_JSON))   // Tell the server it's in JSON format
                .andDo(print()) // Print results to console
                .andExpect(status().isOk());
    }

    // Testing get /customers/{id}
    @Test
    public void testGetById() throws Exception {

        // ACT /customers/{id}
        mockMvc.perform(
                        get("/customers/1")   // Perform the GET request
                                .contentType(MediaType.APPLICATION_JSON))   // Tell the server it's in JSON format
                .andDo(print()) // Print results to console
                .andExpect(status().isOk());
    }



    // Testing POST /customer
    @Test
    public void newCustomerTest() throws Exception {
        //ARRANGE
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

        Mockito.when(customerRepository.save(Mockito.any(Customer.class)))
                .thenReturn(customer);


        // ACT

        mockMvc.perform(
                        post("/customer")               // Perform the POST request
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())                          // Print results to console
                .andExpect(status().isCreated());             // ASSERT (status code is 201)

    }

    // Testing Put /customer
    @Test
    public void updateCustomerTest() throws Exception {
        //ARRANGE
        Customer customer = new Customer();
        customer.setFirstName("Boo");
        customer.setLastName("Doe");
        customer.setAddress1("10 Green St");
        customer.setAddress2("#2");
        customer.setCity("San Francisco");
        customer.setState("California");
        customer.setPostalCode(94123);
        customer.setCountry("USA");
        customer.setPhone("415-555-0089");
        customer.setEmail("johnDoe@me.com");
        customer.setCompany("Amazon");

        Mockito.when(customerRepository.save(Mockito.any(Customer.class)))
                .thenReturn(customer);


        // ACT
        mockMvc.perform(
                        put("/customer/update/1")               // Perform the PUT request
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())                          // Print results to console
                .andExpect(status().isNoContent());             // ASSERT (status code is 204)

    }

    // Testing delete /customer
    @Test
    public void deleteCustomerTest() throws Exception {
        // ACT
        mockMvc.perform(
                        delete("/customer/1")               // Perform the delete request
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())                          // Print results to console
                .andExpect(status().isNoContent());             // ASSERT (status code is 204)

    }
}
