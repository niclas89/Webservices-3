package com.larsson.sushi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.larsson.sushi.security.ConversionHandler;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CollectionId;

import java.awt.event.ItemEvent;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private Customer customer;

    @Column(name = "total_price_sek")
    private BigDecimal totalPriceSek;

    @Column(name ="total_price_euro" )
    private BigDecimal totalPriceEuro;

    @OneToMany(mappedBy = "order", cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    //@Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @JsonIgnoreProperties("order")
    private List<Item> items;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getTotalPriceSek() {
        return totalPriceSek;
    }

    public void setTotalPriceSek(BigDecimal totalPriceSek) {
        this.totalPriceSek = totalPriceSek;

    }

    public BigDecimal getTotalPriceEuro() {
        return totalPriceEuro;
    }

    public void setTotalPriceEuro(BigDecimal totalPriceEuro) {
        this.totalPriceEuro = totalPriceEuro;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;

        for(Item item:items){
            item.setOrder(this);
        }
        updateTotalPrice();
    }

    public void updateTotalPrice(){
        BigDecimal price = new BigDecimal(0);
        int temp = 0 ;
        for(Item item:items){
           temp += item.getDish().getPrice()*item.getQuantity();
        }
        price = BigDecimal.valueOf(temp);
        setTotalPriceSek(price);
        ConversionHandler handler = ConversionHandler.getInstance();
        BigDecimal conversion = handler.getRate("SEK");
        System.out.println("Conversion = " + conversion);
        BigDecimal euro = this.totalPriceSek.divide(conversion,MathContext.DECIMAL32);
        System.out.println("euro = " + euro);
        this.totalPriceEuro = (euro.setScale(0, RoundingMode.UP));
        System.out.println(price);


    }


}
