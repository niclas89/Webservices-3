package com.larsson.sushi.service;

import com.larsson.sushi.model.Customer;
import com.larsson.sushi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository repository;
    @Override
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }
}
