package denshchikov.dmitry.app.dao

import denshchikov.dmitry.app.model.domain.Task
import java.util.UUID

interface TaskDAO : DAO<UUID, Task>{

    fun get(id: UUID, createdBy: String): Task

    fun get(createdBy: String): List<Task>

    fun getExpired(createdBy: String): List<Task>

}