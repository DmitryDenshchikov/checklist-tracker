package denshchikov.dmitry.app.service

import denshchikov.dmitry.app.dao.TaskDAOImpl
import denshchikov.dmitry.app.model.domain.Task
import org.springframework.stereotype.Component
import java.util.*

@Component
class TaskServiceImpl(val dao: TaskDAOImpl) : TaskService {

    override fun createTask(task: Task) = dao.create(task)

    override fun updateTask(task: Task) = when (dao.update(task)) {
        true -> task
        else -> throw RuntimeException("Couldn't update task $task")
    }

    override fun getAllTasks(createdBy: String): List<Task> = dao.get(createdBy)

    override fun getTask(id: UUID, createdBy: String) = dao.get(id, createdBy)

}