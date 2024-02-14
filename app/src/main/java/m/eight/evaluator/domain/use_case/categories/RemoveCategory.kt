package m.eight.evaluator.domain.use_case.categories

import m.eight.evaluator.domain.model.Category
import m.eight.evaluator.domain.repository.CategoryRepository

class RemoveCategory(private val repository: CategoryRepository) {

    suspend operator fun invoke(category: Category) = repository.remove(category)
}