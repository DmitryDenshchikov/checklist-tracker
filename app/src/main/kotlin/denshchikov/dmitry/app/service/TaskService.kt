package denshchikov.dmitry.app.service

import denshchikov.dmitry.app.model.domain.Task
import java.util.*

interface TaskService {

    fun createTask(task: Task): Task

    fun updateTask(task: Task): Task

    fun getAllTasks(createdBy: String): List<Task>

    fun getTask(id: UUID, createdBy: String): Task

    fun getExpired(createdBy: String): List<Task>

}