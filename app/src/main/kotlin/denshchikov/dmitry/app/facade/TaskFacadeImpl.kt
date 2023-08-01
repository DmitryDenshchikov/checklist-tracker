package denshchikov.dmitry.app.facade

import denshchikov.dmitry.app.model.request.CreateTaskRequest
import denshchikov.dmitry.app.model.request.toTask
import denshchikov.dmitry.app.model.response.TaskResponse
import denshchikov.dmitry.app.model.response.toTaskResponse
import denshchikov.dmitry.app.service.TaskService
import org.springframework.stereotype.Component
import java.util.*

@Component
class TaskFacadeImpl(val service: TaskService) : TaskFacade {

    override fun createTask(req: CreateTaskRequest): TaskResponse {
        return with(req.toTask()) {
            service.createTask(this).toTaskResponse()
        }
    }

    override fun completeTask(id: UUID): TaskResponse {
        return service.completeTask(id).toTaskResponse()
    }

}