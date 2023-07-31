package denshchikov.dmitry.app.model.request

import denshchikov.dmitry.app.model.domain.Task
import java.util.*

data class CreateTaskRequest(
    val title: String,
    val description: String
)

fun CreateTaskRequest.toTask() = Task(UUID.randomUUID(), title, description)

