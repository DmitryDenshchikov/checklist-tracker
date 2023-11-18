package denshchikov.dmitry.app.model.response

import denshchikov.dmitry.app.model.domain.Task
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME
import java.time.Instant
import java.util.*

data class TaskResponse(
    val id: UUID,
    val title: String,
    val description: String,
    @DateTimeFormat(iso = DATE_TIME) val expirationDate: Instant,
    val isCompleted: Boolean
)

fun Task.toTaskResponse() = TaskResponse(id, title, description, expirationDate, isCompleted)
