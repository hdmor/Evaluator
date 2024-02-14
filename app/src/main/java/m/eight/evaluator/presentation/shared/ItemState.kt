package m.eight.evaluator.presentation.shared

import androidx.compose.runtime.Immutable

@Immutable
data class ItemState<out T>(
    val tempItem: T? = null,
    val items: List<T> = emptyList(),
    val addOrUpdateFormDialogTitle: String = "",
    val addOrUpdateFromDialogVisibility: Boolean = false,
    val deleteConfirmationFormDialogMessage: String = "",
    val deleteConfirmationFormDialogVisibility: Boolean = false,
    val errors: Map<String, String> = mapOf(),
    val categoryName: String = "",
    val entityName: String = ""
)
