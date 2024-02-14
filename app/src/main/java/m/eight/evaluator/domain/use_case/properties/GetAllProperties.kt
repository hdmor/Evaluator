package m.eight.evaluator.domain.use_case.properties

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import m.eight.evaluator.domain.model.PropertyValue
import m.eight.evaluator.domain.model.relationships.Property
import m.eight.evaluator.domain.repository.PropertyNameRepository
import m.eight.evaluator.domain.repository.PropertyValueRepository
import java.util.UUID

class GetAllProperties(private val propertyNameRepository: PropertyNameRepository, private val propertyValueRepository: PropertyValueRepository) {

    operator fun invoke(categoryUUID: UUID, entityUUID: UUID): Flow<List<Property>> = flow {

        propertyNameRepository.getAll(categoryUUID).collect {
            val properties = mutableListOf<Property>()
            it.forEach { propertyName ->
                val propertyValue = propertyValueRepository.get(entityUUID, propertyName.uuid) ?: PropertyValue(
                    uuid = UUID.randomUUID(),
                    entityUUID = entityUUID,
                    propertyNameUUID = propertyName.uuid,
                    measure = 0.0f,
                    timestamp = System.currentTimeMillis()
                )
                properties.add(
                    Property(
                        propertyName = propertyName,
                        propertyValue = propertyValue
                    )
                )
            }
            emit(properties)
        }
    }
}