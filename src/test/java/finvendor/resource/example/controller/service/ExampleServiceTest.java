//package finvendor.resource.example.controller;
//
//import com.finvendor.server.example.service.IExampleService;
//import com.finvendor.server.example.staticpojo.ExamplePojo;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:testweb.xml"})
//@WebAppConfiguration
//public class ExampleControllerTest {
//    private MockMvc mockMvc;
//
//    @Autowired
//    private IExampleService exampleServiceMock;
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    @Before
//    public void setUp() {
//        //We have to reset our mock between tests because the mock objects
//        //are managed by the Spring container. If we would not reset them,
//        //stubbing and verified behavior would "leak" from one test to another.
//        Mockito.reset(exampleServiceMock);
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }
//
//    @Test
//    public void findAllExampleTest() throws Exception {
//        ExamplePojo pojo1 = new ExamplePojo();
//        pojo1.setId(1);
//        pojo1.setName("Ayush1");
//        ExamplePojo pojo2 = new ExamplePojo();
//        pojo2.setId(2);
//        pojo2.setName("Ayush2");
//
//        List<ExamplePojo> examples = Arrays.asList(
//                pojo1,
//                pojo2);
//        when(exampleServiceMock.findAllExample()).thenReturn(examples);
//        mockMvc.perform(get("/findAllExample"))
//                .andExpect(status().isOk())
//                .andExpect(model().attribute("todos", hasSize(2)))
//
//                ;
//
//    }
//}
