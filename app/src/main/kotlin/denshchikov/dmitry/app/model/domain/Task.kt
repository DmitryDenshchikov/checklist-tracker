package denshchikov.dmitry.app.model.domain

import org.jetbrains.exposed.sql.Table
import java.util.*

data class Task(
    override val id: UUID,
    override val title: String,
    val description: String,
    override val isCompleted: Boolean = false,
) : Item, Completable

object Tasks : Table("task") {
    val id = uuid("id")
    val title = varchar("title", 255)
    val description = varchar("description", 255)
    val isCompleted = bool("is_completed")

    override val primaryKey = PrimaryKey(title, description)
}