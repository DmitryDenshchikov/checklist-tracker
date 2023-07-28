package denshchikov.dmitry.app.model.domain

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