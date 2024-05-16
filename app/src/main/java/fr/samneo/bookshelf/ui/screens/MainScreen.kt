package fr.samneo.bookshelf.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import fr.samneo.bookshelf.R
import fr.samneo.bookshelf.model.UiState
import fr.samneo.bookshelf.ui.theme.BookshelfTheme

@Composable
fun MainScreen(uiState: UiState, modifier: Modifier = Modifier) {
    when (uiState) {
        is UiState.Error -> {}
        is UiState.Loading -> {}
        is UiState.Success -> ImagesLazyGrid(modifier, imgSrc = uiState.imgSrc)
    }
}

@Composable
fun ImagesLazyGrid(modifier: Modifier = Modifier, imgSrc: List<String>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(dimensionResource(id = R.dimen.image_size)),
        modifier = modifier
    ) {
        items(imgSrc) { imgSrc ->
            Surface(
                border = BorderStroke(
                    dimensionResource(id = R.dimen.image_marge),
                    MaterialTheme.colorScheme.background
                ),
                shadowElevation = 16.dp,

                ) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current).data(imgSrc)
                        .crossfade(true).build(),
                    contentDescription = null,
                    error = painterResource(id = R.drawable.ic_connection_error),
                    placeholder = painterResource(id = R.drawable.loading_img),
                    modifier = Modifier.aspectRatio(0.65f),
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}

@Preview
@Composable
fun LazyGridPreview() {
    val imgSrc = List(15) {
        "http://books.google.com/books/content?id=sBy_DwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
    }
    BookshelfTheme {
        ImagesLazyGrid(imgSrc = imgSrc)
    }
}