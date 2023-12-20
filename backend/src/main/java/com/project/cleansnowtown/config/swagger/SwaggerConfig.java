package com.project.cleansnowtown.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        info = @Info(title = "CleanSnowTown API 명세서",
                description = "Spring Boot로 개발하는 RESTful API 명세서 입니다.",
                version = "v1.0.0"))
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi customTestOpenAPi() {
        String[] paths = {"/api/v1/member/**", "/api/v1/admin/**"};

        return GroupedOpenApi
                .builder()
                .group("일반 사용자와 관리자를 위한 Member 도메인에 대한 API")
                .pathsToMatch(paths)
                .build();
    }


}
