package denshchikov.dmitry.app.service

import denshchikov.dmitry.app.dao.TaskDAOImpl
import denshchikov.dmitry.app.model.domain.Task
import org.springframework.stereotype.Component
import java.util.*

@Component
class TaskServiceImpl(val dao: TaskDAOImpl) : TaskService {

    override fun createTask(task: Task): Task {
        return dao.create(task)
    }

    override fun completeTask(id: UUID): Task {
        return dao.get(id).copy(isCompleted = true).let {
            if (dao.update(it)) {
                it
            } else {
                throw RuntimeException("Couldn't update task $id")
            }
        }
    }

}