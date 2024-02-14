package m.eight.evaluator.data.data_source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import m.eight.evaluator.domain.model.PropertyName
import java.util.UUID

@Dao
interface PropertyNameDao {

    @Query("SELECT * FROM property_names WHERE categoryUUID = :categoryUUID ORDER BY timestamp DESC")
    fun selectAll(categoryUUID: UUID): Flow<List<PropertyName>>

    @Query("SELECT COUNT(uuid) FROM property_names WHERE categoryUUID = :categoryUUID")
    suspend fun count(categoryUUID: UUID): Int

    @Upsert
    suspend fun upsert(propertyName: PropertyName)

    @Delete
    suspend fun delete(propertyName: PropertyName)
}