package denshchikov.dmitry.app

import denshchikov.dmitry.app.config.DbProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(DbProperties::class)
@SpringBootApplication
class App

fun main(args: Array<String>) {
    runApplication<App>(*args)
}
