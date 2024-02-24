package m.eight.evaluator.data.repository

import kotlinx.coroutines.flow.Flow
import m.eight.evaluator.data.data_source.local.EntityDao
import m.eight.evaluator.domain.model.Entity
import m.eight.evaluator.domain.repository.EntityRepository
import java.util.UUID

class EntityRepositoryImpl(private val dao: EntityDao) : EntityRepository {

    override fun getAll(categoryUUID: UUID): Flow<List<Entity>> = dao.selectAll(categoryUUID)
    override suspend fun updateScore(score: Float, entityUUID: UUID) = dao.updateScore(score, entityUUID)
    override suspend fun addOrUpdate(entity: Entity) = dao.upsert(entity)
    override suspend fun remove(entity: Entity) = dao.delete(entity)
}