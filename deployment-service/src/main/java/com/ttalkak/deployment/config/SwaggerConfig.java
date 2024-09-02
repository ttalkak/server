//package com.ttalkak.deployment.config;
//
//import org.springdoc.core.GroupedOpenApi;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SwaggerConfig {
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.OAS_30)
//                .useDefaultResponseMessages(true) // Swagger 에서 제공해주는 기본 응답 코드를 표시할 것이면 true
//                .select()
//                // .apis(RequestHandlerSelectors.any())
//                .apis(RequestHandlerSelectors.basePackage("com.ttalkak.deployment"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    @Bean
//    public GroupedOpenApi publicApi() {
//        return GroupedOpenApi.builder()
//                .group("public-api")
//                .pathsToMatch("/**")
//                .build();
//    }
//}
