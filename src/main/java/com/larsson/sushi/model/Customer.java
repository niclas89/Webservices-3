package com.larsson.sushi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "name" ,nullable = false)
    private String name;

    @Column(name = "address",nullable = false)
    private String address;
    @Column(name = "user_name",nullable = false)
    private String userName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Customer() {
    }

    public Customer(Long id, String name, String address, String userName) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.userName = userName;
    }

}
