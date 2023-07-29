package denshchikov.dmitry.app.config

import org.jetbrains.exposed.sql.Database
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DbConnectionConfig(val dbProperties: DbProperties) {

    @Bean
    fun db(): Database {
        with(dbProperties) {
            return Database.connect(url,driverClassName, username, password)
        }
    }

}