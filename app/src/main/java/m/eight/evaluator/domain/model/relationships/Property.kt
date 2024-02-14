package m.eight.evaluator.domain.model.relationships

import androidx.compose.runtime.Immutable
import androidx.room.Embedded
import androidx.room.Relation
import m.eight.evaluator.domain.model.PropertyName
import m.eight.evaluator.domain.model.PropertyValue

@Immutable
data class Property(
    @Embedded val propertyName: PropertyName,
    @Relation(parentColumn = "uuid", entityColumn = "propertyNameUUID")
    val propertyValue: PropertyValue
)
