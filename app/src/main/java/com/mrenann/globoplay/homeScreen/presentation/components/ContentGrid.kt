package com.mrenann.globoplay.homeScreen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.mrenann.globoplay.core.domain.model.TVSerie

@Composable
fun ContentGrid(
    modifier: Modifier = Modifier,
    pagingItems: LazyPagingItems<TVSerie>,
    paddingValues: PaddingValues,
    onClick: (id: Int) -> Unit
) {
    Box(modifier = modifier.background(Color.Black)) {
        LazyRow(
            contentPadding = paddingValues,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            modifier = Modifier.fillMaxSize()
        ) {
            items(pagingItems.itemCount) { index ->
                val item = pagingItems[index]
                item?.let {
                    ContentItem(
                        id = it.id,
                        posterUrl = it.posterPath,
                        onClick = { id -> onClick(id) }
                    )

                }
            }
        }
    }
}