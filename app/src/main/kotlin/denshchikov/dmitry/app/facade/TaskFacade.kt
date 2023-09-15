package denshchikov.dmitry.app.facade

import denshchikov.dmitry.app.model.request.CreateTaskRequest
import denshchikov.dmitry.app.model.request.UpdateTaskRequest
import denshchikov.dmitry.app.model.response.TaskResponse
import java.util.*

interface TaskFacade {

    fun createTask(req: CreateTaskRequest, createdBy: String): TaskResponse

    fun updateTask(id: UUID, req: UpdateTaskRequest, createdBy: String): TaskResponse

    fun getAllTasks(createdBy: String): List<TaskResponse>

    fun getTask(id: UUID, createdBy: String): TaskResponse

    fun getExpired(id: UUID, createdBy: String): List<TaskResponse>

}
