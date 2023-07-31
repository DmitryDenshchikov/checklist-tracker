package denshchikov.dmitry.app.service

import denshchikov.dmitry.app.dao.TaskDAOImpl
import denshchikov.dmitry.app.model.domain.Task
import org.springframework.stereotype.Component
import java.util.*

@Component
class TaskServiceImpl(val dao: TaskDAOImpl) : TaskService {

    override fun createTask(task: Task): UUID {
        return dao.create(task)
    }

}