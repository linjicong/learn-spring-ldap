//package com.learn.ldap.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.ldap.core.support.BaseLdapPathContextSource;
//import org.springframework.security.ldap.authentication.BindAuthenticator;
//import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
//import org.springframework.security.ldap.authentication.LdapAuthenticator;
//import org.springframework.security.ldap.userdetails.PersonContextMapper;
//
//@Configuration
//public class LdapAuthConfig {
//    @Bean
//    BindAuthenticator authenticator(BaseLdapPathContextSource contextSource) {
//        BindAuthenticator authenticator= new BindAuthenticator(contextSource);
//        authenticator.setUserDnPatterns(new String[]{"cn={0},ou=company1,c=CN"});
//
//        return authenticator;
//    }
//
//    @Bean
//    LdapAuthenticationProvider authenticationProvider(LdapAuthenticator authenticator) {
//        LdapAuthenticationProvider provider = new LdapAuthenticationProvider(authenticator);
//        provider.setUserDetailsContextMapper(new PersonContextMapper());
//        return provider;
//    }
//}
