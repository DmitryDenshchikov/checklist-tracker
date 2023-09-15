package denshchikov.dmitry.app.config


import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.JWKSource
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings
import org.springframework.security.web.SecurityFilterChain
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.util.*
import javax.sql.DataSource


@Configuration
class AuthorizationServerConfig {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain = with(http) {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http)
        http.formLogin(Customizer.withDefaults()).build()
    }

    @Bean
    fun registeredClientRepository(
        dataSource: DataSource
    ): RegisteredClientRepository = JdbcRegisteredClientRepository(JdbcTemplate(dataSource))

    @Bean
    fun jwkSource(): JWKSource<SecurityContext> {
        val rsaKey = generateRsa()
        val jwkSet = JWKSet(rsaKey)
        return JWKSource { jwkSelector, context ->
            jwkSelector.select(jwkSet)
        }
    }

    @Bean
    fun providerSettings(
        @Value("\${app.issuer-url}") issuerUrl: String
    ): AuthorizationServerSettings = AuthorizationServerSettings.builder()
        .issuer(issuerUrl)
        .build()

    private fun generateRsa(): RSAKey {
        val keyPair = generateRsaKey()
        val publicKey: RSAPublicKey = keyPair.public as RSAPublicKey
        val privateKey: RSAPrivateKey = keyPair.private as RSAPrivateKey
        return RSAKey.Builder(publicKey)
            .privateKey(privateKey)
            .keyID(UUID.randomUUID().toString())
            .build()
    }

    private fun generateRsaKey(): KeyPair = KeyPairGenerator.getInstance("RSA").let {
        it.initialize(2048)
        it.generateKeyPair()
    }


}