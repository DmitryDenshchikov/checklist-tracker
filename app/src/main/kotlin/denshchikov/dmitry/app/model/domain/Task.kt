package denshchikov.dmitry.app.model.domain

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp
import java.time.Instant
import java.util.*

data class Task(
    override val id: UUID,
    override val title: String,
    val description: String,
    override val isCompleted: Boolean = false,
    val expirationDate: Instant,
    val createdBy: String
) : Item, Completable

object Tasks : Table("task") {
    val id = uuid("id")
    val title = varchar("title", 255)
    val description = varchar("description", 255)
    val isCompleted = bool("is_completed")
    val expirationDate = timestamp("expiration_date")
    val createdBy = varchar("created_by", 255)

    override val primaryKey = PrimaryKey(title, description)
}