//package com.finvendor.config;
//
//import com.google.common.base.Predicates;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig extends WebMvcConfigurerAdapter
//{
//    @Bean
//    public Docket api() {
//        //Register the controllers to swagger
//        //Also it is configuring the Swagger Docket
//        return new Docket(DocumentationType.SWAGGER_2).select()
//                 .apis(RequestHandlerSelectors.any())
//                .apis(Predicates.not(RequestHandlerSelectors.basePackage("com.finvendor")))
//                 .paths(PathSelectors.any())
////                 .paths(PathSelectors.ant("/swagger2-demo"))
//                .build();
//    }
//}