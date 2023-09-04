package com.larsson.sushi.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthoritiesServiceImpl implements AuthoritiesService {

    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    private static final Logger authoritiesLogger = Logger.getLogger(AuthoritiesServiceImpl.class);

    @Override
    public void addAuthority(String userName) {
        Authorities authority = new Authorities(userName,"ROLE_CUSTOMER");
        authoritiesLogger.info("New authority(ROLE_CUSTOMER) Added for User:"+userName);
        authoritiesRepository.save(authority);
    }
}
