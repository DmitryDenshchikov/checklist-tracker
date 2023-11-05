package denshchikov.dmitry.app.filter

import denshchikov.dmitry.app.exception.NoSuchCookieException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.SupplierJwtDecoder
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider
import org.springframework.web.filter.OncePerRequestFilter

class WebSocketCookieBearerTokenAuthorisation(decoder: SupplierJwtDecoder) : OncePerRequestFilter() {

    private val jwtAuthenticationProvider = JwtAuthenticationProvider(decoder)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        request.cookies.iterator().forEach {
            if ("token" == it.name) {
                val auth = jwtAuthenticationProvider.authenticate(BearerTokenAuthenticationToken(it.value))
                SecurityContextHolder.getContext().authentication = auth
                filterChain.doFilter(request, response)
                return
            }
        }

        throw NoSuchCookieException("No JWT token present in cookies");
    }

}