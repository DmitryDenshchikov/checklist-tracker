package denshchikov.dmitry.app.model.response

import denshchikov.dmitry.app.model.domain.Task
import java.util.*

data class TaskResponse(
    val id: UUID,
    val title: String,
    val description: String,
    val isCompleted: Boolean
)

fun Task.toTaskResponse() = TaskResponse(id, title, description, isCompleted)
