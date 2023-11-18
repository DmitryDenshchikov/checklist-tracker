package denshchikov.dmitry.app

import denshchikov.dmitry.app.model.domain.Task
import denshchikov.dmitry.app.model.request.CreateTaskRequest
import denshchikov.dmitry.app.model.request.UpdateTaskRequest
import denshchikov.dmitry.app.model.response.TaskResponse
import org.apache.commons.lang3.RandomStringUtils
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

object TestDataProvider {

    const val testUser = "test-user";


    fun randomTask() = Task(
        UUID.randomUUID(),
        RandomStringUtils.randomAlphabetic(10),
        RandomStringUtils.randomAlphabetic(50),
        Instant.now().truncatedTo(ChronoUnit.MILLIS),
        testUser
    )

    fun randomCompletedTask() = Task(
        UUID.randomUUID(),
        RandomStringUtils.randomAlphabetic(10),
        RandomStringUtils.randomAlphabetic(50),
        Instant.now().truncatedTo(ChronoUnit.MILLIS),
        testUser,
        true
    )

    fun randomTaskResponse() = TaskResponse(
        UUID.randomUUID(),
        RandomStringUtils.randomAlphabetic(10),
        RandomStringUtils.randomAlphabetic(50),
        Instant.now().truncatedTo(ChronoUnit.MILLIS),
        false
    )

    fun randomCompletedTaskResponse() = TaskResponse(
        UUID.randomUUID(),
        RandomStringUtils.randomAlphabetic(10),
        RandomStringUtils.randomAlphabetic(50),
        Instant.now().truncatedTo(ChronoUnit.MILLIS),
        true
    )

    fun randomCreateTaskRequest() = CreateTaskRequest(
        RandomStringUtils.randomAlphabetic(10),
        RandomStringUtils.randomAlphabetic(50),
        Instant.now().truncatedTo(ChronoUnit.MILLIS)
    )

    fun randomUpdateTaskRequest() = UpdateTaskRequest(
        RandomStringUtils.randomAlphabetic(10),
        RandomStringUtils.randomAlphabetic(50),
        true,
        Instant.now().truncatedTo(ChronoUnit.MILLIS)
    )

}