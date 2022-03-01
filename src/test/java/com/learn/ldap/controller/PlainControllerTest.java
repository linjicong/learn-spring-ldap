package com.learn.ldap.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PlainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void showTree() throws Exception {
        this.mockMvc.perform(get("/showTree"))
                .andExpect(status().isOk());
    }

    @Test
    void showPerson() {
    }

    @Test
    void addPerson() throws Exception {
        this.mockMvc.perform(get("/addPerson"))
                .andExpect(status().isOk());
    }

    @Test
    void updatePerson() {
    }

    @Test
    void removePerson() {
    }
}
