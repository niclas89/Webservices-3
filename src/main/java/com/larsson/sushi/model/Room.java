package com.larsson.sushi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    String name;

    @Column(name = "info",nullable = false)
    private String description;

    @Column(name = "max_guest",nullable = false)
    private int maxGuest;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxGuest() {
        return maxGuest;
    }

    public void setMaxGuest(int maxGuest) {
        this.maxGuest = maxGuest;
    }

    public Room(Long id, String name, String description, int maxGuest) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.maxGuest = maxGuest;
    }

    public Room() {
    }
}
