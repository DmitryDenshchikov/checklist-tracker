package denshchikov.dmitry.app.dao

import denshchikov.dmitry.app.model.domain.Task
import denshchikov.dmitry.app.model.domain.Tasks
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import org.springframework.stereotype.Component
import java.util.*

@Component
class TaskDAOImpl : DAO<UUID, Task> {

    override fun create(task: Task): Task {
        return transaction {
            val result = Tasks.insert {
                it[id] = task.id
                it[title] = task.title
                it[description] = task.description
                it[isCompleted] = task.isCompleted
            }
            toTask(result.resultedValues!![0])
        }
    }

    override fun update(task: Task): Boolean {
        return transaction {
            Tasks.update({ Tasks.id eq task.id }) {
                it[id] = task.id
                it[title] = task.title
                it[description] = task.description
                it[isCompleted] = task.isCompleted
            } > 0
        }
    }

    override fun get(id: UUID): Task {
        return transaction {
            Tasks.select {
                Tasks.id eq id
            }.single().let {
                toTask(it)
            }
        }
    }


    private fun toTask(row: ResultRow) = Task(
        row[Tasks.id],
        row[Tasks.title],
        row[Tasks.description],
        row[Tasks.isCompleted]
    )

}