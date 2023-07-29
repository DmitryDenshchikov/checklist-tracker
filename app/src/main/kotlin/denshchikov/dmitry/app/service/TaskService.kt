package denshchikov.dmitry.app.service

import denshchikov.dmitry.app.model.domain.Task

interface TaskService {

    fun createTask(task: Task)

}