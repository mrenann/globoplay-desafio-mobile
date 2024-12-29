package com.mrenann.globoplay.homeScreen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun ContentItem(
    id: Int,
    posterUrl: String,
    title: String = "",
    onClick: (id: Int) -> Unit,
) {
    Column(
        modifier =
            Modifier
                .width(100.dp)
                .height(150.dp)
                .clickable { onClick(id) },
    ) {
        SubcomposeAsyncImage(
            model =
                ImageRequest.Builder(LocalContext.current)
                    .data(posterUrl)
                    .crossfade(true)

                    .build(),
            contentDescription = null,
            modifier =
                Modifier
                    .width(100.dp)
                    .clip(RoundedCornerShape(3.dp)),
            contentScale = ContentScale.FillBounds,
        ) {
            val state by painter.state.collectAsState()
            when (state) {
                AsyncImagePainter.State.Empty,
                is AsyncImagePainter.State.Error,
                is AsyncImagePainter.State.Loading -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFF535353))
                            .padding(1.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = title,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            maxLines = 4,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent()
            }
        }

    }
}

@Preview
@Composable
fun ContentItemPreview() {
    ContentItem(
        id = 1,
        posterUrl = "",
        title = "asdFDDDDDDDDDDDDDDDDDDDDDDDDDDDDD DFDFDFDFSDF SDF SDFSDFDSF"
    ) { }
}
