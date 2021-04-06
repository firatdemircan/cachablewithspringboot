package com.cachable.demo.controller;


import com.cachable.demo.models.Customer;
import com.cachable.demo.services.CustomerService;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerContoller {


    private CustomerService customerService;
    final CacheManager cacheManager;

    public CustomerContoller(CustomerService customerService, CacheManager cacheManager) {
        this.customerService = customerService;
        this.cacheManager = cacheManager;
    }

    @PostMapping("/insert")
    public Map<String, Customer> insert(@RequestBody Customer customer){
        Map<String,Customer> customerMap = new HashMap<>();
        customerService.saveCustomer(customer);
        customerMap.put("Result",customer);

        //we have to fetch our cache again after the insert
        Runnable rn =() ->{
            cacheClear("listcustomer");
            String url="http://localhost:8080/customer/customers";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getForObject(url,String.class);
        };
        //fetching is finish in here, we run it in runnable so that we dont be wait when inserting a column

        return customerMap;
    }

    @GetMapping("/customers")
    @Cacheable("listcustomer")
    public List<Customer> getCustomers() throws InterruptedException {
        Map<String,Customer> customerMap = new HashMap<>();

        List<Customer> ls = customerService.getCustomers();

        Thread.sleep(3000);


        for (Customer l : ls) {
            customerMap.put("Result",l);
        }

        return ls;
    }

    public void cacheClear(String cacheName){
        cacheManager.getCache(cacheName).clear();
    }


}
