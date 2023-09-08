package com.larsson.sushi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig  {


    // databas anslutning
    @Autowired
    private DataSource dataSource;


    // Säger åt Spring Security att det finns tabels i databasen enligt Spring securitys spefikationer,
    // där den kan leta efter Users och Authorities med sina standard querries.
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/item/{id}", "/order/{id}", "/mybookings/{id}","/sushis", "/newbooking", "/updatebooking").hasRole("USER")
                .requestMatchers("/customers", "/add-dish", "/deletedish", "/updateroom").hasRole("ADMIN")
                        .anyRequest()
                                .authenticated();


        http.httpBasic();




        http
                .sessionManagement()
                .sessionCreationPolicy(STATELESS);

        return http.build();


    }




}
