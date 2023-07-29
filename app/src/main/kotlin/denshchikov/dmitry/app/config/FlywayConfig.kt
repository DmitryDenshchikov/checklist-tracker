package denshchikov.dmitry.app.config

import jakarta.annotation.PostConstruct
import org.flywaydb.core.Flyway
import org.springframework.context.annotation.Configuration

@Configuration
class FlywayConfig(val dbProperties: DbProperties) {

    @PostConstruct
    fun init() {
        with(dbProperties) {
            Flyway.configure()
                .dataSource(url, username, password)
                .load()
                .migrate()
        }
    }

}