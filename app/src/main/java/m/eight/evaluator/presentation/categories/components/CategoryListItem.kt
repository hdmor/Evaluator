package m.eight.evaluator.presentation.categories.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import m.eight.evaluator.domain.model.Category
import m.eight.evaluator.presentation.util.convertToTimeAgo

@Composable
fun CategoryListItem(modifier: Modifier = Modifier, onEditIconClicked: () -> Unit, category: Category) {

    Card {
        Column(modifier = modifier.padding(start = 8.dp, top = 8.dp, end = 24.dp, bottom = 24.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onEditIconClicked) { Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit") }
                Text(text = category.name, style = MaterialTheme.typography.titleMedium)
            }
            Text(
                text = category.description,
                modifier = Modifier.padding(start = 16.dp),
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Text(text = convertToTimeAgo(category.timestamp), fontStyle = FontStyle.Italic, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}