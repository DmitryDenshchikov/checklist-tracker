package denshchikov.dmitry.app.facade

import denshchikov.dmitry.app.model.request.CreateTaskRequest
import denshchikov.dmitry.app.model.request.toTask
import denshchikov.dmitry.app.model.response.TaskCreatedResponse
import denshchikov.dmitry.app.service.TaskService
import org.springframework.stereotype.Component

@Component
class TaskFacadeImpl(val service: TaskService) : TaskFacade {

    override fun createTask(req: CreateTaskRequest): TaskCreatedResponse {
        service.createTask(req.toTask()).let {
            return TaskCreatedResponse(it);
        }
    }

}