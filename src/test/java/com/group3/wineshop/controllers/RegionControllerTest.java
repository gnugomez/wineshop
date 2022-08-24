package com.group3.wineshop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.group3.wineshop.entities.Region;
import com.group3.wineshop.services.RegionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RegionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RegionService regionService;

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/regions/")
                .contentType(MediaType.APPLICATION_JSON)
        );
    }

    @Test
    void testGetById_Ok() throws Exception{
        mockMvc.perform(get("/api/regions/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.country", is("Espana")))
                .andReturn();
    }

    @Test
    void testGetById_NotFound() throws Exception {
        mockMvc.perform(get("/api/regions/0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreate_Ok() throws Exception {
        Region region = new Region();
        when(regionService.create(any())).thenReturn(region);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(region);


        mockMvc.perform(post("/api/regions")
                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson)
                )
                .andExpect(status().isOk());
    }
/*
    @Test
    void testDelete_Ok() throws Exception {
        Region region = new Region();
        doNothing().when(regionService).delete(anyLong());

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(region);


        mockMvc.perform(delete("/api/regions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
        verify(regionService, times(1)).delete(anyLong());

    }
    */

}
