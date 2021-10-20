package com.cultureshock.madeleine.config.security

import com.cultureshock.madeleine.auth.security.JwtAuthenticationEntryPoint
import com.cultureshock.madeleine.auth.security.JwtAuthenticationTokenFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.BeanIds
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@EnableGlobalMethodSecurity(
    prePostEnabled = true
)
class SecurityConfig(
    private val unauthorizedHandler: JwtAuthenticationEntryPoint,
    private val jwtAuthenticationTokenFilter: JwtAuthenticationTokenFilter,
    private val userDetailsService: UserDetailsService
) : WebSecurityConfigurerAdapter() {

    @Bean(name = [BeanIds.AUTHENTICATION_MANAGER])
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Autowired
    fun configureAuthentication(authenticationManagerBuilder: AuthenticationManagerBuilder) {
        authenticationManagerBuilder
            .userDetailsService(this.userDetailsService)
            .passwordEncoder(this.passwordEncoder())
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(http: HttpSecurity) {

        http
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers(
                "/health",
                "/api/v1/auth/**",
                "/api/v1/performance/list",
                "/api/v1/performance/list/genrenm",
                // swagger
                 "/configuration/ui",
                "/swagger-resources",
                "/webjars/**", "/swagger/**",
                "/v2/api-docs", "/v3/api-docs",
                "/configuration/**", "/swagger*/**", "/h2-console/**"
            )
            .permitAll()
            .anyRequest().authenticated().and().cors()
        http
            .headers().frameOptions().disable()
        http
            .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter::class.java)

    }
}