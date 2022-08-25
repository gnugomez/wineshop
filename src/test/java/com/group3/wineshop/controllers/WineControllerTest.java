package com.group3.wineshop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.wineshop.entities.Wine;
import com.group3.wineshop.exceptions.NotFoundException;
import com.group3.wineshop.services.WineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
class WineControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    WineService wineService;

    @BeforeEach
    void setupWineryService() {
        when(wineService.getById(anyLong())).thenReturn(new Wine(2L, "Get Wine", "2022", 4.5, 2, 27.38, "4", "3", 1, 1, 1));
        when(wineService.getById(0L)).thenThrow(new NotFoundException("Winery not found"));
        when(wineService.getAll()).thenReturn(List.of(new Wine()));
        when(wineService.save(any(Wine.class))).thenReturn(new Wine(5005L, "Save Wine", "2022", 4.5, 2, 27.38, "4", "3", 1, 1, 1));
        when(wineService.delete(anyLong())).thenReturn(true);
    }

    @Test
    void findAllTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/wines").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.*.id").isNotEmpty());
    }

    @Test
    public void findOneTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/wines/{id}", 1).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2)).andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Get Wine"));
    }

    @Test
    void findOneNotFoundTest() throws Exception {
        mockMvc.perform(get("/api/wines/0").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    public void saveTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/wines").content(writeAsJsonString(new Wine())).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    public void updateTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/wines").content(writeAsJsonString(new Wine())).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Save Wine")).andExpect(MockMvcResultMatchers.jsonPath("$.year").value("2022")).andExpect(MockMvcResultMatchers.jsonPath("$.price").value(27.38));
    }


    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(delete("/api/wines/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted());
    }

    public static String writeAsJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
