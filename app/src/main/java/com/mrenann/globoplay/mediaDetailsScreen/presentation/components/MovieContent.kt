package com.mrenann.globoplay.mediaDetailsScreen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.core.domain.model.MediaDetails
import com.mrenann.globoplay.core.util.formatTime
import com.mrenann.globoplay.homeScreen.presentation.components.ContentItem
import com.mrenann.globoplay.mediaDetailsScreen.data.mapper.toMedia
import com.mrenann.globoplay.mediaDetailsScreen.presentation.DetailsScreen
import com.mrenann.globoplay.ui.theme.Background
import com.mrenann.globoplay.ui.theme.GenreBackground
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.Outline
import compose.icons.evaicons.fill.Bookmark
import compose.icons.evaicons.outline.Bookmark

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MovieContent(
    movie: MediaDetails?,
    pagingMoviesSimilar: LazyPagingItems<Media>,
    isLoading: Boolean,
    isError: String,
    checked: Boolean,
    modifier: Modifier = Modifier,
    onAddToList: (Media) -> Unit
) {
    val navigator = LocalNavigator.currentOrThrow
    var selected by remember { mutableStateOf(0) }
    val titles = listOf("Similares", "Detalhes")

    Column {
        Scaffold(
            topBar = {
                IconButton(
                    modifier = Modifier.padding(vertical = 20.dp),
                    onClick = { navigator.pop() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            },
            content = { innerPadding ->
                LazyColumn(
                    contentPadding = PaddingValues(
                        top = 0.dp,
                        start = innerPadding.calculateStartPadding(
                            layoutDirection = LayoutDirection.Ltr
                        ),
                        bottom = innerPadding.calculateBottomPadding(),
                        end = innerPadding.calculateEndPadding(
                            layoutDirection = LayoutDirection.Ltr
                        ),
                    ),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = modifier
                        .fillMaxSize()
                        .background(Background)

                ) {
                    item {
                        BackdropImage(
                            backdropUrl = movie?.backdropPath.toString(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                        )
                    }

                    item {
                        Text(
                            text = movie?.title ?: "",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }

                    item {
                        FlowRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                        ) {
                            movie?.genres?.forEach { genre ->
                                GenreTag(genre = genre)
                            }
                        }
                    }

                    item {
                        Text(
                            text = movie?.releaseDate?.substring(0, 4) ?: "",
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(top = 12.dp),
                            color = Color.White
                        )
                    }

                    item {
                        Overview(
                            overview = movie?.overview ?: "",
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                        )
                    }

                    item {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically // Align the row items vertically in the center
                        ) {
                            Column(
                                modifier = Modifier
                                    .background(
                                        brush = Brush.horizontalGradient(
                                            colors = listOf(
                                                Color(0xFFE2082A),
                                                Color(0xFFE2082A),
                                                Color(0xFFF58521),
                                                Color(0xFFF79B20)
                                            )
                                        ),
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .height(48.dp)
                                    .weight(1F),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center // Center the content vertically inside the column
                            ) {
                                Text(
                                    text = "Assista agora",
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .background(
                                        color = GenreBackground,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .height(48.dp)
                                    .width(48.dp)
                                    .clickable {
                                        movie?.toMedia()?.let { onAddToList(it) }
                                    },
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    tint = Color.White,
                                    imageVector = if (checked) EvaIcons.Fill.Bookmark else EvaIcons.Outline.Bookmark,
                                    contentDescription = "Localized description"
                                )
                            }
                        }
                    }

                    if (isError.isNotEmpty()) {
                        item {
                            Text(
                                text = isError,
                                color = Color.Red,
                                modifier = Modifier.padding(horizontal = 12.dp)
                            )
                        }
                    }

                    if (isLoading) {
                        item {
                            CircularProgressIndicator(
                                modifier = Modifier.padding(horizontal = 12.dp)
                            )
                        }
                    }

                    item {
                        Column {
                            SecondaryTabRow(
                                containerColor = Background,
                                contentColor = Color.Gray,
                                selectedTabIndex = selected
                            ) {
                                titles.forEachIndexed { index, title ->
                                    Tab(
                                        text = {
                                            Text(
                                                color = if (index == selected) Color.White else Color.Gray,
                                                text = title,
                                                maxLines = 2,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                        },
                                        onClick = { selected = index },
                                        selected = (index == selected)
                                    )
                                }
                            }
                        }
                    }


                    if (selected == 0) {
                        item {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(3),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(400.dp) // Constrain the height
                                    .padding(horizontal = 8.dp)
                            ) {

                                items(pagingMoviesSimilar.itemCount) { index ->
                                    val movie = pagingMoviesSimilar[index]
                                    movie?.let { movieElement ->
                                        ContentItem(
                                            id = movieElement.id,
                                            posterUrl = movieElement.posterPath,
                                            onClick = {
                                                navigator.push(
                                                    DetailsScreen(
                                                        tvId = if (movieElement.type == "tv") movieElement.id else null,
                                                        movieId = if (movieElement.type == "movie") movieElement.id else null,
                                                    )
                                                )
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }

                    if (selected == 1) {
                        item {
                            Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                                Text(
                                    text = "Ficha técnica",
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(Modifier.padding(vertical = 12.dp))
                                Text(
                                    "Título Original: ${movie?.originalTitle}",
                                    color = Color.White
                                )
                                Text(
                                    "Duração: ${movie?.duration?.formatTime()}",
                                    color = Color.White
                                )
                                Text(
                                    "Ano de lançamento: ${movie?.releaseDate?.substring(0, 4)}",
                                    color = Color.White
                                )
                                Text(
                                    "Gênero: ${movie?.genres?.joinToString(", ") { it }}",
                                    color = Color.White
                                )
                                Text(
                                    "País: ${movie?.countries?.joinToString(", ") { it }}",
                                    color = Color.White
                                )
                                Spacer(Modifier.padding(vertical = 4.dp))
                                Text("Sinopse", color = Color.White)
                                Spacer(Modifier.padding(vertical = 8.dp))
                                Text(movie?.overview ?: "Sem descrição", color = Color.White)

                            }
                        }
                    }

                }
            }
        )
    }

}