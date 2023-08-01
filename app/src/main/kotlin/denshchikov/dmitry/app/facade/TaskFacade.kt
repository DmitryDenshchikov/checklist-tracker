package denshchikov.dmitry.app.facade

import denshchikov.dmitry.app.model.request.CreateTaskRequest
import denshchikov.dmitry.app.model.response.TaskResponse
import java.util.*

interface TaskFacade {

    fun createTask(req: CreateTaskRequest): TaskResponse

    fun completeTask(id: UUID): TaskResponse

}
