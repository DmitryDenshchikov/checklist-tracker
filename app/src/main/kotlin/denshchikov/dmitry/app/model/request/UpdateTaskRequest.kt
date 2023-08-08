package denshchikov.dmitry.app.model.request

import denshchikov.dmitry.app.model.domain.Task
import java.util.*

data class UpdateTaskRequest(
    val title: String,
    val description: String,
    val isCompleted: Boolean
)

fun UpdateTaskRequest.toTask(id: UUID) = Task(id, title, description, isCompleted)

