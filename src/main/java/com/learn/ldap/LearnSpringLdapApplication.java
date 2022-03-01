package com.learn.ldap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;

@SpringBootApplication
public class LearnSpringLdapApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnSpringLdapApplication.class, args);
    }

}
