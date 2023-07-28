package denshchikov.dmitry.app.controller

import denshchikov.dmitry.app.facade.TaskFacade
import denshchikov.dmitry.app.model.request.CreateTaskRequest
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TaskController(val facade: TaskFacade) {

    @PostMapping("/tasks", consumes = [APPLICATION_JSON_VALUE])
    fun createTask(@RequestBody req: CreateTaskRequest) {
        facade.createTask(req);
    }

}