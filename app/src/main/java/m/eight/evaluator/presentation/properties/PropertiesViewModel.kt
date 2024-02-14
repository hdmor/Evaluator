package m.eight.evaluator.presentation.properties

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import m.eight.evaluator.domain.model.PropertyName
import m.eight.evaluator.domain.model.PropertyValue
import m.eight.evaluator.domain.model.relationships.Property
import m.eight.evaluator.domain.use_case.PropertyUseCase
import m.eight.evaluator.presentation.shared.ItemEvent
import m.eight.evaluator.presentation.shared.ItemState
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class PropertiesViewModel @Inject constructor(savedStateHandle: SavedStateHandle, private val useCase: PropertyUseCase) : ViewModel() {

    private val categoryUUID = UUID.fromString(savedStateHandle["categoryUUID"]!!)
    private val entityUUID = UUID.fromString(savedStateHandle["entityUUID"]!!)
    private val _state = mutableStateOf(ItemState<Property>())
    val state: State<ItemState<Property>> get() = _state

    init {
        savedStateHandle.get<String>("entityName")?.let { _state.value = state.value.copy(entityName = it) }
        getAllProperties()
    }

    fun onEvent(event: ItemEvent<Property>) {
        when (event) {
            is ItemEvent.ChangeAddOrUpdateFormDialogVisibility -> _state.value = state.value.copy(
                addOrUpdateFormDialogTitle = event.title,
                addOrUpdateFromDialogVisibility = !state.value.addOrUpdateFromDialogVisibility,
                tempItem = event.item,
                errors = mapOf()
            )

            is ItemEvent.ChangeDeleteConfirmationFormDialogVisibility -> _state.value = state.value.copy(
                deleteConfirmationFormDialogMessage = event.promptMessage,
                deleteConfirmationFormDialogVisibility = !state.value.deleteConfirmationFormDialogVisibility,
                tempItem = event.item
            )

            is ItemEvent.OnDeleteButtonClicked -> viewModelScope.launch {
                useCase.removeProperty(event.item.propertyName).apply {
                    _state.value = state.value.copy(deleteConfirmationFormDialogVisibility = !state.value.deleteConfirmationFormDialogVisibility)
                }
            }

            is ItemEvent.OnSaveButtonClicked -> viewModelScope.launch {
                if (event.item.propertyName.name.isEmpty())
                    _state.value = state.value.copy(errors = mapOf("name" to "Please fill out this field."))
                else if (event.item.propertyValue.measure !in 0.0f..10.0f)
                    _state.value = state.value.copy(errors = mapOf("measure" to "Measure must be a number between 0 to 10"))
                else useCase.addOrUpdateProperty(
                    propertyName = PropertyName(
                        uuid = state.value.tempItem?.propertyName?.uuid ?: event.item.propertyName.uuid,
                        categoryUUID = categoryUUID,
                        name = event.item.propertyName.name,
                        timestamp = state.value.tempItem?.propertyName?.timestamp ?: event.item.propertyName.timestamp
                    ),
                    propertyValue = PropertyValue(
                        uuid = state.value.tempItem?.propertyValue?.uuid ?: event.item.propertyValue.uuid,
                        entityUUID = entityUUID,
                        propertyNameUUID = state.value.tempItem?.propertyValue?.propertyNameUUID ?: event.item.propertyValue.propertyNameUUID,
                        measure = event.item.propertyValue.measure,
                        timestamp = state.value.tempItem?.propertyName?.timestamp ?: event.item.propertyName.timestamp
                    )
                ).apply {
                    _state.value = state.value.copy(addOrUpdateFromDialogVisibility = !state.value.addOrUpdateFromDialogVisibility, errors = mapOf())
                }
            }
        }
    }

    private fun getAllProperties() {
        viewModelScope.launch {
            useCase.getAllProperties(categoryUUID, entityUUID)
                .map { properties ->
                    properties.sortedByDescending { property -> property.propertyValue.measure }
                }
                .collect { properties ->
                    _state.value = state.value.copy(items = properties)
                }
        }
    }
}