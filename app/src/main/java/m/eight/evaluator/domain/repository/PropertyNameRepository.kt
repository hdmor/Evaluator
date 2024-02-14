package m.eight.evaluator.domain.repository

import kotlinx.coroutines.flow.Flow
import m.eight.evaluator.domain.model.PropertyName
import java.util.UUID

interface PropertyNameRepository {

    fun getAll(categoryUUID: UUID): Flow<List<PropertyName>>
    suspend fun count(categoryUUID: UUID): Int
    suspend fun addOrUpdate(propertyName: PropertyName)
    suspend fun remove(propertyName: PropertyName)
}