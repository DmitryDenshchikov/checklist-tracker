package denshchikov.dmitry.app.service

import denshchikov.dmitry.app.model.domain.Task
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.sql.ResultSet
import java.sql.Timestamp
import java.time.Instant
import java.util.*

@Component
class TaskServiceImpl(val template: JdbcTemplate) : TaskService {

    private val rm = RowMapper { rs: ResultSet, _: Int ->
        Task(
            UUID.fromString(rs.getString("id")),
            rs.getString("title"),
            rs.getString("description"),
            rs.getTimestamp("expiration_date").toInstant(),
            rs.getString("created_by"),
            rs.getBoolean("is_completed")
        )
    }

    @Transactional
    override fun createTask(task: Task) = task.apply {
        val expirationTimestamp = Timestamp.from(expirationDate)
        template.update(
            "INSERT INTO task(title, description, is_completed, id, expiration_date, created_by) " +
                    "VALUES (?, ?, ?, ?, ?, ?);", title, description, isCompleted, id, expirationTimestamp, createdBy
        )
    }

    @Transactional
    override fun updateTask(task: Task) = task.apply {
        val expirationTimestamp = Timestamp.from(expirationDate)
        template.update(
            "UPDATE task SET title=?, description=?, is_completed=?, expiration_date=?, created_by=? " +
                    "WHERE id = ?;", title, description, isCompleted, expirationTimestamp, createdBy, id
        )
    }

    override fun getAllTasks(createdBy: String): List<Task> =
        template.query("SELECT * FROM task WHERE created_by = ?;", rm, createdBy)

    override fun getTask(id: UUID, createdBy: String) =
        template.queryForObject("SELECT * FROM task WHERE id = ?;", rm, id)
            ?: throw NoSuchElementException("Task $id not found")

    override fun getExpired(createdBy: String): List<Task> {
        val timestamp = Timestamp.from(Instant.now())
        return template.query(
            "SELECT * FROM task WHERE created_by = ? AND is_completed = false AND expiration_date < ?;",
            rm, createdBy, timestamp
        )
    }

}