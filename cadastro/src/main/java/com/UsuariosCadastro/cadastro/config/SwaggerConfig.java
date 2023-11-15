package com.UsuariosCadastro.cadastro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;


@Configuration
@EnableSwagger2
public class SwaggerConfig {


 //metodo para colocar api especifica
//    @Bean
//    public Docket docket() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.UsuariosCadastro.cadastro"))
//                .paths(regex("/usuarios.*"))
//                .build()
//                .apiInfo(metaInfo());
//    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Usuarios Api Rest",
                "Api Rest de cadastro de Usuarios.",
                "1.0",
                "Terms of Service",
                new Contact("William Dutra", "https:https://www.linkedin.com/in/william-dutra-pcd-678919192/",
                        "wil.liam_benicio@hotmail.com"),
                "Apache License Version 2.0",
                "http://www.apache.org/license.html", new ArrayList<VendorExtension>());
        return apiInfo;
    }

}
