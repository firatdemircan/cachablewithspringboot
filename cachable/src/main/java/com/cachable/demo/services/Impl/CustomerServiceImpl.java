package com.cachable.demo.services.Impl;

import com.cachable.demo.models.Customer;
import com.cachable.demo.repostory.UserRepostory;
import com.cachable.demo.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    UserRepostory userRepostory;

    public CustomerServiceImpl(UserRepostory userRepostory) {
        this.userRepostory = userRepostory;
    }

    @Override
    public List<Customer> getCustomers() {
        return userRepostory.findAll();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return userRepostory.save(customer);
    }
}
