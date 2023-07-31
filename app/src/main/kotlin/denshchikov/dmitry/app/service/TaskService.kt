package denshchikov.dmitry.app.service

import denshchikov.dmitry.app.model.domain.Task
import java.util.*

interface TaskService {

    fun createTask(task: Task): UUID

}