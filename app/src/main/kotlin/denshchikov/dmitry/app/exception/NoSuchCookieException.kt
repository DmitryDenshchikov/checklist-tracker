package denshchikov.dmitry.app.exception

import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException

class NoSuchCookieException(msg: String) : RememberMeAuthenticationException(msg)