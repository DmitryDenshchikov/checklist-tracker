package denshchikov.dmitry.app.config

import denshchikov.dmitry.app.service.TaskService
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus.SERVER_ERROR
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.AbstractWebSocketHandler

@Component
class CustomWebSocketHandler(val service: TaskService) : AbstractWebSocketHandler() {

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val principal = session.principal

        if (principal?.name == null) {
            session.close(SERVER_ERROR.withReason("User must be authenticated"));
            return
        }

        val expiredTasks = service.getExpired(principal.name)

        val textMessage = TextMessage(
            expiredTasks.asSequence()
                .map {
                    it.title
                }.joinToString(
                    separator = ",\r\n",
                    prefix = "Expired:\r\n"
                )
        )

        session.sendMessage(textMessage)
    }

}