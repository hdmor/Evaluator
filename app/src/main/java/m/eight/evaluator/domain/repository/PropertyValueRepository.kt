package m.eight.evaluator.domain.repository

import m.eight.evaluator.domain.model.PropertyValue
import java.util.UUID

interface PropertyValueRepository {

    suspend fun get(entityUUID: UUID, propertyNameUUID: UUID): PropertyValue?
    suspend fun sum(entityUUID: UUID): Float
    suspend fun addOrUpdate(propertyValue: PropertyValue)
}