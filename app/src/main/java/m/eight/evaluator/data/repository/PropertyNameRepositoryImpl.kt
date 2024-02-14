package m.eight.evaluator.data.repository

import kotlinx.coroutines.flow.Flow
import m.eight.evaluator.data.data_source.local.PropertyNameDao
import m.eight.evaluator.domain.model.PropertyName
import m.eight.evaluator.domain.repository.PropertyNameRepository
import java.util.UUID

class PropertyNameRepositoryImpl(private val dao: PropertyNameDao) : PropertyNameRepository {

    override fun getAll(categoryUUID: UUID): Flow<List<PropertyName>> = dao.selectAll(categoryUUID)
    override suspend fun count(categoryUUID: UUID): Int = dao.count(categoryUUID)
    override suspend fun addOrUpdate(propertyName: PropertyName) = dao.upsert(propertyName)
    override suspend fun remove(propertyName: PropertyName) = dao.delete(propertyName)
}