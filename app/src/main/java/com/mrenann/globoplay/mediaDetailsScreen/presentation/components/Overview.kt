package com.mrenann.globoplay.mediaDetailsScreen.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Overview(
    modifier: Modifier = Modifier,
    overview: String,
) {
    var expanded by remember { mutableStateOf(false) }
    var hasOverflow by remember { mutableStateOf(true) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        val textLayoutResult = remember { mutableStateOf<TextLayoutResult?>(null) }

        if (expanded) {
            Text(
                text = overview,
                color = Color.LightGray,
                modifier = Modifier.clickable {
                    expanded = !expanded
                }

            )
        } else {
            Column {
                Text(
                    text = overview,
                    color = Color.LightGray,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.clickable {
                        expanded = !expanded
                    },
                    onTextLayout = { layoutResult ->
                        // Verifica se há overflow
                        hasOverflow = !expanded && layoutResult.hasVisualOverflow
                        textLayoutResult.value = layoutResult
                    },

                    )
                if (hasOverflow && !expanded) {
                    Text(
                        text = "mais",
                        color = Color.LightGray,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            expanded = !expanded
                        }
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true, backgroundColor = 0)
@Composable
fun OverviewPreview() {
    Overview(
        overview = "MEU OVERVIE",
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    )
}
