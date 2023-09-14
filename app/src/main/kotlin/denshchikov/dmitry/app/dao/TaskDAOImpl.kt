package denshchikov.dmitry.app.dao

import denshchikov.dmitry.app.model.domain.Task
import denshchikov.dmitry.app.model.domain.Tasks
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component
import java.util.*

@Component
class TaskDAOImpl : TaskDAO {

    override fun create(entity: Task): Task {
        return transaction {
            val result = Tasks.insert {
                it[id] = entity.id
                it[title] = entity.title
                it[description] = entity.description
                it[isCompleted] = entity.isCompleted
                it[expirationDate] = entity.expirationDate
                it[createdBy] = entity.createdBy
            }
            toTask(result.resultedValues!![0])
        }
    }

    override fun update(entity: Task): Boolean {
        return transaction {
            Tasks.update({ (Tasks.id eq entity.id) and (Tasks.createdBy eq entity.createdBy) }) {
                it[id] = entity.id
                it[title] = entity.title
                it[description] = entity.description
                it[isCompleted] = entity.isCompleted
                it[expirationDate] = entity.expirationDate
            } > 0
        }
    }

    override fun get(): List<Task> {
        return transaction {
            Tasks.selectAll().map {
                toTask(it)
            }
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

    override fun get(id: UUID, createdBy: String): Task {
        return transaction {
            Tasks.select {
                (Tasks.id eq id) and (Tasks.createdBy eq createdBy)
            }.single().let {
                toTask(it)
            }
        }
    }

    override fun get(createdBy: String): List<Task> {
        return transaction {
            Tasks.select {
                Tasks.createdBy eq createdBy
            }.map {
                toTask(it)
            }
        }
    }


    private fun toTask(row: ResultRow) = Task(
        row[Tasks.id],
        row[Tasks.title],
        row[Tasks.description],
        row[Tasks.isCompleted],
        row[Tasks.expirationDate],
        row[Tasks.createdBy]
    )

}