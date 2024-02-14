package m.eight.evaluator.domain.use_case.properties

import m.eight.evaluator.domain.model.PropertyName
import m.eight.evaluator.domain.repository.EntityRepository
import m.eight.evaluator.domain.repository.PropertyNameRepository
import m.eight.evaluator.domain.repository.PropertyValueRepository

class RemoveProperty(
    private val propertyNameRepository: PropertyNameRepository,
    private val propertyValueRepository: PropertyValueRepository,
    private val entityRepository: EntityRepository
) {

    suspend operator fun invoke(propertyName: PropertyName) =
        propertyNameRepository.remove(propertyName).apply {

            entityRepository.getAllAsList(propertyName.categoryUUID).forEach { entity ->
                val sum = propertyValueRepository.sum(entity.uuid)
                val count = propertyNameRepository.count(entity.categoryUUID)
                val avg = if (sum == 0.0f || count == 0) 0.0f else sum / count
                entityRepository.updateScore(avg, entity.uuid)
            }
        }
}