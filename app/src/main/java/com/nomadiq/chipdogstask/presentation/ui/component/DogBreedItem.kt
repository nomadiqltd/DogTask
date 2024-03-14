package com.nomadiq.chipdogstask.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nomadiq.chipdogstask.domain.model.DogBreed

/**
 * @author Michael Akakpo
 *
 * Composable to represent each [DogBreed] item in the list retrieved from the [Dog Api].
 */
@Composable
fun DogBreedItem(item: DogBreed, onItemClick: (DogBreed) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Gray,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = { onItemClick(item) })
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = item.name,
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Black,
                    style = typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}