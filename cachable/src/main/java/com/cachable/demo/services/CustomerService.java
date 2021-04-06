package com.cachable.demo.services;

import com.cachable.demo.models.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    List<Customer> getCustomers();

    Customer saveCustomer(Customer customer);

}
