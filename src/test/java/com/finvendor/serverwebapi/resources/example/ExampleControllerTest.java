package com.finvendor.serverwebapi.resources.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finvendor.api.example.controller.ExampleController;
import com.finvendor.api.example.dto.ExampleRequestDto;
import com.finvendor.api.example.service.ExampleService;
import com.finvendor.common.response.ApiResponse;
import com.finvendor.common.util.JsonUtil;
import io.restassured.RestAssured;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
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

    @InjectMocks
    ExampleController exampleController;

    @Mock
    ExampleService exampleService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(exampleController).build();
    }

    @Test
    public void test_findAllExamples() throws Exception {
        ExampleRequestDto e1 = new ExampleRequestDto();
        e1.setId(1);
        e1.setName("User1");
        e1.setPhone("11");

        ExampleRequestDto e2 = new ExampleRequestDto();
        e2.setId(2);
        e2.setName("User2");
        e2.setPhone("22");

        List<ExampleRequestDto> examples = Arrays.asList(e1, e2);
        when(exampleService.findAllExample()).thenReturn(examples);

        //Response Json: {"code":"fv-200","message":"Success","data":[{"id":1,"name":"User1","phone":"11"},{"id":2,"name":"User2","phone":"22"}]}
        mockMvc.perform(get("/api/examples"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("code", is("fv-200")))
                .andExpect(jsonPath("message", is("Success")))
                .andExpect(jsonPath("$.data[*].id", Matchers.containsInAnyOrder(1, 2)))
                .andExpect(jsonPath("$.data[*].name", Matchers.containsInAnyOrder("User1", "User2")))
                .andExpect(jsonPath("$.data[*].phone", Matchers.containsInAnyOrder("11", "22")));

        RestAssured.baseURI ="http://localhost:9999/api/examples";
        RequestSpecification request = RestAssured.given();
        ResponseBody body = request.get("http://localhost:9999/api/examples").getBody();
        ApiResponse as = body.as(ApiResponse.class);
        System.out.println(as);
        //        //Define a postRequest request
//        HttpPost postRequest = new HttpPost("http://localhost:9999/api/examples");
//
//        //Set the API media type in http content-type header
//        postRequest.addHeader("content-type", "application/json");
//
//        //Set the request post body
//        StringEntity userEntity = new StringEntity(JsonUtil.createJsonFromObject(e1));
//        postRequest.setEntity(userEntity);
//
//        //Send the request; It will immediately return the response in HttpResponse object if any
//        HttpResponse response1 = HttpClientBuilder.create().build().execute(postRequest);
//
//        //verify the valid error code first
//        int statusCode = response1.getStatusLine().getStatusCode();
//        if (statusCode != 200) {
//            throw new RuntimeException("Failed with HTTP error code : " + statusCode);
//        }

//        // Given
//        HttpUriRequest request = new HttpGet("http://localhost:9999/api/examples");
//
//        // When
//        CloseableHttpResponse response = HttpClientBuilder.create().build().execute(request);
//
//        // Then
//        ApiResponse<String, List<ExampleRequestDto>> resource = retrieveResourceFromResponse(response);
//
//        Assert.assertThat("User1", Matchers.is(resource.getData().get(0).getName()));
    }

    @SuppressWarnings("unchecked")
    private static ApiResponse<String, List<ExampleRequestDto>> retrieveResourceFromResponse(
            CloseableHttpResponse response) throws IOException {
        String jsonFromResponse = EntityUtils.toString(response.getEntity());
        return (ApiResponse<String, List<ExampleRequestDto>>) JsonUtil.convertJsonToPojo(jsonFromResponse, ApiResponse.class);
    }

    @Test
    public void test_saveExample() throws Exception {
        ExampleRequestDto exampleRequestDto = new ExampleRequestDto();
        exampleRequestDto.setId(1);
        exampleRequestDto.setName("User1");
        exampleRequestDto.setPhone("11");
        doNothing().when(exampleService).saveOrUpdateExample(exampleRequestDto);

        mockMvc.perform(
                post("/api/examples")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(exampleRequestDto))).andExpect(status().isOk());
    }

    /*
     * converts a Java object into JSON representation
     */
    private static String asJsonString(final ExampleRequestDto obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
