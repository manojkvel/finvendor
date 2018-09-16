//package com.finvendor.serverwebapi.resources.example;
//
//import com.finvendor.config.TestContext;
//import com.finvendor.config.WebAppContext;
//import com.finvendor.server.example.service.IExampleService;
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
//@WebAppConfiguration
//public class WebExampleTest {
//
//    private MockMvc mockMvc;
//    @Autowired
//    private WebApplicationContext ctx;
//
//    @Autowired
//    private IExampleService exampleServiceMock;
//
//
//    @Before
//    public void setUp() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
//    }
//
//
////    @Test
////    public void contact() throws Exception {
////        Integer id = 1;
////        mockMvc.perform(get("/contact/{id}",id).accept(MediaType.APPLICATION_JSON))
////                .andDo(print());
////    }
//}