package m.eight.evaluator.data.data_source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import m.eight.evaluator.domain.model.Entity
import java.util.UUID

@Dao
interface EntityDao {

    @Query("SELECT * FROM entities WHERE categoryUUID = :categoryUUID ORDER BY score DESC")
    fun selectAllAsFlow(categoryUUID: UUID): Flow<List<Entity>>

    @Query("SELECT * FROM entities WHERE categoryUUID = :categoryUUID ORDER BY score DESC")
    suspend fun selectAllAsList(categoryUUID: UUID): List<Entity>

    @Query("UPDATE entities SET score = :score WHERE uuid = :entityUUID")
    suspend fun updateScore(score: Float, entityUUID: UUID)

    @Upsert
    suspend fun upsert(entity: Entity)

    @Delete
    suspend fun delete(entity: Entity)
}