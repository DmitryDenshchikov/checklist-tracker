package denshchikov.dmitry.app.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.*


@Configuration
@EnableWebSocket
class WebSocketConfig(val customWebSocketHandler: CustomWebSocketHandler) : WebSocketConfigurer {

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(customWebSocketHandler, "/ws/notifications")
    }

}