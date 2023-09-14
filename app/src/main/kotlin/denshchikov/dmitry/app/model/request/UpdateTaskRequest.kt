package denshchikov.dmitry.app.model.request

import denshchikov.dmitry.app.model.domain.Task
import org.springframework.format.annotation.DateTimeFormat
import java.time.Instant
import java.util.*

data class UpdateTaskRequest(
    val title: String,
    val description: String,
    val isCompleted: Boolean,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) val expirationDate: Instant
)

fun UpdateTaskRequest.toTask(id: UUID, creator: String) =
    Task(id, title, description, isCompleted, expirationDate, creator)
