package com.larsson.sushi.service;


import com.larsson.sushi.model.Order;
import com.larsson.sushi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ItemServiceImpl itemService;


    private RestTemplate restTemplate = new RestTemplate();

    private MultiValueMap<String, String> convertRequest;

    @Override
    public Order getOrder(Long id) {


        Optional<Order> order = repository.findById(id);
        order.get().updateTotalPrice();
        /*
        if(order.isPresent()) {
            Order presentOrder = order.get();
            try {
                JsonNode response = restTemplate.getForObject("http://api.exchangeratesapi.io/v1/latest?access_key=74563b664fec86e81170225d315415bf&base=EUR&symbols=SEK", JsonNode.class);
                String amount = String.valueOf(response.get("rates").get("SEK"));
                BigDecimal sek = presentOrder.getTotalPriceSek();
                BigDecimal conversion = new BigDecimal(amount);
                BigDecimal euro = sek.divide(conversion, MathContext.DECIMAL32);
                //euro = sek.divide(conversion, MathContext.DECIMAL32);
                presentOrder.setTotalPriceEuro(euro.setScale(0, RoundingMode.UP));


            } catch (Exception exception) {
                exception.printStackTrace();
            }
            //order.get().setItems((ArrayList<Item>) itemService.getAll(order.get().getId()));
            return presentOrder;
        }else
            throw new NoSuchElementException();

         */

        return order.get();
    }




}
