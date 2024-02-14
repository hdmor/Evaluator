package m.eight.evaluator.domain.model

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.UUID

@Immutable
@Entity(
    tableName = "entities",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["uuid"],
            childColumns = ["categoryUUID"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class Entity(
    @PrimaryKey(autoGenerate = false) val uuid: UUID,
    val categoryUUID: UUID,
    val name: String,
    val score: Float = 0.0f,
    val timestamp: Long
)
