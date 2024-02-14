package m.eight.evaluator.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import m.eight.evaluator.data.data_source.local.EvaluatorDatabase
import m.eight.evaluator.data.repository.CategoryRepositoryImpl
import m.eight.evaluator.data.repository.EntityRepositoryImpl
import m.eight.evaluator.data.repository.PropertyNameRepositoryImpl
import m.eight.evaluator.data.repository.PropertyValueRepositoryImpl
import m.eight.evaluator.domain.repository.CategoryRepository
import m.eight.evaluator.domain.repository.EntityRepository
import m.eight.evaluator.domain.repository.PropertyNameRepository
import m.eight.evaluator.domain.repository.PropertyValueRepository
import m.eight.evaluator.domain.use_case.CategoryUseCase
import m.eight.evaluator.domain.use_case.EntityUseCase
import m.eight.evaluator.domain.use_case.PropertyUseCase
import m.eight.evaluator.domain.use_case.categories.AddOrUpdateCategory
import m.eight.evaluator.domain.use_case.categories.GetAllCategories
import m.eight.evaluator.domain.use_case.categories.RemoveCategory
import m.eight.evaluator.domain.use_case.entities.AddOrUpdateEntity
import m.eight.evaluator.domain.use_case.entities.GetAllEntities
import m.eight.evaluator.domain.use_case.entities.RemoveEntity
import m.eight.evaluator.domain.use_case.properties.AddOrUpdateProperty
import m.eight.evaluator.domain.use_case.properties.GetAllProperties
import m.eight.evaluator.domain.use_case.properties.RemoveProperty
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideEvaluatorDatabase(@ApplicationContext context: Context): EvaluatorDatabase =
        Room.databaseBuilder(context, EvaluatorDatabase::class.java, EvaluatorDatabase.EVALUATOR_DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideCategoryRepository(database: EvaluatorDatabase): CategoryRepository =
        CategoryRepositoryImpl(database.getCategoryDao())

    @Provides
    @Singleton
    fun provideCategoryUseCase(repository: CategoryRepository): CategoryUseCase =
        CategoryUseCase(GetAllCategories(repository), AddOrUpdateCategory(repository), RemoveCategory(repository))

    @Provides
    @Singleton
    fun provideEntityRepository(database: EvaluatorDatabase): EntityRepository =
        EntityRepositoryImpl(database.getEntityDao())

    @Provides
    @Singleton
    fun provideEntityUseCase(repository: EntityRepository): EntityUseCase =
        EntityUseCase(GetAllEntities(repository), AddOrUpdateEntity(repository), RemoveEntity(repository))

    @Provides
    @Singleton
    fun providePropertyNameRepository(database: EvaluatorDatabase): PropertyNameRepository =
        PropertyNameRepositoryImpl(database.getPropertyNameDao())

    @Provides
    @Singleton
    fun providePropertyValueRepository(database: EvaluatorDatabase): PropertyValueRepository =
        PropertyValueRepositoryImpl(database.getPropertyValueDao())

    @Provides
    @Singleton
    fun providePropertyUseCase(
        propertyNameRepository: PropertyNameRepository,
        propertyValueRepository: PropertyValueRepository,
        entityRepository: EntityRepository
    ): PropertyUseCase =
        PropertyUseCase(
            GetAllProperties(propertyNameRepository, propertyValueRepository),
            AddOrUpdateProperty(propertyNameRepository, propertyValueRepository, entityRepository),
            RemoveProperty(propertyNameRepository, propertyValueRepository, entityRepository)
        )
}