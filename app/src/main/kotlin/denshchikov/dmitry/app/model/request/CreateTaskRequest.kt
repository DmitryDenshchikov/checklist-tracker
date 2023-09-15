package denshchikov.dmitry.app.model.request

import denshchikov.dmitry.app.model.domain.Task
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME
import java.time.Instant
import java.util.*

data class CreateTaskRequest(
    val title: String,
    val description: String,
    @DateTimeFormat(iso = DATE_TIME) val expirationDate: Instant
)

fun CreateTaskRequest.toTask(creator: String) =
    Task(UUID.randomUUID(), title, description, false, expirationDate, creator)