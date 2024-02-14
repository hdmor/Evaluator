package m.eight.evaluator.presentation.categories.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import m.eight.evaluator.domain.model.Category
import m.eight.evaluator.presentation.shared.ItemState
import java.util.UUID

@Composable
fun AddOrUpdateCategoryFormDialog(onSaveButtonClicked: (Category) -> Unit, onDismissedDialog: () -> Unit, state: ItemState<Category>) {

    var name by rememberSaveable { mutableStateOf(state.tempItem?.name ?: "") }
    var description by rememberSaveable { mutableStateOf(state.tempItem?.description ?: "") }

    val nameError = state.errors.containsKey("name") && name.isEmpty()
    val descriptionError = state.errors.containsKey("description") && description.isEmpty()

    Dialog(
        onDismissRequest = {
            name = ""
            description = ""
            onDismissedDialog()
        },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {

        Column(modifier = Modifier.clip(MaterialTheme.shapes.small).background(MaterialTheme.colorScheme.background).padding(16.dp)) {

            Text(text = state.addOrUpdateFormDialogTitle, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = "Name") },
                isError = nameError,
                supportingText = { if (nameError) Text(text = state.errors.getValue("name")) },
                trailingIcon = {
                    if (nameError) Icon(imageVector = Icons.Default.Error, contentDescription = "Error")
                },
                modifier = Modifier.fillMaxWidth()
            )
            if (nameError) Spacer(modifier = Modifier.height(2.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                singleLine = false,
                minLines = 5,
                label = { Text(text = "Description") },
                isError = descriptionError,
                supportingText = { if (descriptionError) Text(text = state.errors.getValue("description")) },
                trailingIcon = {
                    if (descriptionError) Icon(imageVector = Icons.Default.Error, contentDescription = "Error")
                },
                modifier = Modifier.fillMaxWidth()
            )
            if (descriptionError) Spacer(modifier = Modifier.height(2.dp))
            Button(
                modifier = Modifier.align(Alignment.End),
                onClick = {
                    onSaveButtonClicked(
                        Category(
                            uuid = UUID.randomUUID(),
                            name = name,
                            description = description,
                            timestamp = System.currentTimeMillis()
                        )
                    )
                }) {
                Text(text = "Save")
            }
        }
    }
}