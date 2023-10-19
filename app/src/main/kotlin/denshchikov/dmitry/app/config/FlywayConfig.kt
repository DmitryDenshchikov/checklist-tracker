package denshchikov.dmitry.app.config

import jakarta.annotation.PostConstruct
import org.flywaydb.core.Flyway
import org.flywaydb.core.api.output.MigrateResult
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class FlywayConfig(val dataSource: DataSource) {

    @PostConstruct
    fun init(): MigrateResult = Flyway.configure()
        .dataSource(dataSource)
        .load()
        .migrate()

}