package m.eight.evaluator.presentation.categories

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import m.eight.evaluator.domain.model.Category
import m.eight.evaluator.domain.use_case.CategoryUseCase
import m.eight.evaluator.presentation.shared.ItemEvent
import m.eight.evaluator.presentation.shared.ItemState
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(private val useCase: CategoryUseCase) : ViewModel() {

    private val _state = mutableStateOf(ItemState<Category>())
    val state: State<ItemState<Category>> get() = _state

    init {
        getAllCategories()
    }

    fun onEvent(event: ItemEvent<Category>) {
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
                useCase.removeCategory(event.item).apply {
                    _state.value = state.value.copy(deleteConfirmationFormDialogVisibility = !state.value.deleteConfirmationFormDialogVisibility)
                }
            }

            is ItemEvent.OnSaveButtonClicked -> viewModelScope.launch {
                if (event.item.name.isEmpty())
                    _state.value = state.value.copy(errors = mapOf("name" to "Please fill out this field."))
                else if (event.item.description.isEmpty())
                    _state.value = state.value.copy(errors = mapOf("description" to "Please fill out this field."))
                else useCase.addOrUpdateCategory(
                    Category(
                        uuid = state.value.tempItem?.uuid ?: event.item.uuid,
                        name = event.item.name,
                        description = event.item.description,
                        timestamp = state.value.tempItem?.timestamp ?: event.item.timestamp
                    )
                ).apply {
                    _state.value = state.value.copy(addOrUpdateFromDialogVisibility = !state.value.addOrUpdateFromDialogVisibility, errors = mapOf())
                }
            }
        }
    }

    private fun getAllCategories() {
        viewModelScope.launch {
            useCase.getAllCategories().collect { categories ->
                _state.value = state.value.copy(items = categories)
            }
        }
    }
}