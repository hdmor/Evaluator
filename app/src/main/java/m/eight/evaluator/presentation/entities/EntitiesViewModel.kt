package m.eight.evaluator.presentation.entities

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import m.eight.evaluator.domain.model.Entity
import m.eight.evaluator.domain.use_case.EntityUseCase
import m.eight.evaluator.presentation.shared.ItemEvent
import m.eight.evaluator.presentation.shared.ItemState
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class EntitiesViewModel @Inject constructor(savedStateHandle: SavedStateHandle, private val useCase: EntityUseCase) : ViewModel() {

    private val categoryUUID = UUID.fromString(savedStateHandle["categoryUUID"]!!)
    private val _state = mutableStateOf(ItemState<Entity>())
    val state: State<ItemState<Entity>> get() = _state

    init {
        savedStateHandle.get<String>("categoryName")?.let { _state.value = state.value.copy(categoryName = it) }
        getAllEntities()
    }

    fun onEvent(event: ItemEvent<Entity>) {
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
                useCase.removeEntity(event.item).apply {
                    _state.value = state.value.copy(deleteConfirmationFormDialogVisibility = !state.value.deleteConfirmationFormDialogVisibility)
                }
            }

            is ItemEvent.OnSaveButtonClicked -> viewModelScope.launch {
                if (event.item.name.isEmpty())
                    _state.value = state.value.copy(errors = mapOf("name" to "Please fill out this field."))
                else useCase.addOrUpdateEntity(
                    Entity(
                        uuid = state.value.tempItem?.uuid ?: event.item.uuid,
                        categoryUUID = categoryUUID,
                        name = event.item.name,
                        score = event.item.score,
                        timestamp = state.value.tempItem?.timestamp ?: event.item.timestamp
                    )
                ).apply {
                    _state.value = state.value.copy(addOrUpdateFromDialogVisibility = !state.value.addOrUpdateFromDialogVisibility, errors = mapOf())
                }
            }
        }
    }

    private fun getAllEntities() {
        viewModelScope.launch {
            useCase.getAllEntities(categoryUUID).collect { entities ->
                _state.value = state.value.copy(items = entities)
            }
        }
    }
}