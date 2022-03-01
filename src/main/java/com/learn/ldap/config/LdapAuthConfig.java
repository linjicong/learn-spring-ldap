package com.learn.ldap.config;

import org.springframework.boot.autoconfigure.ldap.LdapAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;

@Configuration
public class LdapAuthConfig {
    //@Bean
    //BindAuthenticator authenticator(BaseLdapPathContextSource contextSource) {
    //    BindAuthenticator authenticator= new BindAuthenticator(contextSource);
    //    authenticator.setUserDnPatterns(new String[]{"cn={0},ou=persons"});
    //    return authenticator;
    //}
    //
    //@Bean
    //LdapAuthenticationProvider authenticationProvider(LdapAuthenticator authenticator) {
    //    LdapAuthenticationProvider provider = new LdapAuthenticationProvider(authenticator);
    //    provider.setUserDetailsContextMapper(new PersonContextMapper());
    //    return provider;
    //}
}
