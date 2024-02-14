package m.eight.evaluator.data.data_source.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import m.eight.evaluator.domain.model.PropertyValue
import java.util.UUID

@Dao
interface PropertyValueDao {

    @Query("SELECT * FROM property_values WHERE entityUUID = :entityUUID AND propertyNameUUID = :propertyNameUUID")
    suspend fun get(entityUUID: UUID, propertyNameUUID: UUID): PropertyValue?

    @Query("SELECT SUM(measure) FROM property_values WHERE entityUUID = :entityUUID")
    suspend fun sum(entityUUID: UUID): Float

    @Upsert
    suspend fun upsert(propertyValue: PropertyValue)
}