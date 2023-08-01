package denshchikov.dmitry.app.controller

import denshchikov.dmitry.app.facade.TaskFacade
import denshchikov.dmitry.app.model.request.CreateTaskRequest
import denshchikov.dmitry.app.model.response.TaskResponse
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class TaskController(val facade: TaskFacade) {

    @PostMapping("/tasks", consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun createTask(@RequestBody req: CreateTaskRequest): TaskResponse {
        return facade.createTask(req);
    }

    @PutMapping("/tasks/{id}")
    fun completeTask(@PathVariable id: UUID): TaskResponse {
        return facade.completeTask(id);
    }

}