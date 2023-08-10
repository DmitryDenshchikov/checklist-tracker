package denshchikov.dmitry.app.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.ShallowEtagHeaderFilter

@Configuration
class WebConfig {

    @Bean
    fun shallowEtagHeaderFilter(): ShallowEtagHeaderFilter {
        return ShallowEtagHeaderFilter()
    }

}