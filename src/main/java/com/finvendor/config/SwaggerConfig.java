package com.finvendor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean

    public Docket api(){

//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.regex("/api/.*"))
//                .build()
//                .apiInfo(apiInfo());
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .docExpansion(DocExpansion.LIST) // or DocExpansion.NONE or DocExpansion.FULL
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Finvendor")
                .description("FinVendor strives for democratizing the inventor's community. FinVendor endeavours to help retail and institutional investors make a well-informed investment decisions.")
                .version("v1.0")
                .termsOfServiceUrl("https://www.finvendor.com/view/privacy-policy.jsp")
                .license("DISCLAIMER")
                .licenseUrl("https://www.finvendor.com/view/disclaimer.jsp")
                .build();
    }
}