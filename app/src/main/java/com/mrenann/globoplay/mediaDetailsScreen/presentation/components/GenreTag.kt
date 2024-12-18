package com.mrenann.globoplay.mediaDetailsScreen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mrenann.globoplay.ui.theme.GenreBackground

@Composable
fun GenreTag(
    genre: String
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .background(
                color = GenreBackground,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(10.dp)
    ) {
        Text(
            text = genre,
            color = Color.White,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0)
@Composable
fun GenreTagPreview() {
    GenreTag(genre = "Romance")
}
