package com.company.customerdataservice.Repository;

import com.company.customerdataservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

    @Repository
    public interface CustomerRepository extends JpaRepository<Customer, Integer> {



            List<Customer> findByTitle(String albumTitle);

    }
