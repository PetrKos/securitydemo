package com.example.securitydemo.security

import com.example.securitydemo.security.ApplicationUserRole.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.csrf.CookieCsrfTokenRepository

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class ApplicationSecurityConfig(
    private val passWordEncoder: PasswordEncoder
): WebSecurityConfigurerAdapter() {


    override fun configure(http: HttpSecurity?) {
        http
//            ?.csrf()?.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//            ?.and()
            ?.csrf()?.disable()
            ?.authorizeRequests()
            ?.antMatchers("/", "index", "/css/*", "/js/*")?.permitAll()
            ?.antMatchers("/api/**")?.hasRole(STUDENT.name)
//            ?.antMatchers(HttpMethod.DELETE,"management/api/**")?.hasAuthority(ApplicationUserPermission.COURSE_WRITE.permission)
//            ?.antMatchers(HttpMethod.POST,"management/api/**")?.hasAuthority(ApplicationUserPermission.COURSE_WRITE.permission)
//            ?.antMatchers(HttpMethod.PUT,"management/api/**")?.hasAuthority(ApplicationUserPermission.COURSE_WRITE.permission)
//            ?.antMatchers(HttpMethod.GET,"management/api/**")?.hasAnyRole(ADMIN.name, ADMINTRAINEE.name)
            ?.anyRequest()
            ?.authenticated()
            ?.and()
            ?.formLogin()
            ?.loginPage("/login")?.permitAll()
    }

    //this is how i get users from the db
    @Bean
    override fun userDetailsService(): UserDetailsService {
        val annaSmithUser = User.builder()
            .username("annasmith")
            .password(passWordEncoder.encode("password"))
            //.roles(STUDENT.name) //ROLE_STUDENT
            .authorities(STUDENT.getGrantedAuthorities())
            .build()

        val lindaUser = User.builder()
            .username("linda")
            .password(passWordEncoder.encode("password123"))
            //.roles(ADMIN.name)
            .authorities(ADMIN.getGrantedAuthorities())
            .build()

        val tomUser = User.builder()
            .username("tom")
            .password(passWordEncoder.encode("password123"))
            //.roles(ADMINTRAINEE.name) //ROLE_ADMINTRAINEE
            .authorities(ADMINTRAINEE.getGrantedAuthorities())
            .build()

        return InMemoryUserDetailsManager(
            annaSmithUser,
            lindaUser,
            tomUser
        )
    }
}