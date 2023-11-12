package denshchikov.dmitry.app.ws

import com.fasterxml.jackson.databind.ObjectMapper
import denshchikov.dmitry.app.service.TaskService
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus.SERVER_ERROR
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.AbstractWebSocketHandler

@Component
class CustomWebSocketHandler(val service: TaskService, val om: ObjectMapper) : AbstractWebSocketHandler() {

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val principal = session.principal

        if (principal?.name == null) {
            session.close(SERVER_ERROR.withReason("User must be authenticated"));
            return
        }

        val expiredTasks = service.getExpired(principal.name)

        val textMessage = TextMessage(om.writeValueAsString(expiredTasks))

        session.sendMessage(textMessage)
    }

}