package denshchikov.dmitry.app.model.domain

import java.time.Instant
import java.util.*

data class Task(
    override val id: UUID,
    override val title: String,
    val description: String,
    override val isCompleted: Boolean = false,
    val expirationDate: Instant,
    val createdBy: String
) : Item, Completable