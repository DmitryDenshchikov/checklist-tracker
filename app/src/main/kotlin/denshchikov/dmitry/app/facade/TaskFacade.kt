package denshchikov.dmitry.app.facade

import denshchikov.dmitry.app.model.request.CreateTaskRequest
import java.util.*

interface TaskFacade {

    fun createTask(req: CreateTaskRequest): UUID

}
