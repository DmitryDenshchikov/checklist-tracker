package denshchikov.dmitry.app.controller

import denshchikov.dmitry.app.facade.TaskFacade
import denshchikov.dmitry.app.model.request.CreateTaskRequest
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
class TaskController(val facade: TaskFacade) {

    @PostMapping("/tasks", consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun createTask(@RequestBody req: CreateTaskRequest) = facade.createTask(req)

    @PutMapping("/tasks/{id}", produces = [APPLICATION_JSON_VALUE])
    fun completeTask(@PathVariable id: UUID) = facade.completeTask(id)

    @GetMapping("/tasks", produces = [APPLICATION_JSON_VALUE])
    fun getAllTasks() = facade.getAllTasks()

    @GetMapping("/tasks/{id}", produces = [APPLICATION_JSON_VALUE])
    fun getTask(@PathVariable id: UUID) = facade.getTask(id)

}