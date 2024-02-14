package m.eight.evaluator.domain.use_case

import m.eight.evaluator.domain.use_case.entities.AddOrUpdateEntity
import m.eight.evaluator.domain.use_case.entities.GetAllEntities
import m.eight.evaluator.domain.use_case.entities.RemoveEntity

data class EntityUseCase(
    val getAllEntities: GetAllEntities,
    val addOrUpdateEntity: AddOrUpdateEntity,
    val removeEntity: RemoveEntity
)