package fr.samneo.bookshelf.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import fr.samneo.bookshelf.BookshelfApplication
import fr.samneo.bookshelf.data.ScienceFictionBooksRepository
import fr.samneo.bookshelf.model.UiState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class AppViewModel(private val scienceFictionBooksRepository: ScienceFictionBooksRepository) :
    ViewModel() {

    var uiState: UiState by mutableStateOf(UiState.Loading)
        private set

    init {
        getScienceFictionBooks()
    }

    private fun getScienceFictionBooks() {
        viewModelScope.launch {
            uiState = try {
                UiState.Success(scienceFictionBooksRepository.getBooks())
            } catch (e: IOException) {
                UiState.Error
            } catch (e: HttpException) {
                UiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookshelfApplication)
                val scienceFictionBooksRepository =
                    application.container.scienceFictionBooksRepository
                AppViewModel(scienceFictionBooksRepository = scienceFictionBooksRepository)
            }
        }
    }

}