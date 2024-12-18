package com.mrenann.globoplay.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    message: String,
    retry: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontStyle = FontStyle.Italic,
            fontSize = 16.sp,
            color = Color.White
        )
        Button(onClick = retry) {
            Text(text = "Recarregar")
        }
    }
}

@Preview
@Composable
fun ErrorViewPreview() {
    ErrorView(
        message = "Error",
        retry = {}
    )
}