package com.project.employee.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    private static final String TITLE = "Employee Login Service REST API";
    private static final Contact CONTACT = new Contact("Fundraising", null,
            "julian.markowskyj@gmail.com.ar");
    private static final String DESCRIPTION = "Servicio que retorna empleados logueados";
    private static final String SERVICE_VERSION = "1.0.0";

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .contact(CONTACT)
                .version(SERVICE_VERSION)
                .description(DESCRIPTION)
                .build();
    }

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/employee.*"))
                .build();
    }
}
