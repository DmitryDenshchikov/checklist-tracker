package denshchikov.dmitry.app.dao

import denshchikov.dmitry.app.model.domain.Task
import denshchikov.dmitry.app.model.domain.Tasks
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class TaskDAOImpl : DAO<Task, UUID> {

    override fun create(task: Task): UUID {
        return transaction {
            val result = Tasks.insert {
                it[id] = task.id
                it[title] = task.title
                it[description] = task.description
                it[isCompleted] = task.isCompleted
            }
            result.resultedValues!![0][Tasks.id]
        }
    }

}