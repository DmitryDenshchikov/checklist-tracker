package denshchikov.dmitry.app.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.*
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf {
            it.disable()
        }.authorizeHttpRequests {
            it
                .requestMatchers(PUT, "/tasks/**").hasAuthority("SCOPE_tasks.update")
                .requestMatchers(POST, "/tasks").hasAuthority("SCOPE_tasks.create")
                .requestMatchers(GET, "/tasks").hasAuthority("SCOPE_tasks.read")
                .requestMatchers(GET, "/tasks/**").hasAuthority("SCOPE_tasks.read")
        }.oauth2ResourceServer {
            it.jwt(withDefaults())
        }

        return http.build()
    }

}