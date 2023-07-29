package denshchikov.dmitry.app.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "app.db")
class DbProperties {
    lateinit var url: String
    lateinit var username: String
    lateinit var password: String
    lateinit var driverClassName: String
}