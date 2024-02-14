package m.eight.evaluator.presentation.shared

sealed class ItemEvent<out T> {

    data class ChangeAddOrUpdateFormDialogVisibility<T>(val title: String = "", val item: T? = null) : ItemEvent<T>()
    data class ChangeDeleteConfirmationFormDialogVisibility<T>(val item: T? = null, val promptMessage: String = "") : ItemEvent<T>()
    data class OnSaveButtonClicked<T>(val item: T) : ItemEvent<T>()
    data class OnDeleteButtonClicked<T>(val item: T) : ItemEvent<T>()
}