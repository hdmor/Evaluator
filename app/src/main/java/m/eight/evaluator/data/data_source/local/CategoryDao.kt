package m.eight.evaluator.data.data_source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import m.eight.evaluator.domain.model.Category

@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories ORDER BY timestamp DESC")
    fun selectAll(): Flow<List<Category>>

    @Upsert
    suspend fun upsert(category: Category)

    @Delete
    suspend fun delete(category: Category)
}