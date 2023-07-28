package denshchikov.dmitry.app.model.request

import denshchikov.dmitry.app.model.domain.Task

data class CreateTaskRequest(
    val title: String,
    val description: String
)

fun CreateTaskRequest.toTask() = Task(title, description)

