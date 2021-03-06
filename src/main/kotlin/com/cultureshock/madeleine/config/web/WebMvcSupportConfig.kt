package com.cultureshock.madeleine.config.web

import com.cultureshock.madeleine.auth.security.JwtTokenUtils
import com.cultureshock.madeleine.config.web.resolver.AuthenticationIdResolver
import com.cultureshock.madeleine.config.web.resolver.AuthenticationTokenResolver
import com.cultureshock.madeleine.domain.user.UserRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.web.PageableHandlerMethodArgumentResolver
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport

@Configuration
class WebMvcSupportConfig(
    @Value("\${jwt.header}") private val header: String,
    private val jwtTokenUtils: JwtTokenUtils,
    private val userRepository: UserRepository
) : WebMvcConfigurationSupport() {

    @Bean
    fun corsFilter(): CorsFilter? {
        val source = UrlBasedCorsConfigurationSource()

        val config = CorsConfiguration()
        config.allowCredentials = true
        config.addAllowedOrigin("*")
        config.addAllowedHeader("*")
        config.addAllowedMethod("*")
        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source)
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        // register swagger endpoint
        registry.addResourceHandler("/swagger-ui.html**")
            .addResourceLocations("classpath:/META-INF/resources/")

        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/")
    }

    override fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver>) {
        //jwt token
        //argumentResolvers.add(AuthenticationTokenResolver(header, jwtTokenUtils, userRepository))
        argumentResolvers.add(AuthenticationIdResolver(header,userRepository))
        // pageable handelr
        argumentResolvers.add(PageableHandlerMethodArgumentResolver())
    }
}