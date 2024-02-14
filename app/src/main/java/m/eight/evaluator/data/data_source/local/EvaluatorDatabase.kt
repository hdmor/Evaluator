package m.eight.evaluator.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import m.eight.evaluator.domain.model.Category
import m.eight.evaluator.domain.model.Entity
import m.eight.evaluator.domain.model.PropertyName
import m.eight.evaluator.domain.model.PropertyValue

@Database(entities = [Category::class, Entity::class, PropertyName::class, PropertyValue::class], version = 1, exportSchema = false)
abstract class EvaluatorDatabase : RoomDatabase() {

    abstract fun getCategoryDao(): CategoryDao
    abstract fun getEntityDao(): EntityDao
    abstract fun getPropertyNameDao(): PropertyNameDao
    abstract fun getPropertyValueDao(): PropertyValueDao

    companion object {
        const val EVALUATOR_DATABASE_NAME = "evaluator_db"
    }
}