package m.eight.evaluator.presentation.properties.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import m.eight.evaluator.domain.model.relationships.Property

@Composable
fun PropertyListItem(modifier: Modifier = Modifier, property: Property) {

    Card {
        Row(
            modifier = modifier.fillMaxWidth().padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = property.propertyName.name, style = MaterialTheme.typography.titleMedium)
            Text(text = property.propertyValue.measure.toString(), style = MaterialTheme.typography.titleMedium)
        }
    }
}