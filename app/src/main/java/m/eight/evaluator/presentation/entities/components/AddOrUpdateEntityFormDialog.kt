package m.eight.evaluator.presentation.entities.components

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
import m.eight.evaluator.domain.model.Entity
import m.eight.evaluator.presentation.shared.ItemState
import java.util.UUID

@Composable
fun AddOrUpdateEntityFormDialog(onSaveButtonClicked: (Entity) -> Unit, onDismissedDialog: () -> Unit, state: ItemState<Entity>) {

    var name by rememberSaveable { mutableStateOf(state.tempItem?.name ?: "") }
    val nameError = state.errors.containsKey("name") && name.isEmpty()

    Dialog(
        onDismissRequest = {
            name = ""
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
            Button(
                modifier = Modifier.align(Alignment.End),
                onClick = {
                    onSaveButtonClicked(
                        Entity(
                            uuid = UUID.randomUUID(),
                            categoryUUID = state.tempItem?.categoryUUID ?: UUID.randomUUID(),
                            name = name,
                            score = 0.0f,
                            timestamp = System.currentTimeMillis()
                        )
                    )
                }) {
                Text(text = "Save")
            }
        }
    }
}