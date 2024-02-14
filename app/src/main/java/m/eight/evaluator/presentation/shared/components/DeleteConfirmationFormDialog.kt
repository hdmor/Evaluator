package m.eight.evaluator.presentation.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun DeleteConfirmationFormDialog(onDeleteButtonClicked: () -> Unit, onCancelButtonClicked: () -> Unit, message: String) {

    Dialog(
        onDismissRequest = onCancelButtonClicked,
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {

        Column(modifier = Modifier.clip(MaterialTheme.shapes.small).background(MaterialTheme.colorScheme.background).padding(16.dp)) {

            Text(text = message, textAlign = TextAlign.Justify, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) {
                OutlinedButton(onClick = onCancelButtonClicked) { Text(text = "Cancel") }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = onDeleteButtonClicked) { Text(text = "Delete") }
            }
        }
    }
}