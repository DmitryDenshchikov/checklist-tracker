package denshchikov.dmitry.app.controller

import denshchikov.dmitry.app.TestDataProvider.randomCompletedTaskResponse
import denshchikov.dmitry.app.TestDataProvider.randomCreateTaskRequest
import denshchikov.dmitry.app.TestDataProvider.randomTaskResponse
import denshchikov.dmitry.app.TestDataProvider.randomUpdateTaskRequest
import denshchikov.dmitry.app.TestDataProvider.testUser
import denshchikov.dmitry.app.config.SecurityConfig
import denshchikov.dmitry.app.facade.TaskFacade
import denshchikov.dmitry.app.model.response.TaskResponse
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put
import java.util.*


@WebMvcTest(TaskController::class)
@Import(SecurityConfig::class)
internal class TaskControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var facade: TaskFacade


    @Test
    fun should_BeConsideredAsUnauthorized_When_RequestWithoutTokenForTasksRetrieval() {
        // When & Then
        mvc.get("/tasks")
            .andExpect {
                status { isUnauthorized() }
            }
    }

    @Test
    fun should_BeConsideredAsUnauthorized_When_RequestWithoutTokenForTaskRetrieval() {
        // Given & When & Then
        mvc.get("/tasks/${UUID.randomUUID()}")
            .andExpect {
                status { isUnauthorized() }
            }
    }

    @Test
    fun should_BeConsideredAsUnauthorized_When_RequestWithoutTokenForTaskUpdate() {
        // Given & When & Then
        mvc.put("/tasks/${UUID.randomUUID()}")
            .andExpect {
                status { isUnauthorized() }
            }
    }

    @Test
    fun should_BeConsideredAsUnauthorized_When_RequestWithoutTokenForTaskCreation() {
        // Given & When & Then
        mvc.post("/task")
            .andExpect {
                status { isUnauthorized() }
            }
    }


    @Test
    @WithMockUser(username = testUser, authorities = ["SCOPE_incorrect-test-scope"])
    fun should_BeForbidden_When_RequestForTasksRetrievalWithIncorrectAuthorities() {
        // Given & When & Then
        mvc.get("/tasks")
            .andExpect {
                status { isForbidden() }
            }
    }

    @Test
    @WithMockUser(username = testUser, authorities = ["SCOPE_incorrect-test-scope"])
    fun should_BeForbidden_When_RequestForTaskRetrievalWithIncorrectAuthorities() {
        // Given & When & Then
        mvc.get("/tasks/${UUID.randomUUID()}")
            .andExpect {
                status { isForbidden() }
            }
    }

    @Test
    @WithMockUser(username = testUser, authorities = ["SCOPE_incorrect-test-scope"])
    fun should_BeForbidden_When_RequestForTaskUpdateWithIncorrectAuthorities() {
        // Given & When & Then
        mvc.put("/tasks/${UUID.randomUUID()}")
            .andExpect {
                status { isForbidden() }
            }
    }

    @Test
    @WithMockUser(username = testUser, authorities = ["SCOPE_incorrect-test-scope"])
    fun should_BeForbidden_When_RequestForTaskCreationWithIncorrectAuthorities() {
        // Given & When & Then
        mvc.post("/task")
            .andExpect {
                status { isForbidden() }
            }
    }

    @Test
    @WithMockUser(username = testUser, authorities = ["SCOPE_user"])
    fun should_ReturnTasks_When_RequestForTasksRetrievalAndServiceProvidesData() {
        // Given
        Mockito.`when`(facade.getAllTasks(testUser))
            .thenReturn(listOf(randomTaskResponse(), randomCompletedTaskResponse()))

        // When & Then
        mvc.get("/tasks")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                content {
                    jsonPath("$.length()", `is`(2))
                    jsonPath("$[*].id", notNullValue())
                    jsonPath("$[*].title", notNullValue())
                    jsonPath("$[*].description", notNullValue())
                    jsonPath("$[*].expirationDate", notNullValue())
                    jsonPath("$[*].isCompleted", notNullValue())
                }
            }
    }

    @Test
    @WithMockUser(username = testUser, authorities = ["SCOPE_user"])
    fun should_ReturnTask_When_RequestForTaskRetrievalAndServiceProvidesData() {
        // Given
        val task = randomCompletedTaskResponse()
        val taskId = task.id
        Mockito.`when`(facade.getTask(taskId, testUser))
            .thenReturn(task)

        // When & Then
        mvc.get("/tasks/$taskId")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                content {
                    jsonPath("$.id", `is`(taskId.toString()))
                    jsonPath("$.title", `is`(task.title))
                    jsonPath("$.description", `is`(task.description))
                    jsonPath("$.expirationDate", `is`(task.expirationDate.toString()))
                    jsonPath("$.isCompleted", `is`(task.isCompleted))
                }
            }
    }

    @Test
    @WithMockUser(username = testUser, authorities = ["SCOPE_user"])
    fun should_CreateTask_When_RequestForTaskCreation() {
        // Given
        val req = randomCreateTaskRequest()
        val reqString = """
            {
                "title": "${req.title}",
                "description": "${req.description}",
                "expirationDate": "${req.expirationDate}"
            }
        """.trimIndent()

        val response = TaskResponse(UUID.randomUUID(), req.title, req.description, req.expirationDate, false)

        // When
        Mockito.`when`(facade.createTask(req, testUser))
            .thenReturn(response)

        // Then
        mvc.post("/tasks") {
            contentType = MediaType.APPLICATION_JSON
            content = reqString
        }
            .andExpect {
                status { isOk() }
                content {
                    jsonPath("$.id", `is`(response.id.toString()))
                    jsonPath("$.title", `is`(req.title))
                    jsonPath("$.description", `is`(req.description))
                    jsonPath("$.expirationDate", `is`(req.expirationDate.toString()))
                    jsonPath("$.isCompleted", `is`(false))
                }
            }
    }

    @Test
    @WithMockUser(username = testUser, authorities = ["SCOPE_user"])
    fun should_UpdateTask_When_RequestForTaskUpdateAndServiceUpdatesData() {
        // Given
        val req = randomUpdateTaskRequest()
        val taskId = UUID.randomUUID()
        val reqString = """
            {
                "title": "${req.title}",
                "description": "${req.description}",
                "expirationDate": "${req.expirationDate}",
                "isCompleted": ${req.isCompleted}
            }
        """.trimIndent()

        val response = TaskResponse(taskId, req.title, req.description, req.expirationDate, req.isCompleted)

        // When
        Mockito.`when`(facade.updateTask(taskId, req, testUser))
            .thenReturn(response)

        // When & Then
        mvc.put("/tasks/$taskId") {
            contentType = MediaType.APPLICATION_JSON
            content = reqString
        }
            .andExpect {
                status { isOk() }
                content {
                    jsonPath("$.id", `is`(taskId.toString()))
                    jsonPath("$.title", `is`(response.title))
                    jsonPath("$.description", `is`(response.description))
                    jsonPath("$.expirationDate", `is`(response.expirationDate.toString()))
                    jsonPath("$.isCompleted", `is`(response.isCompleted))
                }
            }
    }

}