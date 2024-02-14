package m.eight.evaluator.data.repository

import m.eight.evaluator.data.data_source.local.PropertyValueDao
import m.eight.evaluator.domain.model.PropertyValue
import m.eight.evaluator.domain.repository.PropertyValueRepository
import java.util.UUID

class PropertyValueRepositoryImpl(private val dao: PropertyValueDao) : PropertyValueRepository {

    override suspend fun get(entityUUID: UUID, propertyNameUUID: UUID): PropertyValue? = dao.get(entityUUID, propertyNameUUID)
    override suspend fun sum(entityUUID: UUID): Float = dao.sum(entityUUID)
    override suspend fun addOrUpdate(propertyValue: PropertyValue) = dao.upsert(propertyValue)
}