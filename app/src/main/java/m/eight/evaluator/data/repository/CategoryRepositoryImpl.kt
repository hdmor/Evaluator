package m.eight.evaluator.data.repository

import kotlinx.coroutines.flow.Flow
import m.eight.evaluator.data.data_source.local.CategoryDao
import m.eight.evaluator.domain.model.Category
import m.eight.evaluator.domain.repository.CategoryRepository

class CategoryRepositoryImpl(private val dao: CategoryDao) : CategoryRepository {

    override fun getAll(): Flow<List<Category>> = dao.selectAll()
    override suspend fun addOrUpdate(category: Category) = dao.upsert(category)
    override suspend fun remove(category: Category) = dao.delete(category)
}