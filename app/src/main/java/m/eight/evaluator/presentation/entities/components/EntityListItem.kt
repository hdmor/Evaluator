package m.eight.evaluator.presentation.entities.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import m.eight.evaluator.domain.model.Entity

@Composable
fun EntityListItem(modifier: Modifier = Modifier, onEditIconClicked: () -> Unit, entity: Entity) {

    Card {
        Row(
            modifier = modifier.fillMaxWidth().padding(start = 8.dp, top = 8.dp, end = 24.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onEditIconClicked) { Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit") }
                Text(text = entity.name, style = MaterialTheme.typography.titleMedium)
            }
            Text(text = "%.2f".format(entity.score), style = MaterialTheme.typography.titleMedium)
        }
        LinearProgressIndicator(progress = entity.score / 10, modifier = Modifier.fillMaxWidth(), trackColor = Color.LightGray)
    }
}