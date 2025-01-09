package com.mrenann.globoplay.mediaDetailsScreen.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.placeholder
import com.mrenann.globoplay.R
import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.core.domain.model.MediaDetails
import com.mrenann.globoplay.core.presentation.components.ErrorView
import com.mrenann.globoplay.core.presentation.components.LoadingView
import com.mrenann.globoplay.core.presentation.components.PlaceholderItem
import com.mrenann.globoplay.core.util.formatTime
import com.mrenann.globoplay.homeScreen.presentation.components.ContentItem
import com.mrenann.globoplay.mediaDetailsScreen.data.mapper.toMedia
import com.mrenann.globoplay.mediaDetailsScreen.presentation.DetailsScreen
import com.mrenann.globoplay.ui.theme.Background
import com.mrenann.globoplay.ui.theme.GenreBackground
import com.mrenann.globoplay.videoScreen.presentation.VideoScreen
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.Outline
import compose.icons.evaicons.fill.Bookmark
import compose.icons.evaicons.outline.Bookmark

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun MovieContent(
    movie: MediaDetails?,
    pagingMoviesSimilar: LazyPagingItems<Media>,
    isLoading: Boolean,
    isError: String,
    checked: Boolean,
    modifier: Modifier = Modifier,
    onAddToList: (Media) -> Unit,
) {
    val navigator = LocalNavigator.currentOrThrow
    var listState = rememberLazyListState()
    var selected by remember { mutableIntStateOf(0) }
    var topBarTitle by remember { mutableStateOf("") }
    var isScrollingUp by remember { mutableStateOf(false) }
    var lastOffset by remember { mutableIntStateOf(0) }
    var isContentVisible by remember { mutableStateOf(true) }
    var scrollFinished by remember { mutableStateOf(false) }

    // Track scroll position
    val scrollOffset = listState.firstVisibleItemScrollOffset
    val contentHeight = remember { mutableFloatStateOf(0f) }
    val fadeOutAlpha = 1f - (scrollOffset / (contentHeight.floatValue * 0.8F)).coerceIn(0f, 1f)

    LaunchedEffect(movie) {
        listState.scrollToItem(0) // Reset scroll position when movie changes
    }

    // Track scroll direction (up or down)
    LaunchedEffect(scrollOffset, movie) {
        val dynamicScrollThreshold =
            contentHeight.floatValue * 0.8F // Set threshold to 80% of content height
        if (scrollOffset > lastOffset) {
            // Scrolling down
            isScrollingUp = false
            if (scrollOffset > dynamicScrollThreshold) {
                // If the user has scrolled enough based on dynamic threshold, hide content
                isContentVisible = false
            }
        } else {
            // Scrolling up
            isScrollingUp = true
            if (scrollOffset < dynamicScrollThreshold) {
                // Show content when scrolling up, using dynamic threshold
                isContentVisible = true
            }
        }
        lastOffset = scrollOffset

        listState.firstVisibleItemScrollOffset

        val isScrolled =
            scrollOffset > dynamicScrollThreshold || listState.firstVisibleItemScrollOffset > 500

        topBarTitle =
            if (isScrolled) {
                movie?.title ?: "Sem Título"
            } else {
                ""
            }
    }

    // Detect when the user stops scrolling
    LaunchedEffect(listState.isScrollInProgress) {
        if (!listState.isScrollInProgress) {
            scrollFinished = true
            if (scrollOffset < 50) {
                isContentVisible = true // Show content when the scroll finishes near the top
            }
        }
    }


    Box(
        modifier = Modifier
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = topBarTitle, color = Color.White) },
                    navigationIcon = {
                        IconButton(onClick = { navigator.pop() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = if (topBarTitle.isEmpty()) Color.Transparent else Color.Black,
                        scrolledContainerColor = Color.Transparent,
                        navigationIconContentColor = Color.White,
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.White
                    )
                )
            },
            content = { innerPadding ->
                LazyColumn(
                    state = listState,
                    contentPadding = PaddingValues(
                        top = 0.dp,
                        start = innerPadding.calculateStartPadding(LayoutDirection.Ltr),
                        bottom = 0.dp,
                        end = innerPadding.calculateEndPadding(LayoutDirection.Ltr),
                    ),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Background)
                ) {
                    item {
                        if (true) {
                            Column(
                                modifier = Modifier
                                    .onGloballyPositioned { coordinates ->
                                        contentHeight.floatValue = coordinates.size.height.toFloat()
                                    }
                                    .graphicsLayer(alpha = fadeOutAlpha)
                                    .fillMaxWidth()
                            ) {
                                BackdropImage(
                                    backdropUrl = movie?.backdropPath.toString(),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(250.dp)
                                )
                                Text(
                                    text = movie?.title ?: "",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 32.sp,
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                )
                                FlowRow(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 8.dp),
                                ) {
                                    movie?.genres?.forEach { genre ->
                                        GenreTag(genre = genre)
                                    }
                                }
                                Text(
                                    text = movie?.releaseDate?.substring(0, 4)
                                        ?: "Sem data de lançamento",
                                    modifier = Modifier
                                        .padding(horizontal = 12.dp)
                                        .padding(top = 12.dp),
                                    color = Color.White
                                )
                                Overview(
                                    overview = movie?.overview ?: "Sem Descrição",
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                                )
                                Row(
                                    modifier = Modifier.padding(horizontal = 12.dp),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
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
                                        verticalArrangement = Arrangement.Center
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
                                                movie?.toMedia(type = movie.type)
                                                    ?.let { onAddToList(it) }
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
                        }
                    }


                    // THIS PART

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
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .fillParentMaxHeight(0.9F)
                                .background(Background)
                        ) {

                            SecondaryTabRow(
                                containerColor = Background,
                                contentColor = Color.Gray,
                                selectedTabIndex = 0,
                                modifier = Modifier,
                            ) {
                                // Example tabs
                                Tab(
                                    text = { Text("Similares", color = Color.White) },
                                    selected = true,
                                    onClick = {}
                                )
                                Tab(
                                    text = { Text("Detalhes", color = Color.Gray) },
                                    selected = false,
                                    onClick = {}
                                )
                            }


                            if (selected == 0) {
                                LazyVerticalGrid(
                                    columns = GridCells.FixedSize(100.dp),
                                    horizontalArrangement = Arrangement.spacedBy(
                                        8.dp,
                                        Alignment.CenterHorizontally
                                    ),
                                    verticalArrangement = Arrangement.spacedBy(8.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 2.dp)
                                ) {

                                    items(pagingMoviesSimilar.itemCount) { index ->
                                        val movie = pagingMoviesSimilar[index]
                                        movie?.let { movieElement ->
                                            ContentItem(
                                                id = movieElement.id,
                                                title = movieElement.name,
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

                                    pagingMoviesSimilar.apply {
                                        when {
                                            loadState.refresh is LoadState.Loading -> {
                                                items(12) { // Number of placeholders to show during refresh
                                                    PlaceholderItem()
                                                }
                                            }

                                            loadState.prepend is LoadState.Loading -> {
                                                item {
                                                    LoadingView()
                                                }
                                            }

                                            loadState.append is LoadState.Loading -> {
                                                items(6) { // Number of placeholders for appending
                                                    PlaceholderItem()
                                                }
                                            }

                                            loadState.refresh is LoadState.Error -> {
                                                item(span = { GridItemSpan(maxLineSpan) }) {
                                                    ErrorView(
                                                        modifier = Modifier.padding(10.dp),
                                                        message = "Tente Novamente"
                                                    ) {
                                                        retry()
                                                    }
                                                }
                                            }

                                            loadState.append is LoadState.Error -> {
                                                item(span = { GridItemSpan(maxLineSpan) }) {
                                                    ErrorView(
                                                        modifier = Modifier.padding(10.dp),
                                                        message = "Tente Novamente"
                                                    ) {
                                                        retry()
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            }


                            if (selected == 1) {
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

                            if (selected == 2) {
                                LazyRow {
                                    items(movie?.videos?.size ?: 0) { index ->
                                        val video = movie?.videos?.get(index)
                                        Column(
                                            modifier = Modifier.clickable {
                                                navigator.push(
                                                    VideoScreen(
                                                        video = video
                                                    )
                                                )
                                            }
                                        ) {
                                            AsyncImage(
                                                model = ImageRequest.Builder(LocalContext.current)
                                                    .data(
                                                        movie?.backdropPath ?: movie?.posterPath
                                                        ?: ""
                                                    )
                                                    .crossfade(true)
                                                    .placeholder(R.drawable.globo)
                                                    .build(),
                                                contentDescription = "",
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier.fillMaxWidth(0.5F)
                                            )
                                            Text("${video?.name}")
                                        }

                                    }
                                }


                            }
                        }
                    }


                }
            }
        )
    }

}