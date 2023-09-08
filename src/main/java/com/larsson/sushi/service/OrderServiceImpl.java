package com.larsson.sushi.service;


import com.larsson.sushi.model.Order;
import com.larsson.sushi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository repository;



    @Override
    public Order getOrder(Long id) {
        Optional<Order> order = repository.findById(id);
        order.get().updateTotalPrice();
        return order.get();
    }




}
