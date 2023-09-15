package denshchikov.dmitry.app.facade

import denshchikov.dmitry.app.model.request.CreateTaskRequest
import denshchikov.dmitry.app.model.request.UpdateTaskRequest
import denshchikov.dmitry.app.model.request.toTask
import denshchikov.dmitry.app.model.response.toTaskResponse
import denshchikov.dmitry.app.service.TaskService
import org.springframework.stereotype.Component
import java.util.*

@Component
class TaskFacadeImpl(val service: TaskService) : TaskFacade {

    override fun createTask(req: CreateTaskRequest, createdBy: String) = req.toTask(createdBy).let {
        service.createTask(it).toTaskResponse()
    }

    override fun updateTask(id: UUID, req: UpdateTaskRequest, createdBy: String) = req.toTask(id, createdBy).let {
        service.updateTask(it).toTaskResponse()
    }

    override fun getAllTasks(createdBy: String) = service.getAllTasks(createdBy).map { it.toTaskResponse() }

    override fun getTask(id: UUID, createdBy: String) = service.getTask(id, createdBy).toTaskResponse()

    override fun getExpired(id: UUID, createdBy: String) = service.getExpired(createdBy).map { it.toTaskResponse() }

}