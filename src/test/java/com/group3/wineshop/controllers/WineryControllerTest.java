package com.group3.wineshop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.group3.wineshop.entities.Winery;
import com.group3.wineshop.exceptions.NotFoundException;
import com.group3.wineshop.services.WineryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
class WineryControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    WineryService wineryService;

    @BeforeEach
    void setupWineryService() {
        when(wineryService.getById(anyInt())).thenReturn(new Winery("manolo"));
        when(wineryService.getById(0)).thenThrow(new NotFoundException("Winery not found"));
        when(wineryService.getAll()).thenReturn(List.of(new Winery()));
        when(wineryService.save(any(Winery.class))).thenReturn(new Winery());
        when(wineryService.update(any(Winery.class))).thenReturn(new Winery());
        when(wineryService.delete(anyInt())).thenReturn(
                new ResponseEntity<>("Winery deleted successfully", null, HttpStatus.NO_CONTENT));
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/wineries")
                .contentType(MediaType.APPLICATION_JSON)
        );
    }

    @Test
    void getById_Ok() throws Exception {
        mockMvc.perform(get("/api/wineries/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("manolo")))
                .andReturn();
    }

    @Test
    void getById_NotFound() throws Exception {
            mockMvc.perform(get("/api/wineries/0"))
                    .andExpect(status().isNotFound());
    }

    @Test
    void testCreate_Ok() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(new Winery());


        mockMvc.perform(post("/api/wineries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void testDelete_Ok() throws Exception {

        mockMvc.perform(delete("/api/wineries/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void testUpdate_Ok() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(new Winery());
        mockMvc.perform(put("/api/wineries/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                )
                .andExpect(status().is2xxSuccessful());
    }
}