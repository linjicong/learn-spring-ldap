package com.learn.ldap.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OdmControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void showTree() throws Exception {
        this.mockMvc.perform(get("/odm/showTree"))
                .andExpect(status().isOk());
    }

    @Test
    void showPerson() throws Exception {
        this.mockMvc.perform(get("/odm/showPerson?country=CN&company=company1&fullName=cwskv9nc"))
                .andExpect(status().isOk());
    }

    @Test
    void addPerson() throws Exception {
        this.mockMvc.perform(get("/odm/addPerson"))
                .andExpect(status().isOk());
    }

    @Test
    void updatePerson() throws Exception {
        this.mockMvc.perform(get("/odm/updatePerson?country=CN&company=company1&fullName=cwskv9nc"))
                .andExpect(status().isOk());
    }

    @Test
    void removePerson() throws Exception {
        this.mockMvc.perform(get("/odm/removePerson?country=CN&company=company1&fullName=John Doe"))
                .andExpect(status().isOk());
    }
}
