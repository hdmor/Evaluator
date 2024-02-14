package m.eight.evaluator.presentation.properties.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import m.eight.evaluator.domain.model.PropertyName
import m.eight.evaluator.domain.model.PropertyValue
import m.eight.evaluator.domain.model.relationships.Property
import m.eight.evaluator.presentation.shared.ItemState
import java.util.UUID

@Composable
fun AddOrUpdatePropertyFormDialog(onSaveButtonClicked: (Property) -> Unit, onDismissedDialog: () -> Unit, state: ItemState<Property>) {

    var name by rememberSaveable { mutableStateOf(state.tempItem?.propertyName?.name ?: "") }
    var measure by rememberSaveable { mutableFloatStateOf(state.tempItem?.propertyValue?.measure ?: 0.0f) }

    val nameError = state.errors.containsKey("name") && name.isEmpty()
    val measureError = state.errors.containsKey("measure") && measure !in 0.0f..10.0f

    Dialog(
        onDismissRequest = {
            name = ""
            measure = 0.0f
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
                trailingIcon = { if (nameError) Icon(imageVector = Icons.Default.Error, contentDescription = "Error") },
                modifier = Modifier.fillMaxWidth()
            )
            if (nameError) Spacer(modifier = Modifier.height(2.dp))
            OutlinedTextField(
                value = measure.toString(),
                onValueChange = { measure = it.toFloat() },
                label = { Text(text = "Measure") },
                isError = measureError,
                supportingText = { if (measureError) Text(text = state.errors.getValue("measure")) },
                trailingIcon = { if (measureError) Icon(imageVector = Icons.Default.Error, contentDescription = "Error") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            if (measureError) Spacer(modifier = Modifier.height(2.dp))
            Button(
                modifier = Modifier.align(Alignment.End),
                onClick = {
                    val propertyNameUUID = UUID.randomUUID()
                    onSaveButtonClicked(
                        Property(
                            propertyName = PropertyName(
                                uuid = propertyNameUUID,
                                categoryUUID = state.tempItem?.propertyName?.categoryUUID ?: UUID.randomUUID(),
                                name = name,
                                timestamp = System.currentTimeMillis()
                            ),
                            propertyValue = PropertyValue(
                                uuid = UUID.randomUUID(),
                                entityUUID = state.tempItem?.propertyValue?.entityUUID ?: UUID.randomUUID(),
                                propertyNameUUID = propertyNameUUID,
                                measure = measure,
                                timestamp = System.currentTimeMillis()
                            )
                        )
                    )
                }) {
                Text(text = "Save")
            }
        }
    }
}