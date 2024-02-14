package m.eight.evaluator.domain.repository

import kotlinx.coroutines.flow.Flow
import m.eight.evaluator.domain.model.Category

interface CategoryRepository {

    fun getAll(): Flow<List<Category>>
    suspend fun addOrUpdate(category: Category)
    suspend fun remove(category: Category)
}