package denshchikov.dmitry.app.controller

import denshchikov.dmitry.app.facade.TaskFacade
import denshchikov.dmitry.app.model.request.CreateTaskRequest
import denshchikov.dmitry.app.model.request.UpdateTaskRequest
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/tasks")
class TaskController(val facade: TaskFacade) {

    @PostMapping(consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun createTask(
        @RequestBody req: CreateTaskRequest, authentication: Authentication
    ) = facade.createTask(req, authentication.name)

    @PutMapping("{id}", produces = [APPLICATION_JSON_VALUE])
    fun updateTask(
        @PathVariable id: UUID, @RequestBody req: UpdateTaskRequest, authentication: Authentication
    ) = facade.updateTask(id, req, authentication.name)

    @GetMapping(produces = [APPLICATION_JSON_VALUE])
    fun getAllTasks(
        authentication: Authentication
    ) = facade.getAllTasks(authentication.name)

    @GetMapping("{id}", produces = [APPLICATION_JSON_VALUE])
    fun getTask(
        @PathVariable id: UUID, authentication: Authentication
    ) = facade.getTask(id, authentication.name)

}