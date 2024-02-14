package m.eight.evaluator.domain.model

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Immutable
@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = false) val uuid: UUID,
    val name: String,
    val description: String,
    val timestamp: Long
)