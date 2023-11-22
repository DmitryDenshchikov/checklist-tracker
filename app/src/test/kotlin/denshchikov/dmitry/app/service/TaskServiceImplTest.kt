package denshchikov.dmitry.app.service

import denshchikov.dmitry.app.TestDataProvider.randomCompletedTask
import denshchikov.dmitry.app.TestDataProvider.randomTask
import denshchikov.dmitry.app.TestDataProvider.testUser
import denshchikov.dmitry.app.config.IntegrationTest
import denshchikov.dmitry.app.model.domain.Task
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class TaskServiceImplTest : IntegrationTest {

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
    fun should_CreateUncompletedTask_When_CorrectParamsProvided() {
        // Given
        val task = randomTask()
        val taskId = task.id

        // When
        taskService.createTask(task)
        val createdTask = taskService.getTask(taskId, testUser)

        // Then
        then(createdTask)
            .isEqualTo(task)
    }

    @Test
    fun should_UpdateTaskTitleAndDescription_When_NewValuesProvided() {
        // Given
        val task = randomTask()
        val taskId = task.id

        // When
        taskService.createTask(task)

        val taskForUpdate = with(task) {
            Task(id, "New Test Title", "New Test Description", expirationDate, createdBy, false)
        }

        taskService.updateTask(taskForUpdate)
        val updatedTask = taskService.getTask(taskId, testUser)

        // Then
        then(updatedTask)
            .isEqualTo(taskForUpdate)
    }

    @Test
    fun should_returnAllUserTasks_When_TasksCreated() {
        // Given
        val firstTask = randomTask()
        val secondTask = randomTask()

        // When
        taskService.createTask(firstTask)
        taskService.createTask(secondTask)

        val tasks = taskService.getAllTasks(testUser)

        // Then
        then(tasks[0])
            .isEqualTo(firstTask)

        then(tasks[1])
            .isEqualTo(secondTask)
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

}