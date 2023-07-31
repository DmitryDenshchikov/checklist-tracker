package denshchikov.dmitry.app.facade

import denshchikov.dmitry.app.model.request.CreateTaskRequest
import denshchikov.dmitry.app.model.request.toTask
import denshchikov.dmitry.app.service.TaskService
import org.springframework.stereotype.Component
import java.util.*

@Component
class TaskFacadeImpl(val service: TaskService) : TaskFacade {

    override fun createTask(req: CreateTaskRequest): UUID {
        return service.createTask(req.toTask())
    }

}