package denshchikov.dmitry.app.controller

import denshchikov.dmitry.app.facade.TaskFacade
import denshchikov.dmitry.app.model.request.UpdateTaskRequest
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/tasks")
class TaskController(val facade: TaskFacade) {

    @PostMapping(consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun createTask(@RequestBody req: UpdateTaskRequest) = facade.createTask(req)

    @PutMapping("{id}", produces = [APPLICATION_JSON_VALUE])
    fun updateTask(@PathVariable id: UUID, @RequestBody req: UpdateTaskRequest) = facade.updateTask(id, req)

    @GetMapping(produces = [APPLICATION_JSON_VALUE])
    fun getAllTasks() = facade.getAllTasks()

    @GetMapping("{id}", produces = [APPLICATION_JSON_VALUE])
    fun getTask(@PathVariable id: UUID) = facade.getTask(id)

}