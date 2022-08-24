package com.group3.wineshop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.wineshop.entities.Wine;
import com.group3.wineshop.services.WineService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class WineControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private WineService wineService;

    @Test
    void findAllTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/wines")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*.id").isNotEmpty());
    }

    @Test
    public void findOneTest() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/wines/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    void findOneNotFoundTest() throws Exception {
            mockMvc.perform(get("/api/wines/0")
                            .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isNotFound());
    }

    @Test
    public void saveTest() throws Exception {
        Wine wine = new Wine(null, "Create Wine", "2022", 4.5,
                2, 27.38, "4", "3", 1, 1, 1);
        when(wineService.save(any())).thenReturn(wine);

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/api/wines")
                        .content(writeAsJsonString(wine))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    public void updateTest() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .patch("/api/wines", 2)
                        .content(writeAsJsonString(new Wine(2L, "Update Wine", "2022", 4.5,
                                2, 27.38, "4", "3", 1, 1, 1)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Update Wine"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value("2022"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(27.38));
    }

    /*
    @Test
    public void deleteTest() throws Exception
    {
        mockMvc.perform( delete("/api/wines/{id}", 5000) )
                .andExpect(status().isAccepted());
    }*/

    public static String writeAsJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
