package m.eight.evaluator.domain.model

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.UUID

@Immutable
@Entity(
    tableName = "property_names",
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
data class PropertyName(
    @PrimaryKey(autoGenerate = false) val uuid: UUID,
    val categoryUUID: UUID,
    val name: String,
    val timestamp: Long
)

