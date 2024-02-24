package m.eight.evaluator.domain.repository

import kotlinx.coroutines.flow.Flow
import m.eight.evaluator.domain.model.Entity
import java.util.UUID

interface EntityRepository {

    fun getAll(categoryUUID: UUID): Flow<List<Entity>>
    suspend fun updateScore(score: Float, entityUUID: UUID)
    suspend fun addOrUpdate(entity: Entity)
    suspend fun remove(entity: Entity)
}