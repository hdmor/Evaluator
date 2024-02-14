package m.eight.evaluator.domain.use_case

import m.eight.evaluator.domain.use_case.properties.AddOrUpdateProperty
import m.eight.evaluator.domain.use_case.properties.GetAllProperties
import m.eight.evaluator.domain.use_case.properties.RemoveProperty

data class PropertyUseCase(
    val getAllProperties: GetAllProperties,
    val addOrUpdateProperty: AddOrUpdateProperty,
    val removeProperty: RemoveProperty
)
