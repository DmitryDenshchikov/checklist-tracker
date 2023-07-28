package denshchikov.dmitry.app.service

import denshchikov.dmitry.app.model.domain.Task
import org.springframework.stereotype.Component

@Component
class TaskServiceImpl : TaskService {

    override fun createTask(task: Task) {
        print(task)
    }

}