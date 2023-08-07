package denshchikov.dmitry.app.facade

import denshchikov.dmitry.app.model.request.CreateTaskRequest
import denshchikov.dmitry.app.model.request.toTask
import denshchikov.dmitry.app.model.response.toTaskResponse
import denshchikov.dmitry.app.service.TaskService
import org.springframework.stereotype.Component
import java.util.*

@Component
class TaskFacadeImpl(val service: TaskService) : TaskFacade {

    override fun createTask(req: CreateTaskRequest) = with(req.toTask()) {
        service.createTask(this).toTaskResponse()
    }

    override fun completeTask(id: UUID) = service.completeTask(id).toTaskResponse()

    override fun getAllTasks() = service.getAllTasks().map { it.toTaskResponse() }

    override fun getTask(id: UUID) = service.getTask(id).toTaskResponse()

}