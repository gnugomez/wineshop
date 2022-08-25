package com.group3.wineshop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.group3.wineshop.entities.Region;
import com.group3.wineshop.exceptions.NotFoundException;
import com.group3.wineshop.services.RegionService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class RegionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegionService regionService;

    private static List<Region> sampleRegions;

    @BeforeAll
    public static void beforeTests() {
        Region region1 = new Region("name1", "countryName1");
        Region region2 = new Region("name2", "countryName2");
        sampleRegions = Arrays.asList(region1, region2);
    }

    @Test
    void testGetAll() throws Exception {
        when(regionService.findAll()).thenReturn(sampleRegions);

        mockMvc.perform(get("/api/region/")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testGetById_Ok() throws Exception{
        when(regionService.findById(0)).thenReturn(sampleRegions.get(0));

        mockMvc.perform(get("/api/region/0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("name1")))
                .andExpect(jsonPath("$.country", is("countryName1")))
                .andReturn();
    }

    @Test
    void testGetById_NotFound() throws Exception {
        when(regionService.findById(anyInt())).thenThrow(new NotFoundException(""));

        mockMvc.perform(get("/api/region/0")
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


        mockMvc.perform(post("/api/region")
                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdate_Ok() throws Exception {
        Region region = new Region();
        when(regionService.update(any())).thenReturn(region);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(region);

        mockMvc.perform(put("/api/region/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    void testDelete_Ok() throws Exception {
        doNothing().when(regionService).delete(anyInt());

        mockMvc.perform(delete("/api/region/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
        verify(regionService, times(1)).delete(anyInt());

    }

}
