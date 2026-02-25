package com.typeB.helloapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testValidNames() throws Exception {
        // Checking first-half names (A-M)
        mockMvc.perform(get("/hello-world").param("name", "alice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Alice"));

        mockMvc.perform(get("/hello-world").param("name", "Bob"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Bob"));
    }

    @Test
    public void testInvalidNames() throws Exception {
        // Checking second-half names (N-Z)
        mockMvc.perform(get("/hello-world").param("name", "noah"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));

        mockMvc.perform(get("/hello-world").param("name", "Zebra"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testValidNameStartWithSpace() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", " charith"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Charith"));
    }

    @Test
    public void testInvalidNamesStartWithSpace() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", " noah"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    @Test
    public void testMissingNameParam() throws Exception {
        mockMvc.perform(get("/hello-world"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testEmptyStringAsParam() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", ""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testStartWithNumber() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "123"))
                .andExpect(status().isBadRequest());
    }
}
