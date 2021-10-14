package com.cultureshock.madeleine.config.swagger

import org.slf4j.LoggerFactory
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebSession
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.ApiKeyVehicle
import springfox.documentation.swagger.web.SecurityConfiguration
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*


@Configuration
@EnableSwagger2
class SwaggerConfig(
    private val buildProperties: BuildProperties
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Bean
    fun api(): Docket{
        logger.debug("Starting Swagger...")

        return Docket(DocumentationType.SWAGGER_2)
            .enable(true)
            .useDefaultResponseMessages(false)
            .ignoredParameterTypes(
                WebSession::class.java,
                ServerHttpRequest::class.java,
                ServerHttpResponse::class.java,
                ServerWebExchange::class.java
            )
            .genericModelSubstitutes(
                Optional::class.java,
                ResponseEntity::class.java
            )
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.cultureshock.madeleine.rest"))
            .paths(PathSelectors.ant("/api/**"))
            .build().apiInfo(apiEndPointsInfo())
            .securityContexts(listOf(securityContext()))
            .securitySchemes(listOf(apiKey()))
    }

    private fun apiEndPointsInfo(): ApiInfo {
        return ApiInfoBuilder()
            .title(buildProperties.name)
            .description("마들렌 공장 API swagger doc")
            .contact(Contact("ChoiPureum", "https://github.com/choipureum", "pooreumsunny@gmail.com"))
            .version(buildProperties.version)
            .license("MIT License")
            .build()
    }

    @Bean
    fun security(): SecurityConfiguration {
        return SecurityConfiguration(null, null, null, null,
            "Bearer access_token", ApiKeyVehicle.HEADER, "Authorization", ",")
    }

    private fun securityContext(): SecurityContext =
        SecurityContext.builder()
            .securityReferences(listOf(defaultAuth()))
            .forPaths(PathSelectors.regex("/*"))
            .build()

    private fun defaultAuth(): SecurityReference =
        SecurityReference("Authorization", arrayOf(AuthorizationScope("global", "accessEverything")))

    private fun apiKey(): ApiKey = ApiKey("Authorization", "jwt", "header")

}