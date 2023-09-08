package com.larsson.sushi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.larsson.sushi.conversion.ConversionHandler;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
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
        //BigDecimal price = new BigDecimal(0);
        int temp = 0 ;
        for(Item item:items){
           temp += item.getDish().getPrice()*item.getQuantity();
        }
        //price = BigDecimal.valueOf(temp);
        setTotalPriceSek(BigDecimal.valueOf(temp));
        ConversionHandler handler = ConversionHandler.getInstance();
        //BigDecimal conversion = handler.getRate("SEK");
        BigDecimal conversion = ConversionHandler.getInstance().getRate("SEK");
        BigDecimal euro = this.totalPriceSek.divide(conversion,MathContext.DECIMAL32);
        this.totalPriceEuro = (euro.setScale(0, RoundingMode.UP));



    }


}
