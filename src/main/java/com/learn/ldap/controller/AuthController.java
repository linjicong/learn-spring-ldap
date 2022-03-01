package com.learn.ldap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthController {
    @Autowired
    private LdapTemplate ldapTemplate;

    @GetMapping("/getAuthName")
    public String getAuthName(Authentication authentication) {
        System.out.print(authentication.getName());
        return "authName: "+authentication.getName();
    }

    @GetMapping("getAuthDetail")
    public List<String> getAuthDetail(Authentication authentication) {
        return ldapTemplate.search("ou=persons","cn="+authentication.getName(), (AttributesMapper<String>) attributes -> attributes.get("cn").get().toString());
    }
}
