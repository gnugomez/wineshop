package com.group3.wineshop.controllers;

import com.group3.wineshop.entities.Wine;
import com.group3.wineshop.services.WineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
class RecommendControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    WineService wineService;

    @BeforeEach
    void setUp() {
        when(wineService.getExpensive())
                .thenReturn(List.of(new Wine()));
        when(wineService.getBest())
                .thenReturn(List.of(new Wine()));
        when(wineService.getBang())
                .thenReturn(List.of(new Wine()));
        when(wineService.getYearsWithBestRatedWines())
                .thenReturn(Map.of("2018", List.of(new Wine())));
    }
    @Test
    void getBang() throws Exception {
        mockMvc.perform(get("/api/recommend/bang")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    void getExpensive() throws Exception {
        mockMvc.perform(get("/api/recommend/expensive")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    void getVintage_Ok() throws Exception {
        mockMvc.perform(get("/api/recommend/vintage")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    void testGetBest_Ok() throws Exception {
        mockMvc.perform(get("/api/recommend/best").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}