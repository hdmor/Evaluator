package m.eight.evaluator.domain.use_case

import m.eight.evaluator.domain.use_case.categories.AddOrUpdateCategory
import m.eight.evaluator.domain.use_case.categories.GetAllCategories
import m.eight.evaluator.domain.use_case.categories.RemoveCategory

data class CategoryUseCase(
    val getAllCategories: GetAllCategories,
    val addOrUpdateCategory: AddOrUpdateCategory,
    val removeCategory: RemoveCategory
)
