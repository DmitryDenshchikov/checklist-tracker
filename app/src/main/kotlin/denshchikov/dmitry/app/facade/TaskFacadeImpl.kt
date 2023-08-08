package denshchikov.dmitry.app.facade

import denshchikov.dmitry.app.model.request.UpdateTaskRequest
import denshchikov.dmitry.app.model.request.toTask
import denshchikov.dmitry.app.model.response.toTaskResponse
import denshchikov.dmitry.app.service.TaskService
import org.springframework.stereotype.Component
import java.util.*

@Component
class TaskFacadeImpl(val service: TaskService) : TaskFacade {

    override fun createTask(req: UpdateTaskRequest) = req.toTask().let {
        service.createTask(it).toTaskResponse()
    }

    override fun updateTask(id: UUID, req: UpdateTaskRequest) = req.toTask(id).let {
        service.updateTask(it).toTaskResponse()
    }

    override fun getAllTasks() = service.getAllTasks().map { it.toTaskResponse() }

    override fun getTask(id: UUID) = service.getTask(id).toTaskResponse()

}