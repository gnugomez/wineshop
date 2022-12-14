package com.group3.wineshop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.group3.wineshop.entities.Type;
import com.group3.wineshop.exceptions.NotFoundException;
import com.group3.wineshop.services.TypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
class TypeControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    TypeService typeService;

    @BeforeEach
    void setupTypeService() {
        when(typeService.getById(anyInt())).thenReturn(new Type("Toro Red"));
        when(typeService.getById(0)).thenThrow(new NotFoundException("Type not found"));
        when(typeService.getAll()).thenReturn(List.of(new Type()));
        when(typeService.save(any(Type.class))).thenReturn(new Type());
        when(typeService.update(any(Type.class))).thenReturn(new Type());
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/type")
                .contentType(MediaType.APPLICATION_JSON)
        );
    }

    @Test
    void getById_Ok() throws Exception {
        mockMvc.perform(get("/api/type/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Toro Red")))
                .andReturn();
    }

    @Test
    void getById_NotFound() throws Exception {
        mockMvc.perform(get("/api/type/0"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreate_Ok() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(new Type("name1"));


        mockMvc.perform(post("/api/type")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void testCreate_MissingField() throws Exception {
        mockMvc.perform(post("/api/type")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreate_EmptyName() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(new Type(""));

        mockMvc.perform(post("/api/type")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name", is("Name is mandatory")));
    }

    @Test
    void testDelete_Ok() throws Exception {
        mockMvc.perform(delete("/api/type/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful());
    }
}