package com.learn.ldap.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PlainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void showTree() throws Exception {
        this.mockMvc.perform(get("/plain/showTree"))
                .andExpect(status().isOk());
    }

    @Test
    void showPerson() throws Exception {
        this.mockMvc.perform(get("/plain/showPerson?country=CN&company=company1&fullName=cwskv9nc"))
                .andExpect(status().isOk());
    }

    @Test
    void addPerson() throws Exception {
        this.mockMvc.perform(get("/plain/addPerson"))
                .andExpect(status().isOk());
    }

    @Test
    void updatePerson() throws Exception {
        this.mockMvc.perform(get("/plain/updatePerson?country=CN&company=company1&fullName=cwskv9nc"))
                .andExpect(status().isOk());
    }

    @Test
    void removePerson() throws Exception {
        this.mockMvc.perform(get("/plain/removePerson?country=CN&company=company1&fullName=John Doe"))
                .andExpect(status().isOk());
    }
}
