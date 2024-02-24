package m.eight.evaluator.domain.use_case.properties

import kotlinx.coroutines.flow.first
import m.eight.evaluator.domain.model.PropertyName
import m.eight.evaluator.domain.model.PropertyValue
import m.eight.evaluator.domain.repository.EntityRepository
import m.eight.evaluator.domain.repository.PropertyNameRepository
import m.eight.evaluator.domain.repository.PropertyValueRepository

class AddOrUpdateProperty(
    private val propertyNameRepository: PropertyNameRepository,
    private val propertyValueRepository: PropertyValueRepository,
    private val entityRepository: EntityRepository
) {

    suspend operator fun invoke(propertyName: PropertyName, propertyValue: PropertyValue) =
        propertyNameRepository.addOrUpdate(propertyName).apply {
            propertyValueRepository.addOrUpdate(propertyValue).apply {
                entityRepository.getAll(propertyName.categoryUUID).first().forEach { entity ->
                    val sum = propertyValueRepository.sum(entity.uuid)
                    val count = propertyNameRepository.count(entity.categoryUUID)
                    val avg = if (sum == 0.0f || count == 0) 0.0f else sum / count
                    entityRepository.updateScore(avg, entity.uuid)
                }
            }
        }
}