package denshchikov.dmitry.app.config

import denshchikov.dmitry.app.filter.WebSocketCookieBearerTokenAuthorisation
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.*
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.jwt.SupplierJwtDecoder
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
                .requestMatchers(PUT, "/tasks/**").hasAuthority("SCOPE_user")
                .requestMatchers(POST, "/tasks").hasAuthority("SCOPE_user")
                .requestMatchers(GET, "/tasks").hasAuthority("SCOPE_user")
                .requestMatchers(GET, "/tasks/**").hasAuthority("SCOPE_user")
                .requestMatchers( "/ws/notifications").permitAll()
        }.oauth2ResourceServer {
            it.jwt(withDefaults())
        }

        return http.build()
    }

    @Bean
    fun webSocketCookieBearerTokenAuthorisationFilterRegistration(decoder: SupplierJwtDecoder) =
        FilterRegistrationBean<WebSocketCookieBearerTokenAuthorisation>().apply {
            filter = WebSocketCookieBearerTokenAuthorisation(decoder)
            order = 2
            addUrlPatterns("/ws/notifications")
        }

}