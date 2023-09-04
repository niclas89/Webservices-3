package com.larsson.sushi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne()
    private Dish dish;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="order_number")
    @JsonIgnoreProperties("items")
    private Order order;




    @Column(name = "qty")
    private int quantity;


    public Item() {
    }

    public Item( Dish dish, int quantity,Order order) {
        this.order = order;
        this.dish = dish;
        this.quantity = quantity;
    }





    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
