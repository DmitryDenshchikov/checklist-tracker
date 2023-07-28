package denshchikov.dmitry.app.facade

import denshchikov.dmitry.app.model.request.CreateTaskRequest

interface TaskFacade {

    fun createTask(req: CreateTaskRequest)

}
