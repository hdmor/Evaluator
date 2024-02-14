package m.eight.evaluator.domain.use_case.categories

import kotlinx.coroutines.flow.Flow
import m.eight.evaluator.domain.model.Category
import m.eight.evaluator.domain.repository.CategoryRepository

class GetAllCategories(private val repository: CategoryRepository) {

    operator fun invoke(): Flow<List<Category>> = repository.getAll()
}