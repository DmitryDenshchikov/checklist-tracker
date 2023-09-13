package denshchikov.dmitry.app.model.request

import denshchikov.dmitry.app.model.domain.Task
import org.springframework.format.annotation.DateTimeFormat
import java.time.Instant
import java.util.*

data class CreateTaskRequest(
    val title: String,
    val description: String,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    val expirationDate: Instant
)

fun CreateTaskRequest.toTask() = Task(UUID.randomUUID(), title, description, expirationDate = expirationDate)