package denshchikov.dmitry.app.service

import denshchikov.dmitry.app.config.IntegrationTest
import denshchikov.dmitry.app.model.domain.Task
import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.Instant
import java.util.*

@SpringBootTest
internal class TaskServiceImplTest : IntegrationTest {

    val testUser = "test-user";

    @Autowired
    lateinit var taskService: TaskService


    @AfterEach
    fun clearContext() {
        with(IntegrationTest.container) {
            val execInContainer = execInContainer(
                "psql", "-U", username, "-d", databaseName,
                "-c", "TRUNCATE TABLE task;"
            )
            println(execInContainer)
        }
    }

    @Test
    fun should_MarkTaskAsCompleted_When_TaskCreated() {
        // Given
        val task = randomTask()
        val taskId = task.id
        val taskForUpdate = with(task) {
            Task(id, title, description, expirationDate, createdBy, true)
        }

        // When
        taskService.createTask(task)
        taskService.updateTask(taskForUpdate)
        val result = taskService.getTask(taskId, testUser)

        // Then
        then(result.isCompleted)
            .isTrue
    }

    @Test
    fun should_ReturnExpiredTask_When_TaskExpired() {
        // Given
        val task = randomTask()
        val taskId = task.id

        // When
        taskService.createTask(task)
        Thread.sleep(1000)
        val result = taskService.getExpired(testUser)

        // Then
        then(result)
            .singleElement()
            .extracting(Task::id)
            .isEqualTo(taskId)
    }

    @Test
    fun should_ReturnNoExpiredTasks_When_TaskIsCompletedButDeadlinePassed() {
        // Given
        val task = randomCompletedTask()

        // When
        taskService.createTask(task)
        Thread.sleep(1000)
        val result = taskService.getExpired(testUser)

        // Then
        then(result)
            .isEmpty()
    }

    @Test
    fun should_ReturnMultipleEntries_When_MultipleTasksCreated() {
        // Given
        val firstTask = randomTask()
        val secondTask = randomTask()
        val firstId = firstTask.id
        val secondId = secondTask.id

        // When
        taskService.createTask(firstTask)
        taskService.createTask(secondTask)
        val result = taskService.getAllTasks(testUser)

        // Then
        then(result)
            .hasSize(2)
            .extracting("id")
            .containsExactlyInAnyOrder(firstId, secondId)
    }

    private fun randomTask() = Task(
        UUID.randomUUID(),
        randomAlphabetic(10),
        randomAlphabetic(50),
        Instant.now(),
        testUser
    )

    private fun randomCompletedTask() = Task(
        UUID.randomUUID(),
        randomAlphabetic(10),
        randomAlphabetic(50),
        Instant.now(),
        testUser,
        true
    )

}