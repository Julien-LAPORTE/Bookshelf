package fr.samneo.bookshelf.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.samneo.bookshelf.ui.screens.MainScreen
import fr.samneo.bookshelf.viewmodel.AppViewModel

@Composable
fun BookshelfApp(modifier: Modifier = Modifier) {
    val viewModel: AppViewModel = viewModel(factory = AppViewModel.Factory)
    MainScreen(uiState = viewModel.uiState, modifier)
}