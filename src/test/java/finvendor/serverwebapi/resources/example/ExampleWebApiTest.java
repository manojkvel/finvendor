//package finvendor.serverwebapi.resources.example;//package com.finvendor.serverwebapi.resources.example;
//
//import com.finvendor.server.example.service.service.ExampleServiceImpl;
//import com.finvendor.server.example.staticpojo.ExamplePojo;
//import com.finvendor.serverwebapi.resources.example.IWebExample;
//import com.finvendor.serverwebapi.resources.example.service.WebExampleImpl;
//import com.google.gson.Gson;
//import org.junit.Before;
//import org.junit.Test;
//
//import junit.framework.Assert;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {WebExampleImpl.class, ExampleServiceImpl.class })
//@WebAppConfiguration
//public class ExampleWebApiTest {
//
//    private MockMvc mockMvc;
//
//    @Autowired
//    private WebApplicationContext wac;
//
//    @Before
//    public void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//    }
//
//    @Test
//    public void testCreationOfANewProjectSucceeds() throws Exception {
//        ExamplePojo examplePojo = new ExamplePojo();
//        examplePojo.setId(1);
//        examplePojo.setName("MyName");
//        String json = new Gson().toJson(examplePojo);
//
//        mockMvc.perform(
//                post("/system/api/saveexample")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isCreated());
//    }
//}
