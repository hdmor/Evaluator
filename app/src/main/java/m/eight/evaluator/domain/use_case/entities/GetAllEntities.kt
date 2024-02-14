package m.eight.evaluator.domain.use_case.entities

import kotlinx.coroutines.flow.Flow
import m.eight.evaluator.domain.model.Entity
import m.eight.evaluator.domain.repository.EntityRepository
import java.util.UUID

class GetAllEntities(private val repository: EntityRepository) {

    operator fun invoke(categoryUUID: UUID): Flow<List<Entity>> = repository.getAllAsFlow(categoryUUID)
}