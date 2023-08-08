package denshchikov.dmitry.app.facade

import denshchikov.dmitry.app.model.request.UpdateTaskRequest
import denshchikov.dmitry.app.model.response.TaskResponse
import java.util.*

interface TaskFacade {

    fun createTask(req: UpdateTaskRequest): TaskResponse

    fun updateTask(id: UUID, req: UpdateTaskRequest): TaskResponse

    fun getAllTasks(): List<TaskResponse>

    fun getTask(id: UUID): TaskResponse

}
