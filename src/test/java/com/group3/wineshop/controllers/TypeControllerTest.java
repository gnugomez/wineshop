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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TypeControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    TypeService typeService;

    @BeforeEach
    void setupTypeService() {
        when(typeService.getById(anyLong())).thenReturn(new Type("Toro Red"));
        when(typeService.getById(0L)).thenThrow(new NotFoundException("Type not found"));
        when(typeService.getAll()).thenReturn(List.of(new Type()));
        when(typeService.save(any(Type.class))).thenReturn(new Type());
        when(typeService.update(any(Type.class))).thenReturn(new Type());
        when(typeService.delete(anyLong())).thenReturn(true);
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/types")
                .contentType(MediaType.APPLICATION_JSON)
        );
    }

    @Test
    void getById_Ok() throws Exception {
        mockMvc.perform(get("/api/types/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Toro Red")))
                .andReturn();
    }

    @Test
    void getById_NotFound() throws Exception {
        mockMvc.perform(get("/api/types/0"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreate_Ok() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(new Type());


        mockMvc.perform(post("/api/types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void testDelete_Ok() throws Exception {
        mockMvc.perform(delete("/api/types/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful());
    }
}