package denshchikov.dmitry.app.model.domain

import org.jetbrains.exposed.sql.Table

class Task(
    title: String,
    val description: String,
    val isCompleted: Boolean = false
) : Item(title), Completable {

    override fun toString(): String {
        return "Task(title='$title', description='$description', isCompleted=$isCompleted)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Task) return false
        if (!super.equals(other)) return false

        if (description != other.description) return false
        if (isCompleted != other.isCompleted) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + isCompleted.hashCode()
        return result
    }

}

object Tasks : Table("task") {
    val title = varchar("title", 255)
    val description = varchar("description", 255)
    val isCompleted = bool("is_completed")

    override val primaryKey = PrimaryKey(title, description)
}