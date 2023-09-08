package com.larsson.sushi.security;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;



// authorities entity used by Spring-security to validate if user has access to resource
@Entity
@Table(name = "authorities")
public class Authorities {

    @Id
    private String userName;

    private String authority;

    public Authorities(String userName, String authority) {
        this.userName = userName;
        this.authority = authority;
    }

    public Authorities() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
