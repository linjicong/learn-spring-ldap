package com.learn.ldap.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAuthName() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("Some Person","password");
        this.mockMvc.perform(get("/getAuthName").headers(headers))
                .andExpect(status().isOk());
    }

    @Test
    void getAuthDetail() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("linjicong","Ljc19940501");
        this.mockMvc.perform(get("/getAuthDetail").headers(headers))
                .andExpect(status().isOk());
    }
}
