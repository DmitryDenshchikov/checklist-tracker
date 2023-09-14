package denshchikov.dmitry.app.facade

import denshchikov.dmitry.app.model.request.CreateTaskRequest
import denshchikov.dmitry.app.model.request.UpdateTaskRequest
import denshchikov.dmitry.app.model.request.toTask
import denshchikov.dmitry.app.model.response.TaskResponse
import denshchikov.dmitry.app.model.response.toTaskResponse
import denshchikov.dmitry.app.service.TaskService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class TaskFacadeImpl(val service: TaskService) : TaskFacade {

    override fun createTask(req: CreateTaskRequest): TaskResponse {
        val author = SecurityContextHolder.getContext().authentication?.name
            ?: throw IllegalStateException("User must be authenticated")

        return req.toTask(author).let {
            service.createTask(it).toTaskResponse()
        }
    }

    override fun updateTask(id: UUID, req: UpdateTaskRequest): TaskResponse {
        val author = SecurityContextHolder.getContext().authentication?.name
            ?: throw IllegalStateException("User must be authenticated")

        return req.toTask(id, author).let {
            service.updateTask(it).toTaskResponse()
        }
    }

    override fun getAllTasks(): List<TaskResponse> {
        val author = SecurityContextHolder.getContext().authentication?.name
            ?: throw IllegalStateException("User must be authenticated")

        return service.getAllTasks(author).map { it.toTaskResponse() }
    }

    override fun getTask(id: UUID): TaskResponse {
        val author = SecurityContextHolder.getContext().authentication?.name
            ?: throw IllegalStateException("User must be authenticated")

        return service.getTask(id, author).toTaskResponse()
    }

}