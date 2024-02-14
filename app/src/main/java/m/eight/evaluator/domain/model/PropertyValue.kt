package m.eight.evaluator.domain.model

import androidx.compose.runtime.Immutable
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.UUID

@Immutable
@androidx.room.Entity(
    tableName = "property_values",
    foreignKeys = [
        ForeignKey(
            entity = Entity::class,
            parentColumns = ["uuid"],
            childColumns = ["entityUUID"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PropertyName::class,
            parentColumns = ["uuid"],
            childColumns = ["propertyNameUUID"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class PropertyValue(
    @PrimaryKey(autoGenerate = false) val uuid: UUID,
    val entityUUID: UUID,
    val propertyNameUUID: UUID,
    val measure: Float = 0.0f,
    val timestamp: Long
)
