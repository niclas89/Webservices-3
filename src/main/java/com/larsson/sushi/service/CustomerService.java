package com.larsson.sushi.service;

import com.larsson.sushi.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CustomerService {

    List<Customer> getAllCustomers();
}
