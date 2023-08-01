package denshchikov.dmitry.app.service

import denshchikov.dmitry.app.model.domain.Task
import java.util.*

interface TaskService {

    fun createTask(task: Task): Task

    fun completeTask(id: UUID): Task

}