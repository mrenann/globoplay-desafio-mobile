package com.mrenann.globoplay.homeScreen.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.placeholder
import com.mrenann.globoplay.R

@Composable
fun ContentItem(
    id: Int,
    posterUrl: String,
    onClick: (id: Int) -> Unit,
) {
    Column(
        modifier =
            Modifier
                .padding(3.dp)
                .height(150.dp)
                .clickable { onClick(id) },
    ) {
        AsyncImage(
            model =
                ImageRequest.Builder(LocalContext.current)
                    .data(posterUrl)
                    .crossfade(true)
                    .placeholder(R.drawable.globo)
                    .build(),
            contentDescription = null,
            modifier =
                Modifier
                    .width(100.dp)
                    .clip(RoundedCornerShape(3.dp)),
            contentScale = ContentScale.FillBounds,
        )
    }
}

@Preview
@Composable
fun ContentItemPreview() {
    ContentItem(
        id = 1,
        posterUrl = "",
    ) { }
}
