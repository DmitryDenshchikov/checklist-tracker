package denshchikov.dmitry.app.dao

import denshchikov.dmitry.app.model.domain.Task
import denshchikov.dmitry.app.model.domain.Tasks
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

@Component
class TaskDAOImpl : DAO<Task> {

    override fun create(task: Task) {
        transaction {
            Tasks.insert {
                it[title] = task.title
                it[description] = task.description
                it[isCompleted] = task.isCompleted
            }
        }
    }

}