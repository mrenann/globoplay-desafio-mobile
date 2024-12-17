package com.mrenann.globoplay.homeScreen.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.mrenann.globoplay.core.domain.model.Media

@Composable
fun ContentGrid(
    title: String,
    pagingItems: LazyPagingItems<Media>,
    onClick: (id: Int) -> Unit,
) {
    Column {
        Text(
            modifier = Modifier.padding(all = 10.dp),
            fontSize = 18.sp,
            text = title,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
        LazyRow(
            contentPadding = PaddingValues(start = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier,
        ) {
            items(pagingItems.itemCount) { index ->
                val item = pagingItems[index]
                item?.let {
                    ContentItem(
                        id = it.id,
                        posterUrl = it.posterPath,
                        onClick = { id -> onClick(id) },
                    )
                }
            }

            pagingItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            Text("CARREGANDO")
                        }
                    }

                    loadState.prepend is LoadState.Loading -> {
                        item {
                            Text("MAIS xzx")
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item {
                            Text("MAIS MAIS")
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        item {
                            Text("ERROR")
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        item {
                            Text("ERROR")
                        }
                    }
                }
            }
        }
    }
}
