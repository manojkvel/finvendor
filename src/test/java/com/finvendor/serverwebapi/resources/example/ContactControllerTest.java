//package com.finvendor.serverwebapi.resources.example;
//
//import com.finvendor.server.common.commondao.ICommonDao;
//import com.finvendor.server.common.commondao.impl.CommonDaoImpl;
//import com.finvendor.server.example.dao.impl.ExampleDaoImpl;
//import com.finvendor.server.example.service.IExampleService;
//import com.finvendor.server.example.service.impl.ExampleServiceImpl;
//import org.hibernate.SessionFactory;
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
//@WebAppConfiguration
//public class ContactControllerTest {
//
//    @Autowired
//    private WebApplicationContext ctx;
//
//    @Autowired
//    private IExampleService exampleServiceMock;
//
//    @Autowired
//    ExampleDaoImpl exampleDaoImpl;
//
//    @Autowired
//    private ICommonDao commonDao;
//
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    private MockMvc mockMvc;
//
//    @Before
//    public void setUp() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
//    }
//
//
//    @Configuration
//    @EnableWebMvc
//    public static class TestConfiguration {
//
//        @Bean
//        public ContactController contactController() {
//            return new ContactController();
//        }
//
//        @Bean
//        public IExampleService exampleService() {
//            return new ExampleServiceImpl();
//        }
//
//        @Bean
//        public ExampleDaoImpl exampleDao() {
//            return new ExampleDaoImpl();
//        }
//
//        @Bean
//        public ICommonDao commonDao() {
//            return new CommonDaoImpl();
//        }
//
//        @Bean
//        public SessionFactory sessionFactory() {
//            return null;
//        }
//    }
////    @Test
////    @Ignore
////    public void contact() throws Exception {
////        Integer id = 1;
////        mockMvc.perform(get("/contact/{id}", id).accept(MediaType.APPLICATION_JSON))
////                .andExpect(status().isOk())
////                .andExpect(jsonPath("id").value(1))
////        ;
////    }
////
////    @Test
////    @Ignore
////    public void contacts() throws Exception {
////        Integer id = 1;
////
////        mockMvc.perform(get("/contacts").accept(MediaType.APPLICATION_JSON))
////                .andExpect(status().isOk())
////                .andExpect(jsonPath("$.[0].id").value(2))
////        ;
////    }
//
////    @Test
//////    public void examples() throws Exception {
//////        List<ExamplePojo> list = new ArrayList<>();
//////        ExamplePojo pojo1 = new ExamplePojo();
//////        pojo1.setId(1);
//////        pojo1.setName("TEST");
//////        list.add(pojo1);
//////
//////
//////        when(exampleServiceMock.findAllExample()).thenReturn(list);
////////        mockMvc.perform(get("/findallexample").accept(MediaType.APPLICATION_JSON))
////////                .andExpect(status().isOk());
////////        ;
//////    }
//
//
//}