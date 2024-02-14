package m.eight.evaluator.domain.use_case.entities

import m.eight.evaluator.domain.model.Entity
import m.eight.evaluator.domain.repository.EntityRepository

class RemoveEntity(private val repository: EntityRepository) {

    suspend operator fun invoke(entity: Entity) = repository.remove(entity)
}