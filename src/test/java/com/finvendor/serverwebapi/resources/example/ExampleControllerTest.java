package com.finvendor.serverwebapi.resources.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finvendor.model.Example;
import com.finvendor.api.example.controller.ExampleController;
import com.finvendor.api.example.dto.ExampleDto;
import com.finvendor.api.example.service.ExampleService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ExampleControllerTest {
    private MockMvc mockMvc;

    @Mock
    ExampleService exampleService;

    @InjectMocks
    ExampleController exampleController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(exampleController)
                .build();
    }

    @Test
    public void test_get_all_examples() throws Exception {
        List<ExampleDto> examples = Arrays.asList(new ExampleDto(1, "Ayush"), new ExampleDto(2, "Danny"));
        when(exampleService.findAllExample()).thenReturn(examples);
        mockMvc.perform(get("/system/api/findallexample"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Ayush")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Danny")));
    }

    @Test
    public void test_save_example() throws Exception {
        Example example = new Example();
        example.setId(1);
        example.setName("Danny");
        doNothing().when(exampleService).saveOrUpdateExample1(example);

        mockMvc.perform(
                post("/system/api/saveexample")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(example)))
                .andExpect(status().isOk());
    }

    /*
     * converts a Java object into JSON representation
     */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
