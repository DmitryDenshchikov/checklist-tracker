package denshchikov.dmitry.app.service

import denshchikov.dmitry.app.config.IntegrationTest
import denshchikov.dmitry.app.model.domain.Task
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.Instant
import java.util.*

@SpringBootTest
internal class TaskServiceImplTest : IntegrationTest {

    @Autowired
    lateinit var taskService: TaskService

    @Test
    fun should_MarkTaskAsCompleted_When_TaskCreated() {
        // Given
        val taskId = UUID.randomUUID()
        val author = "testUser"

        val taskForCreate = Task(taskId, "Test title", "Test description", Instant.now(), author)
        val taskForUpdate = Task(taskId, "Test title", "Test description", Instant.now(), author, true)

        // When
        taskService.createTask(taskForCreate)
        taskService.updateTask(taskForUpdate)
        val result = taskService.getTask(taskId, author)

        // Then
        then(result.isCompleted)
            .isTrue
    }

    @Test
    fun should_ReturnExpiredTask_When_TaskExpired() {
        // Given
        val taskId = UUID.randomUUID()
        val author = "testUser"
        val taskForCreate = Task(taskId, "Test title", "Test description", Instant.now(), author)

        // When
        taskService.createTask(taskForCreate)
        Thread.sleep(1000)
        val result = taskService.getExpired(author)

        // Then
        then(result)
            .singleElement()
            .extracting(Task::id)
            .isEqualTo(taskId)
    }

}